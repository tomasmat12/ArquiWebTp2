package dtos;

public class CareerReportDTO {
	
	private String nameCareer;
	private int year;
	private Long qEnrolled;
	private Long qGraduates;
	
	
	public CareerReportDTO(String nameCareer, int year, Long qEnrolled, Long qGraduates) {
		super();
		this.nameCareer = nameCareer;
		this.year = year;
		this.qEnrolled = qEnrolled;
		this.qGraduates = qGraduates;
	}


	public String getNameCareer() {
		return nameCareer;
	}


	public void setNameCareer(String nameCareer) {
		this.nameCareer = nameCareer;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public Long getqEnrolled() {
		return qEnrolled;
	}


	public void setqEnrolled(Long qEnrolled) {
		this.qEnrolled = qEnrolled;
	}


	public Long getqGraduates() {
		return qGraduates;
	}


	public void setqGraduates(Long qGraduates) {
		this.qGraduates = qGraduates;
	}


	@Override
	public String toString() {
		return "CareerReportDTO [nameCareer=" + nameCareer + ", year=" + year + ", qEnrolled=" + qEnrolled
				+ ", qGraduates=" + qGraduates + "]";
	}
	
	

	
	

}
