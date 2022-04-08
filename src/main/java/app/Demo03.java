package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {

	public static void main(String[] args) {
		// eliminar un usuario
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// empez� la transacci�n
		em.getTransaction().begin();
		// proceso --> eliminar un usuario
		// Forma 1. borrado l�gico --> no lo borras, s�lo cambias alg�n estado, flag
		
		// Forma 2. borrado f�sico --> borra
		Usuario u = new Usuario(33, "Carla", "Toro", "U0022@gmail.com", "10002", "2022-03-24", 2, 1);
		em.remove(u);
		
		// confirmar la transacci�n
		em.getTransaction().commit();
		em.close();
		
		
		
	}
}
