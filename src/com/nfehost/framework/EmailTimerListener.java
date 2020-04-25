package com.nfehost.framework;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.Data;

import com.nfehost.view.AnexoBean;

@Data
public class EmailTimerListener implements ServletContextListener {

	private AnexoBean anexoBean;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		this.anexoBean = LookupBean.getBean("anexoBean", AnexoBean.class);
		
		new Thread() {
			
			@Override
			public void run() {
				
				while (true) {
					
					try {
						anexoBean.process();
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}
}
