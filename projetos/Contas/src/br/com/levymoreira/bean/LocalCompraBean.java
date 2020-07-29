package br.com.levymoreira.bean;

import java.util.ArrayList;
import br.com.levymoreira.DAO.LocalCompraDAO;
import br.com.levymoreira.model.LocalCompra;
import br.com.levymoreira.util.FacesUtil;

/**
 * Bean responsavel por gerenciar as paginas edicaoLocalCompra e listaLocaisCompra.
 * 
 * @author Levy Moreira
 * @since 11/03/2012
 */
public class LocalCompraBean {
	
	//Constants ----------------------------------------------------------------------
	
	private final String EDICAO = "_edicao";
	private final String INSERCAO = "_insercao";
	private final String LISTAGEM = "_listagem";
	
	//Variables ----------------------------------------------------------------------

	private String estado = LISTAGEM;	
    private ArrayList<LocalCompra> localCompras;
    private LocalCompra localCompra = new LocalCompra();
    private LocalCompraDAO localCompraDAO = new LocalCompraDAO();
    
    //Constructor Default ------------------------------------------------------------
    
    public LocalCompraBean(){
    	System.out.println("LocalCompraBean()");
        this.localCompras = localCompraDAO.getTodos(); 
        if (FacesUtil.getInRequestMap("localCompraToEdit") != null) {  
        	localCompra = (LocalCompra) FacesUtil.getInRequestMap("localCompraToEdit");
        }
        if (FacesUtil.getInRequestMap("estado") != null) {   
        	estado = (String) FacesUtil.getInRequestMap("estado");        	
        }        
        System.out.println(estado);
    }
    
    //Actions --------------------------------------------------------------------------
    
    public String deletar(){
    	System.out.println("deletar()");
    	localCompraDAO.deletar(localCompra);
    	return "listaLocaisCompra.xhtml";
    }
    
    public String editar(){
    	System.out.println("editar()");
        FacesUtil.putInRequestMap("estado", EDICAO);
        FacesUtil.putInRequestMap("localCompraToEdit", localCompra);
    	return "edicaoLocalCompra.xhtml";
    }
    
    public String incluir(){
    	System.out.println("incluir()");    	
    	FacesUtil.putInRequestMap("estado", INSERCAO);
    	System.out.println( (String) FacesUtil.getInRequestMap("estado"));
    	localCompra = new LocalCompra();
        return "edicaoLocalCompra.xhtml";
    }
    
    public String cancelarEdicaoInclusao(){
    	System.out.println("cancelarEdicaoInclusao()");   	
    	localCompra = null;
    	return "listaLocaisCompra.xhtml";
    }
    
    public String salvar(){
    	System.out.println("salvar()");
    	localCompraDAO.salvar(localCompra);
    	FacesUtil.addInfoMsg("Ok!", "Local de Compra salvo com sucesso!");
    	return "listaLocaisCompra.xhtml";
    }    
    
    public String salvarEContinuar(){    	
    	System.out.println("salvarEContinuar()");
    	localCompraDAO.salvar(localCompra);    	
    	FacesUtil.addInfoMsg("Ok!", "Local de Compra salvo com sucesso!");
    	return this.incluir();    	
    }  
    
    //Getters and Setters ---------------------------------------------------------------
    
    public ArrayList<LocalCompra> getLocalCompras(){
    	return this.localCompras;
    }
    
    public void setEstado(String estado){    	
    	this.estado = estado;
    }

	public Boolean getEstadoEdicao() {
		return estado.equals(EDICAO);
	}
	
	public Boolean getEstadoInsercao() {
		return estado.equals(INSERCAO);
	}
	
	public Boolean getEstadoListagem() {
		return estado.equals(LISTAGEM);
	}	

	public LocalCompra getLocalCompra() {
		return localCompra;
	}

	public void setLocalCompra(LocalCompra localCompra) {
		this.localCompra = localCompra;
	}
    
}
