package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Food;
import model.Order;
import model.Staff;
import model.Member;
import util.ConnectionManager;

public class StaffRepository {

	private final String ADD_STAFF = "INSERT INTO staff(first_name , last_name , street_address , mobile_no) VALUES (?,?,?,?)";
	private final String UPDATE_STAFF = "UPDATE staff SET first_name = ? where staff_id = ?";
	private final String DELETE_STAFF = "DELETE FROM staff where staff_id = ?";
	private final String GET_ALL_STAFF = "SELECT * FROM staff";
	private final String GET_STAFF_BY_ID = "SELECT * FROM staff where staff_id = ?";


	public void addStaff(Staff staff) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_STAFF))

		{
			preparedStatement.setString(1, staff.getFirstName());
			preparedStatement.setString(2, staff.getLastName());
			preparedStatement.setString(3, staff.getStreetAddress());
			preparedStatement.setString(4, staff.getMobileNo());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public List<Staff> getAllStaff() {
		List<Staff> allStaff = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STAFF);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Staff staff = new Staff();
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setFirstName(rs.getString("first_name"));
				staff.setLastName(rs.getString("last_name"));
				staff.setStreetAddress(rs.getString("street_address"));
				staff.setMobileNo(rs.getString("mobile_no"));
				allStaff.add(staff);
			}
			return allStaff;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}

	public void updateStaff(Staff staff) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STAFF)) {
			preparedStatement.setString(1, staff.getFirstName());
			preparedStatement.setInt(2, staff.getStaffId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void deleteStaff(Integer staffId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF);) {
			preparedStatement.setInt(1, staffId);

			int result = preparedStatement.executeUpdate();
			System.out.println("Number of records affected :: " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}

	public Staff getStaffById(Integer staffId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_STAFF_BY_ID);) {
			preparedStatement.setInt(1, staffId);
			ResultSet rs = preparedStatement.executeQuery();
			Staff staff = new Staff();
			while (rs.next()) {
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setFirstName(rs.getString("first_name"));
				staff.setLastName(rs.getString("last_name"));
			}
			return staff;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}

}