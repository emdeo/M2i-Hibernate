package m2i.jpa;

import javax.persistence.*;

@Entity
@Table(name = "tblPersonnes") // Crée la table si elle n'existe pas déjà
public class Personne {

	@Id // clé primaire
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID_Personne;
	@Column(name = "Last_Name", nullable = false)
	private String Nom;
	@Column(name = "First_Name", nullable = false)
	private String Prenom;

	
	
	
	public Personne() {
	}

	public Personne(String nom, String prenom) {
		super();
		Nom = nom;
		Prenom = prenom;
	}

	public Personne(int id_personne, String nom, String prenom) {
		super();
		this.ID_Personne = id_personne;
		Nom = nom;
		Prenom = prenom;
	}

	public int getId_personne() {
		return ID_Personne;
	}

	public void setId_personne(int id_personne) {
		this.ID_Personne = id_personne;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	@Override
	public String toString() {
		return "Personne [id_personne=" + ID_Personne + ", Nom=" + Nom + ", Prenom=" + Prenom + "]";
	}

}
