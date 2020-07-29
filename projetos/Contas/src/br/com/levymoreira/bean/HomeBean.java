package br.com.levymoreira.bean;

import javax.faces.context.FacesContext;

import br.com.levymoreira.util.FacesUtil;

/**
 * Bean responsavel por gerenciar a pagina home.
 * 
 * @author Levy Moreira
 * @since 21/01/2012
 */
public class HomeBean {
	//Variables	
	private String paginaAtual = "/paginas/BoasVindas.xhtml";
	
	//Constructor default
	public HomeBean(){
		System.out.println("Instancia o bean"+ FacesUtil.getInRequestMap("porra"));
	}
	
	//Logout
	public String logout(){
		FacesContext faces = FacesContext.getCurrentInstance();  
		@SuppressWarnings("deprecation") //ver uma solucao nova pra esse velho problema
		LoginBean loginBean = (LoginBean) faces.getApplication().createValueBinding("#{loginBean}").getValue(faces);  
		loginBean.setIsLogado(false);
		return "/paginas/login/login.xhtml";		
	}

	//Getters and Setters
	public String getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(String paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

}
