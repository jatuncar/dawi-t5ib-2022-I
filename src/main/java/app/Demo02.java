package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {

	public static void main(String[] args) {
		// actualizar los datos de un usuario
		Usuario u = new Usuario(33, "Carla", "Toro", "U0022@gmail.com", "10002", "2022-03-24", 2, 1);

		// --> JPA
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// empez� la transacci�n
		em.getTransaction().begin();
		// proceso --> actualizar en la tabla
		em.merge(u); // actualiz� si existe el c�digo, sino lo inserta
		// confirmar la transacci�n
		em.getTransaction().commit();
		em.close();

	}
}
