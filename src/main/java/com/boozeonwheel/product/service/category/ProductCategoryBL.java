package com.boozeonwheel.product.service.category;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boozeonwheel.product.domain.category.ProductCategory;
import com.boozeonwheel.product.domain.file.ProductCategoryFileMetaData;
import com.boozeonwheel.product.dto.master.CategoryDTO;
import com.boozeonwheel.product.dto.root.Root;
import com.boozeonwheel.product.dto.taxonomies.HomeResult;
import com.boozeonwheel.product.dto.taxonomies.Taxonomy;
import com.boozeonwheel.product.dto.taxons.Taxon;
import com.boozeonwheel.product.exception.category.CategoryNotFoundException;
import com.boozeonwheel.product.repository.category.ProductCategoryRepository;
import com.boozeonwheel.product.repository.file.ProductCategoryFileRepository;
import com.google.gson.Gson;

@Service
public class ProductCategoryBL {

	@Autowired
	ProductCategoryRepository productCategoryRepository;
	@Autowired
	ProductCategoryFileRepository productCategoryFileRepository;

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
		List<ProductCategory> list = productCategoryRepository.findByParentId(categoryId);
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
				List<ProductCategory> rootTaxonList = productCategoryRepository.findByParentId(productCategory.getId());

				if (rootTaxonList != null && rootTaxonList.size() > 0) {
					List<Taxon> listTaxon = new ArrayList<Taxon>();
					for (ProductCategory taxonListObj : rootTaxonList) {
						Taxon taxon = new Taxon();
						taxon=getProductCategoryFileMetaDataBByCategoryId(taxonListObj.getId(), taxon);
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
								.findByParentId(taxonListObj.getId());
						if (taxonList != null && taxonList.size() > 0) {
							List<Taxon> listTaxonFinal = new ArrayList<Taxon>();
							for (ProductCategory taxonObj : taxonList) {
								Taxon taxonData = new Taxon();
								taxonData=getProductCategoryFileMetaDataBByCategoryId(taxonData.getId(), taxon);
								taxonData.setId(taxonObj.getId());
								taxonData.setDescription(taxonObj.getDescription());
								taxonData.setMetaDescription(taxonObj.getMetaDescription());
								taxonData.setMetaTitle(taxonObj.getMetaTitle());
								taxonData.setName(taxonObj.getCategoryName());
								taxonData.setParentId(taxonObj.getParentCategoryId());
								taxonData.setPermalink(taxonObj.getPermalink());
								taxonData.setPrettyName(taxonObj.getPrettyName());
								taxonData.setTaxonomyId(taxonObj.getTaxonomyId());
								List<ProductCategory> taxonListFinal = productCategoryRepository
										.findByParentId(taxonObj.getId());
								if (taxonListFinal != null && taxonListFinal.size() > 0) {
									List<Taxon> listTaxonFinalDepth = new ArrayList<Taxon>();
									for (ProductCategory taxonObjFinal : taxonListFinal) {
										Taxon taxonDataFinal = new Taxon();
										taxonDataFinal=getProductCategoryFileMetaDataBByCategoryId(taxonObjFinal.getId(), taxon);
										taxonDataFinal.setId(taxonObjFinal.getId());
										taxonDataFinal.setDescription(taxonObjFinal.getDescription());
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
				List<ProductCategory> rootTaxonList = productCategoryRepository.findByParentId(productCategory.getId());
				if (rootTaxonList != null && rootTaxonList.size() > 0) {
					List<Taxon> listTaxon = new ArrayList<Taxon>();
					for (ProductCategory taxonListObj : rootTaxonList) {
						Taxon taxon = new Taxon();
						taxon=getProductCategoryFileMetaDataBByCategoryId(categoryId, taxon);
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
		ProductCategory productCategory=null;
		try {
			productCategory = productCategoryRepository.findByCategory(id);
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ((productCategory != null)) {
			root.setId(productCategory.getId());
			root.setName(productCategory.getCategoryName());
			root.setPrettyName(productCategory.getPrettyName());
			root.setPermalink(productCategory.getPermalink());
			root.setParentId(productCategory.getParentCategoryId());
			root.setTaxonomyId(productCategory.getTaxonomyId());
			root.setMetaTitle(productCategory.getMetaTitle());
			root.setMetaDescription(productCategory.getMetaDescription());
			List<ProductCategory> rootTaxonList = productCategoryRepository.findByParentId(productCategory.getId());
			if (rootTaxonList != null && rootTaxonList.size() > 0) {
				List<Taxon> listTaxon = new ArrayList<Taxon>();
				for (ProductCategory taxonListObj : rootTaxonList) {
					Taxon taxon = new Taxon();
					taxon=getProductCategoryFileMetaDataBByCategoryId(id, taxon);
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

		return root;
	}
	public Taxon getProductCategoryFileMetaDataBByCategoryId(long productCategoryId,Taxon taxon) {
		List<ProductCategoryFileMetaData> prodCatFileList=productCategoryFileRepository.findByProductCategoryId(productCategoryId);
		if(prodCatFileList!=null && prodCatFileList.size()>0) {
			for (ProductCategoryFileMetaData productCategoryFileMetaData : prodCatFileList) {
				if(productCategoryFileMetaData!=null && productCategoryFileMetaData.getUrlTypeId()==5) {
					taxon.setBannerImage(productCategoryFileMetaData.getS3Path());
				}else if(productCategoryFileMetaData!=null && productCategoryFileMetaData.getUrlTypeId()==6) {
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
				if (productCategory != null /*&& !productCategory.getIsMasterCategory()
						&& !productCategory.getIsMasterSubCategory()*/) {
					CategoryDTO dto = new CategoryDTO();
					dto.setCategoryId(productCategory.getId());
					dto.setCategoryName(productCategory.getCategoryName());
					categoryList.add(dto);
				}
			}
		}
		return categoryList;
	}
	
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		if(productCategory !=null) {
			if(productCategory.getIsMasterCategory() && productCategory.getIsMasterSubCategory()) {
				productCategory.setPrettyName(productCategory.getCategoryName());
				productCategory.setPermalink(productCategory.getCategoryName().replaceAll(" ", "_").toLowerCase());
			}else if(!productCategory.getIsMasterCategory() && !productCategory.getIsMasterSubCategory()) {
				//Optional<ProductCategory> productCategoryList=productCategoryRepository.findById(productCategory.getId());
				List<Optional<ProductCategory>> listOfOptionals = Arrays.asList(productCategoryRepository.findById(productCategory.getParentCategoryId()));
				List<ProductCategory> productCategoryList = listOfOptionals.stream()
						  .filter(Optional::isPresent)
						  .map(Optional::get)
						  .collect(Collectors.toList());
				List<String> categoryNames=new ArrayList<String>();
				for (ProductCategory productCategory2 : productCategoryList) {
					categoryNames.add(productCategory2.getCategoryName());
				}
				categoryNames.add(productCategory.getCategoryName());
				productCategory.setPrettyName(getPreetyName(categoryNames));
				productCategory.setPermalink(getPermaLink(categoryNames));
			}
		}
		return productCategoryRepository.save(productCategory);
		
	}
	private static String getPreetyName(List<String> str) {
		return str.stream()
                .collect(Collectors.joining(" -> "));
	}
	
	private static String getPermaLink(List<String> nameList) {
		
		return nameList.stream()
                .collect(Collectors.joining("/")).replaceAll(" ", "_").toLowerCase();
	}
}
