package me.lattice.shortlink.common.utiils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * 
 * ClassName: SpringContextUtil
 * @Description: 上下文信息常用类，注意，spring上下文全部加载之后才可以用，初始化的时候会报NPE
 */
public class SpringContextUtil {
	private static ApplicationContext applicationContext;

    public SpringContextUtil(@Autowired ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}

    public static void setApplicationContext(
			ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}
    
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
    	return applicationContext.getBean(clazz);
	}

	public static <T> T getBeanByName(Class<T> clazz, String beanName) {
		return applicationContext.getBean(clazz, beanName);
	}
}
