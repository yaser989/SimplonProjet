package hibernate.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Session getSession() throws HibernateException
	{
		Configuration configuration = new Configuration()
				.configure("hibernate/hibernate/PremierExemple.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		return sessionFactory.openSession();
	}

	public static void main(String[] args) throws ReadException
	{
		try
		{
			Session s = getSession();
			Personne jean = new Personne("Jean", "Dupond");
			Transaction t = s.beginTransaction();
			s.persist(jean);
			t.commit();
//			s.close();
			Query query = s.createQuery(" from Personne");
		
			@SuppressWarnings("unchecked")
			List<Personne> list = query.list();
			s.close();
			if (list.size() != 0)
			{
				Personne myName = list.get(0);
				System.out.println("the name is :"+myName.getPrenom()+" "+myName.getNom());
			
			
			}
			else
			throw new ReadException(null);
	}
		catch (HibernateException ex)
		{
			throw new RuntimeException("Probleme de configuration : "
					+ ex.getMessage(), ex);
		}
	}
	
	
}
