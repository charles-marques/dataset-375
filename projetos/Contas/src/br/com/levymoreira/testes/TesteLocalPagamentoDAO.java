package br.com.levymoreira.testes;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import br.com.levymoreira.DAO.LocalPagamentoDAO;
import br.com.levymoreira.model.LocalPagamento;

public class TesteLocalPagamentoDAO {
	private static LocalPagamentoDAO localPagamentoDAO;	
	private static LocalPagamento localPagamento; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*Roda uma vez antes de se iniciarem os testes*/
		localPagamentoDAO = new LocalPagamentoDAO();	
		localPagamento = new LocalPagamento("LocalPagamentoParaTeste");
	}
	
	@Test
	public void testeSalvarLocalPagamento() {	
		System.out.println("1");
		assertEquals(true, localPagamentoDAO.salvar(localPagamento));
	}
	
	@Test
	public void testeAlterarLocalPagamento(){
		System.out.println("2");
		localPagamento.setDescricao("LocalPagamentoTeste");
		assertEquals(true, localPagamentoDAO.salvar(localPagamento));
	}
	
	@Test
	public void testeListarTodos(){
		System.out.println("3");
		boolean achou = false;
		for(LocalPagamento localPagamentoLoop : localPagamentoDAO.getTodos() ){
			System.out.println(localPagamentoLoop.getDescricao());
			if(localPagamentoLoop.getDescricao().equalsIgnoreCase("LocalPagamentoTeste")){
				achou = true;
			}
		}
		assertEquals(true, achou);
	}
	
	@Test
	public void testeDeletarLocalPagamento() {	
		System.out.println("4");
		assertEquals(true, localPagamentoDAO.deletar(localPagamento));
	}
}
