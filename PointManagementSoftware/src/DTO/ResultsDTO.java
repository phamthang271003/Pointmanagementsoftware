package DTO;

public class ResultsDTO {
	private String sub_id, stu_id;
	private int res_time;
	private float res_score, res_score_2, avg_score;
	public ResultsDTO(String sub_id, String stu_id, int res_time, float res_score, float res_score_2, float avg_score) {
		super();
		this.sub_id = sub_id;
		this.stu_id = stu_id;
		this.res_time = res_time;
		this.res_score = res_score;
		this.res_score_2 = res_score_2;
		this.avg_score = avg_score;
	}
	public ResultsDTO() {
		super();
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public int getRes_time() {
		return res_time;
	}
	public void setRes_time(int res_time) {
		this.res_time = res_time;
	}
	public float getRes_score() {
		return res_score;
	}
	public void setRes_score(float res_score) {
		this.res_score = res_score;
	}
	public float getRes_score_2() {
		return res_score_2;
	}
	public void setRes_score_2(float res_score_2) {
		this.res_score_2 = res_score_2;
	}
	public float getAvg_score() {
		return avg_score;
	}
	public void setAvg_score(float avg_score) {
		this.avg_score = avg_score;
	}
	
	
}
