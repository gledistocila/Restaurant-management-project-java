package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.CustomException;
import model.Restaurant;
import model.Food;
import model.Member;
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
}