package repositories;

import javax.persistence.EntityManager;

import daos.Career;

public class CareerRep {
	
	private EntityManager em;

	public CareerRep(EntityManager em) {
		this.em = em;
	}
	
	public Career saveCareer(Career c) {
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public Career getCareerById(Long id) {
		return em.find(Career.class, id);
	}	
	
}
