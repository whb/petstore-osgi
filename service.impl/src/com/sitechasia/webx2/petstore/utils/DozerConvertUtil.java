package com.sitechasia.webx2.petstore.utils;

import java.util.List;

import com.sitechasia.webx.core.model.IDomainObject;
import com.sitechasia.webx.core.model.IViewObject;

/**
 * 使用dozer包装的DO，VO转换器.
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public interface DozerConvertUtil {

	/**
	 * 
	 * VO到DO的转换
	 * 
	 * @param viewObject
	 * @param domainObject
	 */
	void viewObjectToDomainObject(IViewObject viewObject,
			IDomainObject domainObject);

	/**
	 * 
	 * DO到VO的转换
	 * 
	 * 
	 * @param domainObject
	 * @param viewObject
	 */
	void domainObjectToViewObject(IDomainObject domainObject,
			IViewObject viewObject);

	/**
	 * 
	 * VO集合转换DO集合
	 * 
	 * 
	 * @param viewObjects
	 * @param domainObject
	 * @return List<IDomainObject>
	 */
	List viewObjectsToDomainObjects(List viewObjects, Class domainObject);

	/**
	 * 
	 * DO集合转换VO集合
	 * 
	 * 
	 * @param domainObjects
	 * @param viewObject
	 * @return List<IViewObject>
	 */
	List domainObjectsToViewObjects(List domainObjects, Class viewObject);

}
