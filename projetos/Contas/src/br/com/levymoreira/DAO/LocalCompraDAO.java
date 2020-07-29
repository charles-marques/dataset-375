package br.com.levymoreira.DAO;

import java.util.ArrayList;
import br.com.levymoreira.model.LocalCompra;

/**
 * Classe que realiza acessos e logicas relacionadas a banco de dados para o
 * pojo LocalCompra.
 * 
 * @author levy
 * @since 21/01/2012
 */
public class LocalCompraDAO extends DAOGenerico {

	/**
	 * Salva uma instancia de LocalCompra, que e passada por parametro, no
	 * banco de dados.
	 * 
	 * @author Levy Moreira
	 * @since 22/01/2012
	 * @param localCompra
	 *        Instancia de local de compra a ser salvo
	 * @return boolean 
	 *         Retorna true caso o registro tenha sido salvo com sucesso
	 */
	public boolean salvar(LocalCompra localCompra) {
		return salvarOuAlterarPojo(localCompra);
	}

	/**
     * Deleta um local de compra que e passado por parametro do banco de dados.
     * 
     * @author Levy Moreira
     * @since 22/01/2012
     * @param localCompra 
     *        Instancia do pojo LocalCompra a ser deletada
     * @return boolean
     *         Retorna true caso o registro tenha sido deletado com sucesso       
     */
	public boolean deletar(LocalCompra localCompra) {
		return deletarPojo(localCompra);
	}

	 /**
     * Retorna todos os locais de compra cadastrados no banco de dados
     * 
     * @author Levy Moreira
     * @since 22/01/2012
     * @return ArrayList<LocalCompra> 
     *         Lista com todos os locais de compra cadastrados
     */
	public ArrayList<LocalCompra> getTodos() {
		return getTodos(LocalCompra.class);
	}
}
