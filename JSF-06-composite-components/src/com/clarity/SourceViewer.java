package com.clarity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean()
@ApplicationScoped()

public class SourceViewer {
  private static final String SHOW_SOURCE_VIEW_ID = "/views/showSource";

  public String showSource() {
	FacesContext fc = FacesContext.getCurrentInstance();
	String viewId = fc.getViewRoot().getViewId();
	HttpServletRequest request = 
	  (HttpServletRequest)fc.getExternalContext().getRequest();
	
	request.setAttribute("file", viewId);
	
	HttpSession session = (HttpSession)fc.getExternalContext().getSession(true);
	session.setAttribute("returnUrl", viewId);
	
    return SHOW_SOURCE_VIEW_ID;
  }
  
  public String exitShowSource() {
	 return ((HttpSession)FacesContext.getCurrentInstance()
			 .getExternalContext().getSession(true))
			 .getAttribute("returnUrl").toString();
  }
}

