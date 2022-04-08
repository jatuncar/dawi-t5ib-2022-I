package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {

	public static void main(String[] args) {
		// eliminar versión 2 --> usando búsqueda
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// empezó la transacción
		em.getTransaction().begin();
		// proceso --> eliminar un usuario
		// Forma 2. borrado físico --> borra
		Usuario u = em.find(Usuario.class, 13);
		if (u == null)
			System.err.println("Código NO existe");
		else {
			em.remove(u);
			System.out.println("Usuario ELIMINADO...!");
		}
		// confirmar la transacción
		em.getTransaction().commit();
		em.close();
		
	}
}
