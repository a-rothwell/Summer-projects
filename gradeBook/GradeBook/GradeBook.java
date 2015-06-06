package GradeBook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import GradeBook.Users.*;

public class GradeBook extends JFrame implements ActionListener
{
	private JFileChooser chooser;
	private Container contentPane;
	private JTextField username = new JTextField();
	private JTextField password = new JTextField();
	private JButton login = new JButton("Login");
	private File inFile;
	private File outFile;
	private FileInputStream inFileStream;
	private ObjectInputStream inObjectStream;
	private FileOutputStream outFileStream;
	private ObjectOutputStream outObjectStream;
	private User[] userIndex = new User[100];
	private User currentUser;
	public GradeBook() 
	{
		//userIndex[0] = new User("New Admin", "New Password");
		contentPane = getContentPane();
		contentPane.setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("GradeBook");
		setSize(700,700);
		setLocationRelativeTo(null);
		setResizable(false);
		
		username.setBounds(300,250,100,50);
		username.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(username);
		
		password.setBounds(300,350,100,50);
		password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(password);
		
		login.setBounds(300, 450, 100,50);
		contentPane.add(login);
		login.addActionListener(this);
		
		//saveFile();
		openFile();
	}
	public static void main(String args[])
	{
		GradeBook frame = new GradeBook();
		frame.setVisible(true);
	}
	private boolean checkPassword(String userName, String password)
	{
		for(int i = 0; i < 100; i++)
		{
			if(userIndex[i].returnUserName() == userName && userIndex[i].returnPassword() == password)
			{
				currentUser = userIndex[i];
				return true;
			}
		}
		return false;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() instanceof JButton)
		{
			if(e.getSource() == login && checkPassword(username.getText(), password.getText()))
			{ 
				if(currentUser.getClass().toString() ==  "class GradeBook.Users.Admin")
				{
					showAdminPerspective();
				}
				else if(currentUser.getClass().toString() ==  "class GradeBook.Users.Teacher")
				{
					showTeacherPerspective();
				}
				else if(currentUser.getClass().toString() ==  "class GradeBook.Users.Student")
				{
					showStudentPerspective();
				}
			}			
		}
		
		
	}
	private String getUserType(String name) {
		//User 
		return null;
	}
	private void showStudentPerspective() {
		// TODO Auto-generated method stub
		
	}
	private void showTeacherPerspective() {
		// TODO Auto-generated method stub
		
	}
	private void showAdminPerspective()
	{
		JButton addNewStudent = new JButton("Add New Student");
		JButton addNewTeacher = new JButton("Add New Teacher");
		addNewStudent.setBounds(250, 500, 100, 50);
		addNewTeacher.setBounds(250, 500, 100, 50);
		contentPane.add(addNewStudent);
		addNewStudent.addActionListener(this);
		contentPane.add(addNewTeacher);
		addNewTeacher.addActionListener(this);
	}
	private void openFile()
	{
		chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		inFile = chooser.getSelectedFile();
		try
		{
			System.out.println("Her e");
			inFileStream = new FileInputStream(inFile);
			inObjectStream = new ObjectInputStream(inFileStream);
			try
			{
				userIndex = (User[])inObjectStream.readObject();	
				System.out.println("Here");
			}
			catch(ClassNotFoundException e)
			{
				System.out.println("Exception");
			}
			inObjectStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}	
	private void saveFile()
	{
		chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		outFile = chooser.getSelectedFile();
		try
		{
			outFileStream = new FileOutputStream(outFile);
			outObjectStream = new ObjectOutputStream(outFileStream);
			outObjectStream.writeObject(userIndex);
			outObjectStream.close();
		}
		catch(IOException e)
		{
			
		}
	}	

}
