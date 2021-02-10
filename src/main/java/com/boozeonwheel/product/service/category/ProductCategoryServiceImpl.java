package com.boozeonwheel.product.service.category;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

import com.boozeonwheel.product.constants.CategoryConstants;
import com.boozeonwheel.product.domain.category.EntityType;
import com.boozeonwheel.product.domain.category.ProductCategory;
import com.boozeonwheel.product.domain.file.ProductCategoryFileMetaData;
import com.boozeonwheel.product.dto.master.CategoryDTO;
import com.boozeonwheel.product.dto.root.Root;
import com.boozeonwheel.product.dto.taxonomies.HomeResult;
import com.boozeonwheel.product.dto.taxonomies.Taxonomy;
import com.boozeonwheel.product.dto.taxons.Taxon;
import com.boozeonwheel.product.exception.file.NotFoundException;
import com.boozeonwheel.product.objects.TreeNode;
import com.boozeonwheel.product.repository.category.ProductCategoryRepository;
import com.boozeonwheel.product.repository.file.ProductCategoryFileRepository;
import com.boozeonwheel.product.service.file.FileSequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
@Slf4j
public class ProductCategoryServiceImpl implements IProductCategoryService, CategoryConstants {

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    ProductCategoryFileRepository productCategoryFileRepository;

    @Autowired
    FileSequenceGeneratorService fileSeq;

    @Autowired
    IProductCategoryService productCategoryService;

    int DEFAULT_ROOT_NODE_ID = -1;

    public HomeResult getDummyBanner() {
        Gson gson = new Gson();
        HomeResult bannerResult = null;
        try (Reader reader = new FileReader("/Users/apple/Desktop/banner.json")) {

            // Convert JSON File to Java Object
            bannerResult = gson.fromJson(reader, HomeResult.class);

            // print staff object
            System.out.println(bannerResult);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bannerResult;
    }

    public HomeResult getProductCategory(int categoryId) {

        HomeResult homeResult = new HomeResult();
        List<ProductCategory> list = productCategoryRepository.findByMasterId(categoryId);
        if ((list != null && list.size() > 0)) {
            for (ProductCategory productCategory : list) {
                List<Taxonomy> taxonomiesList = new ArrayList<Taxonomy>();
                Taxonomy taxonomy = new Taxonomy();
                taxonomy.setId(productCategory.getId());
                taxonomy.setName(productCategory.getCategoryName());
                taxonomy.setCount(1);
                taxonomy.setCurrent_page(1);
                taxonomy.setPages(1);
                Root root = new Root();
                root.setId(productCategory.getId());
                root.setName(productCategory.getCategoryName());
                root.setPrettyName(productCategory.getPrettyName());
                root.setPermalink(productCategory.getPermalink());
                root.setParentId(productCategory.getParentCategoryId());
                root.setTaxonomyId(productCategory.getTaxonomyId());
                root.setMetaTitle(productCategory.getMetaTitle());
                root.setMetaDescription(productCategory.getMetaDescription());
                List<ProductCategory> rootTaxonList = productCategoryRepository.findByMasterId(productCategory.getId());

                if (rootTaxonList != null && rootTaxonList.size() > 0) {
                    List<Taxon> listTaxon = new ArrayList<Taxon>();
                    for (ProductCategory taxonListObj : rootTaxonList) {
                        Taxon taxon = new Taxon();
                        taxon = getProductCategoryFileMetaDataBByCategoryId(taxonListObj.getId(), taxon);
                        taxon.setId(taxonListObj.getId());
                        taxon.setDescription(taxonListObj.getDescription());
                        taxon.setMetaDescription(taxonListObj.getMetaDescription());
                        taxon.setMetaTitle(taxonListObj.getMetaTitle());
                        taxon.setName(taxonListObj.getCategoryName());
                        taxon.setParentId(taxonListObj.getParentCategoryId());
                        taxon.setPermalink(taxonListObj.getPermalink());
                        taxon.setPrettyName(taxonListObj.getPrettyName());
                        taxon.setTaxonomyId(taxonListObj.getTaxonomyId());
                        List<ProductCategory> taxonList = productCategoryRepository
                                .findByMasterId(taxonListObj.getId());
                        if (taxonList != null && taxonList.size() > 0) {
                            List<Taxon> listTaxonFinal = new ArrayList<Taxon>();
                            for (ProductCategory taxonObj : taxonList) {
                                Taxon taxonData = new Taxon();
                                taxonData = getProductCategoryFileMetaDataBByCategoryId(taxonData.getId(), taxon);
                                taxonData.setId(taxonObj.getId());
                                taxonData.setMetaDescription(taxonObj.getMetaDescription());
                                taxonData.setMetaTitle(taxonObj.getMetaTitle());
                                taxonData.setName(taxonObj.getCategoryName());
                                taxonData.setParentId(taxonObj.getParentCategoryId());
                                taxonData.setPermalink(taxonObj.getPermalink());
                                taxonData.setPrettyName(taxonObj.getPrettyName());
                                taxonData.setTaxonomyId(taxonObj.getTaxonomyId());
                                List<ProductCategory> taxonListFinal = productCategoryRepository
                                        .findByMasterId(taxonObj.getId());
                                if (taxonListFinal != null && taxonListFinal.size() > 0) {
                                    List<Taxon> listTaxonFinalDepth = new ArrayList<Taxon>();
                                    for (ProductCategory taxonObjFinal : taxonListFinal) {
                                        Taxon taxonDataFinal = new Taxon();
                                        taxonDataFinal = getProductCategoryFileMetaDataBByCategoryId(
                                                taxonObjFinal.getId(), taxon);
                                        taxonDataFinal.setId(taxonObjFinal.getId());
                                        taxonDataFinal.setMetaDescription(taxonObjFinal.getMetaDescription());
                                        taxonDataFinal.setMetaTitle(taxonObjFinal.getMetaTitle());
                                        taxonDataFinal.setName(taxonObjFinal.getCategoryName());
                                        taxonDataFinal.setParentId(taxonObjFinal.getParentCategoryId());
                                        taxonDataFinal.setPermalink(taxonObjFinal.getPermalink());
                                        taxonDataFinal.setPrettyName(taxonObjFinal.getPrettyName());
                                        taxonDataFinal.setTaxonomyId(taxonObjFinal.getTaxonomyId());
                                        listTaxonFinalDepth.add(taxonDataFinal);
                                    }
                                    taxonData.setTaxons(listTaxonFinalDepth);
                                }
                                listTaxonFinal.add(taxonData);
                            }
                            taxon.setTaxons(listTaxonFinal);
                        }
                        listTaxon.add(taxon);
                    }
                    root.setTaxons(listTaxon);
                }
                taxonomy.setRoot(root);
                taxonomiesList.add(taxonomy);
                homeResult.setTaxonomies(taxonomiesList);
            }

        }

        return homeResult;
    }

    public Root getProductCategoryByParentIdAndCategoryId(int parentId, int categoryId) {

        Root root = new Root();
        List<ProductCategory> list = productCategoryRepository.findByParentIdAndCategoryId(parentId, categoryId);
        if ((list != null && list.size() > 0)) {
            for (ProductCategory productCategory : list) {
                root.setId(productCategory.getId());
                root.setName(productCategory.getCategoryName());
                root.setPrettyName(productCategory.getPrettyName());
                root.setPermalink(productCategory.getPermalink());
                root.setParentId(productCategory.getParentCategoryId());
                root.setTaxonomyId(productCategory.getTaxonomyId());
                root.setMetaTitle(productCategory.getMetaTitle());
                root.setMetaDescription(productCategory.getMetaDescription());
                List<ProductCategory> rootTaxonList = productCategoryRepository.findByMasterId(productCategory.getId());
                if (rootTaxonList != null && rootTaxonList.size() > 0) {
                    List<Taxon> listTaxon = new ArrayList<Taxon>();
                    for (ProductCategory taxonListObj : rootTaxonList) {
                        Taxon taxon = new Taxon();
                        taxon = getProductCategoryFileMetaDataBByCategoryId(categoryId, taxon);
                        taxon.setId(taxonListObj.getId());
                        taxon.setDescription(taxonListObj.getDescription());
                        taxon.setMetaDescription(taxonListObj.getMetaDescription());
                        taxon.setMetaTitle(taxonListObj.getMetaTitle());
                        taxon.setName(taxonListObj.getCategoryName());
                        taxon.setParentId(taxonListObj.getParentCategoryId());
                        taxon.setPermalink(taxonListObj.getPermalink());
                        taxon.setPrettyName(taxonListObj.getPrettyName());
                        taxon.setTaxonomyId(taxonListObj.getTaxonomyId());
                        listTaxon.add(taxon);
                    }
                    root.setTaxons(listTaxon);
                }
            }

        }

        return root;
    }

    public Root getProductSubCategoryByParentIdAndCategoryId(int parentId, int id) {

        Root root = new Root();
        ProductCategory productCategory = null;
        productCategory = productCategoryRepository.findByCategory(id);
        if ((productCategory != null)) {
            root.setId(productCategory.getId());
            root.setName(productCategory.getCategoryName());
            root.setPrettyName(productCategory.getPrettyName());
            root.setPermalink(productCategory.getPermalink());
            root.setParentId(productCategory.getParentCategoryId());
            root.setTaxonomyId(productCategory.getTaxonomyId());
            root.setMetaTitle(productCategory.getMetaTitle());
            root.setMetaDescription(productCategory.getMetaDescription());
            List<ProductCategory> rootTaxonList = productCategoryRepository.findByMasterId(productCategory.getId());
            if (rootTaxonList != null && rootTaxonList.size() > 0) {
                List<Taxon> listTaxon = new ArrayList<Taxon>();
                for (ProductCategory taxonListObj : rootTaxonList) {
                    Taxon taxon = new Taxon();
                    taxon = getProductCategoryFileMetaDataBByCategoryId(id, taxon);
                    taxon.setId(taxonListObj.getId());
                    taxon.setMetaDescription(taxonListObj.getMetaDescription());
                    taxon.setMetaTitle(taxonListObj.getMetaTitle());
                    taxon.setName(taxonListObj.getCategoryName());
                    taxon.setParentId(taxonListObj.getParentCategoryId());
                    taxon.setPermalink(taxonListObj.getPermalink());
                    taxon.setPrettyName(taxonListObj.getPrettyName());
                    taxon.setTaxonomyId(taxonListObj.getTaxonomyId());
                    listTaxon.add(taxon);
                }
                root.setTaxons(listTaxon);
            }
        }

        return root;
    }

    public Taxon getProductCategoryFileMetaDataBByCategoryId(long productCategoryId, Taxon taxon) {
        List<ProductCategoryFileMetaData> prodCatFileList = productCategoryFileRepository
                .findByProductCategoryId(productCategoryId);
        if (prodCatFileList != null && prodCatFileList.size() > 0) {
            for (ProductCategoryFileMetaData productCategoryFileMetaData : prodCatFileList) {
                if (productCategoryFileMetaData != null && productCategoryFileMetaData.getUrlTypeId() == 5) {
                    taxon.setBannerImage(productCategoryFileMetaData.getS3Path());
                } else if (productCategoryFileMetaData != null && productCategoryFileMetaData.getUrlTypeId() == 6) {
                    taxon.setIcon(productCategoryFileMetaData.getS3Path());
                }
            }
        }
        return taxon;
    }

    public List<CategoryDTO> findAllProductCategory() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
        if (productCategoryList != null && productCategoryList.size() > 0) {
            for (ProductCategory productCategory : productCategoryList) {
                if (productCategory != null /*
                                             * && !productCategory.getIsMasterCategory() &&
											 * !productCategory.getIsMasterSubCategory()
											 */) {
                    CategoryDTO dto = new CategoryDTO();
                    dto.setLabel(""+productCategory.getCategoryName());
                    dto.setValue(""+productCategory.getMasterId());
                    categoryList.add(dto);
                }
            }
        }
        return categoryList;
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        ProductCategory cat = new ProductCategory();
        log.info("Parent Category Id: " + productCategory.getParentCategoryId());

        Long id=null;
        List<ProductCategory> prodCatList = productCategoryRepository.findByTest(productCategory.getParentCategoryId(), productCategory.getParentCategoryId());
        if (prodCatList.size()==0 && productCategory.getParentCategoryId() == 0) {
            id = fileSeq.generateSequence(ProductCategory.SEQUENCE_NAME);
            productCategory.setType(EntityType.Root);
            productCategory.setMasterId(id);
            productCategory.setId(id);
            productCategory.setChangesetId(new Long(25080022));
            cat = mapProductCategory(productCategory, cat);
            cat.setParentCategoryId(productCategory.getParentCategoryId());
            return productCategoryRepository.save(cat);

        } else if (prodCatList.size()>0 && productCategory.getParentCategoryId() == 0 &&
                prodCatList.stream().filter(Objects::nonNull).anyMatch(s -> s.getType() == EntityType.get(ROOT))) {
            log.info("Virtual Node already exist....");
            Optional<ProductCategory> matchingObject = prodCatList.stream().
                    filter(s -> s.getType() == EntityType.get(ROOT)).
                    findFirst();
            return matchingObject.get();
        } else if (prodCatList.size() > 0 &&
                prodCatList.stream().filter(Objects::nonNull).anyMatch(s -> s.getType() == EntityType.get(ROOT))) {
            id = fileSeq.generateSequence(ProductCategory.SEQUENCE_NAME);
            productCategory.setType(EntityType.Dimension);
            productCategory.setChangesetId(new Long(25080022));
            productCategory.setMasterId(id);
            productCategory.setId(id);
            cat = mapProductCategory(productCategory, cat);
            cat.setParentCategoryId(productCategory.getParentCategoryId());
            cat.addParentIds(productCategory.getParentCategoryId());
            return productCategoryRepository.save(cat);
        } else if (prodCatList.size() > 0 &&
                prodCatList.stream().filter(Objects::nonNull).anyMatch(s ->
                        (s.getType() == EntityType.get(DIMENSION) || s.getType() == EntityType.get(NODE) ))) {
            id = fileSeq.generateSequence(ProductCategory.SEQUENCE_NAME);
            productCategory.setType(EntityType.Node);
            productCategory.setChangesetId(new Long(25080022));
            productCategory.setMasterId(id);
            productCategory.setId(id);
            cat = mapProductCategory(productCategory, cat);
            cat.setParentCategoryId(productCategory.getParentCategoryId());
            cat.addParentIds(productCategory.getParentCategoryId());
            List<ProductCategory> masterListIn = productCategoryRepository.
                    findByMasterIdIdIn(prodCatList.get(0).getParentId());
            if (masterListIn != null && masterListIn.size() > 0) {
                for (ProductCategory obj : masterListIn) {
                    if (obj.getType() != EntityType.Root) {
                        cat.addParentIds(obj.getMasterId());
                    }
                }
            }
            Collections.sort(cat.getParentId());
            return productCategoryRepository.save(cat);
        }else{
            return cat;
        }
    }

    private ProductCategory mapProductCategory(ProductCategory src, ProductCategory dest) {
        BeanUtils.copyProperties(src, dest);
        return dest;
    }

    @Override
    public TreeNode getFullTree(Long changesetId) throws Exception {
        List<ProductCategory> nodes = productCategoryRepository.findDistinctByChangesetId(changesetId).orElseThrow(NotFoundException::new);

        List<TreeNode> treeNodes = new ArrayList<>();
        for (ProductCategory node : nodes) {
            TreeNode treeNode = new TreeNode();
            BeanUtils.copyProperties(node, treeNode, "id", "children");

            treeNodes.add(treeNode);
        }

        return (IProductCategoryService.assembleTree(treeNodes, IProductCategoryService.DEFAULT_ROOT_NODE_ID));
    }

    @Override
    public TreeNode getSubTree(Long changesetId, Long nodeId) throws Exception {
        List<ProductCategory> nodes = productCategoryRepository.getSubTree(changesetId, nodeId).orElseThrow(NotFoundException::new);

        List<TreeNode> flatList = nodes.stream().
                map(ProductCategory::getChildren).
                flatMap(Collection::stream)
                .map(node -> {
                    TreeNode tr = new TreeNode();
                    BeanUtils.copyProperties(node, tr, "id");
                    return tr;
                }).collect(Collectors.toList());

        TreeNode root = new TreeNode();
        BeanUtils.copyProperties(nodes.get(0), root, "id", "children");
        flatList.add(root);

        return (IProductCategoryService.assembleTree(flatList, nodeId));
    }

    private boolean validateNodes(Long parentCategoryId) {
        List<ProductCategory> allList = productCategoryRepository.findByParentCategoryId(parentCategoryId);
        return allList.stream().filter(Objects::nonNull).anyMatch(e -> e.getType().equals(ROOT));

    }
}
