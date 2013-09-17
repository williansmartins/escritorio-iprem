package teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Usuario;

public class GeraUsuarios {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("manager-mysql");
	static EntityManager em = factory.createEntityManager();
	
	public static void main(String[] args) {
		inserir();
	}
	public static void inserir() {
		
		Usuario obj = new Usuario();
		obj.setUsuario( "nayara" );
		obj.setSenha( "456" );
		obj.setTipo("gestor");
		

		
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		
	}
	public static void listar() {
		//Apresenta 1
		Usuario produto = em.find(Usuario.class, 1);
		System.out.println(produto.getUsuario());
		
		//Apresenta varios
		List<Usuario> lista = buscarTudo();
		for (Usuario item : lista) {
			System.out.println("Nome:" + item.getNome() + " - Usuario:" + item.getUsuario());
		}
		 
	}
	public static List<Usuario> buscarTudo() {
		Query query = (Query) em.createQuery("SELECT p FROM Usuario p");
		return query.getResultList();
	}
	public static List<Usuario> buscar(Double item) {
		List<Usuario> lista = em.createQuery(
				"SELECT c FROM Usuario c WHERE c.preco LIKE ?1")
				.setParameter(1, item)
				.getResultList();
		List<Usuario> lista2 = em.createQuery(
				"SELECT c FROM Usuario c WHERE c.preco > 0")
				.getResultList();
		
		return lista;
	}
}
