package DTO;

public class SubjectsDTO {
	private String sub_id, sub_name, sub_semester;
	private int sub_year;
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getSub_semester() {
		return sub_semester;
	}
	public void setSub_semester(String sub_semester) {
		this.sub_semester = sub_semester;
	}
	public int getSub_year() {
		return sub_year;
	}
	public void setSub_year(int sub_year) {
		this.sub_year = sub_year;
	}
	public SubjectsDTO(String sub_id, String sub_name, String sub_semester, int sub_year) {
		super();
		this.sub_id = sub_id;
		this.sub_name = sub_name;
		this.sub_semester = sub_semester;
		this.sub_year = sub_year;
	}
	public SubjectsDTO() {
		super();
	}
	@Override
	public String toString() {
		return "SubjectsDTO [sub_id=" + sub_id + ", sub_name=" + sub_name + ", sub_semester=" + sub_semester
				+ ", sub_year=" + sub_year + "]";
	}
}
