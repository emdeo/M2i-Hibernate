# Introduction à Hibernate et à la JPA

**Hibernate** est un framework gérant la persistance des objets dans des BDD relationnelles.

La **Java Persistence API** est une interface de programmation Java permettant d'organiser des données relationnelles.

Consultez la <a href="https://github.com/emdeo/M2i-Hibernate/wiki/Concepts-JPA-et-Hibernate">**page wiki**</a> pour plus de détails.

## Table des matières
1. [Créer une unité de persistance](#créer-une-unité-de-persistance)
2. [Opération READ](#opération-read)
3. [Opération CREATE](#opération-create)

## Créer une unité de persistance

Fichier XML - configurer l'unité de persistance :

    <?xml version="1.0" encoding="UTF-8"?>
    <persistence version="2.1"
        xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">

        <!-- Nom de l'unité de persistance -->
        <persistence-unit name="AdeliumService" transaction-type="RESOURCE_LOCAL">

            <class>m2i.jpa.Personne2</class>
            <properties>
                <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
                <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/jpa_formation?serverTimezone=UTC" />
                <property name="javax.persistence.jdbc.user" value="root" />
                <property name="javax.persistence.jdbc.password" value="" />
                <property name="hibernate.show_sql" value="true" />
                <property name="hibernate.hbm2ddl.auto" value="create"/>
            </properties>

        </persistence-unit>

    </persistence>
    
Classe Main - démarrer les échanges avec la BDD :

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdeliumService");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin(); // Signaler le début des échanges avec la BDD
    
    /* Code ici */
    
    em.getTransaction().commit(); // Signaler la fin des échanges avec la BDD

## Opération READ

On l'utilise avec la méthode **.find()**

Classe Main - récupérer une entrée de la table référencée dans l'unité de persistance :

    Personne per = em.find(Personne.class, 101);
    
## Opération CREATE

On l'utilise avec la méthode **.persist()**

### Classe Main

    Personne per = new Personne();
    Login log = new Login();
    
    per.setFirst_Name("Carole");
    per.setLast_Name("Charly");
    per.setLogin(log);
    
    log.setUser("cacho@free.net");
    log.setPwd("caca");
    log.setRole("user");
    log.setPersonne(per);
    
    em.persist(per);

### Classe Personne

    import javax.persistence.*;
    
    // Une entité est une classe persistante
    @Entity
    public class Personne {
    
	    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	    private int Id;
	    private String First_Name;
	    private String Last_Name;

	    // JONCTION DES TABLES (3 techniques au choix, voir ci-dessous)
	    private Login login ;

	    // Constructeur VIDE
	    public Personne() {
		    super();
	    }

	    // Getters & setters
    }

Jonction avec une clé étrangère :

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "col_id_login", referencedColumnName="Id_Login")
        
Jonction avec une clé primaire partagée :

    @OneToOne(mappedBy="perso", cascade = CascadeType.ALL)

Jonction de tables :

    A venir

### Classe Login


    import javax.persistence.*;
    
    // Une entité est une classe persistante
    @Entity
    public class Login {
    
	    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	    private int Id_Login; // clé primaire, jonction avec la table Personne2
	    private String User;
	    private String Pwd;
	    private String Role;

	    // JONCTION DES TABLES (3 techniques au choix, voir ci-dessous)
	    private Personne perso;

	    public Login() {
	    	super();
	    }
    
    // Getters & setters
    
    }
  
Relation 1 à 1 avec une clé étrangère :

    @OneToOne(mappedBy = "login")
        
Relation 1 à 1 avec une clé primaire partagée :

    @OneToOne @MapsId

Jonction de tables :

    A venir
