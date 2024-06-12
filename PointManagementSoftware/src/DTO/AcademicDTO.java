package DTO;

public class AcademicDTO {
	private String aca_id;
	private String aca_name;
	private String aca_semester;
	private String depart_id;
	
	public AcademicDTO() {
	}

	public AcademicDTO(String aca_id, String aca_name, String aca_semester, String depart_id) {
		this.aca_id = aca_id;
		this.aca_name = aca_name;
		this.aca_semester = aca_semester;
		this.depart_id = depart_id;
	}

	public String getAca_id() {
		return aca_id;
	}

	public void setAca_id(String aca_id) {
		this.aca_id = aca_id;
	}

	public String getAca_name() {
		return aca_name;
	}

	public void setAca_name(String aca_name) {
		this.aca_name = aca_name;
	}

	public String getAca_semester() {
		return aca_semester;
	}

	public void setAca_semester(String aca_semester) {
		this.aca_semester = aca_semester;
	}

	public String getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}
	
	
}
