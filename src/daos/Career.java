package daos;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class Career {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private int nameCareer;
	@Column
	private int duration;
	
	public Career() {
		super();
	}
	public Career(int nameCareer, int duration) {
		super();
		this.nameCareer = nameCareer;
		this.duration = duration;
	}
	public int getNameCareer() {
		return nameCareer;
	}
	public void setNameCareer(int nameCareer) {
		this.nameCareer = nameCareer;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Career [id=" + id + ", nameCareer=" + nameCareer + ", duration=" + duration + "]";
	}
	
	
	

}
