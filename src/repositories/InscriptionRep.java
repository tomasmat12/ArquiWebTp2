package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import daos.Inscription;
import dtos.CareerDTO;
import dtos.CareerReportDTO;

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
	
	@SuppressWarnings("unchecked")
	public List<CareerDTO> getInscriptionCareers() {
		List<CareerDTO> result = new ArrayList<CareerDTO>();
	 	Query query = em.createQuery("SELECT new dtos.CareerDTO(c.nameCareer, COUNT(i.student) AS students) FROM Career c, Inscription i WHERE c.id = i.career GROUP BY c.nameCareer ORDER BY students DESC");
	 	result = query.getResultList();
	 	return result;

	}
	

	/*
	 * nameCareer
	 * year
	 * cantInscriptios
	 * cantEgr
	 * 
	 * order by nameCareer asc and year asc
	 * */
	
	@SuppressWarnings("unchecked")
	public List<CareerReportDTO> getCareerOrderByNameAndYear(){
		List<CareerReportDTO> result = new ArrayList<CareerReportDTO>();
		Query query = em.createQuery(
				"SELECT new dtos.CareerReportDTO(c.nameCareer, YEAR(i.graduation) as graduationYear, COUNT(i.graduation) as Egresados) "
						+ "	FROM Career c, Inscription i WHERE c.id = i.career AND i.graduation != NULL GROUP BY c.nameCareer, graduationYear ORDER BY c.nameCareer ASC, graduationYear");
	 	result = query.getResultList();
	 	return result;
	}

}
