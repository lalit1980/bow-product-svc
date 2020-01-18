package com.boozeonwheel.product.repository.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.domain.master.Product;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductRepositoryImpl implements ProductRespositoryCustom {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public Product findByProductMasterId(Integer id) {
		Query query = new Query(Criteria.where("id").is(id));
		Product master=mongoTemplate.find(query, Product.class).get(0);
		return master;

	}

	@Override
	public DeleteResult deleteProductMaster(Integer id) {
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.remove(query, Product.class);
	}

	@Override
	public void addAllProductMasters(List<Product> masterList) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, Product.class);
		masterList.forEach(masterListObj -> {
			bulkOperations.insert(masterListObj);
		});
		bulkOperations.execute();
	}

	@Override
	public void deleteAllProductMaster() {
		mongoTemplate.remove(new Query(), "Product");

	}

	@Override
	public UpdateResult updateProductMaster(Product master) {
		/*Query query = new Query(Criteria.where("id").is(master.getId()));
		Update update = new Update();
		update.set("depth", master.getDepth());
		update.set("height", master.getHeight());
		update.set("width", master.getWidth());
		update.set("description", master.getDescription());
		update.set("displayPrice", master.getDisplayPrice());
		update.set("images", master.getImages());
		update.set("inStock", master.getInStock());
		update.set("isBackorderable", master.getIsBackorderable());
		update.set("isDestroyed", master.getIsDestroyed());
		update.set("isMaster", master.getIsMaster());
		update.set("isOrderable", master.getIsOrderable());
		update.set("name", master.getName());
		update.set("optionsText", master.getOptionsText());
		update.set("optionValues", master.getOptionValues());
		update.set("price", master.getPrice());
		update.set("sku", master.getSku());
		update.set("slug", master.getSlug());
		update.set("totalOnHand", master.getTotalOnHand());
		update.set("trackInventory", master.getTrackInventory());
		update.set("weight", master.getWeight());
		return mongoTemplate.updateFirst(query, update, ProductCategory.class);*/
		return null;
	}

}
