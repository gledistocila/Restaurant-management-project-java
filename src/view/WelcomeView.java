package view;
import java.util.*;
import model.*;

public class WelcomeView {
	
	public void welcomeStart() {
		Restaurant juvenilja = new Restaurant("Juvenilja");
		System.out.println(" Welcome to " +juvenilja.getRestaurantName()+ "Restaurant! \n");
		System.out.println("Type 1 to login as an admin, 2 to login as a member, 3 to sign up: \n");
		Scanner input = new Scanner(System.in);
		switch(input.nextInt()) {
		                         case 1: 
			                             new AdminView().adminMenu();
			                     
		                         case 2: 
				                    	 new MemberView().memberMenu();
				       
		                         case 3:
		                        	     new RegisterView().registrationProcedure();
		                     
		}
	}
	
}
