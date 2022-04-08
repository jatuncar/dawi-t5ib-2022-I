package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {

	public static void main(String[] args) {
		// eliminar versi�n 2 --> usando b�squeda
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// empez� la transacci�n
		em.getTransaction().begin();
		// proceso --> eliminar un usuario
		// Forma 2. borrado f�sico --> borra
		Usuario u = em.find(Usuario.class, 13);
		if (u == null)
			System.err.println("C�digo NO existe");
		else {
			em.remove(u);
			System.out.println("Usuario ELIMINADO...!");
		}
		// confirmar la transacci�n
		em.getTransaction().commit();
		em.close();
		
	}
}
