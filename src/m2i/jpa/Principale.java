package m2i.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principale {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdeliumService");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); // Signaler le d�but des �changes avec la BDD
		
//		OPERATION READ : r�cup�rer une entr�e de la table r�f�renc�e dans l'unit� de persistance "AdeliumService"
//		Personne2 per = em.find(Personne2.class, 101);
//		System.out.println(per);
		
//		OPERATION CREATE
		Personne2 per = new Personne2();
		Login log = new Login();
		
		per.setFirst_Name("Carole");
		per.setLast_Name("Charly");
		per.setLogin(log);
		
		log.setUser("cacho@free.net");
		log.setPwd("caca");
		log.setRole("user");
		log.setPersonne(per);
		
		System.out.println(per.toString());
		
		em.persist(per);
		
		em.getTransaction().commit(); // Signaler la fin des �changes avec la BDD

	}

}
