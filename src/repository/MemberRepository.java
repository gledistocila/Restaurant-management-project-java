package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Food;
import model.Order;
import model.Member;
import util.ConnectionManager;

public class MemberRepository {
	
	private final String ADD_MEMBER = "INSERT INTO member (first_name , last_name , email , password , question , answer)  VALUES (?,?,?,?,?,?)";
	private final String UPDATE_MEMBER = "UPDATE member SET first_name = ? where member_id = ?";
	private final String DELETE_MEMBER = "DELETE FROM member where member_id = ?";
	private final String GET_ALL_MEMBERS = "SELECT * FROM member";
	private final String GET_MEMBER_BY_ID = "SELECT * FROM member where member_id = ?";


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
			else
				return null;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}

}