package hibernate.hibernate;

import javax.persistence.*;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



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


}