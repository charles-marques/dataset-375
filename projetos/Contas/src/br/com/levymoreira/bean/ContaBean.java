package br.com.levymoreira.bean;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.RequestScoped;
import br.com.levymoreira.DAO.ContaDAO;
import br.com.levymoreira.DAO.LocalCompraDAO;
import br.com.levymoreira.DAO.LocalPagamentoDAO;
import br.com.levymoreira.model.*;
import br.com.levymoreira.util.FacesUtil;

/**
 * Bean responsavel por gerenciar as paginas edicaoConta e listaContas.
 * 
 * @author Levy Moreira
 * @since 12/03/2012
 */

@RequestScoped
public class ContaBean {
	
	//Constants ----------------------------------------------------------------------
	
	private final String EDICAO = "_edicao";
	private final String INSERCAO = "_insercao";
	private final String LISTAGEM = "_listagem";
	
	//Variables ----------------------------------------------------------------------

	private String estado = LISTAGEM;	
    private ArrayList<Conta> contas;
    private Conta conta = new Conta();
    private ContaDAO contaDAO = new ContaDAO();
    private ArrayList<LocalCompra> locaisCompra;
    private ArrayList<LocalPagamento> locaisPagamento;
    private LocalCompra localCompraSelecionado = new LocalCompra();
    private LocalPagamento localPagamentoSelecionado = new LocalPagamento();
    private boolean vendaAVista = true;
    
    //Constructor Default ------------------------------------------------------------
    
    public ContaBean(){
    	System.out.println("UsuarioBean()");
        this.contas = contaDAO.getTodos(); 
        if (FacesUtil.getInRequestMap("contaToEdit") != null) {  
        	conta = (Conta) FacesUtil.getInRequestMap("contaToEdit");
        	localCompraSelecionado = conta.getLocalCompra();
        	localPagamentoSelecionado = conta.getLocalPagamento();    		
        }
        if (FacesUtil.getInRequestMap("estado") != null) {   
        	estado = (String) FacesUtil.getInRequestMap("estado");
        }   
    }
    
    //Actions -------------------------------------------------------------------------
    
    public String deletar(){
    	System.out.println("deletar()");
    	contaDAO.deletar(conta);
    	return "listaContas.xhtml";
    }
    
    public String editar(){
    	System.out.println("editar()");
        FacesUtil.putInRequestMap("estado", EDICAO);
        FacesUtil.putInRequestMap("contaToEdit", conta);
    	return "edicaoConta.xhtml";
    }
    
    public String incluir(){
    	System.out.println("incluir()");    	
    	FacesUtil.putInRequestMap("estado", INSERCAO);
    	conta = new Conta();    	
    	conta.setDataCompra(new Date());
        return "/paginas/conta/edicaoConta.xhtml";
    }
    
    public String cancelarEdicaoInclusao(){
    	System.out.println("cancelarEdicaoInclusao()");   	
    	conta = null;
    	return "listaContas.xhtml";
    }
    
    private void salvar(){
    	if(this.vendaAVista == true){
			this.conta.setTotalParcelas(0);
		}
    	conta.setLocalCompra(localCompraSelecionado);
    	conta.setLocalPagamento(localPagamentoSelecionado);
    	contaDAO.salvar(conta);
    	FacesUtil.addInfoMsg("Ok!", "Conta salva com sucesso!");
    }
    
    public String salvarESair(){
    	System.out.println("salvar()");
    	this.salvar();
    	return "listaContas.xhtml";
    }    
    
    public String salvarEContinuar(){    	
    	System.out.println("salvarEContinuar()");
    	this.salvar();
    	return this.incluir();    	
    }  
    
    //Getters and Setters ---------------------------------------------------------------
    
    public ArrayList<Conta> getContas(){
    	return this.contas;
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

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public ArrayList<LocalCompra> getLocaisCompra() {
		if(locaisCompra == null){
			locaisCompra = new LocalCompraDAO().getTodos();
		}
		return locaisCompra;
	}

	public void setLocaisCompra(ArrayList<LocalCompra> locaisCompra) {
		this.locaisCompra = locaisCompra;
	}

	public LocalCompra getLocalCompraSelecionado() {
		return localCompraSelecionado;
	}

	public void setLocalCompraSelecionado(LocalCompra localCompraSelecionado) {
		this.localCompraSelecionado = localCompraSelecionado;
	}

	public LocalPagamento getLocalPagamentoSelecionado() {
		return localPagamentoSelecionado;
	}

	public void setLocalPagamentoSelecionado(
			LocalPagamento localPagamentoSelecionado) {
		this.localPagamentoSelecionado = localPagamentoSelecionado;
	}

	public ArrayList<LocalPagamento> getLocaisPagamento() {
		if(locaisPagamento == null){
			locaisPagamento = new LocalPagamentoDAO().getTodos();
		}
		return locaisPagamento;
	}

	public void setLocaisPagamento(ArrayList<LocalPagamento> locaisPagamento) {
		this.locaisPagamento = locaisPagamento;
	}

	public boolean isVendaAVista() {
		if(this.conta.getTotalParcelas() > 0){
			vendaAVista = false;
		}
		return vendaAVista;
	}

	public void setVendaAVista(boolean vendaAVista) {
		if(vendaAVista == true){
			this.conta.setTotalParcelas(0);
		}
		this.vendaAVista = vendaAVista;
	}
		    
}