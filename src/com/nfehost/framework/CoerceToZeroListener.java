package com.nfehost.framework;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CoerceToZeroListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.getProperties().put("org.apache.el.parser.COERCE_TO_ZERO", "false");
	}

}
