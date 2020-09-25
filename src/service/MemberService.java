package service;

import java.util.ArrayList;
import java.util.List;

import exceptions.CustomException;
import model.Admin;
import model.Category;
import model.Food;
import model.Member;
import model.Rating;
import model.Restaurant;
import model.Staff;
import repository.MemberRepository;
import util.ErrorMessage;

public class MemberService {
	MemberRepository memberRepository = new MemberRepository();

	public void addMember(Member member) {
		memberRepository.addMember(member);
	}

	public void addRating(Rating rating) {
		memberRepository.addRating(rating);
	}
	public List<Member> getAllMembers(){
		return memberRepository.getAllMembers();
	}
	
	public List<Food> getAllFood(){
		return memberRepository.getAllFood();
	}
	
	public List<Rating> getAllRatings(){
		return memberRepository.getAllRatings();
	}
	
	public void updateMember(Member member) {
		if (memberRepository.getMemberById(member.getMemberId()) != null) {
			memberRepository.updateMember(member);
		} else {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
	}
	
	public void updateMemberPassword(Member member) {
		if (memberRepository.getMemberById(member.getMemberId()) != null) {
			memberRepository.updateMemberPassword(member);
		} else {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
	}
	
	public void deleteMember(Member member) {
		if (memberRepository.getMemberById(member.getMemberId()) != null) {
			memberRepository.deleteMember(member.getMemberId());			
		} else {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
	}
	
	public void deleteRating(Rating rating) {
		if (memberRepository.getRatingById(rating.getRateId()) != null) {
			memberRepository.deleteRating(rating.getRateId());			
		} else {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
	}
		
	public Member getMemberById(int memberId) {
		Member member = memberRepository.getMemberById(memberId);
		if(member == null) {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
		else{
			  return member;
	        }
    }
	
	public Food getFoodById(int foodId) {
		Food food = memberRepository.getFoodById(foodId);
		if(food == null) {
			throw new CustomException(ErrorMessage.FOOD_DOES_NOT_EXIST.getErrorMessage());
		}
		else{
			  return food;
	        }
    }
	
	public Member getMemberByEmail(String memberEmail) {
		Member member = memberRepository.getMemberByEmail(memberEmail);
		if(member == null) {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
		else{
			  return member;
	        }
    }
	
	public Category getCategoryById(int categoryId) {
		Category category = memberRepository.getCategoryById(categoryId);
		if(category == null) {
			throw new CustomException(ErrorMessage.CATEGORY_DOES_NOT_EXIST.getErrorMessage());
		}
		else{
			  return category;
	        }
    }
	
	public Rating getRatingById(int ratingId) {
		Rating rating = memberRepository.getRatingById(ratingId);
		if(rating == null) {
			throw new CustomException(ErrorMessage.RATING_DOES_NOT_EXIST.getErrorMessage());
		}
		else{
			  return rating;
	        }
    }
	
	public Rating getRatingByName(String ratingName) {
		Rating rating = memberRepository.getRatingByName(ratingName);
		if(rating == null) {
			throw new CustomException(ErrorMessage.RATING_DOES_NOT_EXIST.getErrorMessage());
		}
		else{
			  return rating;
	        }
    }
}