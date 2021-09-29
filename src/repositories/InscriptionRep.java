package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import daos.Inscription;
import daos.Student;
import dto.CareerDto;

public class InscriptionRep {
	
	private EntityManager em;

	public InscriptionRep(EntityManager em) {
		this.em = em;
	}
	
	public Inscription saveInscription(Inscription i) {
		try {
			em.getTransaction().begin();
			em.persist(i);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	public Inscription getInscriptionById(Long id) {
		return em.find(Inscription.class, id);
	}
	
	/**
	 * Se retorna una lista de carreras con su respectiva cantidad de inscriptos
	 *
	 * @return List<CareerDTO>
	 */
	
	public List<CareerDto> getInscriptionCareers() {
		List<CareerDto> result = new ArrayList<CareerDto>();
	 	Query query = em.createQuery("SELECT new dto.CareerDto(c.nameCareer, COUNT(i.student) AS students) FROM Career c, Inscription i WHERE c.id = i.career GROUP BY c.nameCareer ORDER BY students DESC");
	 	result = query.getResultList();
	 	return result;

	}

}
