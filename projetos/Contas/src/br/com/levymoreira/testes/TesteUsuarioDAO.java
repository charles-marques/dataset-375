package br.com.levymoreira.testes;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import br.com.levymoreira.DAO.UsuarioDAO;
import br.com.levymoreira.model.Usuario;
import br.com.levymoreira.util.HibernateUtil;

public class TesteUsuarioDAO {
	private static UsuarioDAO usuarioDAO;	
	private static Usuario usuario; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*Roda uma vez antes de se iniciarem os testes*/
		HibernateUtil hu = new HibernateUtil();
		hu.recriarTodasTebelas();
		System.out.println("Recriou as tabelas");
		usuarioDAO = new UsuarioDAO();	
		usuario = new Usuario("UsuarioParaTeste", "123");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		/*Roda uma vez apos serem executados todos os testes unitarios*/
	}

	@Before
	public void setUp() throws Exception {
		/*Roda antes de cada teste unitario*/
	}

	@After
	public void tearDown() throws Exception {
		/*Roda apos cada teste unitario*/
	}
	
	@Test
	public void testeSalvarUsuario() {				
		assertEquals(true, usuarioDAO.salvar(usuario));
	}

	@Test
	public void testeValidarLoginUsuario() {		
		assertEquals(true, usuarioDAO.validarLogin(usuario) != null);
	}
	
	@Test
	public void testeAlterarUsuario(){
		usuario.setNome("UsuarioTestePara");
		assertEquals(true, usuarioDAO.salvar(usuario));
	}
	
	@Test
	public void testeListarTodos(){
		boolean achou = false;
		for(Usuario usuarioLoop : usuarioDAO.getTodos() ){
			if(usuarioLoop.getNome().equalsIgnoreCase("UsuarioTestePara")){
				achou = true;
			}
		}
		assertEquals(true, achou);
	}
	
	@Test
	public void testeDeletarUsuario() {		
		assertEquals(true, usuarioDAO.deletar(usuario));
	}
}
