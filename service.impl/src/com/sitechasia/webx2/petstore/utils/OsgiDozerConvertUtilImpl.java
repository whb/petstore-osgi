package com.sitechasia.webx2.petstore.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.util.CollectionUtils;

import org.osgi.framework.BundleContext;

import com.sitechasia.webx.core.model.IDomainObject;
import com.sitechasia.webx.core.model.IViewObject;

public class OsgiDozerConvertUtilImpl implements DozerConvertUtil {

	private BundleContext bundleContext;
	private DozerBeanMapper dozerBeanMapper;
	private List<String> mappingFiles;

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
		initializeDozerBeanMapper();
	}

	// 注册dozer MapperIF
	public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
		this.dozerBeanMapper = dozerBeanMapper;
		initializeDozerBeanMapper();
	}

	public void setMappingFiles(List<String> mappingFiles) {
		this.mappingFiles = mappingFiles;
		initializeDozerBeanMapper();
	}

	private void initializeDozerBeanMapper() {
		if (bundleContext != null && dozerBeanMapper != null
				&& mappingFiles != null) {
			List<String> bundleFileUrls = new ArrayList<String>();
			for (String fileUrl : mappingFiles) {
				String bundleFileUrl = "bundleresource://"
						+ bundleContext.getBundle().getBundleId() + "/"
						+ fileUrl;
				bundleFileUrls.add(bundleFileUrl);
			}
			dozerBeanMapper.setMappingFiles(bundleFileUrls);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.util.DozerConvertUtil#domainObjectToViewObject(com.sitechasia.webx.core.model.IDomainObject,
	 *      com.sitechasia.webx.core.model.IViewObject)
	 */
	public void domainObjectToViewObject(IDomainObject domainObject,
			IViewObject viewObject) {
		dozerBeanMapper.map(domainObject, viewObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.util.DozerConvertUtil#domainObjectsToViewObjects(java.util.List,
	 *      java.lang.Class)
	 */
	public List domainObjectsToViewObjects(List domainObjects, Class viewObject) {
		List viewObjects = new ArrayList(0);
		try {

			if (CollectionUtils.isCollection(domainObjects.getClass())) {
				int domainObjects_length = CollectionUtils
						.getLengthOfCollection(domainObjects);
				viewObjects = new ArrayList(domainObjects_length);
				for (int i = 0; i < domainObjects_length; i++) {

					IViewObject vo = (IViewObject) viewObject.newInstance();
					dozerBeanMapper.map(CollectionUtils.getValueFromCollection(
							domainObjects, i), vo);
					viewObjects.add(vo);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			viewObjects = new ArrayList(0);
		}

		return viewObjects;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.util.DozerConvertUtil#viewObjectToDomainObject(com.sitechasia.webx.core.model.IViewObject,
	 *      com.sitechasia.webx.core.model.IDomainObject)
	 */
	public void viewObjectToDomainObject(IViewObject viewObject,
			IDomainObject domainObject) {
		dozerBeanMapper.map(viewObject, domainObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.util.DozerConvertUtil#viewObjectsToDomainObjects(java.util.List,
	 *      java.lang.Class)
	 */
	public List viewObjectsToDomainObjects(List viewObjects, Class domainObject) {

		List domainObjects = new ArrayList(0);
		try {

			if (CollectionUtils.isCollection(viewObjects.getClass())) {
				int viewObjects_length = CollectionUtils
						.getLengthOfCollection(viewObjects);
				domainObjects = new ArrayList(viewObjects_length);
				for (int i = 0; i < viewObjects_length; i++) {

					IDomainObject DO = (IDomainObject) domainObject
							.newInstance();

					dozerBeanMapper.map(CollectionUtils.getValueFromCollection(
							viewObjects, i), DO);
					domainObjects.add(DO);
				}

			}
		} catch (Exception e) {
			domainObjects = new ArrayList(0);
		}

		return domainObjects;

	}

}
