package GradeBook.Users;

import java.io.*;


public class Admin extends User implements Serializable{
	public Admin(String username, String password) 
	{
		// TODO Auto-generated constructor stub
		super(username, password);
		Student studentArray[] = new Student[100];
	}
	private void createNewTeacher(String username, String password)
	{
		
	}
	private void createNewStudent(String username, String password)
	{
		
	}
	public void print()
	{
		System.out.println("Here");
	}

}
