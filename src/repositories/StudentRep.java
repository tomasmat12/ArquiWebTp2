package repositories;

import javax.persistence.EntityManager;

import daos.Inscription;
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
	
	/** 
	 * @param student
	 * @param insc
	 * 
	 * utilizamos la funcion studentInscription del dao de estudiantes 
	 * para completar la inscripcion.
	 */
	public void studentInscription(Student student, Inscription insc) {

		try {
			Student s = em.find(Student.class, student.getNumBook());
			em.getTransaction().begin();
			s.studentInscription(insc);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
