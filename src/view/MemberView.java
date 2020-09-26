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
import model.Reservation;
import service.MemberService;

public class MemberView {

    MemberService memberService = new MemberService();
    Scanner input = new Scanner(System.in);
   
	private Member member;
	
	public MemberView(Member member) {
		this.member = member;
	}
    
	public void memberMenu() {
				
		System.out.println("Mireseerdhet " +member.getFirstName()+ " ! ");
		
		System.out.println("1)Menu || 2)Jep porosi || 3)Shiko ratings || 4)Shto nje rating || 5)Shiko tavolinat || 6)Rezervo tavoline");
		System.out.println("7)Dil");
		
		
		switch(input.nextInt()) {
		case 1:
			listFood();
			this.memberMenu();
			break;
			
		case 2:
			placeOrder();
			this.memberMenu();
			break;
			
		case 3:
			listRatings();
			this.memberMenu();
			break;
		
		case 4:
			System.out.println("Per cilin ushqim do te shtoni nje rating? \n");
			
			switch(input.nextInt()) {
			case 1:
				   addRating(member.getMemberId(), 1);
				   break;
		    case 2:
			       addRating(member.getMemberId(), 2);
			       break;
		    case 3:
				addRating(member.getMemberId(), 3);
				break;
		    case 4:
				addRating(member.getMemberId(), 4);
				break;
		    case 5:
				addRating(member.getMemberId(), 5);
				break;
		    case 6:
				addRating(member.getMemberId(), 6);
				break;
		    case 7:
				addRating(member.getMemberId(), 7);
				break;
		    case 8:
				addRating(member.getMemberId(), 8);
				break;
		    case 9:
				addRating(member.getMemberId(), 9);
				break;
		    case 10:
				addRating(member.getMemberId(), 10);
				break;
			default:
				System.out.println("Zgjedhje e gabuar! \n");
				this.memberMenu();
			}
			break;
		case 5:
			listTables();
			this.memberMenu();
			break;
		case 6:
			updateTableAvailability();
			this.memberMenu();
			break;
		case 7:
			new WelcomeView().welcomeStart();
			break;
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
				Reservation reservation = new Reservation();
				reservation.setMemberId(this.member.getMemberId());
				reservation.setTableId(tableId);
				memberService.addReservation(reservation);
				System.out.println("Tavolina u rezervua me sukses!");

				this.memberMenu();
				
			}
			else {
				System.out.println("Tavolina eshte e zene. 1)Rezervo tavoline tjeter || 2)Kthehu ne faqen kryesore || 3)Dil ");
				switch(input.nextInt()) {
				case 1:
					updateTableAvailability();
					break;
				case 2:
					this.memberMenu();
					break;
				case 3:	
					new WelcomeView().welcomeStart();
					break;
				default:
					System.out.println("Zgjedhje e gabuar! \n");
					this.memberMenu();
				}
			}
			
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.memberMenu();
			
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
    				                             break;
    			                       case 2:
    			                    	         rating.setRateName("Shume mire");
    			                    	         break;
    			                       case 3:
    			                    	         rating.setRateName("Mire");
    			                    	         break;
    			                       case 4:
    			                    	         rating.setRateName("Dobet");
    			                    	         break;
    			                       default:
    			                    	   System.out.println("Zgjedhje e gabuar! \n");
    			}
    			
    			memberService.addRating(rating);
    			
    			System.out.println("Rating u shtua me sukses! Faleminderit per vleresimin tuaj! ");
    			memberMenu();
    		} catch (CustomException exception) {
    			System.out.println(exception.getMessage());
    			System.out.println("\n");
    			this.memberMenu();
    		} 
    		input.close();
    	}
    
}