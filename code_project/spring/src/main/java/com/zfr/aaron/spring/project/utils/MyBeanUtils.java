package com.zfr.aaron.spring.project.utils;

import cn.hutool.core.bean.BeanUtil;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;


/**
 * 对象属性属性自动封装,
 * fastCopyProperties参考来源： BeanCopier 与 BeanUtils 及 人工setter之间的比较
 * https://segmentfault.com/a/1190000006922799
 *
 * @Description:
 * @version V1.0
 */
public class MyBeanUtils extends BeanUtil {

	private MyBeanUtils() {
	}

	private static final Map<String, BeanCopier> BEAN_COPIER_CACHE_MAP = new ConcurrentHashMap<>(100);
	private static final Map<String,ConstructorAccess> CONSTRUCTOR_ACCESS_CACHE_MAP = new ConcurrentHashMap<>(100);

	/**
	 * 无返回值的copy，要自己new
	 */
	public static void fastCopyProperties(Object source, Object target) {
		BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
		copier.copy(source, target, null);
	}

	/**
	 * 带返回值的copy，不用自己new
	 */
	public static <T> T fastCopyProperties(Object source, Class<T> targetClass) {
		T t;
		try {
			/*t = targetClass.newInstance();*/
			t = fastClone(targetClass);
		} catch (RuntimeException e) {
			throw new RuntimeException(format("Create new instance of %s failed: %s", targetClass, e.getMessage()));
		}
		fastCopyProperties(source, t);
		return t;
	}

	/**
	 * 批量的，带返回值的copy，不用自己new
	 * 列子 List<ErpOrderHeadDto> erpHeadDto2 = MyBeanUtils.fastCopyPropertiesOfList(headVoList, ErpOrderHeadDto.class);
	 */
	public static <T> List<T> fastCopyProperties(List<?> sourceList, Class<T> targetClass) {
		if (CollectionUtils.isEmpty(sourceList)) {
			return Collections.emptyList();
		}
		ConstructorAccess<T> constructorAccess = getConstructorAccess(targetClass);
		List<T> resultList = new ArrayList<>(sourceList.size());
		for (Object o : sourceList) {
			T t;
			try {
				t = constructorAccess.newInstance();
				fastCopyProperties(o, t);
				resultList.add(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return resultList;
	}


	/**
	 * 高效克隆
	 */
	public static <T> T fastClone(Class<T> targetClass) {
		ConstructorAccess<T> constructorAccess = getConstructorAccess(targetClass);
		T t;
		try {
			t = constructorAccess.newInstance();
		} catch (RuntimeException e) {
			throw new RuntimeException(format("Create new instance of %s failed: %s", targetClass, e.getMessage()));
		}

		return t;
	}


	private static BeanCopier getBeanCopier(Class sourceClass, Class targetClass) {
		String beanKey = generateKey(sourceClass, targetClass);
		BeanCopier copier = null;
		if (!BEAN_COPIER_CACHE_MAP.containsKey(beanKey)) {
			copier = BeanCopier.create(sourceClass, targetClass, false);
			BEAN_COPIER_CACHE_MAP.put(beanKey, copier);
		} else {
			copier = BEAN_COPIER_CACHE_MAP.get(beanKey);
		}
		return copier;
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}


	private static <T> ConstructorAccess<T> getConstructorAccess(Class<T> targetClass) {
		ConstructorAccess<T> constructorAccess = CONSTRUCTOR_ACCESS_CACHE_MAP.get(targetClass.toString());
		if(constructorAccess != null) {
			return constructorAccess;
		}
		try {
			constructorAccess = ConstructorAccess.get(targetClass);
			constructorAccess.newInstance();
			CONSTRUCTOR_ACCESS_CACHE_MAP.put(targetClass.toString(),constructorAccess);
		} catch (Exception e) {
			throw new RuntimeException(format("Create new instance of %s failed: %s", targetClass, e.getMessage()));
		}
		return constructorAccess;
	}


}
