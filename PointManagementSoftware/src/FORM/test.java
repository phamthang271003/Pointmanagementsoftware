package FORM;

import java.util.List;

import DAO.SubjectsDAO;
import DTO.SubjectsDTO;

public class test {
	public static void main(String[] args) {
		try {
			SubjectsDAO subject = new SubjectsDAO();
			List<SubjectsDTO> lstSubject = subject.getAll();
			for(SubjectsDTO sub : lstSubject) {
				System.out.println(sub.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
