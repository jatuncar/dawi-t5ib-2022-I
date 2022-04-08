package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	public static void main(String[] args) {
		// obtener los datos de un usuario, según el código
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// empezó la transacción
		em.getTransaction().begin();
		
		Usuario u = em.find(Usuario.class, 90);
		System.out.println(u);
		
		// confirmar la transacción
		em.getTransaction().commit();
		em.close();
		
	}
}
