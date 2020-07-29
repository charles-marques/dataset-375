package edu.fh.ostfalia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import edu.fh.ostfalia.domain.Project;

public class TestProjectDao {
	@Test
	public void testInsert() throws Exception {
		
		// Take a look at pom.xml and /src/test/resources/META-INF/persistence.xml
		
		// Bootstrapping.
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum");
		EntityManager entityManager = factory.createEntityManager();

		// Create and insert projects.
		Project project1 = new Project("Facebook", "Zuckerberg", "Social Network");
		Project project2 = new Project("Google", "Larry Papge", "Search engine");
		Project project3 = new Project("Twitter", "???", "Social Network");
		
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(project1);
		entityManager.persist(project2);
		entityManager.persist(project3);
		tx.commit();

		TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p", Project.class);
		List<Project> list = query.getResultList();
		for (Project p : list) {
			System.out.println(p);
		}
	}
}
