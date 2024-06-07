package DTO;

import java.sql.Date;

public class StudentDTO {
	private String stu_id;
	private String stu_name;
	private Date stu_day_entry;
	private int stu_semester;
	private String stu_sex;
	private Date stu_dob;
	private String stu_address;
	private String aca_id;
	private String class_id;
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public Date getStu_day_entry() {
		return stu_day_entry;
	}
	public void setStu_day_entry(Date stu_day_entry) {
		this.stu_day_entry = stu_day_entry;
	}
	public int getStu_semester() {
		return stu_semester;
	}
	public void setStu_semester(int stu_semester) {
		this.stu_semester = stu_semester;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public Date getStu_dob() {
		return stu_dob;
	}
	public void setStu_dob(Date stu_dob) {
		this.stu_dob = stu_dob;
	}
	public String getStu_address() {
		return stu_address;
	}
	public void setStu_address(String stu_address) {
		this.stu_address = stu_address;
	}
	public String getAca_id() {
		return aca_id;
	}
	public void setAca_id(String aca_id) {
		this.aca_id = aca_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public StudentDTO(String stu_id, String stu_name, Date stu_day_entry, int stu_semester, String stu_sex,
			Date stu_dob, String stu_address, String aca_id, String class_id) {
		super();
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.stu_day_entry = stu_day_entry;
		this.stu_semester = stu_semester;
		this.stu_sex = stu_sex;
		this.stu_dob = stu_dob;
		this.stu_address = stu_address;
		this.aca_id = aca_id;
		this.class_id = class_id;
	}
	public StudentDTO() {
		super();
	}
	@Override
	public String toString() {
		return "StudentDTO [stu_id=" + stu_id + ", stu_name=" + stu_name + ", stu_semester=" + stu_semester + "]";
	}
	
	
}
