package DTO;

import java.sql.Date;

public class ExamSchedulesDTO {
	private String exam_id, semester, exam_start, exam_time, exam_year, room_id;
	private Date exam_date;
	
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getExam_id() {
		return exam_id;
	}
	public void setExam_id(String exam_id) {
		this.exam_id = exam_id;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getExam_start() {
		return exam_start;
	}
	public void setExam_start(String exam_start) {
		this.exam_start = exam_start;
	}
	public String getExam_time() {
		return exam_time;
	}
	public void setExam_time(String exam_time) {
		this.exam_time = exam_time;
	}
	public String getExam_year() {
		return exam_year;
	}
	public void setExam_year(String exam_year) {
		this.exam_year = exam_year;
	}
	public Date getExam_date() {
		return exam_date;
	}
	public void setExam_date(Date exam_date) {
		this.exam_date = exam_date;
	}
	public ExamSchedulesDTO(String exam_id, String semester, String room_id, String exam_start, String exam_time, String exam_year,Date exam_date) {
		super();
		this.exam_id = exam_id;
		this.semester = semester;
		this.room_id =  room_id;
		this.exam_start = exam_start;
		this.exam_time = exam_time;
		this.exam_year = exam_year;
		this.exam_date = exam_date;
	}
	public ExamSchedulesDTO() {
		super();
	}
	@Override
	public String toString() {
		return getExam_id();
	}
	
	
}
