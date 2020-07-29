package br.com.levymoreira.DAO;

import java.util.ArrayList;
import br.com.levymoreira.model.Conta;
import br.com.levymoreira.DAO.ParcelaDAO;

/**
 * Classe que realiza acessos e logicas relacionadas a banco de dados para o
 * pojo Conta.
 * 
 * @author levy
 * @since 21/01/2012
 */
public class ContaDAO extends DAOGenerico {

	/**
	 * Salva uma instancia de Conta, que e passada por parametro, no banco de
	 * dados.
	 * 
	 * @author Levy Moreira
	 * @since 22/01/2012
	 * @param conta
	 *            Instancia de uma conta a ser salva
	 * @return boolean Retorna true caso o registro tenha sido salvo com sucesso
	 */
	public boolean salvar(Conta conta) {
		boolean insercao = conta.getId() == null;
		boolean result = salvarOuAlterarPojo(conta);
		if (insercao && result) { // se salvou com sucesso e for insercao(nova
									// conta) gera as parcelas
			ParcelaDAO parcelaDAO = new ParcelaDAO();
			parcelaDAO.gerarParcelas(conta);
		}
		return result;
	}

	/**
	 * Deleta um local de pagamento que e passado por parametro do banco de
	 * dados.
	 * 
	 * @author Levy Moreira
	 * @since 22/01/2012
	 * @param conta
	 *            Instancia do pojo Conta a ser deletada
	 * @return boolean Retorna true caso o registro tenha sido deletado com
	 *         sucesso
	 */
	public boolean deletar(Conta conta) {
		// Deletando as parcelas
		ParcelaDAO parcelaDAO = new ParcelaDAO();
		parcelaDAO.deletarParcelas(conta);

		return deletarPojo(conta);
	}

	/**
	 * Retorna todas as contas cadastrados no banco de dados
	 * 
	 * @author Levy Moreira
	 * @since 22/01/2012
	 * @return ArrayList<Conta> Lista com todas as contas cadastradas
	 */
	public ArrayList<Conta> getTodos() {
		return getTodos(Conta.class);
	}

	/**
	 * 
	 * 
	 * @author Levy Moreira
	 * @since 10/03/2012
	 * @param id
	 * @return
	 */
	public Conta getContaPorId(int id) {
		return (Conta) getPojo(Conta.class, id);
	}
}
