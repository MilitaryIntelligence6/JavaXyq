package com.mxxy.game.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.mxxy.game.base.BasePresenter;
import com.mxxy.game.base.IBaseView;

/**
 * 字节码实例化工具
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class InstanceUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Class<BasePresenter> getModlerClazz(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return BasePresenter.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return BasePresenter.class;
		}
		if (!(params[index] instanceof Class)) {
			return BasePresenter.class;
		}
		return (Class) params[index];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <V> V bindView(IBaseView view, BasePresenter presenter) {
		if (view != presenter.getView()) {
			if (presenter.getView() != null) {
				presenter.destoryView();
			}
			presenter.setView(view);
		}
		return (V) view;
	}

	public static <T> T getInstance(Class<?> clazz) {
		try {
			return create(clazz);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T create(Class<?> clazz) {
		T t = null;
		try {
			t = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 实例化内部类 Class<?>
	 * class1=Class.forName("com.mxxy.extendpackage."+rootElement.attributeValue("handel"));
	 * imageComponents.get(count).addLoginListener(InstanceUtil.create(class1,Login.class));
	 * 
	 * @param classes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T create(Class<?>... classes) {
		T t = null;
		try {
			t = (T) classes[0].getConstructors()[0].newInstance(create(classes[1]));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return t;
	}
}
