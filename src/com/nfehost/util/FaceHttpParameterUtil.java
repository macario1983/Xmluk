package com.nfehost.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.FactoryFinder;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.context.Flash;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class FaceHttpParameterUtil implements Serializable {
	
	private static final long serialVersionUID = -451345027342819465L;
	
	//private Map<String, Map<String, UIComponent>> componentMap;

	public static String getParameter(String param) {
		return getParameterMap().get(param);
	}
		
	public static Map<String, String> getParameters() {
		return getParameterMap();
	}

	/**
	 * Gets the faces context.
	 *
	 * @return the faces context
	 */
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	
	public static FacesContext getFacesContext(HttpServletRequest request, HttpServletResponse response) {
		 // Get current FacesContext.
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Check current FacesContext.
        if (NullUtil.isNull(facesContext)) {

            // Create new Lifecycle.
            LifecycleFactory lifecycleFactory = (LifecycleFactory)
                FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY); 
            Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

            // Create new FacesContext.
            FacesContextFactory contextFactory  = (FacesContextFactory)
                FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
            facesContext = contextFactory.getFacesContext(request.getSession().getServletContext(), request, response, lifecycle);

            // Create new View.
            UIViewRoot view = facesContext.getApplication().getViewHandler().createView(facesContext, "");
            facesContext.setViewRoot(view);                

            // Set current FacesContext.
            FacesContextWrapper.setCurrentInstance(facesContext);
        }
        return facesContext;
	}
	
	private static abstract class FacesContextWrapper extends FacesContext {
        protected static void setCurrentInstance(FacesContext facesContext) {
            FacesContext.setCurrentInstance(facesContext);
        }
    } 
	
	public static void invalidateSession() {
		((HttpSession)getFacesContext().getExternalContext().getSession(false)).invalidate();
		//getFacesContext().getExternalContext().invalidateSession();
	}
	
	public static void removerSessao(String bean) {		
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(true);
		session.removeAttribute(bean);
	}
	
	/**
	 * Gets the parameter map.
	 *
	 * @return the parameter map
	 */
	private static Map<String, String> getParameterMap() {
		return getFacesContext().getExternalContext().getRequestParameterMap();
	}
	
	public static String getParameterRequestHeader(String chave) {
		return getFacesContext().getExternalContext().getRequestHeaderMap().get(chave);
	}
	
	public static String getClientIpAddr() {
		//return getParameterRequestHeader("X-FORWARDED-FOR");
		HttpServletRequest request = (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		return request.getRemoteAddr();//.getHeader("X-FORWARDED-FOR");
	}
	
	public static Object getParameterRequest(String key) {
		return getRequestMap().get(key);
	}
	
	public static void setParameterRequest(String key, Object obj) {
		getRequestMap().put(key, obj);
	}
	
	public static void getRemoveParameterRequest(String chave) {
		getRequestMap().remove(chave);
	}
	
	/**
	 * Gets the request map.
	 *
	 * @return the request map
	 */
	private static Map<String, Object> getRequestMap () {
		return getFacesContext().getExternalContext().getRequestMap();
	}
	
	/**
	 * return an Object containing the value of the attribute, or null if no attribute exists matching the given name.
	 *
	 * @param key the key
	 * @return the attribute context
	 */
	public static Object getAttributeContext(String key) {
		return getServletContext().getAttribute(key);
	}
	
	public static void removeAttributeContext(String key) {
		getServletContext().removeAttribute(key);
	}
	
	public static void setAttributeContext(String key, Object obj) {
		getServletContext().setAttribute(key, obj);
	}
	
	public static Enumeration<String> getAttributeNamesContext() {
		return getServletContext().getAttributeNames();
	}

	protected static ServletContext getServletContext() {
		return (ServletContext) getFacesContext().getExternalContext().getContext();
	}
	
	public static void removeAttributeSession(String key) {
		getSessionMap().remove(key);
	}
	
	public static void setAttributeSession(String key, Object obj) {
		getSessionMap().put(key, obj);
	}

	public static Object getAttributeSession(String key) {
		return getSessionMap().get(key);
	}
	//Escopo de aplicacao 
	
	public static Map<String, Object> getApplicationMap() {
		return getFacesContext().getExternalContext().getApplicationMap();
	}
	
	public static void removeAttributeApplication(String key) {
		getApplicationMap().remove(key);
	}
	
	public static void setAttributeApplication(String key, Object obj) {
		getApplicationMap().put(key, obj);
	}

	public static Object getAttributeApplication(String key) {
		return getApplicationMap().get(key);
	}
	
	public static Object getAttributeError(String key) {
		return getFacesContext().getExternalContext().getRequestMap().get(key);
	}
	
	/**
	 * Gets the session map.
	 *
	 * @return the session map
	 */
	private static Map<String, Object> getSessionMap() {
		return getFacesContext().getExternalContext().getSessionMap();
	}
	
	
	public static String getClientId(String componentId) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();

		UIComponent c = findComponent(root, componentId);
		return c.getClientId(context);
	}
	
	/**
	 * retorna o component com o especifico id
	 * 
	 * @param id
	 * @return
	 */
	public static UIComponent getUIComponent(FacesContext context, String componentId) {
		UIViewRoot root = context.getViewRoot();		
		UIComponent component = findComponent(root, componentId);	
		
		if(NullUtil.isNull(component)) {
			throw new IllegalArgumentException("Component não encontrato através do id: " + componentId);
		}
		return component;
	}

	/**
	 * Finds component with the given id
	 */
	public static UIComponent findComponent(UIComponent c, String id) {
		if (id.equals(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}
	
	public static Locale getLocale() {
		return getFacesContext().getViewRoot().getLocale();
	}
	
	public static void redirect(String page) {
		try {
			getFacesContext().getExternalContext().redirect(page);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addMessageFromBundleInFlash(Map<String, Severity> msgs) {
		for(String key : msgs.keySet()) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(msgs.get(key), key);
		}
	}
	
	/**
	 * Recebe como parametro uma chave, faz a tradução da chave para a mensagem pelo 
	 * bundle e a adiciona no escopo Flash da aplicação
	 * @param severity INFO WARN ERROR FALTAL
	 * @param key Caso a key não for encontrada no arquivo de mensagens, a propria chave sera a mensagem.
	 */
	public static void addMessageFromBundleInFlash(Severity severity, String key) {
		Flash flash = getFacesContext().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		getFacesContext().addMessage(null, new FacesMessage(severity, getMessage(key), null));
	}

	private static String getMessage(String key) {
		String message = null;
		try {
			message = ResourceBundle.getBundle("messages", getLocale()).getString(key);
		} catch(MissingResourceException e) {
			message = key;
		}
		return message;
	}

}
