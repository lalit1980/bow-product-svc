package com.boozeonwheel.product.domain.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.boozeonwheel.product.domain.master.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "ProductCategory")
public class ProductCategory {

	@Transient
    public static final String SEQUENCE_NAME = "productcategory_sequence";

	@Id
	@Indexed(name = "id_index", direction = IndexDirection.ASCENDING)
	private Long id;
	@Indexed(name = "masterId_index", direction = IndexDirection.ASCENDING)
	private Long masterId;
	private EntityType type;
	private Long parentCategoryId;
	private String categoryName;
	private String prettyName;
	private String permalink;
	private long taxonomyId;
	private String metaTitle;
	private String metaDescription;
	private String description;
	private Long changesetId;
	private List<ProductCategory> children=new ArrayList<ProductCategory>();
    private List<Product> products=new ArrayList<Product>();
	@Indexed(name = "parentId_index", direction = IndexDirection.ASCENDING)
	private List<Long> parentId=new ArrayList<>();
	public void addParentIds(Long parentId) {
        if (!this.parentId.contains(parentId) && parentId != null)
            this.parentId.add(parentId);
    }

	public void addChildrens(ProductCategory productCategory) {
		if (!this.children.contains(productCategory) && productCategory != null)
			this.children.add(productCategory);
	}

}
