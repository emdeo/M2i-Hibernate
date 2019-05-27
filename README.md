# Introduction à Hibernate et au JPA

## Table des matières
1. [Créer une unité de persistance](#créer-une-unité-de-persistance)
2. [Opération READ](#opération-read)
3. [Opération CREATE](#opération-create)

## Créer une unité de persistance

Nommer l'unité de persistance fichier XML :

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
    
Démarrer les échanges avec la BDD dans une classe Main :

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdeliumService");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin(); // Signaler le début des échanges avec la BDD
    em.getTransaction().commit(); // Signaler la fin des échanges avec la BDD

## Opération READ

Classe Main : récupérer une entrée de la table référencée dans l'unité de persistance :

    Personne2 per = em.find(Personne2.class, 101);
    System.out.println(per);
    
## Opération CREATE

### Techniques de jonction
* avec une clé étrangère
* avec une clé primaire partagée
* avec une jonction de tables
