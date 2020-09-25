package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.CustomException;
import model.Restaurant;
import model.Food;
import model.Member;
import model.Rating;
import service.MemberService;

public class MemberView {

	MemberService memberService = new MemberService();
	
	public void memberMenu() {
		
		listFood();
		
	}
	
    public void listFood() {
		
		System.out.println("Menu e ushqimeve: \n");	
				
		List<Food> foodList = memberService.getAllFood();
		
		for(Food food : foodList) {
			System.out.println("Ushqimi: "+food.getFoodId()+ ")" +food.getFoodName()+ " || Kategoria: " +memberService.getCategoryById(food.getCategoryId()).getCategoryName()+ " || Cmimi: " +food.getFoodPrice()+ " . ");
				
	}
  }
    
public void listRatings() {
		
		System.out.println("Lista e ratings: \n");	
				
		List<Rating> ratingsList = memberService.getAllRatings();
		
		for(Rating rating : ratingsList) {
			System.out.println("Id e rating: " +rating.getRateId()+ ") || Ushqimi: "+memberService.getFoodById(rating.getFoodId()).getFoodName()+ " || Vleresimi: " +rating.getRateName()+ " i bere nga klienti me ID: " +rating.getMemberId()+ " i quajtur" +memberService.getMemberById(rating.getMemberId()).getFirstName()+ " . ");
				
	}
  }
    
    public void addRating(Integer memberId, Integer foodId) {
    	
    		System.out.println("Ju lutem jepni vleresimin mbi ushqimin qe zgjodhet: \n");
    		Scanner input = new Scanner(System.in);
    		
    		try {
    			
    			Rating rating = new Rating();
    			rating.setMemberId(memberId);
    			rating.setFoodId(foodId);
    			
    			System.out.println("Opsione: 1)Shkelqyeshem /t 2)Shume mire /t 3)Mire /t 4)Dobet \n");
    			switch(input.next()) {
    			                       case "1":
    				                             rating.setRateName("Shkelqyeshem");
    			                       case "2":
    			                    	         rating.setRateName("Shume mire");
    			                       case "3":
    			                    	         rating.setRateName("Mire");
    			                       case "4":
    			                    	         rating.setRateName("Dobet");
    			}
    			
    			memberService.addRating(rating);
    			
    			System.out.println("Rating u shtua me sukses! Faleminderit per vleresimin tuaj! ");
    			new WelcomeView().welcomeStart();
    		} catch (CustomException exception) {
    			System.out.println(exception.getMessage());
    			
    		} finally {
    			input.close();
    		}
    }
}