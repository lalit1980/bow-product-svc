
package com.boozeonwheel.product.dto.master;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "position",
    "attachment_content_type",
    "attachment_file_name",
    "type",
    "attachment_updated_at",
    "attachment_width",
    "attachment_height",
    "alt",
    "viewable_type",
    "viewable_id",
    "mini_url",
    "small_url",
    "product_url",
    "large_url"
})
public class Image {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("attachment_content_type")
    private String attachmentContentType;
    @JsonProperty("attachment_file_name")
    private String attachmentFileName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("attachment_updated_at")
    private Date attachmentUpdatedAt;
    @JsonProperty("attachment_width")
    private Double attachmentWidth;
    @JsonProperty("attachment_height")
    private Double attachmentHeight;
    @JsonProperty("alt")
    private String alt;
    @JsonProperty("viewable_type")
    private String viewableType;
    @JsonProperty("viewable_id")
    private Integer viewableId;
    @JsonProperty("mini_url")
    private String miniUrl;
    @JsonProperty("small_url")
    private String smallUrl;
    @JsonProperty("product_url")
    private String productUrl;
    @JsonProperty("large_url")
    private String largeUrl;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Image withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    public Image withPosition(Integer position) {
        this.position = position;
        return this;
    }

    @JsonProperty("attachment_content_type")
    public Object getAttachmentContentType() {
        return attachmentContentType;
    }

    @JsonProperty("attachment_content_type")
    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    public Image withAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
        return this;
    }

    @JsonProperty("attachment_file_name")
    public Object getAttachmentFileName() {
        return attachmentFileName;
    }

    @JsonProperty("attachment_file_name")
    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public Image withAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Image withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("attachment_updated_at")
    public Object getAttachmentUpdatedAt() {
        return attachmentUpdatedAt;
    }

    @JsonProperty("attachment_updated_at")
    public void setAttachmentUpdatedAt(Date attachmentUpdatedAt) {
        this.attachmentUpdatedAt = attachmentUpdatedAt;
    }

    public Image withAttachmentUpdatedAt(Date attachmentUpdatedAt) {
        this.attachmentUpdatedAt = attachmentUpdatedAt;
        return this;
    }

    @JsonProperty("attachment_width")
    public Object getAttachmentWidth() {
        return attachmentWidth;
    }

    @JsonProperty("attachment_width")
    public void setAttachmentWidth(Double attachmentWidth) {
        this.attachmentWidth = attachmentWidth;
    }

    public Image withAttachmentWidth(Double attachmentWidth) {
        this.attachmentWidth = attachmentWidth;
        return this;
    }

    @JsonProperty("attachment_height")
    public Object getAttachmentHeight() {
        return attachmentHeight;
    }

    @JsonProperty("attachment_height")
    public void setAttachmentHeight(Double attachmentHeight) {
        this.attachmentHeight = attachmentHeight;
    }

    public Image withAttachmentHeight(Double attachmentHeight) {
        this.attachmentHeight = attachmentHeight;
        return this;
    }

    @JsonProperty("alt")
    public String getAlt() {
        return alt;
    }

    @JsonProperty("alt")
    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Image withAlt(String alt) {
        this.alt = alt;
        return this;
    }

    @JsonProperty("viewable_type")
    public String getViewableType() {
        return viewableType;
    }

    @JsonProperty("viewable_type")
    public void setViewableType(String viewableType) {
        this.viewableType = viewableType;
    }

    public Image withViewableType(String viewableType) {
        this.viewableType = viewableType;
        return this;
    }

    @JsonProperty("viewable_id")
    public Integer getViewableId() {
        return viewableId;
    }

    @JsonProperty("viewable_id")
    public void setViewableId(Integer viewableId) {
        this.viewableId = viewableId;
    }

    public Image withViewableId(Integer viewableId) {
        this.viewableId = viewableId;
        return this;
    }

    @JsonProperty("mini_url")
    public String getMiniUrl() {
        return miniUrl;
    }

    @JsonProperty("mini_url")
    public void setMiniUrl(String miniUrl) {
        this.miniUrl = miniUrl;
    }

    public Image withMiniUrl(String miniUrl) {
        this.miniUrl = miniUrl;
        return this;
    }

    @JsonProperty("small_url")
    public String getSmallUrl() {
        return smallUrl;
    }

    @JsonProperty("small_url")
    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public Image withSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
        return this;
    }

    @JsonProperty("product_url")
    public String getProductUrl() {
        return productUrl;
    }

    @JsonProperty("product_url")
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Image withProductUrl(String productUrl) {
        this.productUrl = productUrl;
        return this;
    }

    @JsonProperty("large_url")
    public String getLargeUrl() {
        return largeUrl;
    }

    @JsonProperty("large_url")
    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public Image withLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }


}
