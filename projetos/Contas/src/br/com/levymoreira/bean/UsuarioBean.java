package br.com.levymoreira.bean;

import java.util.ArrayList;
import javax.faces.bean.RequestScoped;
import br.com.levymoreira.DAO.UsuarioDAO;
import br.com.levymoreira.model.*;
import br.com.levymoreira.util.FacesUtil;

/**
 * Comentar...
 * @author Levy
 *
 */

@RequestScoped
public class UsuarioBean {
	
	//Constants ----------------------------------------------------------------------
	
	private final String EDICAO = "_edicao";
	private final String INSERCAO = "_insercao";
	private final String LISTAGEM = "_listagem";
	
	//Variables ----------------------------------------------------------------------

	private String estado = LISTAGEM;	
    private ArrayList<Usuario> usuarios;
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    //Constructor Default ------------------------------------------------------------
    
    public UsuarioBean(){
    	System.out.println("UsuarioBean()");
        this.usuarios = usuarioDAO.getTodos(); 
        if (FacesUtil.getInRequestMap("usuarioToEdit") != null) {  
        	usuario = (Usuario) FacesUtil.getInRequestMap("usuarioToEdit");
        }
        if (FacesUtil.getInRequestMap("estado") != null) {   
        	estado = (String) FacesUtil.getInRequestMap("estado");
        }        
    }
    
    //Actions -------------------------------------------------------------------------
    
    public String deletar(){
    	System.out.println("deletar()");
    	usuarioDAO.deletar(usuario);
    	return "listaUsuarios.xhtml";
    }
    
    public String editar(){
    	System.out.println("editar()");
        FacesUtil.putInRequestMap("estado", EDICAO);
        FacesUtil.putInRequestMap("usuarioToEdit", usuario);
    	return "edicaoUsuario.xhtml";
    }
    
    public String incluir(){
    	System.out.println("incluir()");    	
    	FacesUtil.putInRequestMap("estado", INSERCAO);
    	usuario = new Usuario();
        return "/paginas/usuario/edicaoUsuario.xhtml";
    }
    
    public String cancelarEdicaoInclusao(){
    	System.out.println("cancelarEdicaoInclusao()");   	
    	usuario = null;
    	return "listaUsuarios.xhtml";
    }
    
    public String salvar(){
    	System.out.println("salvar()");
    	usuarioDAO.salvar(usuario);
    	FacesUtil.addInfoMsg("Ok!", "Usu‡rio salvo com sucesso!");
    	return "listaUsuarios.xhtml";
    }    
    
    public String salvarEContinuar(){    	
    	System.out.println("salvarEContinuar()");
    	usuarioDAO.salvar(usuario);    	
    	FacesUtil.addInfoMsg("Ok!", "Usu‡rio salvo com sucesso!");
    	return this.incluir();    	
    }  
    
    //Getters and Setters ---------------------------------------------------------------
    
    public ArrayList<Usuario> getUsuarios(){
    	return this.usuarios;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}
