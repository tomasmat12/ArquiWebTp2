package repositories;

import javax.persistence.EntityManager;

import daos.Student;

public class StudentRep {
	
	private EntityManager em;

	public StudentRep(EntityManager em) {
		this.em = em;
	}
	
	
	public Student saveStudent(Student s) {
		try {
			em.getTransaction().begin();
			em.persist(s);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public Student getStudentByNumBook(Long nb) {
		return em.find(Student.class, nb);
	}
	
	
}
