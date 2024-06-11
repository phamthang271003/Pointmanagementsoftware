package DTO;

public class TeacherDTO {

	
	 	private String teaId;
	    private String teaName;
	    private String teaSex;
	    private String departId;

	    // Constructors, getters and setters
	    public TeacherDTO(String teaId, String teaName, String teaSex, String departId) {
	        this.teaId = teaId;
	        this.teaName = teaName;
	        this.teaSex = teaSex;
	        this.departId = departId;
	    }

	    // Getters and setters
	    public String getTeaId() {
	        return teaId;
	    }

	    public void setTeaId(String teaId) {
	        this.teaId = teaId;
	    }

	    public String getTeaName() {
	        return teaName;
	    }

	    public TeacherDTO() {
			
		}

		public void setTeaName(String teaName) {
	        this.teaName = teaName;
	    }

	    public String getTeaSex() {
	        return teaSex;
	    }

	    public void setTeaSex(String teaSex) {
	        this.teaSex = teaSex;
	    }

	    public String getDepartId() {
	        return departId;
	    }

	    public void setDepartId(String departId) {
	        this.departId = departId;
	    }
}
