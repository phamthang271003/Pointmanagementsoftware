package DTO;

public class SubjectDTO {
private String sub_id;
private String sub_name;
private String sub_semester;

public SubjectDTO() {
}

public SubjectDTO(String sub_id, String sub_name, String sub_semester) {
	this.sub_id = sub_id;
	this.sub_name = sub_name;
	this.sub_semester = sub_semester;
}

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

@Override
public String toString() {
	return sub_name;
}
}
