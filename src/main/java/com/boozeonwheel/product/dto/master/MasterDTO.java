package com.boozeonwheel.product.dto.master;

import java.util.ArrayList;
import java.util.List;

import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.domain.master.OptionValue;

public class MasterDTO {
	
	private long id;
    private String name;
    private String sku;
    private String price;
    private String weight;
    private Double height;
    private Double width;
    private Double depth;
    private Boolean isMaster;
    private String slug;
    private String description;
    private List<OptionValue> optionValues = new ArrayList<OptionValue>();
    private List<FileMetaData> images = new ArrayList<FileMetaData>();
    private String displayPrice;
    private String optionsText;
    private Boolean inStock;
    private Boolean isBackorderable;
    private Boolean isOrderable;
    private Integer totalOnHand;
    private Boolean isDestroyed;
    private Integer categoryId;
    private String categoryName;
    private String status;
    private String message;
    private Boolean trackInventory;
    private double priceList;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getDepth() {
		return depth;
	}
	public void setDepth(Double depth) {
		this.depth = depth;
	}
	public Boolean getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(Boolean isMaster) {
		this.isMaster = isMaster;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<OptionValue> getOptionValues() {
		return optionValues;
	}
	public void setOptionValues(List<OptionValue> optionValues) {
		this.optionValues = optionValues;
	}
	public List<FileMetaData> getImages() {
		return images;
	}
	public void setImages(List<FileMetaData> images) {
		this.images = images;
	}
	public String getDisplayPrice() {
		return displayPrice;
	}
	public void setDisplayPrice(String displayPrice) {
		this.displayPrice = displayPrice;
	}
	public String getOptionsText() {
		return optionsText;
	}
	public void setOptionsText(String optionsText) {
		this.optionsText = optionsText;
	}
	public Boolean getInStock() {
		return inStock;
	}
	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}
	public Boolean getIsBackorderable() {
		return isBackorderable;
	}
	public void setIsBackorderable(Boolean isBackorderable) {
		this.isBackorderable = isBackorderable;
	}
	public Boolean getIsOrderable() {
		return isOrderable;
	}
	public void setIsOrderable(Boolean isOrderable) {
		this.isOrderable = isOrderable;
	}
	public Integer getTotalOnHand() {
		return totalOnHand;
	}
	public void setTotalOnHand(Integer totalOnHand) {
		this.totalOnHand = totalOnHand;
	}
	public Boolean getIsDestroyed() {
		return isDestroyed;
	}
	public void setIsDestroyed(Boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "MasterDTO [id=" + id + ", name=" + name + ", sku=" + sku + ", price=" + price + ", weight=" + weight
				+ ", height=" + height + ", width=" + width + ", depth=" + depth + ", isMaster=" + isMaster + ", slug="
				+ slug + ", description=" + description + ", optionValues=" + optionValues + ", images=" + images
				+ ", displayPrice=" + displayPrice + ", optionsText=" + optionsText + ", inStock=" + inStock
				+ ", isBackorderable=" + isBackorderable + ", isOrderable=" + isOrderable + ", totalOnHand="
				+ totalOnHand + ", isDestroyed=" + isDestroyed + ", categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", status=" + status + ", message=" + message + ", trackInventory=" + trackInventory
				+ "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getTrackInventory() {
		return trackInventory;
	}
	public void setTrackInventory(Boolean trackInventory) {
		this.trackInventory = trackInventory;
	}
	public double getPriceList() {
		return priceList;
	}
	public void setPriceList(double priceList) {
		this.priceList = priceList;
	}
	

}
