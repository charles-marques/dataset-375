package br.com.levymoreira.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import br.com.levymoreira.util.HibernateUtil;

/**
 * DAO generico que deve ser estendido pelas classes que gerenciam pojos.
 * 
 * @author Levy Moreira
 * @since 27/11/2011
 */
public abstract class DAOGenerico {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrao.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 */
	public DAOGenerico() {
	}

	/**
	 * Retorna uma sessao do hibernate, pega da classe HibernateUtil.
	 * 
	 * @author Levy Moreira
	 * @see br.com.levymoreira.util.HibernateUtil.java
	 * @since 27/11/2011
	 * @return Session 
	 *         Sessao do hibernate.
	 */
	protected Session getSession() {
		return HibernateUtil.getInstance().getSession();
	}

	/**
	 * Salva um pojo, ou realiza o update caso ele ja exista.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 * @param pojo
	 *        Objeto pojo, que implementa Serializable, a ser persistido
	 * @return boolean 
	 *         Returna true se o objeto foi salvo com sucesso
	 */
	protected boolean salvarOuAlterarPojo(Serializable pojo) {
		boolean result = false;
		Session ses = getSession();
		try {
			ses.saveOrUpdate(pojo);
			ses.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			System.out.print("Erro ao salvar!");
		} finally {
			ses.close();
		}
		return result;
	}

	/**
	 * Retorna um pojo do tipo da que foi passado no parametro
	 * <i>classToSearch</i> a pesquisa e realizada pelo id na tabela referente
	 * a classe passada neste mesmo parametro.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 * @param classe
	 *        Classe que esta chamando este metodo
	 * @param id
	 *        Id do registro a ser retornado
	 * @return <T extends Serializable> Pojo resultado da busca
	 */
	@SuppressWarnings("unchecked")
	protected <T extends Serializable> T getPojo(Class<T> classe,
			Serializable id) {
		Session ses = getSession();
		Serializable toReturn = (Serializable) ses.get(classe, id);
		ses.getTransaction().commit();
		ses.close();
		return (T) toReturn;
	}

	/**
	 * Deleta um pojo do banco de dados.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 * @param pojo
	 *        Objeto pojo, que implementa Serializable, a ser removido
	 * @return boolean
	 *         Retorna true se tiver deletado com sucesso
	 */
	protected boolean deletarPojo(Serializable pojo) {
		boolean result = false;
		Session ses = getSession();
		try {
			ses.delete(pojo);
			ses.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			ses.close();
		}
		return result;
	}

	/**
	 * Executa uma consulta ao banco de dados e retorna uma lista de registros.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 * @param classe
	 *        Classe que esta chamando este metodo
	 * @param query
	 *        SQL de consulta que sera executado
	 * @param parametros
	 *        Parametros que serao inseridos no SQL de consulta
	 * @return ArrayList<T> 
	 *         Lista de pojos resultado da consulta
	 * 
	 */
	protected <T extends Serializable> ArrayList<T> executarSQLLista(
			Class<T> classe, String query, Object... parametros) {
		Session ses = getSession();
		Query qr = ses.createQuery(query);
		for (int i = 1; i <= parametros.length; i++) {
			qr.setParameter(String.valueOf(i), parametros[i - 1]);
		}
		@SuppressWarnings("unchecked")
		ArrayList<T> toReturn = (ArrayList<T>) qr.list();

		ses.getTransaction().commit();
		ses.close();
		return toReturn;
	}

	/**
	 * Retorna um objeto (pojo) resultado da consulta da query passada por
	 * parametro.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 * @param query
	 *            SQL de consulta que sera executado
	 * @param parametros
	 *            Parametros há serem inseridos no SQL de consulta
	 * @return Serializable Pojo resultado da consulta ao banco de dados
	 */
	protected Serializable executarSQLPojo(String query, Object... parametros) {
		Session ses = getSession();
		Query qr = ses.createQuery(query);
		for (int i = 1; i <= parametros.length; i++) {
			qr.setParameter(String.valueOf(i), parametros[i - 1]);
		}
		Object toReturn = qr.uniqueResult();
		ses.getTransaction().commit();
		ses.close();
		return (Serializable) toReturn;
	}
	
    /**
     * Retorna uma instancia da class criteria
     * @param classe
     *        Classe que esta chamando este metodo
     * @return Criteria 
     *         Instancia da class criteria
     */
	protected <T> Criteria getCriteria(Class<T> classe){
    	return getSession().createCriteria(classe);
    }
	
    /**
     * Retorna todos os registros cadastrados no banco de dados
     * 
     * @author Levy Moreira
     * @since 23/12/2011
     * @param classe
     *        Classe que esta chamando este metodo
     * @return ArrayList<T>
     *         Lista com todos os registros cadastrados
     */    
	@SuppressWarnings("unchecked")
	protected <T extends Serializable> ArrayList<T> getTodos(Class<T> classe){		
        return (ArrayList<T>) getCriteria(classe).addOrder(Order.asc("id")).list();        
    }
}