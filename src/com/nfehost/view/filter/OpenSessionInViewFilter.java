package com.nfehost.view.filter;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nfehost.dao.HibernateSessionFactory;
import com.nfehost.exception.NfeHostException;
import com.nfehost.util.DateUtil;
import com.nfehost.util.FaceHttpParameterUtil;

@WebFilter(urlPatterns="/*")
public class OpenSessionInViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		
		try {
			chain.doFilter(request, response);
			transaction.commit();
			//throw new Exception();
		} catch(Throwable e) {
			session.flush();
			transaction.rollback();
			System.err.println(e.getMessage());
			
			String msg = DateUtil.dateHourToString(new Date()) + "\n" + NfeHostException.printStackTrace(e);
			request.setAttribute("stackTrace", msg);
			
			throw e;
			
		} finally {
			if(session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void redicionarPage(HttpServletRequest request, HttpServletResponse response, String mensagem) {
		FacesContext fc = FaceHttpParameterUtil.getFacesContext((HttpServletRequest)request, (HttpServletResponse)response);
		//Flash flash = fc.getExternalContext().getFlash();
		//flash.setKeepMessages(true);
		try {
		System.out.println(request.getContextPath() + "/pageError.xhtml");	
		response.sendRedirect(request.getContextPath() + "/pageError.xhtml" );
		//fc.getExternalContext().redirect(fc.getExternalContext().getRealPath("/") + "pageError.xhtml");
		fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, null));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}	

	@Override
	public void destroy() {
	}
}
