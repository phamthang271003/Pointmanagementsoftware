package DTO;

public class SubjectExamDTO {
	private String exam_id;
	private String sub_id;
	private int exam_time;
	public String getExam_id() {
		return exam_id;
	}
	public void setExam_id(String exam_id) {
		this.exam_id = exam_id;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public int getExam_time() {
		return exam_time;
	}
	public void setExam_time(int exam_time) {
		this.exam_time = exam_time;
	}
	public SubjectExamDTO(String exam_id, String sub_id, int exam_time) {
		super();
		this.exam_id = exam_id;
		this.sub_id = sub_id;
		this.exam_time = exam_time;
	}
	public SubjectExamDTO() {
		super();
	}
	
}
