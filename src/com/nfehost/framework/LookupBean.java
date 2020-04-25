package com.nfehost.framework;

import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class LookupBean {
	
	private static final ApplicationContext context;
	
	static {
		String PATH = "resource/spring/";
		String[] springs = new String[]{PATH + "spring-pojo.xml", 
										PATH + "spring-service.xml", 
										PATH + "spring-dao.xml", 
										PATH + "spring-view.xml", 
										PATH + "spring-filter.xml"};  
		context = new ClassPathXmlApplicationContext(springs);
	}
	
	public static <Bean> Bean getBean(Class<Bean> classBean) {
		try {	
			return context.getBean(classBean);
		} catch (org.springframework.beans.BeansException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	public static <Bean> Bean getBean(String nameBean, Class<Bean> classBean) {
		try {
			return context.getBean(nameBean, classBean);
		} catch (org.springframework.beans.BeansException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	public static <Bean> Map<String, Bean> getBeanMap(Class<Bean> classBean) {
		try {	
			return context.getBeansOfType(classBean);
		} catch (org.springframework.beans.BeansException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public static <Bean> String getBeanName(Class<Bean> classBean) {
		try {	
			return context.getBeanNamesForType(classBean)[0];
		} catch (org.springframework.beans.BeansException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Adiciona e remove novo bean na sessao
	 * @throws ClassNotFoundException
	 */
	public static <Bean> void addAndRemoveObjects(Class<Bean> classBean) throws ClassNotFoundException {
		//Bean bean = getBean(classBean);
		String beanName = getBeanName(classBean);
		BeanDefinitionRegistry factory = (BeanDefinitionRegistry) context.getAutowireCapableBeanFactory();
		((DefaultListableBeanFactory) factory).destroyBean(beanName, classBean);
		/*
		((ConfigurableBeanFactory)context.getAutowireCapableBeanFactory()).destroyBean(arg0, arg1).destroyScopedBean(beanName);
		
		//bean = LookupBean.getBean(classBean);
       
         
        // Creating and registering bean to the container       
        BeanDefinition beanDefinition = new RootBeanDefinition(classBean);
        /*
        Scope scope =context.getAutowireCapableBeanFactory().getRegisteredScope("session");
        ((ConfigurableBeanFactory)context.getAutowireCapableBeanFactory()).registerScope(beanName, getRegisteredScope("session"));
        beanDefinitionRegistry.registerBeanDefinition("demo", beanDefinition);
        context.getAutowireCapableBeanFactory()
     
        //Obtaining from container again
        demo = getBean(context);
        assertEquals("Shinu", demo.getName());
        */
	 }
}
