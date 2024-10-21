package Main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//import javax.crypto.spec.PSource;

public class BooksCrud {
	
	public BooksCrud() {
		// TODO Auto-generated constructor stub
	}
	Scanner sc = new Scanner(System.in) ;
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String className = "com.mysql.cj.jdbc.Driver" ;		
		String url = "jdbc:mysql://localhost:3306/library_management" ;
		String user = "root" ;
		String pass = "root" ;
		
	 Class.forName(className);
	 
	 Connection connection = DriverManager.getConnection(url, user, pass);
	 
	 return connection;

	} //get connection block end
	
	public void getAllBooks() throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection();
		String sql = "select * from books" ;
		PreparedStatement statement = connection.prepareStatement(sql) ;
		
		ResultSet set = statement.executeQuery();
		
		while(set.next())
		{
			System.out.print("ID      :-");
			System.out.println(set.getInt("id"));
			
			System.out.print("Name    :-");
			System.out.println(set.getString("name"));
			
			System.out.print("Author      :-");
			System.out.println(set.getString("author"));
			
			System.out.print("Price      :-");
			System.out.println(set.getDouble("price"));
			
			System.out.print("Genre      :-");
			System.out.println(set.getString("genre"));
			System.out.println("*******************************************");
		}
		
//		connection.close();

	}
	
	public void addBook(Books book) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = getConnection();
		
		String sql = "insert into books values(?,?,?,?,?)" ;
		PreparedStatement statement = connection.prepareStatement(sql);
		// Fetch data from books object and store in database
		statement.setInt(1, book.getId());
		statement.setString(2,book.getName());
		statement.setString(3, book.getAuthor());
		statement.setDouble(4, book.getPrice());
		statement.setString(5, book.getGenre());
		
		
		int res = statement.executeUpdate();
		
		if(res>0) System.out.println("Book Added Successfully...!");
		else System.err.println("Book Not Added");
		
//		connection.close();
 		
		

	} // add book ends here
	
	public void deleteBook() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = getConnection();
			
		String sql = "delete from books where id = ?" ;
		PreparedStatement statement = connection.prepareStatement(sql) ;
		
		System.out.println("Enter Book Id");
		int id = sc.nextInt() ;
		statement.setInt(1,id );
		
		int res = statement.executeUpdate();
		if (res>0) System.out.println("Book Delete Successfully...");
		
		
	} // delete book block ends
	
	public void updateBook(Books books) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//Update Book Information

		Connection connection = getConnection();
			
		String sql = "update books set name=? , author=? ,price =? , genre=? where id=?" ;
		PreparedStatement statement = connection.prepareStatement(sql) ;
		
		System.out.println("Enter Id of Book Which Want to update");
		int id = sc.nextInt();
		
		System.out.println("Enter Book Name :- ");
		String name = sc.next();
		
		System.out.println("Enter Book Author :- ");
		String author = sc.next();
		
		System.out.println("Enter Price of Book :- ");
		double price = sc.nextDouble();
		
		System.out.println("Enter Genre of Book :- ");
		String genre = sc.next();
		
	
		books.setName(name);
		books.setAuthor(author);
		books.setPrice(price);
		books.setGenre(genre);
		books.setId(id);
		
		statement.setString(1,books.getName());
		statement.setString(2, books.getAuthor());
		statement.setDouble(3, books.getPrice());
		statement.setString(4, books.getGenre());
		statement.setInt(5, books.getId());
		
		int res =statement.executeUpdate();
		
		if(res>0) System.out.println("Updated Successfully...");
		else System.err.println("Not Updated Successfully...");
		

	} // Update bLOCK end
	
	public void getById() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		
		String sql = "select * from books where id= ?" ;
		PreparedStatement statement = connection.prepareStatement(sql) ;
		
		System.out.println("Enter Id of Book");
		int id = sc.nextInt();
		
		statement.setInt(1, id);
		
		ResultSet  set = statement.executeQuery();
		
		if(set.next())
		{
			System.out.println(set.getInt("id"));
			System.out.println(set.getString("name"));
			System.out.println(set.getString("author"));
			System.out.println(set.getDouble("price"));
			System.out.println(set.getString("genre"));
		}
		else System.err.println("Book is Not Availabe");
			

	} // get by if ends 
	
	
	public void getByName() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
				Connection connection = getConnection();
				
				String sql = "select * from books where name=?" ;
				PreparedStatement statement = connection.prepareStatement(sql) ;
				
				System.out.println("Enter Book Name :- ");
				String name = sc.next();
				
				statement.setString(1,name);
				
				ResultSet  set = statement.executeQuery();
				
				while(set.next())
				{
					System.out.println(set.getInt("id"));
					System.out.println(set.getString("name"));
					System.out.println(set.getString("author"));
					System.out.println(set.getDouble("price"));
					System.out.println(set.getString("genre"));
				}
				

	} // getByName ends 
	
	public void getByAuthor() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = getConnection();
		
		String sql = "select * from books where author=?" ;
		PreparedStatement statement = connection.prepareStatement(sql) ;
		
		System.out.println("Enter Book 	Author :- ");		
		String author = sc.next();
		
		statement.setString(1,author);
		
		ResultSet  set = statement.executeQuery();
		
		while(set.next())
		{
			System.out.println(set.getInt("id"));
			System.out.println(set.getString("name"));
			System.out.println(set.getString("author"));
			System.out.println(set.getDouble("price"));
			System.out.println(set.getString("genre"));
		}
		
		

	} // getByAuhor ends
	
	public void getByGenre() throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection();
		
		String sql = "select * from books where genre=?" ;
		PreparedStatement statement = connection.prepareStatement(sql) ;
		
		System.out.println("Enter Book 	Genre :- ");		
		String genre = sc.next();
		
		statement.setString(1,genre);
		
		ResultSet  set = statement.executeQuery();
		
		while(set.next())
		{
			System.out.println(set.getInt("id"));
			System.out.println(set.getString("name"));
			System.out.println(set.getString("author"));
			System.out.println(set.getDouble("price"));
			System.out.println(set.getString("genre"));
		}

	} // get By Genre is end
	

}
// Name :- Tejas Wakchaure
