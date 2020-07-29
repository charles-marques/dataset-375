package br.com.levymoreira.testes;

import java.math.BigDecimal;
import java.util.Date;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import br.com.levymoreira.DAO.ContaDAO;
import br.com.levymoreira.DAO.LocalCompraDAO;
import br.com.levymoreira.DAO.LocalPagamentoDAO;
import br.com.levymoreira.DAO.ParcelaDAO;
import br.com.levymoreira.model.Conta;
import br.com.levymoreira.model.LocalCompra;
import br.com.levymoreira.model.LocalPagamento;
import br.com.levymoreira.model.Parcela;
import br.com.levymoreira.util.HibernateUtil;

//Arrumar isso que ta uma bagunca...

public class TesteContaDAO extends TestCase{
	private static ContaDAO contaDAO;	
	private static Conta conta; 
	private static LocalPagamentoDAO lpDAO;
	private static LocalPagamento lp;
	private static LocalCompra lc;
	private static LocalCompraDAO lcDAO;
	private static ParcelaDAO parcelaDAO;
	private static Parcela parcela;
	
	public TesteContaDAO (String testName) {
	      super (testName);
   }

	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*Roda uma vez antes de se iniciarem os testes*/
		HibernateUtil hu = new HibernateUtil();
		hu.recriarTodasTebelas();
		System.out.println("Recriou as tabelas");
		
		//Salva um local de pagamento
		lpDAO = new LocalPagamentoDAO(); 
		lp = new LocalPagamento("LocalPagamentoT1");
		lpDAO.salvar(lp);		
		
		//Salva um local de compra
		lcDAO = new LocalCompraDAO(); 
		lc = new LocalCompra("LocalCompraT1");
		lcDAO.salvar(lc);	
		
		//Instancia uma conta
		contaDAO = new ContaDAO();	
		conta = new Conta("Descricaoo", new Date(), 1, BigDecimal.valueOf(60.50), lp, lc);
		
		//Instancia uma parcela
		Date dt = new Date();
		dt.setDate(dt.getDate() + 30);
		//parcela = new Parcela(dt, 1, BigDecimal.valueOf(60.50), BigDecimal.valueOf(0));
	}
	
	@Test
	public void testeSalvarConta() {		
		System.out.println("Teste 1");
		assertEquals(true, contaDAO.salvar(conta));
	}
	
	@Test
	public void testeSalvarParcela1() {		
		System.out.println("Teste 2");
	//	assertEquals(true, parcelaDAO.salvar(parcela));
	}
	


}
