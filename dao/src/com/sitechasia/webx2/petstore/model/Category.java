/**
 * @{#} Category.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import com.sitechasia.webx.core.model.IDomainObject;

/**
 * Category domain object.
 *
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class Category implements IDomainObject{

	private String categoryId;
	private String name;
	private String description;

	public Category() {
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String newCategoryId) {
		this.categoryId = newCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
}
