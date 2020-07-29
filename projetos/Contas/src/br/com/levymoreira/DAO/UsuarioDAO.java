package br.com.levymoreira.DAO;

import br.com.levymoreira.model.Usuario;
import java.util.ArrayList;
import org.hibernate.criterion.Restrictions;

/**
 * Classe que realiza acessos e logicas relacionadas a banco de dados para o pojo
 * usuario.
 * 
 * @author levy
 * @since 27/11/2011
 */
public class UsuarioDAO extends DAOGenerico {
	
    /**
     * Salva um usuario que e passado por parametro no banco de dados.
     * 
     * @author Levy Moreira
     * @since 27/11/2011
     * @param usuario 
     *        Instancia do usuario a ser salvo 
     * @return boolean
     *         Retorna true caso o usuario tenha sido salva com sucesso
     */
    public boolean salvar(Usuario usuario) {
         return salvarOuAlterarPojo(usuario);
    }

    /**
     * Deleta uma instancia de usuario, que e passada por parametro, do banco de dados.
     * 
     * @author Levy Moreira
     * @since 04/12/2011
     * @param usuario 
     *        Instancia do usuario a ser deletado
     * @return boolean
     *         Retorna true caso o registro tenha sido deletado com sucesso       
     */
    public boolean deletar(Usuario usuario) {
        return deletarPojo(usuario);
    }

    /** 
     * Retorna uma instancia de usuario referente ao id passado por parametro.
     * 
     * @author Levy Moreira
     * @since 04/12/2011
     * @param id
     *        Id do usuario a ser retornado
     * @return Usuario
     *         Usuario que possui o id passado por parametro
     */
    public Usuario getUsuarioPorId(int id) {
        return getPojo(Usuario.class, id);
    }

    /**
     * Executa um determinado SQL passado por parametro e retorna uma lista 
     * com os registros retornados.
     * 
     * @author Levy Moreira
     * @since 04/12/2011
     * @param query
     *        SQL a ser executado
     * @param parametros
     *        Parametros a serem passados para o SQL
     * @return ArrayList<Usuario>
     *         Lista de usuários retornada do SQL       
     */
    public ArrayList<Usuario> executarSQLLista(String query, Object... parametros) {
        return executarSQLLista(Usuario.class, query, parametros);
    }

    /**
     * Executa um determinado SQL passado por parametro e retorna uma instancia
     * de usuario com o retorno do SQL.
     * 
     * @author Levy Moreira
     * @since 04/12/2011
     * @param query
     *        SQL a ser executado
     * @param parametros
     *        Parametros a serem passados para o SQL
     * @return Usuario
     *         Instancia de Usuario retornado pelo SQL
     */
    public Usuario executarSQLUsario(String query, Object... parametros) {
        return (Usuario) executarSQLPojo(query, parametros);    
    }
    
    /**
     * Retorna todos os usuarios cadastrados no banco de dados
     * 
     * @author Levy Moreira
     * @since 04/12/2011
     * @return ArrayList<Usuario> 
     *         Lista com todos os usuarios cadastrados
     */    
	public ArrayList<Usuario> getTodos(){		
        return getTodos(Usuario.class);       
    }
    
    /**
     * Valida o login de um usuario ao sistema
     * 
     * @param usuario 
     *        Usuario com nome e senha a serem validados
     * @return Usuario
     *         Retorna um usuario caso algum exista com o nome e 
     *         senha passados ou null caso nao exista
     */
    public Usuario validarLogin(Usuario usuario){       	
    	//getSession().createQuery("from Usuario where usuario = "+usuario.getNome()+" and senha = " + usuario.getSenha());
    	return (Usuario) getCriteria(Usuario.class).add(Restrictions.and(Restrictions.eq("nome", usuario.getNome()), Restrictions.eq("senha",usuario.getSenha()))).uniqueResult();
       
    }
   
}
