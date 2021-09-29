package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import daos.Inscription;
import daos.Student;
import dtos.StudentDTO;

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
	
	/**
	 * Se retorna una lista de todos los estudiantes
	 * 
	 * @return List<StudentDTO>
	 */
	public List<StudentDTO> getStudents() {
		List<StudentDTO> students;
		Query query = em.createQuery("SELECT new dtos.StudentDTO(s.numBook, s.name, s.lastname, s.age, s.gender, s.numDoc, s.cityResident)"
				+ " FROM Student s ORDER BY s.name ASC");
		students = query.getResultList();
		return students;
	}
	
	
	/**
	 * Se retorna una lista de estudiantes en base a su genero
	 * 
	 * @param gender
	 * @return List<StudentDTO>
	 */
	public List<StudentDTO> getStudentsByGender(String gender) {
		List<StudentDTO> students;
		Query query = em.createQuery("SELECT new dtos.StudentDTO(s.numBook, s.name, s.lastname, s.age, s.gender, s.numDoc, s.cityResident) FROM Student s WHERE s.gender = :gender");
		query.setParameter("gender", gender);
		students = query.getResultList();
		return students;
	}

	/**
	 * Se retorna un estudiante en base a su libreta universitaria
	 * 
	 * @param numLibret
	 * @return List<StudentDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<StudentDTO> getStudentByNumLibret(int numBook) {
		List<StudentDTO> s = new ArrayList<StudentDTO>();
		Query query = em.createQuery(
				"SELECT new dtos.StudentDTO(s.numBook, s.name, s.lastname, s.age, s.gender, s.numDoc, s.cityResident) FROM Student s WHERE s.numBook = :numBook");
		query.setParameter("numBook", numBook);
		s = query.getResultList();
		return s;
	}
	
	
}
