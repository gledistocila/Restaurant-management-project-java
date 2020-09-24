package service;

import java.util.ArrayList;
import java.util.List;

import exceptions.CustomException;
import model.Member;
import model.Restaurant;
import model.Staff;
import repository.MemberRepository;
import repository.StaffRepository;
import util.ErrorMessage;

public class StaffService {
	StaffRepository staffRepository = new StaffRepository();

	public void addStaff(Staff staff) {
		staffRepository.addStaff(staff);
	}

	public List<Staff> getAllStaff(){
		return staffRepository.getAllStaff();
	}
	
	public void updateStaff(Staff staff) {
		if (staffRepository.getStaffById(staff.getStaffId()) != null) {
			staffRepository.updateStaff(staff);
		} else {
			throw new CustomException(ErrorMessage.STAFF_DOES_NOT_EXIST.getErrorMessage());
		}
	}
	
	public void deleteStaff(Staff staff) {
		if (staffRepository.getStaffById(staff.getStaffId()) != null) {
			staffRepository.deleteStaff(staff.getStaffId());			
		} else {
			throw new CustomException(ErrorMessage.STAFF_DOES_NOT_EXIST.getErrorMessage());
		}
	}
	
	public Staff getStaffById(int staffId) {
		Staff staff = staffRepository.getStaffById(staffId);
		if(staff == null) {
			throw new CustomException(ErrorMessage.MEMBER_DOES_NOT_EXIST.getErrorMessage());
		}
		return staff;
	}
}