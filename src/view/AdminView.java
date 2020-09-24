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
import service.MemberService;
import service.StaffService;
import view.WelcomeView;

public class AdminView {

	MemberService memberService = new MemberService();
	StaffService staffService = new StaffService();
	
	public void adminMenu() {
		
		listMembers();
		
	}
	
	public void addMember() {
		System.out.println("Ju lutem jepni te dhenat e anetarit qe doni te shtoni: \n");
		Scanner input = new Scanner(System.in);
		
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
			new WelcomeView().welcomeStart();
		} catch (CustomException exception) {
			System.out.println(exception.getMessage());
			
		} finally {
			input.close();
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
		Scanner input = new Scanner(System.in);
		
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
			
		}
		finally {
			input.close();
		}
		
	}
	
	public void deleteMember() {
		System.out.println("Ju lutem jepni ID e anetarit qe doni te fshini: ");
		Scanner input = new Scanner(System.in);
		
		try {
			Member member = new Member();
			member.setMemberId(input.nextInt());
			memberService.deleteMember(member);
			System.out.println("Anetari u fshi me sukses!");
			new WelcomeView().welcomeStart();
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			
		}
		finally {
			input.close();
		}
	}
	
	public void addStaff() {
		System.out.println("Ju lutem jepni te dhenat e punonjesit qe doni te shtoni: \n");
		Scanner input = new Scanner(System.in);
		
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
			
		} finally {
			input.close();
		}
		
     }
	
	public void listStaff() {
		
		System.out.println("Listimi i stafit: \n");

	}
	
	public void updateStaff() {
		System.out.println("Ju lutem jepni ID e punonjesit qe doni te updatoni: ");
		Scanner input = new Scanner(System.in);
		
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
			
		}
		finally {
			input.close();
		}
	}
	
	public void deleteStaff() {
		System.out.println("Ju lutem jepni ID e punonjesit qe doni te fshini: ");
		Scanner input = new Scanner(System.in);
		
		try {
			Staff staff = new Staff();
			staff.setStaffId(input.nextInt());
			staffService.deleteStaff(staff);
			System.out.println("Punonjesi u fshi me sukses!");
			new WelcomeView().welcomeStart();
		}
		catch(CustomException exception) {
			System.out.println(exception.getMessage());
			
		}
		finally {
			input.close();
		}
	}	
}