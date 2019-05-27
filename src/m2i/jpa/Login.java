package m2i.jpa;

import javax.persistence.*;

// Une entité est une classe persistante
@Entity
public class Login {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int Id_Login; // clé primaire, jonction avec la table Personne2
	private String User;
	private String Pwd;
	private String Role;
	
//	TECHNIQUES DE JONCTION (3 AU CHOIX) :
//	@OneToOne(mappedBy = "login")	// Relation 1 à 1 avec une clé étrangère
	@OneToOne @MapsId				// Relation 1 à 1 avec  une clé primaire partagée
	private Personne2 perso;
	
	public Login() {
		super();
	}

	public Login(int id_Login, String user, String pwd, String role, Personne2 personne) {
		super();
		Id_Login = id_Login;
		User = user;
		Pwd = pwd;
		Role = role;
		this.perso = personne;
	}

	public int getId_Login() {
		return Id_Login;
	}

	public void setId_Login(int id_Login) {
		Id_Login = id_Login;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public Personne2 getPersonne() {
		return perso;
	}

	public void setPersonne(Personne2 personne) {
		this.perso = personne;
	}
	
}
