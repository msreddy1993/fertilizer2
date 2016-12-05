package hibernate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory=buildSessionFactory(); 
	
	private static SessionFactory buildSessionFactory(){
		String hibernatePropsFilePath = "/hibernate1.cfg.xml";
		File hibernatePropsFile = new File(hibernatePropsFilePath);
		Configuration cfg = new Configuration();
		cfg.configure(hibernatePropsFilePath);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		return cfg.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
