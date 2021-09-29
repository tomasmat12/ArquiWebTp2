package dto;

public class CareerDto {
	
	private String career;
	private Long inscriptions;
	
	
	public CareerDto(String career, Long inscriptions) {
		super();
		this.career = career;
		this.inscriptions = inscriptions;
	}


	public String getCareer() {
		return career;
	}


	public void setCareer(String career) {
		this.career = career;
	}


	public Long getInscriptions() {
		return inscriptions;
	}


	public void setInscriptions(Long inscriptions) {
		this.inscriptions = inscriptions;
	}


	@Override
	public String toString() {
		return "CareerDto [career=" + career + ", inscriptions=" + inscriptions + "]";
	}

	
	
	

}
