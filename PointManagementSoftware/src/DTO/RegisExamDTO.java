package DTO;

public class RegisExamDTO {
	private String exam_id, tea_id;

	public String getExam_id() {
		return exam_id;
	}

	public void setExam_id(String exam_id) {
		this.exam_id = exam_id;
	}

	public String getTea_id() {
		return tea_id;
	}

	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}

	public RegisExamDTO(String exam_id, String tea_id) {
		super();
		this.exam_id = exam_id;
		this.tea_id = tea_id;
	}

	public RegisExamDTO() {
		super();
	}

	@Override
	public String toString() {
		return "RegisExamDTO [exam_id=" + exam_id + ", tea_id=" + tea_id + "]";
	}
	
	
}
