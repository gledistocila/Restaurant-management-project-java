package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.CustomException;
import model.Restaurant;
import model.Staff;
import repository.MemberRepository;
import model.Admin;
import model.Member;
import model.Order;
import model.Rating;
import model.Reservation;
import service.MemberService;
import service.StaffService;
import view.WelcomeView;

public class AdminView {

	MemberService memberService = new MemberService();
	StaffService staffService = new StaffService();
	Scanner input = new Scanner(System.in);
	
	private Admin admin;
	
	public AdminView(Admin admin) {
		this.admin = admin;
	}
	
	public void adminMenu() {
		
		System.out.println("Mireseerdhet "+admin.getUsername()+ " ! ");
		
		System.out.println("1)Shto anetar       || 2)Listo anetaret  || 3)Updato anetar || 4)Fshi anetar \n");
		System.out.println("5)Shto staf         || 6)Listo stafin    || 7)Updato staf   || 8)Fshi staf   \n");
		System.out.println("9)Shiko ratings     || 10)Fshi rating nepermjet ID          || 11)Fshi rating nepermjet vleresimit || 12)Fshi rating te nje klienti \n");
		System.out.println("13)Shiko rezervimet || 14)Shiko porosite || 15)Dil \n");
		
		switch(input.nextInt()) {
		case 1:
			addMember();
			this.adminMenu();
			break;
		case 2:
			listMembers();
			this.adminMenu();
			break;
		case 3:
			updateMember();
			this.adminMenu();
			break;
		case 4:
			deleteMember();
			this.adminMenu();
			break;
		case 5:
			addStaff();
			this.adminMenu();
			break;
		case 6:
			listStaff();
			this.adminMenu();
			break;
		case 7:
			updateStaff();
			this.adminMenu();
			break;
		case 8:
			deleteStaff();
			this.adminMenu();
			break;
		case 9:
			listRatings();
			this.adminMenu();
			break;
		case 10:
			deleteRating();
			this.adminMenu();
			break;
		case 11:	
			System.out.println("Shkruani vleresimin te cilin doni ta fshini nga rating: \n");
			Scanner stringInput = new Scanner(System.in);
			String rateName = stringInput.next();
			deleteRatingByName(rateName);
			stringInput.close();
			this.adminMenu();
			break;
		case 12:
			System.out.println("Shkruani ID e anetarit te cilit do t'i fshihen te gjithe rating: \n");
			Scanner newInput = new Scanner(System.in);
			int memberId = newInput.nextInt();
			deleteRatingByMember(memberId);
			this.adminMenu();
			break;
		case 13:
			listReservations();
			this.adminMenu();
			break;
		case 14:
			listOrders();
			this.adminMenu();
			break;
		case 15:
			new WelcomeView().welcomeStart();
			break;
		default:
			System.out.println("Zgjedhje e gabuar! ");
			this.adminMenu();
			
		}
		
	                        }
	
    public void listRatings() {
		
		System.out.println("Lista e ratings: \n");	
				
		List<Rating> ratingsList = memberService.getAllRatings();
		
		for(Rating rating : ratingsList) {
			System.out.println("Id e rating: " +rating.getRateId()+ ") || Ushqimi: "+memberService.getFoodById(rating.getFoodId()).getFoodName()+ " || Vleresimi: " +rating.getRateName()+ " i bere nga klienti me ID: " +rating.getMemberId()+ " i quajtur" +memberService.getMemberById(rating.getMemberId()).getFirstName()+ " . ");
				
	     }
     }
    
public void listOrders() {
		
		System.out.println("Lista e porosive: \n");	
				
		List<Order> ordersList = memberService.getAllOrders();
		
		for(Order order : ordersList) {
			System.out.println("Id e porosise: " +order.getOrderId()+" e bere nga klienti me ID: "+order.getMemberId()+" e kryer nga anetari i stafit me ID: "+order.getStaffId()+ " me total: "+order.getTotal()+ " leke. ");
	     }
     }
    
public void listReservations() {
		
		System.out.println("Lista e rezervimeve: \n");	
				
		List<Reservation> reservationsList = memberService.getAllReservations();
		
		for(Reservation reservation : reservationsList) {
			System.out.println("Id e rezervimit: " +reservation.getReservationId()+ ") || nga klienti: "+memberService.getMemberById(reservation.getMemberId()).getFirstName()+ " || me ID: " +reservation.getMemberId()+ " || per tavolinen me ID: " +reservation.getReservationId()+ " e quajtur " +memberService.getTableById(reservation.getTableId()).getTableName()+ " . ");
				
	     }
     }

	public void addMember() {
		System.out.println("Ju lutem jepni te dhenat e anetarit qe doni te shtoni: \n");
				
		try {
			Member member = new Member();
			
			System.out.println("Jepni emrin: \n");
			member.setFirstName(input.nextLine());
			System.out.println("Jepni mbiemrin: \n");
			member.setLastName(input.nextLine());
			System.out.println("Jepni emailin: \n");
			member.setEmail(input.nextLine());
			System.out.println("Jepni passwordin: \n");
			member.setPassword(input.nextLine());
			System.out.println("Jepni pyetjen e sigurise: \n");
			member.setQuestion(input.nextLine());
			System.out.println("Jepni pergjigjen e pyetjes se sigurise: \n");
			member.setAnswer(input.nextLine());
			
			memberService.addMember(member);
			
			System.out.println("Anetari u shtua me sukses!");
			this.adminMenu();
		} catch (CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
			
		} 
     }
	
	public void listMembers() {
		
		System.out.println("Lista e anetareve: \n");	
				
		List<Member> members = memberService.getAllMembers();
		
		for(Member m : members) {
			System.out.println("Id e anetarit: " +m.getMemberId()+ " || Emri i anetarit: " +m.getFirstName()+ " || Mbiemri i anetarit: " +m.getLastName()+ " || Pyetja e sigurise: " +m.getQuestion()+ " || Pergjigjja: " +m.getAnswer()+ " . ");
		}
				
	}
	
	public void updateMember() {
		System.out.println("Ju lutem jepni ID e anetarit qe doni te updatoni: ");
				
		try {
			Member member = new Member();
			member.setMemberId(input.nextInt());
			System.out.println("Jepni emrin e updatuar te anetarit: ");
			member.setFirstName(input.next());
					
			memberService.updateMember(member);
			System.out.println("Anetari u updatua me sukses!");
			new WelcomeView().welcomeStart();
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
			
		}
		
	}
	
	public void deleteMember() {
		System.out.println("Ju lutem jepni ID e anetarit qe doni te fshini: \n");
				
		try {
			Member member = new Member();
			member.setMemberId(input.nextInt());
			memberService.deleteMember(member);
			System.out.println("Anetari u fshi me sukses!");
			new WelcomeView().welcomeStart();
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
			
		}
		
	}
	
	public void deleteRating() {
		System.out.println("Ju lutem jepni ID e rating qe doni te fshini: \n");
		
		try {
			Rating rating = new Rating();
			rating.setRateId(input.nextInt());
			memberService.deleteRating(rating);
			System.out.println("Rating u fshi me sukses!");
			new WelcomeView().welcomeStart();
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
		}
		
	}
	
	public void deleteRatingByName(String ratingName) {
		
	  try{
			List<Rating> allRatings = memberService.getAllRatings();
		    List<Rating> newRatings = new ArrayList<>();
		
		    for(Rating rating:allRatings) {
			                               if(rating.getRateName().equals(ratingName)) {
				                           newRatings.add(rating);
			}
		}
		
		for(Rating rating:newRatings) {
			memberService.deleteRating(rating);
		}
	 }
	catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
	 }
		
	}
	
	public void deleteRatingByMember(Integer memberId) {
		try {
			  List<Rating> allRatings = memberService.getAllRatings();
			  List<Rating> memberRatings = new ArrayList<>();
			  
			  for(Rating rating:allRatings) {
				                             if(rating.getMemberId() == memberId) {
				                            	 memberRatings.add(rating);
				                             }
				  
			  }
			  
			  for(Rating rating:memberRatings) {
				  memberService.deleteRating(rating);
			  }
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
		}
	}
	
	public void addStaff() {
		System.out.println("Ju lutem jepni te dhenat e punonjesit qe doni te shtoni: \n");
		
		try {
			Staff staff = new Staff();
			
			System.out.println("Jepni emrin: \n");
			staff.setFirstName(input.nextLine());
			System.out.println("Jepni mbiemrin: \n");
			staff.setLastName(input.nextLine());
			System.out.println("Jepni adresen: \n");
			staff.setStreetAddress(input.nextLine());
			System.out.println("Jepni numrin e tel: \n");
			staff.setMobileNo(input.nextLine());
						
			staffService.addStaff(staff);
			
			System.out.println("Punonjesi u shtua me sukses!");
			new WelcomeView().welcomeStart();
		} catch (CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
			
		} 
		
     }
	
	public void listStaff() {
		
		System.out.println("Listimi i stafit: \n");
		
		List<Staff> staffList = staffService.getAllStaff();
		
		for(Staff staff : staffList) {
			System.out.println("Id e stafit: " +staff.getStaffId()+ " || Emri i stafit: " +staff.getFirstName()+ " || Mbiemri i stafit: " +staff.getLastName()+ " || Numri i telefonit: " +staff.getMobileNo()+ " || Adresa: " +staff.getStreetAddress()+ " . ");
		}

	}
	
	public void updateStaff() {
		System.out.println("Ju lutem jepni ID e punonjesit qe doni te updatoni: ");
		
		try {
			Staff staff = new Staff();
			staff.setStaffId(input.nextInt());
			System.out.println("Jepni emrin e updatuar te punonjesit: ");
			staff.setFirstName(input.next());
					
			staffService.updateStaff(staff);
			System.out.println("Punonjesi u updatua me sukses!");
			new WelcomeView().welcomeStart();
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
		}
		
	}
	
	public void deleteStaff() {
		System.out.println("Ju lutem jepni ID e punonjesit qe doni te fshini: ");
		
		try {
			Staff staff = new Staff();
			staff.setStaffId(input.nextInt());
			staffService.deleteStaff(staff);
			System.out.println("Punonjesi u fshi me sukses!");
			new WelcomeView().welcomeStart();
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			System.out.println("\n");
			this.adminMenu();
		}
	input.close();	
	}	
	
}