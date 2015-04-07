package backendEntities;
/**
 *TODO: delete this class
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class TEST {
    public static void main( String[] args ){
        
    	SessionFactory sessionFact = createSessionFactory();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("utilizador", "palavra passe", "email@coisas.sitio");
		Person person = new Person("nome", new Address("rua do lugar"));
		user.getBookedEntities().put(person.getName(), person);
		
//		ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, "utilizador");
		Person person2 = new Person("nome2", new Address("rua do lugar2"), new PhoneNumber(123456789));

		user.getBookedEntities().put(person2.getName(), person2);
		
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		
		session.close();
    }
    
	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    return configuration.buildSessionFactory(serviceRegistry);
	}
}
