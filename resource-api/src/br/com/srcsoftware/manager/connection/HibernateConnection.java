package br.com.srcsoftware.manager.connection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.utilidades.Utilidades;

/**
 * 
 * Componente.
 * Classe criada a fim de se definir um componente de Conectividade dos nossos Softwares.
 * 
 * Este componente será responsável por gerenciar as Sessoes, Transações, Commits e Rollbacks.
 * Ele possuirá todos os metodos Basicos de Persistencia e de consulta.
 *
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 6 de ago de 2018 22:20:51
 * @version 1.0
 */
public final class HibernateConnection{

	private StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private Session currentSession;
	private Transaction transaction;

	public void destroy() {
		if ( registry != null ) {
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	private SessionFactory getSessionfactory() {
		/** usando o singleton */

		if ( sessionFactory == null ) {
			try {

				/**
				 * Criando um registry builder
				 * Usado para definir os property do hibernate.cfg.xml aqui no java.
				 * Ex: registryBuilder.applySetting( Environment.DRIVER, "com.mysql.jdbc.Driver" );
				 */
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				registry = registryBuilder.configure( "hibernate.cfg.xml" ).build();

				/**
				 * Criando o MetadataSource
				 * Usado para informar os mapeamentos dos POS caso queira fazer pelo Java.
				 * Ex: sources.addAnnotatedClass( AbstractPO.class );
				 */
				MetadataSources sources = new MetadataSources( registry );

				MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

				/** Especificando o SCHEMA padrão que será utilizado em todos os POs */
				metadataBuilder.applyImplicitSchemaName( Utilidades.SCHEMA );

				Metadata metadata = metadataBuilder.build();

				/** Criando Session Factory */

				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch ( Throwable e ) {
				e.printStackTrace();
				destroy();
			}
		}

		return sessionFactory;

	}

	public Session getCurrentSession() throws BackendException {

		if ( currentSession == null ) {
			throw new BackendException( "A sessão não está aberta" );
		}
		return currentSession;
	}

	public void setCurrentSession( Session currentSession ) {
		this.currentSession = currentSession;
	}

	private void abrirSessao() {

		setCurrentSession( getSessionfactory().openSession() );
	}

	private void fecharSessao() throws BackendException {
		getCurrentSession().close();
		setCurrentSession( null );
	}

	public void iniciarTransicao() throws BackendException {

		abrirSessao();
		transaction = getCurrentSession().beginTransaction();

	}

	public void commitTransacao() throws BackendException {

		if ( transaction == null ) {
			throw new BackendException( "A Transação não está aberta" );
		}

		transaction.commit();
		fecharSessao();
		transaction = null;

	}

	public void rollBackTransacao() throws BackendException {
		if ( transaction == null ) {
			throw new BackendException( "A Transação não está aberta" );
		}

		transaction.rollback();
		fecharSessao();
		transaction = null;
	}

	public AbsctractPO inserir( AbsctractPO absctractPO ) throws BackendException {

		try {

			return (AbsctractPO) getCurrentSession().merge( absctractPO );

		} catch ( javax.persistence.PersistenceException e ) {
			throw new BackendException( "Registro Duplicado" );
		}

		catch ( Throwable e ) {
			throw new BackendException( "Erro ao Inserir", e );
		}

	}

	public void Alterar( AbsctractPO absctractPO ) throws BackendException {
		try {

			getCurrentSession().merge( absctractPO );

		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao Alterar", e );
		}
	}

	public void Excluir( AbsctractPO absctractPO ) throws BackendException {
		try {

			getCurrentSession().delete( absctractPO );

		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao Excluir", e );
		}
	}

	public AbsctractPO filtrarPorId( Long id, Class clazz ) throws BackendException {
		try {

			iniciarTransicao();

			/** Utilizando a criteriaBuilder para a confecção de QUERIES */
			// Criando a Criteria Builder com base na CriteriaBuilder

			CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( clazz );

			// Definindo o From

			Root root = criteria.from( clazz );

			//Deixando a Criteria Preparada para a consulta

			criteria.select( root );
			// definindo os Predicates

			Predicate idParam = builder.equal( root.get( "id" ), id );

			// Adicionando o Predicate no where
			criteria.where( idParam );

			Object encontrado = getCurrentSession().createQuery( criteria ).getSingleResult();

			commitTransacao();

			return (AbsctractPO) encontrado;
		} catch ( javax.persistence.NoResultException e ) {
			return null;
		}

		catch ( Throwable e ) {
			throw new BackendException( "Erro ao Filtrar por ID", e );
		}

	}

}
