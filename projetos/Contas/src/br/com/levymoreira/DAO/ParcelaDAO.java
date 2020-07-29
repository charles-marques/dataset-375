package br.com.levymoreira.DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hibernate.criterion.Restrictions;
import br.com.levymoreira.model.Conta;
import br.com.levymoreira.model.Parcela;

/**
 * Classe que realiza acessos e logicas relacionadas a banco de dados para o
 * pojo Parcela.
 * 
 * @author levy
 * @since 21/01/2012
 */
public class ParcelaDAO extends DAOGenerico {

	/**
	 * Salva uma parcela que e passada por parametro no banco de dados.
	 * 
	 * @author Levy Moreira
	 * @since 22/01/2012
	 * @param parcela
	 *            Instancia de Parcela a ser salvo
	 * @return boolean Retorna true caso a Parcela tenha sido salva com sucesso
	 */
	private boolean salvar(Parcela parcela) {
		return salvarOuAlterarPojo(parcela);
	}

	/**
	 * Deleta uma instancia de parcela, que e passada por parametro, do banco de
	 * dados.
	 * 
	 * @author Levy Moreira
	 * @since 22/01/2012
	 * @param parcela
	 *            Instancia da parcela a ser deletada
	 * @return boolean Retorna true caso o registro tenha sido deletado com
	 *         sucesso
	 */
	public boolean deletar(Parcela parcela) {
		return deletarPojo(parcela);
	}

	/**
	 * Retorna todas as parcelas cadastradas no banco de dados
	 * 
	 * @author Levy Moreira
	 * @since 22/01/2012
	 * @return ArrayList<Parcela> Lista com todas as parcelas cadastradas
	 */
	public ArrayList<Parcela> getTodos() {
		return getTodos(Parcela.class);
	}

	/**
	 * 
	 * 
	 * @author Levy Moreira
	 * @since 10/03/2012
	 * @param conta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Parcela> getParcelasPorConta(Conta conta) {
		return (ArrayList<Parcela>) getCriteria(Parcela.class).add(
				Restrictions.eq("conta", conta)).list();
	}

	/**
	 * Gera as parcelas de uma determinada conta
	 * 
	 * @author Levy Moreira
	 * @since 25/03/2012
	 * @param conta
	 *            Conta da qual as parcelas devem ser geradas
	 */
	public void gerarParcelas(Conta conta) {
		if (conta.getTotalParcelas() == 0) { // A Vista
			this.salvar(new Parcela(conta, conta.getDataCompra(), 1, conta
					.getValorTotal(), conta.getValorTotal()));
		} else { // A Prazo
		    BigDecimal valorParcela = conta.getValorTotal().setScale(2).divide(BigDecimal.valueOf(conta.getTotalParcelas()), RoundingMode.CEILING);
			
			for (int j = 1; j <= conta.getTotalParcelas(); j++) {				
				this.salvar(new Parcela(conta, incrementarMes(j, conta.getDataCompra()), j, valorParcela, new BigDecimal(0.00)));
			}
		}
	}
	
	public static Date incrementarMes(int quantidade, Date data){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
	    gc.add(Calendar.MONTH, quantidade);
	    return gc.getTime();
	}

	/**
	 * Deleta todas as parcelas de uma determinada conta
	 * 
	 * @author Levy Moreira
	 * @since 25/03/2012
	 * @param conta
	 *            Conta a qual suas parcelas devem ser deletadas
	 */
	public void deletarParcelas(Conta conta) {
		@SuppressWarnings("unchecked")
		ArrayList<Parcela> parcelas = (ArrayList<Parcela>) getCriteria(
				Parcela.class).add(Restrictions.eq("conta", conta)).list();

		if (parcelas != null) {
			for (Parcela parcela : parcelas) {
				System.out.println("lol- " + parcela.getConta().getDescricao());
				this.deletar(parcela);
			}
		}

	}
	
	public void pagar(Parcela parcela){
		parcela.setValorPago(parcela.getValorParcela());
		this.salvar(parcela);
	}

}
