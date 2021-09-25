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
		/*ESTE METODO TODAVIA NO ANDA*/
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
