package service;

import java.util.ArrayList;
import java.util.List;

import exceptions.CustomException;
import model.Member;
import model.Restaurant;
import model.Staff;
import repository.MemberRepository;
import util.ErrorMessage;

public class MemberService {
	MemberRepository memberRepository = new MemberRepository();

	public void addMember(Member member) {
		memberRepository.addMember(member);
	}

	public void updateMember(Member member) {
		if (memberRepository.getMemberById(member.getMemberId()) != null) {
			memberRepository.updateMember(member);
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
	
	public List<Member> getAllMembers(){
		return memberRepository.getAllMembers();
	}

}
