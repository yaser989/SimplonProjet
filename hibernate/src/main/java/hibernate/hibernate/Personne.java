package hibernate.hibernate;

import javax.persistence.*;
import javax.persistence.Table;





@Entity
@Table(name = "personne")
class Personne
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "num")
	private int num;

	@Column(name = "nom")
	private String nom;

	@Column(name = "prenom")
	private String prenom;

	public Personne(String prenom, String nom)
	{
		this.nom = nom;
		this.prenom = prenom;
	}

	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	@Override
	public String toString() {
		return "Personne [num=" + num + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

}