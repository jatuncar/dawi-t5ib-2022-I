package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {

	public static void main(String[] args) {
		// valores del nuevo usuario
		Usuario u = new Usuario(15, "Juan", "Perez", "jperez@mail.com", "345",
					"2000/01/15", 1, 1);
		
		//  --> JPA
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// empez� la transacci�n
		em.getTransaction().begin();
		// proceso --> grabar en la tabla
		em.persist(u);
		// confirmar la transacci�n
		em.getTransaction().commit();
		em.close();
		
	}
	
}
