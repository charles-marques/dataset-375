/*
 * $Id: WindowViewNode.java,v 1.5 2007/02/06 00:46:03 laddi Exp $
 * Created on 4.10.2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.faces.viewnode;

import java.util.logging.Logger;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import com.idega.core.view.ComponentClassViewNode;
import com.idega.core.view.ViewNode;
import com.idega.faces.WindowViewHandler;
import com.idega.idegaweb.IWMainApplication;
import com.idega.presentation.IWContext;
import com.idega.repository.data.RefactorClassRegistry;
import com.idega.util.RequestUtil;
import com.idega.util.StringHandler;


/**
 * Last modified: $Date: 2007/02/06 00:46:03 $ by $Author: laddi $
 * 
 * @author <a href="mailto:tryggvil@idega.com">tryggvil</a>
 * @version $Revision: 1.5 $
 */
public class WindowViewNode extends ComponentClassViewNode {

	private static Logger log = Logger.getLogger(WindowViewNode.class.getName());
	
	private ViewHandler windowViewHandler;
	/**
	 * @param viewId
	 * @param parent
	 */
	public WindowViewNode(String viewId, ViewNode parent) {
		super(viewId, parent);
		initialize();
	}

	/**
	 * @param iwma
	 */
	public WindowViewNode(IWMainApplication iwma) {
		super(iwma);	
		initialize();
	}
	
	private void initialize(){
		this.setComponentBased(true);
		/*try {
			this.setComponentClass(Class.forName("com.idega.webface.workspace.WorkspaceLoginPage"));
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/* (non-Javadoc)
	 * @see javax.faces.application.ViewHandler#createView(javax.faces.context.FacesContext, java.lang.String)
	 */
	public UIComponent createComponent(FacesContext ctx) {
		
		String viewId = ctx.getExternalContext().getRequestPathInfo();

		//UIComponent component = (UIComponent) Class.forName(realClassName).newInstance();
		//UIViewRoot ret = new UIViewRoot();
		
		UIComponent component = null;

		try {
			if(isFrameRequest(ctx)){
				IWContext iwc = IWContext.getIWContext(ctx);
				com.idega.presentation.Page frame = com.idega.presentation.Page.loadPage(iwc);
				/*String frameId = (String)*/ ctx.getExternalContext().getRequestParameterMap().get(com.idega.presentation.Page.IW_FRAMESET_PAGE_PARAMETER);
				//String newViewId = viewId+frameId;
				//ret.setViewId(newViewId);
				
				component=frame;
				
				//Page page = new PageWrapper(frame);
				//page.init(ctx,ret);
			}
			else{
				//ret.setViewId(viewId);
				Class descriptorClazz = null;
				try {
					descriptorClazz = getDescriptorClassNameForViewId(viewId);
				} catch (ClassNotFoundException e) {
					try {
						HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
						String referer = RequestUtil.getReferer(req);
						System.err.println("[WindowViewHandler] Referer = "+referer);
					} catch (Exception ex) {
						System.err.println("[WindowViewHandler] Failed getting referer ("+ex.getMessage()+")");
					}
					throw e;
				}
				if(descriptorClazz == null) { 
					// JSP page....
				} else {
					//if(Page.class.isAssignableFrom(descriptorClazz)) {
					//	Page page = (Page) descriptorClazz.newInstance();
					//	page.init(ctx,ret);
					//} else {
					//	Page page = new PageWrapper((UIComponent)descriptorClazz.newInstance());
					//	page.init(ctx,ret);
					//}
					component = (UIComponent)descriptorClazz.newInstance();
				}
			}
		} catch(IllegalAccessException e) {
			//throw new SmileException("Please make sure that the default constructor for descriptor class of <" + viewId + "> is public.",e);
			throw new RuntimeException("Please make sure that the default constructor for descriptor class of <" + viewId + "> is public.",e);
		} catch(InstantiationException e) {
			//throw new SmileException("An exception was generated by the default constructor of the descriptor class of <" + viewId + ">.",e);
			throw new RuntimeException("An exception was generated by the default constructor of the descriptor class of <" + viewId + ">.",e);
		} catch(Throwable t) {
			//throw new SmileException("Descriptor Class for '" + viewId + "' threw an exception during initialize() !",t);
			//throw new RuntimeException("Descriptor Class for '" + viewId + "' threw an exception during initialize() !",t);
			
			t.printStackTrace();
			
			//Page page;
			try {
				component = (UIComponent) getDefaultPageClass().newInstance();
				
				//page = new PageWrapper((UIComponent) getDefaultPageClass().newInstance());
				//page.init(ctx,ret);
			} catch (InstantiationException e1) {
				log.warning(e1.getMessage());
			} catch (IllegalAccessException e1) {
				log.warning(e1.getMessage());
			} catch (ClassNotFoundException e1) {
				log.warning(e1.getMessage());
			}
		
		}

		//set the locale
		//ret.setLocale(calculateLocale(ctx));


		//return ret;
		
		return component;
	}

	private Class getDescriptorClassNameForViewId(String viewId) throws ClassNotFoundException{
		String encryptedClassName = null;
		//if(viewId.startsWith("/window")){
		//	encryptedClassName = viewId.substring(11,viewId.length());
		//}
		//else{
			String[] urlArray= StringHandler.breakDownURL(viewId);
			if(urlArray == null || urlArray.length<1){
				//encryptedClassName = "6975";
				Class defaultClass = RefactorClassRegistry.forName("com.idega.workspace.WorkspaceLoginPage");
				encryptedClassName = IWMainApplication.getEncryptedClassName(defaultClass);
			}
			else if(urlArray.length==1){
				encryptedClassName = urlArray[0];
			}
			else if(urlArray.length==2){
				encryptedClassName = urlArray[1];
			}
			
			//String encryptedClassName=urlArray[1];
		//}
		String realClassName = IWMainApplication.decryptClassName(encryptedClassName);
		
		if (realClassName != null && !realClassName.equals(encryptedClassName)) {
			return RefactorClassRegistry.forName(realClassName);
		}
		return null;
	}

	/**
	 * @param ctx
	 * @return
	 */
	private boolean isFrameRequest(FacesContext ctx) {
		String value = (String)ctx.getExternalContext().getRequestParameterMap().get(com.idega.presentation.Page.IW_FRAMESET_PAGE_PARAMETER);
		if(value!=null){
			return true;
		}
		return false;
	}
	
	public Class getDefaultPageClass() throws ClassNotFoundException{
		return RefactorClassRegistry.forName("com.idega.workspace.WorkspaceLoginPage");
		//return defaultPageClass;
	}
	
	public ViewHandler getViewHandler() {
		if(this.windowViewHandler==null){
			setViewHandler(new WindowViewHandler(this));
		}
		return this.windowViewHandler;
	}
	/* (non-Javadoc)
	 * @see com.idega.faces.view.DefaultViewNode#setViewHandler(javax.faces.application.ViewHandler)
	 */
	public void setViewHandler(ViewHandler viewHandler) {
		this.windowViewHandler=viewHandler;
	}
	
}
