package view;
import java.sql.SQLException;
import java.util.*;

import exceptions.CustomException;
import model.*;
import service.AuthenticationService;
import service.MemberService;
import util.ErrorMessage;

public class WelcomeView {
	
	public void welcomeStart() {
		Restaurant juvenilja = new Restaurant("Juvenilja");
		
		System.out.println(" Welcome to " +juvenilja.getRestaurantName()+ "Restaurant! \n");
		System.out.println("Type 1 to login as an admin, 2 to login as a member, 3 to sign up: \n");
		
		Scanner input = new Scanner(System.in);
		
		AuthenticationService authenticationService = new AuthenticationService();
		
		switch(input.nextInt()) {
		                         case 1: 
		                        	 System.out.println("Username: \n");
		                        	 String username = input.next();
		                        	 System.out.println("Password: \n");
		                        	 String password = input.next();
		                        	 
		                        	 Admin admin = new Admin();
		                        	 admin.setUsername(username);
		                        	 admin.setPassword(password);
		                        	 
		                        	 try {
		                        		 Admin newAdmin = authenticationService.adminLogin(admin);
		                        		 if(newAdmin == null) {
		                        			 throw new CustomException(ErrorMessage.ADMIN_DOES_NOT_EXIST.getErrorMessage());
		                        		 }
		                        		 else {
			                                    new AdminView().adminMenu(newAdmin);
		                        		 }
		                        	 }catch (CustomException exception) {
			                     			System.out.println(exception.getMessage());
			                     			new WelcomeView().welcomeStart();
		                        	 }
		                        	 finally {
		                        		 input.close();
		                        	 }
			                     break;
			                     
		                         case 2: 
		                        	 System.out.println("Email: \n");
		                        	 String email = input.next();
		                        	 System.out.println("Password: \n");
		                        	 String memberPassword = input.next();
		                        	 
		                        	 Member member = new Member();
		                        	 member.setEmail(email);
		                        	 member.setPassword(memberPassword);
		                        	 
		                        	 try {
		                        		 Member newMember = authenticationService.memberLogin(member);
		                        		 if(newMember == null) {
		                        			 throw new CustomException(ErrorMessage.MEMBER_CAN_NOT_LOGIN.getErrorMessage());
		                        		 }
		                        		 else {
		                        	            System.out.println("Mireseerdhet ! ");
			                                    new MemberView().memberMenu(newMember);
		                        		 }
		                        	 }catch (CustomException exception) {
			                     			System.out.println(exception.getMessage());
			                     			new WelcomeView().welcomeStart();
		                        	 }
		                        	 finally {
		                        		 input.close();
		                        	 }
				                 break;
				                 
		                         case 3:
		                        	 System.out.println("Ju lutem jepni te dhenat tuaja: \n");
		                     				                     		
		                     		 try {
		                     			Member newMember = new Member();
		                     			
		                     			System.out.println("Jepni emrin: \n");
		                     			newMember.setFirstName(input.nextLine());
		                     			System.out.println("Jepni mbiemrin: \n");
		                     			newMember.setLastName(input.nextLine());
		                     			System.out.println("Jepni emailin: \n");
		                     			newMember.setEmail(input.nextLine());
		                     			System.out.println("Jepni passwordin: \n");
		                     			newMember.setPassword(input.nextLine());
		                     			System.out.println("Jepni pyetjen e sigurise: \n");
		                     			newMember.setQuestion(input.nextLine());
		                     			System.out.println("Jepni pergjigjen e pyetjes se sigurise: \n");
		                     			newMember.setAnswer(input.nextLine());
		                     			
		                     			authenticationService.register(newMember);
		                     			
		                     			System.out.println("Shtimi juaj u krye me sukses!");
		                     			System.out.println("Mireseerdhet " +newMember.getFirstName()+ " ! ");
		                     			new MemberView().memberMenu(newMember);
		                     		} catch (CustomException exception) {
		                     			System.out.println(exception.getMessage());
		                     			new WelcomeView().welcomeStart();
		                     		} finally {
		                     			input.close();
		                     		}
		                        break;	     
		                     
		}
	}	
}