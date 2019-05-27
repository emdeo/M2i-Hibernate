package m2i.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principale {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdeliumService");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); // Signaler le début des échanges avec la BDD
		
//		OPERATION READ : récupérer une entrée de la table stockée à "jdbc:mysql://localhost:3306/jpa_formation?serverTimezone=UTC"
//		Personne2 per = em.find(Personne2.class, 101);
//		System.out.println(per);
		
//		OPERATION CREATE
		Personne2 per = new Personne2();
		Login log = new Login();
		
		per.setFirst_Name("Bernard");
		per.setLast_Name("Bravo");
		per.setLogin(log);
		
		log.setUser("babar@free.net");
		log.setPwd("babibu");
		log.setRole("user");
		log.setPersonne(per);
		
		System.out.println(per.toString());
		
		em.persist(per);
		
		em.getTransaction().commit(); // Signaler la fin des échanges avec la BDD

	}

}
