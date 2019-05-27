package m2i.jpa;

import javax.persistence.*;

// Une entit� est une classe persistante
@Entity
public class Personne2 {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String First_Name;
	private String Last_Name;
	
//	TECHNIQUES DE JONCTION (3 AU CHOIX) :
//	Nom de la colonne cl� �trang�re dans la table Personne2
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "col_id_login", referencedColumnName="Id_Login")
	
//	Nom de la colonne cl� primaire
	@OneToOne(mappedBy="perso", cascade = CascadeType.ALL)
    private Login login ;

	/**
	 * Constructeur 1 (vide).
	 */
	public Personne2() {
		super();
	}

	/**
	 * Constructeur 2.
	 * 
	 * @param id
	 * @param first_Name
	 * @param last_Name
	 * @param login
	 */
	public Personne2(int id, String first_Name, String last_Name, Login login) {
		super();
		Id = id;
		First_Name = first_Name;
		Last_Name = last_Name;
		this.login = login;
	}

	public int getId() {
		return Id;
	}

//	Pas de setId() : laissez le conteneur cr�er et utiliser la m�thode pour vous

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
