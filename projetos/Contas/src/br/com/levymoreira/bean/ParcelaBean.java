package br.com.levymoreira.bean;

import java.util.ArrayList;
import br.com.levymoreira.DAO.ParcelaDAO;
import br.com.levymoreira.model.Parcela;
import br.com.levymoreira.util.FacesUtil;

/**
 * Bean responsavel por gerenciar a pagina controleParcelas.
 * 
 * @author Levy Moreira
 * @since 26/03/2012
 */
public class ParcelaBean {
	
	//Variables ----------------------------------------------------------------------
	
    private ArrayList<Parcela> parcelas;
    private Parcela parcela = new Parcela();
    private ParcelaDAO parcelaDAO = new ParcelaDAO();
    
    //Constructor Default ------------------------------------------------------------
    
    public ParcelaBean(){
    	System.out.println("ParcelaBean()");
        this.parcelas = parcelaDAO.getTodos();     
    }
    
    //Actions --------------------------------------------------------------------------
       
    public String pagar(){
    	System.out.println("pagar()");
        parcelaDAO.pagar(parcela);
    	return "/paginas/parcela/controleParcelas.xhtml";
    }
        
    //Getters and Setters ---------------------------------------------------------------
    
    public ArrayList<Parcela> getParcelas(){
    	return this.parcelas;
    }

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
    
}
