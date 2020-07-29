package br.com.levymoreira.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.com.levymoreira.DAO.UsuarioDAO;
import br.com.levymoreira.model.Usuario;
import br.com.levymoreira.util.FacesUtil;

@ManagedBean
@RequestScoped
public class LoginBean {

	//private static final long serialVersionUID = 1L;	
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Boolean isLogado = false;
	
	public LoginBean(){
	}
	
	public String entrar(){		
		String result = "/paginas/login/login.xhtml"; 
		if(usuarioDAO.validarLogin(usuario) != null){
			isLogado = true;
			result = "/paginas/index.xhtml";
		}else{
			isLogado = false;
			FacesUtil.addInfoMsg("Login inv‡lido!","Usu‡rio ou senha incorreto(s)!");
		}
		return result;
	}
	
	//Getters and Setters
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getIsLogado() {
		return isLogado;
	}

	public void setIsLogado(Boolean isLogado) {
		if(isLogado == false){ //se setar como nao logado retira o usuario
			usuario = new Usuario();
		}
		this.isLogado = isLogado;
	}
	
}
