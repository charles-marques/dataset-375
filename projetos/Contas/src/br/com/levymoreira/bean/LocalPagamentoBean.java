package br.com.levymoreira.bean;

import java.util.ArrayList;

import br.com.levymoreira.DAO.LocalPagamentoDAO;
import br.com.levymoreira.model.*;
import br.com.levymoreira.util.FacesUtil;

/**
 * Bean responsavel por gerenciar as paginas edicaoLocalPagamento e listaLocaisPagamento.
 * 
 * @author Levy Moreira
 * @since 21/01/2012
 */
public class LocalPagamentoBean {
	
	//Constants ----------------------------------------------------------------------
	
	private final String EDICAO = "_edicao";
	private final String INSERCAO = "_insercao";
	private final String LISTAGEM = "_listagem";
	
	//Variables ----------------------------------------------------------------------

	private String estado = LISTAGEM;	
    private ArrayList<LocalPagamento> localPagamentos;
    private LocalPagamento localPagamento = new LocalPagamento();
    private LocalPagamentoDAO localPagamentoDAO = new LocalPagamentoDAO();
    
    //Constructor Default ------------------------------------------------------------
    
    public LocalPagamentoBean(){
    	System.out.println("LocalPagamentoBean()");
        this.localPagamentos = localPagamentoDAO.getTodos(); 
        if (FacesUtil.getInRequestMap("localPagamentoToEdit") != null) {  
        	localPagamento = (LocalPagamento) FacesUtil.getInRequestMap("localPagamentoToEdit");
        }
        if (FacesUtil.getInRequestMap("estado") != null) {   
        	estado = (String) FacesUtil.getInRequestMap("estado");
        }        
    }
    
    //Actions --------------------------------------------------------------------------
    
    public String deletar(){
    	System.out.println("deletar()");
    	localPagamentoDAO.deletar(localPagamento);
    	return "listaLocaisPagamento.xhtml";
    }
    
    public String editar(){
    	System.out.println("editar()");
        FacesUtil.putInRequestMap("estado", EDICAO);
        FacesUtil.putInRequestMap("localPagamentoToEdit", localPagamento);
    	return "edicaoLocalPagamento.xhtml";
    }
    
    public String incluir(){
    	System.out.println("incluir()");    	
    	FacesUtil.putInRequestMap("estado", INSERCAO);
    	localPagamento = new LocalPagamento();
        return "edicaoLocalPagamento.xhtml";
    }
    
    public String cancelarEdicaoInclusao(){
    	System.out.println("cancelarEdicaoInclusao()");   	
    	localPagamento = null;
    	return "listaLocaisPagamento.xhtml";
    }
    
    public String salvar(){
    	System.out.println("salvar()");
    	localPagamentoDAO.salvar(localPagamento);
    	FacesUtil.addInfoMsg("Ok!", "Local de Pagamento salvo com sucesso!");
    	return "listaLocaisPagamento.xhtml";
    }    
    
    public String salvarEContinuar(){    	
    	System.out.println("salvarEContinuar()");
    	localPagamentoDAO.salvar(localPagamento);    	
    	FacesUtil.addInfoMsg("Ok!", "Local de Pagamento salvo com sucesso!");
    	return this.incluir();    	
    }  
    
    //Getters and Setters ---------------------------------------------------------------
    
    public ArrayList<LocalPagamento> getLocalPagamentos(){
    	return this.localPagamentos;
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

	public LocalPagamento getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(LocalPagamento localPagamento) {
		this.localPagamento = localPagamento;
	}
    
	
}
