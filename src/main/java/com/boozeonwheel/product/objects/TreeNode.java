
package com.boozeonwheel.product.objects;

import com.boozeonwheel.product.domain.category.EntityType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.TypeName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TypeName("TreeNode")
@Getter
@Setter
public class TreeNode implements Serializable {

	@Id
	private Long id;
	private Long masterId;

	private String name;

	private EntityType type;

	@DiffIgnore
	private int changesetId;

	@DiffIgnore
	private List<Long> parentId;

	private List<TreeNode> children;

	@DiffIgnore
	@JsonIgnore
	private List<TreeNode> parents;

	@JsonIgnore
	private Long parentCategoryId;
	@JsonIgnore
	private String categoryName;
	@JsonIgnore
	private String prettyName;
	@JsonIgnore
	private String permalink;
	@JsonIgnore
	private Long taxonomyId;
	@JsonIgnore
	private String metaTitle;
	@JsonIgnore
	private String metaDescription;
	@JsonIgnore
	private String description;

	public TreeNode() {
		this.parentId = new ArrayList<>();
		this.children = new ArrayList<>();
		this.parents = new ArrayList<>();
	}

	public void addChild(TreeNode child) {
		if (!this.children.contains(child) && child != null)
			this.children.add(child);
	}

	public void addParent(TreeNode parent) {
		if (!this.parents.contains(parent) && parent != null)
			this.parents.add(parent);
	}

}
