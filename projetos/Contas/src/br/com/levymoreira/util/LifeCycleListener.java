package br.com.levymoreira.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import br.com.levymoreira.bean.LoginBean;

@SuppressWarnings("serial")
public class LifeCycleListener implements PhaseListener {

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    public void beforePhase(PhaseEvent event) {   
    	FacesContext faces = event.getFacesContext();
    	if(faces.getViewRoot() != null){
    	   if (!faces.getViewRoot().getViewId().equals("/paginas/login/login.xhtml")){
    		   @SuppressWarnings("deprecation") //ver uma solucao nova pra esse velho problema
    		   LoginBean bean = (LoginBean) faces.getApplication().getVariableResolver().resolveVariable(faces, "loginBean"); 
    		   if (bean.getIsLogado()){
    	        	System.out.println("logado!");
    	       }else{
    	        	System.out.println("nao logado!");
    	        	NavigationHandler navigationHandler = faces.getApplication().getNavigationHandler();  
    	            navigationHandler.handleNavigation( faces, null, "/paginas/login/login.xhtml" );   
    	            faces.renderResponse();  
    	       }
    	    }    	    
    	}    
       System.out.println("START PHASE " + event.getPhaseId());	
    }

    public void afterPhase(PhaseEvent event) {
        System.out.println("END PHASE " + event.getPhaseId());
    }

}
