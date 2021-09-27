package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import daos.Career;
import daos.Inscription;
import daos.Student;
import repositories.CareerRep;
import repositories.InscriptionRep;
import repositories.StudentRep;

 class Main {

	public static void main(String[] args) throws Exception {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();

		StudentRep studentRepository = new StudentRep(em);
		CareerRep careerRepository = new CareerRep(em);
		InscriptionRep inscriptionRepository = new InscriptionRep(em);
			
		try {
			loadDataStudent(studentRepository);
			loadDataCareer(careerRepository);
			loadDataInscription(inscriptionRepository, em);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		/**
		 * Ejercicio 2 - Inciso A
		 * Dar de alta un estudiante
		 * Creamos un nuevo estudiante y llamamos a la funcion saveStudent
		 * del repositorio de estuduantes para guardarlo en la db
		 */
		Student newStudent = new Student(224677, "Melany", "Pedemonte", 24, "female", 40555112, "Tandil");
		studentRepository.saveStudent(newStudent);
		
		/**
		 * Ejercicio 2 - Inciso B
		 * Matricular un estudiante en una carrera.
		 * Buscamos la carrera en la que deseamos inscribir el estudiante,
		 * creamos una nueva inscripcion, le agregamos la fecha,
		 * y llamamos a la funcion studentInscription del repositorio de estudiantes 
		 * que se encarga de inscribir al estudiante en esa carrera.
		 */
		Career carrer = careerRepository.getCareerById(1);
		Inscription inscription = new Inscription();
		inscription.setCareer(carrer);
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dateInscription = formatDate.parse("2021-09-26 09:00:00");
		Timestamp startDate = new Timestamp(dateInscription.getTime());
		inscription.setStartDate(startDate);
		studentRepository.studentInscription(newStudent, inscription); 
		inscriptionRepository.saveInscription(inscription);
		
		
		emf.close();

	}
	
	
	public static void loadDataStudent(StudentRep repository) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader("./src/folder/estudiantes.csv"));
		for (CSVRecord row : parser) {

			Student e = new Student(Integer.parseInt(row.get("numBook")), row.get("name"), row.get("lastname"),
					Integer.parseInt(row.get("age")), row.get("gender"), Integer.parseInt(row.get("numDoc")),
					row.get("cityResident"));
			repository.saveStudent(e);
		}
	}
	
	public static void loadDataCareer(CareerRep repository) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader("./src/folder/carreras.csv"));
		for (CSVRecord row : parser) {

			Career c = new Career(row.get("nameCareer"), Integer.parseInt(row.get("duration")));
			repository.saveCareer(c);
		}
	}
	
	public static void loadDataInscription(InscriptionRep repository, EntityManager em)
			throws Exception {
		
		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader("./src/folder/inscripciones.csv"));
		for (CSVRecord row : parser) {

			Student s = em.getReference(Student.class, Integer.parseInt(row.get("numBook")));
			Career c = em.getReference(Career.class, Integer.parseInt(row.get("id_career")));

			SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date lFromDate1 = datetimeFormatter1.parse(row.get("startDate"));
			Timestamp startD = new Timestamp(lFromDate1.getTime());
			Timestamp grad = null;
			if (!row.get("graduation").equals("")) {

				SimpleDateFormat datetimeFormatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date lFromDate2 = datetimeFormatter2.parse(row.get("graduation"));
				grad = new Timestamp(lFromDate2.getTime());
			}

			Inscription i = new Inscription(s, c, startD, grad);
			repository.saveInscription(i);
		}
	}
		
}
