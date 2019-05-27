# Introduction à Hibernate et au JPA

## Table des matières
1. [Créer une unité de persistance](#créer-une-unité-de-persistance)
2. [Opération READ](#opération-read)
3. [Opération CREATE](#opération-create)

## Créer une unité de persistance

Fichier XML - nommer l'unité de persistance :

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
    em.getTransaction().commit(); // Signaler la fin des échanges avec la BDD

## Opération READ

Classe Main - récupérer une entrée de la table référencée dans l'unité de persistance :

    Personne per = em.find(Personne.class, 101);
    System.out.println(per);
    
## Opération CREATE

Classe Personne :

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

### Techniques de jonction
#### Avec une clé étrangère :

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "col_id_login", referencedColumnName="Id_Login")
        
#### Avec une clé primaire partagée :

        @OneToOne(mappedBy="perso", cascade = CascadeType.ALL)

#### Avec une jonction de tables :



Classe Login :

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
  
### Techniques de jonction
#### Relation 1 à 1 avec une clé étrangère :

        @OneToOne(mappedBy = "login")
        
#### Relation 1 à 1 avec une clé primaire partagée :

        @OneToOne @MapsId

#### Avec une jonction de tables :
