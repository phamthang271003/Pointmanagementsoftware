package DTO;

public class TeachersDTO {
	private String tea_id, tea_name, tea_sex, depart_id;

	public String getTea_id() {
		return tea_id;
	}

	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}

	public String getTea_name() {
		return tea_name;
	}

	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}

	public String getTea_sex() {
		return tea_sex;
	}

	public void setTea_sex(String tea_sex) {
		this.tea_sex = tea_sex;
	}

	public String getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}

	public TeachersDTO(String tea_id, String tea_name, String tea_sex, String depart_id) {
		super();
		this.tea_id = tea_id;
		this.tea_name = tea_name;
		this.tea_sex = tea_sex;
		this.depart_id = depart_id;
	}

	public TeachersDTO() {
		super();
	}

	@Override
	public String toString() {
		return getTea_name();
	}
	
	
}
