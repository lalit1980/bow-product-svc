package com.boozeonwheel.product.repository.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.dto.master.MasterDTO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductMasterRepositoryImpl implements ProductMasterRespositoryCustom{

	@Autowired
	MongoOperations mongoTemplate;
	
	@Override
	public Master findByProductMasterId(long id) {
		Query query = new Query(Criteria.where("id").is(id));
		Master master=mongoTemplate.find(query, Master.class).get(0);
		return master;
	}
	
	@Override
	public List<Master> findByProductCategoryId(long categoryId) {
		Query query = new Query(Criteria.where("categoryId").is(categoryId));
		List<Master> master=mongoTemplate.find(query, Master.class);
		return master;
	}

	@Override
	public DeleteResult deleteProductMaster(long id) {
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.remove(query, Master.class);
	}

	@Override
	public void addAllProductMasters(List<Master> masterList) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, Master.class);
		masterList.forEach(masterListObj -> {
			bulkOperations.insert(masterListObj);
		});
		bulkOperations.execute();
		
	}

	@Override
	public void deleteAllProductMaster() {
		mongoTemplate.remove(new Query(), "Master");
		
	}

	@Override
	public UpdateResult updateProductMaster(MasterDTO master) {
		Query query = new Query(Criteria.where("id").is(master.getId()));
		Update update = new Update();
		update.set("name", master.getName());
		update.set("sku", master.getSku());
		update.set("price", master.getPrice());
		update.set("weight", master.getWeight());
		update.set("height", master.getHeight());
		update.set("width", master.getWidth());
		update.set("depth", master.getDepth());
		update.set("isMaster", master.getIsMaster());
		update.set("slug", master.getSlug());
		update.set("description", master.getDescription());
		update.set("trackInventory", master.getTrackInventory());
		update.set("displayPrice", master.getDisplayPrice());
		update.set("optionsText", master.getOptionsText());
		update.set("inStock", master.getInStock());
		update.set("isBackorderable", master.getIsBackorderable());
		update.set("isOrderable", master.getIsOrderable());
		update.set("totalOnHand", master.getTotalOnHand());
		update.set("isDestroyed", master.getIsDestroyed());
		update.set("categoryId", master.getCategoryId());
		update.set("optionValues", master.getOptionValues());
		update.set("images", master.getImages());
		return mongoTemplate.updateFirst(query, update, Master.class);
	}

	@Override
	public UpdateResult updateCategoryIdByMasterId(Integer id, Integer CategoryId) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("categoryId", CategoryId);
		return mongoTemplate.updateFirst(query, update, Master.class);
	}
	 
}
