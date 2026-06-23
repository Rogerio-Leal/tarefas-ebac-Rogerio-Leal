package br.com.rleal.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.rleal.domain.Acessorio;

public class AcessorioDAO implements IAcessorioDAO {

	@Override
	public Acessorio cadastrar(Acessorio acessorio) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("mod33");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(acessorio);
		entityManager.getTransaction().commit();
		 
		entityManager.close();
		entityManagerFactory.close();
		
		
		return acessorio;
	}
}
