package Main;

import java.sql.SQLException;
import java.util.Scanner;

public class Controller {
		
	public static void main(String[] args) throws SQLException, Exception {
		System.out.println("		-Welcome to Library");
		Scanner sc = new Scanner(System.in) ;
		
		UserCRUD usercrud = new UserCRUD();
		
		String s = "Sachine" ;
		System.out.println(s.concat("Tendulkar"));
	 s =s.concat("Tendulkar");
	 System.out.println(s);
				
		while(true)
		{
			System.out.println("*************************************************");
			System.out.println("Enter your Choice\n 1 :- SignUP \n 2 :-LogIN \n 3 :-Exit");
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:{
				//  block for user SingU
				
				usercrud.signUP();
				
				
			}	// Outer Sign up Case Block 1 end		
				break;
				
			case 2:{
				// Log In block
				
				System.out.println("Enter Email id");
				String email = sc.next();
				
				System.out.println("Enter Password ");
				String password = sc.next() ;
				
				usercrud.logIN(email,password);
				
				
				
			}	// Outer Case Block 2 end			
				break;
				
			case 3:{
				System.out.println("Exit Success...");
					
				}	// Outer Case Block 3	end	
					break;

			default:{
				
				System.out.println("Please Enter Valid Input");
			}
				break;
			} //Outer Switch Statement

//			sc.close();
			
		} //while block end
		
	} //  Main block end 

} //class block end


 // Name :- Tejas Wakchaure

