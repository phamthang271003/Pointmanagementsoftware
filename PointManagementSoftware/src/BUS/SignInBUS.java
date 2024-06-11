package BUS;

import DAO.SignInDAO;

public class SignInBUS {
	
	private SignInDAO signInDAO;
	
	public SignInBUS()
	{
		signInDAO=new SignInDAO();
	}
	
	public int checkAccount(String username, String password)
	{
		int check=0;
		check=signInDAO.checkAccount(username, password);
		return check;
	}
	
	public int checkRoleAccount(String username)
	{
		int check=0;
		check=signInDAO.checkRoleAccount(username);
		return check;
	}

}
