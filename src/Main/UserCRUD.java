package Main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class UserCRUD {
	
	public UserCRUD() {
		// TODO Auto-generated constructor stub
	}
	Scanner sc = new Scanner(System.in) ;
	private Connection getConnection() throws  SQLException {
		// TODO Auto-generated method stub
		String className = "com.mysql.cj.jdbc.Driver" ;		
		String url = "jdbc:mysql://localhost:3306/library_management" ;
		String user = "root" ;
		String pass = "root" ;
		
	 try {
		Class.forName(className);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("From User crud");
	}
	 
	 Connection connection = DriverManager.getConnection(url,user,pass);
	 
	 return connection;

	} //get connection block end
	
	public int signUP() throws Exception, SQLException {
		User user = new User() ;
		
		System.out.println("*****************Sign Up*****************");
		System.out.println("Enter ID");
		int id = sc.nextInt();
		
		System.out.println("Enter Your Name");
		String name = sc.next();
		
		System.out.println("Enter Phone Number");
		long phone = sc.nextLong();
		
		System.out.println("Enter Email");
		String email = sc.next();
		
		System.out.println("Enter Password");
		String password = sc.next();
		
		user.setId(id);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		
		Connection connection = getConnection();
		
		String sql = "insert into user values(?,?,?,?,?)" ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, user.getId());
		statement.setString(2, user.getName());
		statement.setLong(3, user.getPhone());
		statement.setString(4, user.getEmail());
		statement.setString(5, user.getPassword());
		
		int status = statement.executeUpdate();

		if(status > 0) System.out.println("Sign In Succesfully...\n");
		else System.err.println("Sign In Unsuccesfull ...\n");
	
		connection.close();
		
		return status ;

	} // Sign up block end
	
	public void logIN(String email ,String password) throws Exception {
		
		//Log In block

		Connection connection = getConnection();
		
		String sql = "select * from user where email = ?" ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		
		ResultSet set = statement.executeQuery();
		
		if(set.next())
		{
			if(password.equals(set.getString("password")))
			{
				// Log in success
				System.out.println("Log In Success..");
				
				admin() ;
		
				
			}// inner if block end
			else {
				System.err.println("Invalid Password...");
				
				System.out.println("Enter your Choice \n 1:- Forgot Password \n2:- Exit");
				int choice = sc.nextInt();
				
				switch (choice) {
				case 1:
				{
					String query = "update user set password=? where email=?" ;
					PreparedStatement preparedstatement = connection.prepareStatement(query) ;
					
					System.out.println("Enter phone Number");
					long phn = sc.nextLong();
					
					if(phn== set.getLong("phone")) 
					{
						System.out.println("Enter new Password :- ");
						long newpass = sc.nextLong();
						
						preparedstatement.setLong(1, newpass);
						preparedstatement.setString(2, email);
						int res = preparedstatement.executeUpdate();
						
						if(res>0) System.out.println("Password Updated Successfully...!");
						else System.out.println("Password Has Not Been Updated");
					}
					else System.err.println("Invalid User");
					
					
				}
					break;
				case 2:
				{
					System.err.println("Exit Sucesssfully..!");
					}
					
					break;

				default:{
					System.err.println("Please Enter Valid Inputs");
				}
					break;
				} //Switch statement end
			}
		}//if block end
		else
		{
			System.err.println("Invalis User");
			
			System.out.println("Please Enter your chioice \n 1:- Sign Up \2 :- Exit");
			int choice = sc.nextInt() ;
			
			switch (choice) {
			
			case 1:
			{				// again sign in 
				
				signUP();
				
			}//case 1 block end
				break;
				
			case 2:
			{
				System.err.println("You Had Successfully Exit");
				
			}//case 2 block end
				break;

			default:{
					System.out.println("Please Enter Valid Data");
			}
				break;
			}//  inside switch end
			
		} // else block end
		

	}// login block end
	
	private void admin() throws ClassNotFoundException, SQLException {
		
		System.err.println("********************** Admin Panel ***************************");
		
		System.out.println(" 1 :- Get All Books \n 2 :- Add Books \n 3 :- Delete Books \n 4 :- Update Books \n 5 :- Get Book By Id \n 6 :- Get Book By Name"
				+ "	\n 7 :- Get Book By Author \n 8 :- Get Book By Genre \n 9 :- Update User Details \n 10 :- LogOut ");
		
		System.out.println("***************************************************************");
		
		int choice = sc.nextInt();		
		
		BooksCrud bookcrud = new BooksCrud();
		Books book = new Books() ;
		
		switch (choice) {
		case 1:{
			
			bookcrud.getAllBooks();
			
			System.out.println("1:- Go to Admin panel \n 2:- Exit ");
			choice = sc.nextInt();
			
			if(choice==1) admin() ;
			else {
				System.err.println("Exit Successs");
				break ;
			}
			
		}
			
			break;
		case 2:{
			// Case for to add book
			boolean flag = true ;
			while(flag = true)
			{
				System.out.println("\n ****************** Add Book ****************");
				System.out.println("Enter Book ID :-");
				int id = sc.nextInt();
				
				System.out.println("Enter Book Name :- ");
				String name = sc.next();
				
				System.out.println("Enter Book Author :- ");
				String author = sc.next();
				
				System.out.println("Enter Price of Book :- ");
				double price = sc.nextDouble();
				
				System.out.println("Enter Genre of Book :- ");
				String genre = sc.next();
				
				// Store all input in book object
				book.setId(id);
				book.setName(name);
				book.setAuthor(author);
				book.setPrice(price);
				book.setGenre(genre);
				
				bookcrud.addBook(book);
				
				System.out.println("\n 1:- Add another Book \n 2:- LogIn Panel");
				choice = sc.nextInt() ;
				
				if(choice == 1) 
					{
						flag = true ;
					}
					
				else {
					System.out.println("LogOut  Success..!");
					//flag = false ;
					break ;
					
					}
			}
		} // add book or case 2 ends
			
			break;
			
		case 3:{
				
			bookcrud.deleteBook();
			
			while(true)
			{
				System.out.println("\n 1:- Delete one more \n 2:- admin \n 3:- LogOut");
				choice = sc.nextInt();
				
				if(choice==1) bookcrud.deleteBook();
				else if (choice == 2) {
					admin() ;
					break ;
				}
				else if(choice == 3) {
					System.err.println("LogOut  Success...");
					break ;
				}
				else {
					System.err.println("Please Enter Valid Input");
					
				}
			}
		}
	
			break;
		case 4:{
			bookcrud.updateBook(book);
			
			while(true) {

				System.out.println("\n 1:- Update one more \n 2:- Admin Panel \n 3:- LogOut");
				choice = sc.nextInt();
				
				if(choice==1) bookcrud.updateBook(book);
				else if (choice == 2) 
				{
					admin() ;
					break ;
				}
				else if(choice == 3) {
					System.err.println("LogOut  Success...");
					break ;
				}
				else {
					System.err.println("Please Enter Valid Input");
					
				}
			}
	
			}
	
			break;
			
		case 5:{
			
			// get by id
			
			bookcrud.getById();
			
			
			while(true)
			{
				System.out.println("\n 1:- Get Another Book \n 2:- Admin Panel \n 3:- LogOut");
				choice = sc.nextInt();
				
				if(choice==1) bookcrud.getById();
				
				else if (choice == 2)
				{
					 admin() ;
					 break ;
				}
				else if(choice == 3) {
					System.err.println("LogOut  Success...");
					
					break ;
				}
				else {
					System.err.println("Please Enter Valid Input");
					
				}
			}
	
	
			}
	
			break;
		
		case 6:{
			
			//Get Book By Name 
			bookcrud.getByName();
			

			while(true)
			{
				System.out.println("\n 1:- Get Another Book \n 2:- Admin Panel \n 3:- LogOut");
				choice = sc.nextInt();
				
				if(choice==1) bookcrud.getByName();
				
				else if (choice == 2) 
				{
					admin() ;
					break ;
				}
				else if(choice == 3) {
					System.err.println("LogOut  Success...");
					break ;
				}
				else {
					System.err.println("Please Enter Valid Input");
					
				}
			}
			} // case 6 end
	
			break;
	
		case 7:{
			
			bookcrud.getByAuthor();

			while(true)
			{
				System.out.println("\n 1:- Get Another Book \n 2:- Admin Panel \n 3:- LogOut");
				choice = sc.nextInt();
				
				if(choice==1) bookcrud.getByAuthor();
				
				else if (choice == 2) 
				{
					admin() ;
					break ;
				}
				else if(choice == 3) {
					System.err.println("LogOut  Success...");
					break ;
				}
				else {
					
					System.err.println("Please Enter Valid Input");
					
				}
			}
	
		}
	
			break;
		case 8:{
			//get by genre
				bookcrud.getByGenre();

				while(true)
				{
					System.out.println("\n 1:- Get Another Book \n 2:- Admin Panel \n 3:- LogOut");
					choice = sc.nextInt();
					
					if(choice==1) bookcrud.getByGenre();
					
					else if (choice == 2) 
					{
						admin() ;
						break ;
					}
					else if(choice == 3) {
						System.err.println("LogOut  Success...");
						break ;
					}
					else {
						System.err.println("Please Enter Valid Input");
						
					}
				}
	
			}
	
			break;
		case 9:{		
					updateUser();
			
			while(true)
			{
				System.out.println("\n 1:- Update User \n 2:- Admin Panel \n 3:- LogOut");
				choice = sc.nextInt();
				
				if(choice==1) updateUser();
				
				else if (choice == 2) 
				{
					admin() ;
					break ;
				}
				else if(choice == 3) {
					System.err.println("LogOut  Success...");
					break ;
				}
				else {
					System.err.println("Please Enter Valid Input");
				
				}
			}
			
	
			} // case 9 end here ... 
	
			break;
		case 10:{
			
			System.err.println("Logout  Successfullt...");
			break ;
	
		}
	
		default:{
			System.err.println("Please Enter Valid Input");
		}
			break;
		} // Switch Block End
		

	} // admin block end
	
	
	private void updateUser() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		
		String sql = "update user set uname=? , phone=? ,email=?,password=? where id=?" ;
		PreparedStatement statement = connection.prepareStatement(sql) ;
		
		User user = new User() ;
		System.out.println("Enter ID");
		int id = sc.nextInt();
		
		System.out.println("Enter Your Name");
		String name = sc.next();
		
		System.out.println("Enter Phone Number");
		long phone = sc.nextLong();
		
		System.out.println("Enter Email");
		String email = sc.next();
		
		System.out.println("Enter Password");
		String password = sc.next();
		
		user.setId(id);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
				
		statement.setString(1, user.getName());
		statement.setLong(2, user.getPhone());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getPassword());	
		statement.setInt(5,user.getId());
		
		int res = statement.executeUpdate();
		
		if(res>0) System.out.println("Updated Successfully...");
		else System.err.println("User not found...");
		
		

	} // update user block end


}//class block end

// Name :- Tejas Wakchaure
