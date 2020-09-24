package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Food;
import model.Order;
import model.Member;
import util.ConnectionManager;

public class MemberRepository {
	
	private final String ADD_MEMBER = "INSERT INTO member (first_name , last_name , email , password , question , answer)  VALUES (?,?,?,?,?,?)";
	private final String UPDATE_MEMBER = "UPDATE member SET first_name = ? where member_id = ?";
	private final String DELETE_MEMBER = "DELETE FROM member where member_id = ?";
	private final String GET_ALL_MEMBERS = "SELECT * FROM member";
	private final String GET_MEMBER_BY_ID = "SELECT COUNT(*) FROM member where member_id = ?";
    private final String GET_MEMBER_BY_EMAIL_PASSWORD = "SELECT * FROM member where email = ? and password = ?";
    private final String GET_ADMIN_BY_USERNAME_PASSWORD = "SELECT * FROM admin where username = ? and password = ?";
    		
	public void addMember(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBER))

		{
			preparedStatement.setString(1, member.getFirstName());
			preparedStatement.setString(2, member.getLastName());
			preparedStatement.setString(3,  member.getEmail());
			preparedStatement.setString(4,  member.getPassword());
			preparedStatement.setString(5,  member.getQuestion());
			preparedStatement.setString(6,  member.getAnswer());
		
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}

	public List<Member> getAllMembers() {
		List<Member> members = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MEMBERS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setFirstName(rs.getString("first_name"));
				member.setLastName(rs.getString("last_name"));
				members.add(member);
			}
			return members;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public void updateMember(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEMBER)) {
			preparedStatement.setString(1, member.getFirstName());
			preparedStatement.setInt(2, member.getMemberId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void deleteMember(Integer memberId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEMBER);) {
			preparedStatement.setInt(1, memberId);

			int result = preparedStatement.executeUpdate();
			System.out.println("Number of records affected :: " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}

	
	

	public Member getMemberById(Integer memberId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_MEMBER_BY_ID);) {
			preparedStatement.setInt(1, memberId);
			ResultSet rs = preparedStatement.executeQuery();
			Member member = new Member();
			if(rs.next()) {
				member.setMemberId(rs.getInt("member_id"));
				member.setFirstName(rs.getString("first_name"));
				member.setLastName(rs.getString("last_name"));
				return member;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error! " + e);
			return null;
		}
	}
	
	public Member getMemberByEmailPassword(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_MEMBER_BY_EMAIL_PASSWORD);) {
			preparedStatement.setString(1, member.getEmail());
			preparedStatement.setString(2, member.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			Member newMember = new Member();
			if(rs.next()) {
				newMember.setFirstName(rs.getString("first_name"));
				newMember.setFirstName(rs.getString("last_name"));
				newMember.setFirstName(rs.getString("question"));
				newMember.setFirstName(rs.getString("answer"));
				return newMember;
			}
			else {
				return null;
			}		
		} catch(SQLException e) {
			System.out.println("Error!" +e);
			return null;
		}
	}
	
	public Admin getAdminByUsernamePassword(Admin admin) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_BY_USERNAME_PASSWORD);) {
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement.setString(2, admin.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			Admin newAdmin = new Admin();
			if(rs.next()) {
				newAdmin.setAdminId(rs.getInt("admin_id"));
				return newAdmin;
			}
			else {
				return null;
			}
	   } catch(SQLException e) {
		System.out.println("Error!" +e);
		return null;
	    }
	}
}