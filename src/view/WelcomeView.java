package view;
import java.sql.SQLException;
import java.util.*;

import exceptions.CustomException;
import model.*;
import service.AuthenticationService;
import service.MemberService;
import util.ErrorMessage;

public class WelcomeView {
	
	Scanner input = new Scanner(System.in);
	
	
	public void welcomeStart() {
		Restaurant juvenilja = new Restaurant("Juvenilja");
		
		System.out.println(" Welcome to " +juvenilja.getRestaurantName()+ "Restaurant! \n");
		System.out.println("Type 1 to login as an admin, 2 to login as a member, 3 to sign up: \n");
		
		AuthenticationService authenticationService = new AuthenticationService();
		MemberService memberService = new MemberService();
		
		switch(input.next()) {
		                         case "1": 
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
			                                    new AdminView().adminMenu();
		                        		 }
		                        	 }catch (CustomException exception) {
			                     			System.out.println(exception.getMessage());
		                        	 }
		                        	 
			                     break;
			                     
		                         case "2": 
		                        	 System.out.println("Email: \n");
		                        	 String email = input.next();
		                        	 System.out.println("Opsioni 1: Password: \n Opsioni 2: Harruat password? \n");
		                        	 switch(input.next()) {
		                                  case "1":
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
			                                            new MemberView().memberMenu();
		                        		                }
		                        	             }catch (CustomException exception) {
			                     			                                         System.out.println(exception.getMessage());
		                        	                                                }
		                        	              
				                         break;
		                                  case "2":
		                                	      System.out.println("Pergjigjja e pyetjes se sigurise: \n");
		                                	      String answer = input.next();
		                                	      
		                                	      Member newMember = new Member();
		                                	      newMember.setEmail(email);
		                                	      newMember.setAnswer(answer);
		                                	      
		                                	      try {
		                                	    	  Member anotherMember = memberService.getMemberByEmail(email);
		                                	    	  String correctAnswer = anotherMember.getAnswer();
		                                	    	  System.out.println(correctAnswer);
		                                	    	  if(answer.equals(correctAnswer)) {
		                                	    		  System.out.println("Vendosni passwordin e ri: \n");
		                                	    		  String newPassword = input.next();
		                                	    		  anotherMember.setPassword(newPassword);
		                                	    		  memberService.updateMemberPassword(anotherMember);
		                                	    		  System.out.println("Passwordi juaj u rivendos! ");
		                                	    		  new MemberView().memberMenu();
		                                	    	  }
		                                	    	  
		                                	    	  else {
		                                	    		  System.out.println("Pergjigje e gabuar e pyetjes se sigurise! ");
		                                	    	  }
		                                	      } catch(CustomException exception) {
		                                	    	  System.out.println(exception.getMessage());
		                                	      }
		                                	     
		                        	 }
		                        	 break;
		                         case "3":
		                        	 System.out.println("Ju lutem jepni te dhenat tuaja: \n");
		                     				                     		
		                     		 try {
		                     			Member newMember = new Member();
		                     			
		                     			System.out.println("Jepni emrin: ");
		                     			newMember.setFirstName(input.next());
		                     			System.out.println("Jepni mbiemrin: ");
		                     			newMember.setLastName(input.next());
		                     			System.out.println("Jepni emailin: \n");
		                     			newMember.setEmail(input.next());
		                     			System.out.println("Jepni passwordin: \n");
		                     			newMember.setPassword(input.next());
		                     			System.out.println("Jepni pyetjen e sigurise: \n");
		                     			newMember.setQuestion(input.next());
		                     			System.out.println("Jepni pergjigjen e pyetjes se sigurise: \n");
		                     			newMember.setAnswer(input.next());
		                     			
		                     			authenticationService.register(newMember);
		                     			
		                     			System.out.println("Shtimi juaj u krye me sukses!");
		                     			new MemberView().memberMenu();
		                     		} catch (CustomException exception) {
		                     			System.out.println(exception.getMessage());
		                     		} 
		                        break;	 
		                        default:
		                        	System.out.println("Keni shtypur numrin e gabuar.Provoni perseri! \n");
		                        	this.welcomeStart();
		                     
		}
		input.close();
	}	
}