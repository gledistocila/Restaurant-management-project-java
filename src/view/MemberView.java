package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.CustomException;
import model.Restaurant;
import model.Table;
import model.Food;
import model.Member;
import model.Rating;
import service.MemberService;

public class MemberView {

    MemberService memberService = new MemberService();
    Scanner input = new Scanner(System.in);
   
	private Member member;
	
	public MemberView(Member member) {
		this.member = member;
	}
    
	public void memberMenu() {
		//updateTableAvailability();
		
		listTables();
		
		System.out.println("Mireseerdhet " +member.getFirstName()+ " ! ");
		
		System.out.println("1)Jep porosi || 2)Rezervo tavoline || 3)Shiko ratings || 4)Shto nje rating || 5)Dil");
		
		
		switch(input.nextInt()) {
		case 1:
			placeOrder();
			
		case 2:
			listTables();
			updateTableAvailability();
			
		case 3:
			listRatings();
			this.memberMenu();
		
		case 4:
			System.out.println("Per cilin ushqim do te shtoni nje rating? \n");
			
			switch(input.nextInt()) {
			case 1:
				   addRating(member.getMemberId(), 1);
		    case 2:
			       addRating(member.getMemberId(), 2);
		    case 3:
				addRating(member.getMemberId(), 3);
		    case 4:
				addRating(member.getMemberId(), 4);
		    case 5:
				addRating(member.getMemberId(), 5);
		    case 6:
				addRating(member.getMemberId(), 6);
		    case 7:
				addRating(member.getMemberId(), 7);
		    case 8:
				addRating(member.getMemberId(), 8);
		    case 9:
				addRating(member.getMemberId(), 9);
		    case 10:
				addRating(member.getMemberId(), 10);
			}	
		case 5:
			new WelcomeView().welcomeStart();
			
		default:
			System.out.println("Zgjedhje e gabuar. \n");
			this.memberMenu();
		}
		
		
	}
	
	public void placeOrder() {
		
	}
	
	public void updateTableAvailability() {
		System.out.println("Ju lutem jepni ID e tavolines qe doni te rezervoni: ");
		
		
		try {
			Table table = new Table();
			int tableId = input.nextInt();
			table.setTableID(tableId);
			if(memberService.getTableById(tableId).isFree() == true) {
				table.setFree(false);
				
				memberService.updateTableAvailability(table);
				System.out.println("Tavolina u rezervua me sukses!");

				this.memberMenu();
				
			}
			else {
				System.out.println("Tavolina eshte e zene. 1)Rezervo tavoline tjeter || 2)Kthehu ne faqen kryesore || 3)Dil ");
				switch(input.nextInt()) {
				case 1:
					updateTableAvailability();
				case 2:
					this.memberMenu();
				case 3:	
					new WelcomeView().welcomeStart();
				}
			}
			
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			
		}
		
		
	}
	
	public void listTables() {
		List<Table> tableList = memberService.getAllTables();
		
		System.out.println("Lista e tavolinave: \n");
		
		String gjendja;
		
		for(Table tableItem:tableList){
			boolean isFree = memberService.getTableById(tableItem.getTableID()).isFree();
			gjendja = (isFree == true) ? "lire" : "zene";
			System.out.println("Nr "+tableItem.getTableID()+") Tavolina: "+tableItem.getTableName()+ " || Gjendja: "+gjendja+ " . ");
		}
	}
	
    public void listFood() {
		
		System.out.println("Menu e ushqimeve: \n");	
				
		List<Food> foodList = memberService.getAllFood();
		
		for(Food food:foodList){
			System.out.println("Ushqimi: "+food.getFoodId()+ ")" +food.getFoodName()+ " || Kategoria: " +memberService.getCategoryById(food.getCategoryId()).getCategoryName()+ " || Cmimi: " +food.getFoodPrice()+ " . ");
			
	}
  }
    
public void listRatings() {
		
		System.out.println("Lista e ratings: \n");	
				
		List<Rating> ratingsList = memberService.getAllRatings();
		
		for(Rating rating:ratingsList){
			System.out.println("Id e rating: " +rating.getRateId()+ ") || Ushqimi: "+memberService.getFoodById(rating.getFoodId()).getFoodName()+ " || Vleresimi: " +rating.getRateName()+ " i bere nga klienti me ID: " +rating.getMemberId()+ " i quajtur " +memberService.getMemberById(rating.getMemberId()).getFirstName()+ " . ");
				
	}
  }
    
    public void addRating(Integer memberId, Integer foodId) {
    	
    		System.out.println("Ju lutem jepni vleresimin mbi ushqimin qe zgjodhet: \n");
    		    		
    		try {
    			
    			Rating rating = new Rating();
    			rating.setMemberId(memberId);
    			rating.setFoodId(foodId);
    			
    			System.out.println("Opsione: 1)Shkelqyeshem /t 2)Shume mire /t 3)Mire /t 4)Dobet \n");
    			switch(input.nextInt()) {
    			                       case 1:
    				                             rating.setRateName("Shkelqyeshem");
    			                       case 2:
    			                    	         rating.setRateName("Shume mire");
    			                       case 3:
    			                    	         rating.setRateName("Mire");
    			                       case 4:
    			                    	         rating.setRateName("Dobet");
    			}
    			
    			memberService.addRating(rating);
    			
    			System.out.println("Rating u shtua me sukses! Faleminderit per vleresimin tuaj! ");
    			memberMenu();
    		} catch (CustomException exception) {
    			System.out.println(exception.getMessage());
    			
    		} 
    		input.close();
    	}
    
}