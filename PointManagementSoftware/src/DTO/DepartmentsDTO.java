package DTO;

public class DepartmentsDTO {
	private String depart_id;
	private String depart_name;

	public DepartmentsDTO() {
	}

	public DepartmentsDTO(String depart_id, String depart_name) {
		this.depart_id = depart_id;
		this.depart_name = depart_name;
	}

	public String getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	@Override
	public String toString() {
		return depart_name;
	}
}
