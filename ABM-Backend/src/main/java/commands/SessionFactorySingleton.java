package commands;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 * This class contains an instance of {@code SessionFactory}, and implements the Singleton pattern
 * to guarantee that only one instance will be initiated. 
 * 
 * @author Lucas Andrade
 *
 */
public class SessionFactorySingleton {
	
	/**
	 * The instance of the {@code SessionFactory}
	 */
	private static volatile SessionFactory sessionFactory;
	
	/**
	 * To avoid the constructor being misused.
	 */
	private SessionFactorySingleton(){}
	
	/**
	 * @return the instance of the {@code SessionFactory} of {@code this} singleton,
	 * with thread safe lazy initialisation
	 */
	public static SessionFactory getInstance(){
		if(sessionFactory == null){
			
			synchronized(SessionFactorySingleton.class) {
				if(sessionFactory == null){
					sessionFactory = createSessionFactory();
				}
			}
		}
		
		return sessionFactory;
	}
	
	/**
	 * Instantiates a {@code SessionFactory} object
	 * @return the {@code SessionFactory}
	 */
	private static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    return configuration.buildSessionFactory(serviceRegistry);
	}
}
