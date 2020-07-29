package br.com.levymoreira.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import br.com.levymoreira.model.*;

/**
 * Class utilizada para centralizar a existência das sessões do hibernate, ela
 * e responsavel por realizar a conexão com o banco de dados.
 * 
 * @author Levy Moreira
 * @since 27/11/2011
 */
public class HibernateUtil implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Objeto desta mesma classe, static para que existe somente um objeto em
	 * toda a aplicação.
	 */
	private static HibernateUtil hibernateUtil;

	/** Objeto responsável por gerar sesões. */
	private SessionFactory sessionFactory;

	/** Objeto resopnsável por gerar a sessionFsactory */
	private AnnotationConfiguration configuration;

	/**
	 * Construtor padrão, ele instancia a sessionFactory.
	 */
	public HibernateUtil() {
		setSession();
	}

	/**
	 * Quando chamado cria toda a estrutura de tabelas do banco com base nas
	 * classes configuradas no hibernate.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 */
	public void recriarTodasTebelas() {
		SchemaExport se = new SchemaExport(configuration);
		/*O primeiro parametro indica se deve ser impresso o sql, e o segundo
		 * se este sql deve ser executado*/
		se.create(true, true); 
	}

	/**
	 * Realiza os updates e drops nas tabelas e campos alterados, criados ou
	 * deletados.
	 * @author Levy Moreira
	 * @since 04/12/2011
	 */
	public void aplicarAlteracoes(){
		SchemaUpdate su = new SchemaUpdate(configuration);
		su.execute(true, true);
	}
	
	/**
	 * Instancia o annotationConfiguration e o sessionFactory com base nas
	 * confirugações passadas.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 */
	private void setSession() {
		if (sessionFactory == null) {
			try {
				configuration = new AnnotationConfiguration()
						/*.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
						.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
						.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/contasDB")
						.setProperty("hibernate.connection.username", "postgres")
						.setProperty("hibernate.connection.password", "root")*/
				    	.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
		       		    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
		       		    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/ContasDBMySql")
	        			.setProperty("hibernate.connection.username", "root")
		    		    .setProperty("hibernate.connection.password", "root")
						.setProperty("hibernate.show_sql", "true")
						.setProperty("hibernate.format_sql", "true")
						.setProperty("hibernate.c3p0.acquire_increment", "1")
						.setProperty("hibernate.c3p0.idle_test_period", "100")
						.setProperty("hibernate.c3p0.maxIdleTime", "300")
						.setProperty("hibernate.c3p0.max_size", "75")
						.setProperty("hibernate.c3p0.max_statements", "0") //testar velocidade
						.setProperty("hibernate.c3p0.min_size", "20")
						.setProperty("hibernate.c3p0.timeout", "180")
						.setProperty("hibernate.cache.user_query_cache", "true") //ativa o cache de querys com setCacheable(true)
						//.setProperty("hibernate.hbm2ddl.auto", "update") aplica alteracoes nas tabelas automaticamente
						.addAnnotatedClass(Usuario.class)
						.addAnnotatedClass(Conta.class)
						.addAnnotatedClass(Parcela.class)
						.addAnnotatedClass(LocalCompra.class)
						.addAnnotatedClass(LocalPagamento.class);

				sessionFactory = configuration.buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
	}

	/**
	 * Retorna uma sessão do hibernate, utilizda nos pojos que estendem esta
	 * clase.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 * @return Session Sessão do hibernate
	 */
	public Session getSession() {
		setSession();
		Session toReturn = sessionFactory.openSession();
		toReturn.beginTransaction();
		return toReturn;
	}

	/**
	 * Retorna uma instancia do HibernateUtil.
	 * 
	 * @author Levy Moreira
	 * @since 27/11/2011
	 * @return HibernateUtil Instancia do HibernateUtil
	 */
	public static HibernateUtil getInstance() {
		if (hibernateUtil == null) {
			hibernateUtil = new HibernateUtil();
		}
		return hibernateUtil;
	}
}
