package com.boozeonwheel.product.service.category;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.directconnect.model.Loa;
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
								.findByParentId(taxonListObj.getId());
						if (taxonList != null && taxonList.size() > 0) {
							List<Taxon> listTaxonFinal = new ArrayList<Taxon>();
							for (ProductCategory taxonObj : taxonList) {
								Taxon taxonData = new Taxon();
								taxonData = getProductCategoryFileMetaDataBByCategoryId(taxonData.getId(), taxon);
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
										taxonDataFinal = getProductCategoryFileMetaDataBByCategoryId(
												taxonObjFinal.getId(), taxon);
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
					taxon = getProductCategoryFileMetaDataBByCategoryId(id, taxon);
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
					dto.setCategoryId(productCategory.getId());
					dto.setCategoryName(productCategory.getCategoryName());
					categoryList.add(dto);
				}
			}
		}
		return categoryList;
	}

	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		ProductCategory cat=new ProductCategory();
		if (productCategory != null) {
			List<ProductCategory> allList=productCategoryRepository.findAll();
			if(allList!=null && allList.size()>0) {
				ProductCategory newCategory=new ProductCategory(productCategory.getId(), productCategory.getParentCategoryId());
				newCategory.setPrettyName(productCategory.getCategoryName());
				newCategory.setPermalink(productCategory.getCategoryName().replaceAll(" ", "_").toLowerCase());
				newCategory.setCategoryName(productCategory.getCategoryName());
				newCategory.setDescription(productCategory.getDescription());
				newCategory.setMetaDescription(productCategory.getMetaDescription());
				newCategory.setMetaTitle(productCategory.getMetaTitle());
				newCategory.setTaxonomyId(productCategory.getTaxonomyId());
				
				allList.add(newCategory);
				ProductCategory category=createTree(allList);
				cat=productCategoryRepository.save(category);
				
			}else {
				allList=new ArrayList<>();
				ProductCategory newCategory=new ProductCategory(productCategory.getId(), new Long(0));
				newCategory.setPrettyName(productCategory.getCategoryName());
				newCategory.setPermalink(productCategory.getCategoryName().replaceAll(" ", "_").toLowerCase());
				newCategory.setCategoryName(productCategory.getCategoryName());
				newCategory.setDescription(productCategory.getDescription());
				newCategory.setMetaDescription(productCategory.getMetaDescription());
				newCategory.setMetaTitle(productCategory.getMetaTitle());
				newCategory.setTaxonomyId(productCategory.getTaxonomyId());
				//newCategory.setParentCategory(newCategory);
				allList.add(newCategory);
				ProductCategory category=createTree(allList);
				if(category!=null && category.getSubcategories()!=null && category.getSubcategories().size()>0) {
					List<ProductCategory> newList=category.getSubcategories();
					for (ProductCategory productCategory2 : newList) {
						
					}
				}
				cat=productCategoryRepository.save(category);
				
			}
			
			
			
		}
		return cat;

	}
	 private static ProductCategory createTree(List<ProductCategory> nodes) {
		 
	        Map<Long, ProductCategory> mapTmp = new HashMap<Long, ProductCategory>();
	        
	        //Save all nodes to a map
	        for (ProductCategory current : nodes) {
	            mapTmp.put(current.getId(), current);
	        }
	 
	        //loop and assign parent/child relationships
	        for (ProductCategory current : nodes) {
	            Long parentId = current.getParentCategoryId();
	 
	            if (parentId != null) {
	            	ProductCategory parent = mapTmp.get(parentId);
	                if (parent != null) {
	                	current.setParentCategory(parent);
	                    //current.setParent(parent);
	                    parent.addSubCategories(current);
	                    mapTmp.put(parentId, parent);
	                    mapTmp.put(current.getId(), current);
	                }
	            }
	 
	        }
	 
	    
	        //get the root
	        ProductCategory root = null;
	        for (ProductCategory node : mapTmp.values()) {
	            if(node.getParentCategory() == null) {
	                root = node;
	                break;
	            }
	        }
	 
	        System.out.println(root);
	        return root;
	    }
	private static String getPreetyName(List<String> str) {
		return str.stream().collect(Collectors.joining(" -> "));
	}

	private static String getPermaLink(List<String> nameList) {

		return nameList.stream().collect(Collectors.joining("/")).replaceAll(" ", "_").toLowerCase();
	}
	/*
	public static ProductCategory saveProductCategoryNew(ProductCategory productCategory, List<ProductCategory> productCategoryList) {
		List<String> categoryNames=new ArrayList<String>();
		//List<ProductCategory> productCategoryList=productCategoryRepository.findAll();
		List<ProductCategory> level1=new ArrayList<>();
		if(productCategoryList!=null && productCategoryList.size()>0) {
			
			for(int i=0;i<productCategoryList.size();i++) {
				ProductCategory allProdCat=productCategoryList.get(i);
				if(productCategory.getParentCategoryId()==allProdCat.getId()) {
					categoryNames.add(allProdCat.getCategoryName());
					level1.add(allProdCat);
					
				}
			}
			categoryNames.add(productCategory.getCategoryName());
			
		}
		System.out.println("Preety Name: "+getPreetyName(categoryNames));
		System.out.println("Perma link: "+getPermaLink(categoryNames));
		return productCategory;
	}

	public static void main(String[] args) {
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		ProductCategory obj1 = new ProductCategory();
		obj1.setCategoryName("Master Category");
		obj1.setId(new Long(1));
		obj1.setParentCategoryId(null);
		
		ProductCategory obj2 = new ProductCategory();
		obj2.setCategoryName("Wood Craft");
		obj2.setId(new Long(6));
		obj2.setParentCategoryId(null);
		 //
		 
		ProductCategory obj3 = new ProductCategory();
		obj3.setCategoryName("Alcohol");
		obj3.setId(new Long(2));
		obj3.setParentCategoryId(new Long(1));
		obj3.setPrettyName(obj3.getCategoryName());
		obj3.setPermalink("alcohol");

		ProductCategory obj4 = new ProductCategory();
		obj4.setCategoryName("Beer");
		obj4.setId(new Long(3));
		obj4.setParentCategoryId(new Long(2));

		ProductCategory obj5 = new ProductCategory();
		obj5.setCategoryName("Domestic Beer");
		obj5.setId(new Long(4));
		obj5.setParentCategoryId(new Long(3));

		ProductCategory obj6 = new ProductCategory();
		obj6.setCategoryName("Imported Beer");
		obj6.setId(new Long(5));
		obj6.setParentCategoryId(new Long(3));
		list.add(obj1);
		//list.add(obj2);
		//list.add(obj3);
		//list.add(obj4);
		//list.add(obj5);

		
		//saveProductCategoryNew(obj6, list);
		createTree(list);

	}*/
}
