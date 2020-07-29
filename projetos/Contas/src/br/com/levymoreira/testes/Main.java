package br.com.levymoreira.testes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import br.com.levymoreira.DAO.ContaDAO;
import br.com.levymoreira.DAO.LocalCompraDAO;
import br.com.levymoreira.DAO.LocalPagamentoDAO;
import br.com.levymoreira.DAO.ParcelaDAO;
import br.com.levymoreira.DAO.UsuarioDAO;
import br.com.levymoreira.model.Conta;
import br.com.levymoreira.util.HibernateUtil;

public class Main {

	public static ContaDAO contaDAO = new ContaDAO();
	public static LocalCompraDAO localCompraDAO = new LocalCompraDAO();
	public static LocalPagamentoDAO localPagamentoDAO = new LocalPagamentoDAO();
	public static ParcelaDAO parcelaDAO = new ParcelaDAO();
	public static UsuarioDAO usuarioDAO = new UsuarioDAO();
	public static Scanner sc = new Scanner(System.in);

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
	
	   ContaDAO cd = new ContaDAO();
		Conta c = cd.getContaPorId(1);
		
		ParcelaDAO pd = new ParcelaDAO();
		
		System.out.println("Iniciou...");
		
			pd.gerarParcelas(c);
	
		System.out.println("Finalizando...");

	}
	
	public static Date incrementarMes(int quantidade){
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, quantidade);
	    return c.getTime();
	}
	
	public static Date incrementarMes2(int quantidade, Date data){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
	    gc.add(Calendar.MONTH, quantidade);
	    return gc.getTime();
	}
	
	public static void setValorParcelas(BigDecimal a1, BigDecimal a2){
		a1 = new BigDecimal(4.0);
		a2 = new BigDecimal(4.0);
	}



}
