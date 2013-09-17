package dao.cadastro;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.FileModel;
import modelo.Usuario;
import dao.jpa.JpaGenericDao;

//@NamedQuery(name="UsuarioDaoImpl.findEntity", query="SELECT p FROM Usuario p WHERE p.usuario = :user and p.senha = :pass ")
public class FileDaoImpl extends JpaGenericDao<FileModel> implements IFileDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	private EntityManagerFactory emf;
	List<FileModel> lista;
	
	public FileDaoImpl() {
		emf = Persistence.createEntityManagerFactory("manager-mysql");
		entityManager = getEntityManager();
	}
	
	public List<FileModel> findEspecific(String s) {
		entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		
		String jpql = "SELECT p FROM file p WHERE p.titulo like '%" + s + "%'";
		Query query = entityManager.createQuery(jpql);
		lista = (List<FileModel>)query.getResultList();
		entityManager.flush();
		entityManager.close();
		if(lista.size() > 0){
			return lista;
		}else{
			return null;
		}
	}
}

