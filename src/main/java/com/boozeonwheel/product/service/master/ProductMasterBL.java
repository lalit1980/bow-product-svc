package com.boozeonwheel.product.service.master;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.CollectionUtils;
import com.boozeonwheel.product.domain.category.ProductCategory;
import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.dto.master.MasterDTO;
import com.boozeonwheel.product.exception.category.CategoryNotFoundException;
import com.boozeonwheel.product.exception.file.NotFoundException;
import com.boozeonwheel.product.repository.category.ProductCategoryRepository;
import com.boozeonwheel.product.repository.file.FileRepository;
import com.boozeonwheel.product.repository.liquor.LiquorRepository;
import com.boozeonwheel.product.repository.master.ProductMasterRepository;

@Service
public class ProductMasterBL {

	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Autowired
	LiquorRepository liquorRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	
	public void mapLiquorToProduct() {		
		List<M_LIQUOR> lisquorList=liquorRepository.findAll();
		if(lisquorList!=null && lisquorList.size()>0) {
			List<Master> masterList=new ArrayList<Master>();
			for(int i=0;i<lisquorList.size();i++) {
				Master master=new Master();
				master.setId((int)lisquorList.get(i).getLIQUOR_CODE());
				master.setName(lisquorList.get(i).getLIQUOR_DESCRIPTION());
				if(lisquorList.get(i).getLIQUOR_TYPE().trim().equalsIgnoreCase("BEER")) {
					master.setCategoryId(2);
				}else if(lisquorList.get(i).getLIQUOR_TYPE().trim().equalsIgnoreCase("WINE")) {
					master.setCategoryId(5);
				}else if(lisquorList.get(i).getLIQUOR_TYPE().trim().equalsIgnoreCase("LIQUOR")) {
					master.setCategoryId(3);
				}else if(lisquorList.get(i).getLIQUOR_TYPE().trim().equalsIgnoreCase("NON-ALCOHOL")) {
					master.setCategoryId(4);
				}else if(lisquorList.get(i).getLIQUOR_TYPE().trim().equalsIgnoreCase("STR_SUPPLIES") ||
						lisquorList.get(i).getLIQUOR_TYPE().trim().equalsIgnoreCase("REF")) {
					master.setCategoryId(6);
				}
				masterList.add(master);
			}
			productMasterRepository.addAllProductMasters(masterList);
			
		}
	
	}
	public List<MasterDTO> getMasterDataByCategory(long categoryId) {
		List<Master> masterList=productMasterRepository.findByProductCategoryId(categoryId);
		List<MasterDTO> dtoList=new ArrayList<MasterDTO>();
		try {
			if((masterList!=null && masterList.size()>0)) {
				for (Master master : masterList) {
					MasterDTO dto=new MasterDTO();
					if(master.getCategoryId()==0) {
						dto.setCategoryId(3);
					}else{
						dto.setCategoryId(master.getCategoryId());
					}
					
					System.out.println("Sku Code: "+master.getSku()+" Find by Category Id: "+master.getCategoryId());
					ProductCategory list=productCategoryRepository.findByCategory(master.getCategoryId());
					if(list!=null ) {
						dto.setCategoryName(productCategoryRepository.findByCategory(master.getCategoryId()).getCategoryName());
					}
					dto.setId(master.getId());
					dto.setDisplayPrice(master.getDisplayPrice());
					dto.setName(master.getName());
					dto.setCategoryId(2);
					dto.setSku(master.getSku());
					dto.setWidth(master.getWidth());
					dto.setHeight(master.getHeight());
					dto.setDepth(master.getDepth());
					dto.setDescription(master.getDescription());
					dto.setPrice(master.getPrice());
					dto.setInStock(master.getInStock());
					dto.setIsBackorderable(master.getInStock());
					dto.setIsMaster(master.getIsMaster());
					dto.setIsOrderable(master.getIsOrderable());
					dto.setOptionsText(master.getOptionsText());
					dto.setSlug(master.getSlug());
					dto.setTotalOnHand(master.getTotalOnHand());
					dto.setTrackInventory(master.getTrackInventory());
					dto.setWeight(master.getWeight());
					dto.setImages(fileRepository.findByProductId(master.getId()));
					//dto.setOptionValues(master.getOptionValues());
					dtoList.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dtoList;
	}

	public MasterDTO getMasterDataByProductId(long id) {
		Master master=productMasterRepository.findByProductMasterId(id);
		final char[] delimiters = { ' ', '_' };
		MasterDTO dto=null;
		if((master!=null)) {
				dto=new MasterDTO();
				dto.setCategoryId(master.getCategoryId());
				ProductCategory category=null;
				try {
					category = productCategoryRepository.findByCategory(master.getCategoryId());
					dto.setCategoryName(category.getCategoryName());
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
				
				dto.setDepth(master.getDepth());
				dto.setDescription(master.getDescription());
				dto.setDisplayPrice(master.getDisplayPrice());
				dto.setHeight(master.getHeight());
				dto.setId(master.getId());
				dto.setImages(fileRepository.findByProductId(master.getId()));
				dto.setInStock(master.getInStock());
				dto.setIsBackorderable(master.getIsBackorderable());
				dto.setIsDestroyed(master.getIsDestroyed());
				dto.setIsMaster(master.getIsMaster());
				dto.setIsOrderable(master.getIsOrderable());
				dto.setMessage("success");
				dto.setName(WordUtils.capitalizeFully(master.getName(),delimiters));
				dto.setOptionsText(master.getOptionsText());
				//dto.setOptionValues(master.getOptionValues());
				dto.setPrice(master.getDisplayPrice());
				dto.setSku(master.getSku());
				dto.setSlug(master.getSlug());
				dto.setStatus("success");
				dto.setTotalOnHand(master.getTotalOnHand());
				dto.setTrackInventory(master.getTrackInventory());
				dto.setWeight(master.getWeight());
				dto.setWidth(master.getWidth());
		}
		return dto;
	}
	
}
