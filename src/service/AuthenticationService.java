package service;

import exceptions.CustomException;
import model.Admin;
import model.Member;
import repository.MemberRepository;
import util.ErrorMessage;

public class AuthenticationService {
	
	MemberRepository memberRepository = new MemberRepository();
	
	public Member memberLogin(Member member) {
		Member newMember = memberRepository.getMemberByEmailPassword(member);
		if(newMember == null) {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
		else {
			return newMember;
		}
	}
	
	public Admin adminLogin(Admin admin) {
		Admin newAdmin = memberRepository.getAdminByUsernamePassword(admin);
		if(newAdmin == null) {
			throw new CustomException(ErrorMessage.ADMIN_DOES_NOT_EXIST.getErrorMessage());
		}
		else {
			return newAdmin;
		}
	}
	
	public void register(Member member) {
		memberRepository.addMember(member);
	}

}