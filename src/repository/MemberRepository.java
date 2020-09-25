package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Category;
import model.Food;
import model.Order;
import model.Rating;
import model.Member;
import util.ConnectionManager;

public class MemberRepository {
	
	private final String ADD_MEMBER = "INSERT INTO member (first_name , last_name , email , password , question , answer)  VALUES (?,?,?,?,?,?)";
	private final String GET_ALL_MEMBERS = "SELECT * FROM member";
	private final String UPDATE_MEMBER = "UPDATE member SET first_name = ? where member_id = ?";
	private final String DELETE_MEMBER = "DELETE FROM member where member_id = ?";
	
	private final String UPDATE_MEMBER_PASSWORD = "UPDATE member SET password = ? where member_id = ?";
	private final String GET_MEMBER_BY_EMAIL = "SELECT * FROM member where email = ?";
	private final String GET_MEMBER_BY_ID = "SELECT * FROM member where member_id = ?";
    private final String GET_MEMBER_BY_EMAIL_PASSWORD = "SELECT * FROM member where email = ? and password = ?";
    private final String GET_ADMIN_BY_USERNAME_PASSWORD = "SELECT * FROM admin where username = ? and password = ?";
   
    private final String GET_ALL_FOOD = "SELECT * FROM food";
    private final String GET_CATEGORY_BY_ID = "SELECT * FROM category where category_id = ?";
    private final String GET_FOOD_BY_ID = "SELECT * FROM food where food_id = ?";
    private final String ADD_RATING = "INSERT INTO rating (rate_name , member_id , food_id) VALUES (?,?,?)";
    private final String GET_ALL_RATINGS = "SELECT * from rating";
    private final String GET_RATING_BY_ID = "SELECT * from rating where rate_id = ?";
    private final String GET_RATING_BY_NAME = "SELECT * from rating where rate_name = ?";
    private final String DELETE_RATING = "DELETE FROM rating where rate_id = ?";
    private final String DELETE_RATING_BY_NAME = "DELETE FROM rating where rate_name = ?";
    private final String DELETE_RATING_BY_MEMBER = "DELETE FROM rating where member_id = ?";
    		
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

	public void addRating(Rating rating) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_RATING))

		{
			preparedStatement.setString(1, rating.getRateName());
			preparedStatement.setInt(2, rating.getMemberId());
			preparedStatement.setInt(3,  rating.getFoodId());
					
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
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setQuestion(rs.getString("question"));
				member.setAnswer(rs.getString("answer"));
				members.add(member);
			}
			return members;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public List<Rating> getAllRatings() {
		List<Rating> ratings = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RATINGS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Rating rating = new Rating();
				rating.setRateId(rs.getInt("rate_id"));
				rating.setRateName(rs.getString("rate_name"));
				rating.setMemberId(rs.getInt("member_id"));
				rating.setFoodId(rs.getInt("food_id"));
				
				ratings.add(rating);
			}
			return ratings;
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
	
	public void updateMemberPassword(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEMBER_PASSWORD)) {
			preparedStatement.setString(1, member.getPassword());
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
			
			System.out.println("\n Number of records affected : " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}

	public void deleteRating(Integer ratingId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RATING);) {
			preparedStatement.setInt(1, ratingId);

			int result = preparedStatement.executeUpdate();
			
			System.out.println("\n Number of records affected : " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void deleteRatingByName(String ratingName) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RATING_BY_NAME);) {
			preparedStatement.setString(1, ratingName);

			int result = preparedStatement.executeUpdate();
			
			System.out.println("\n Number of records affected : " + result);
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void deleteRatingByMember(Integer memberId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RATING_BY_MEMBER);) {
			preparedStatement.setInt(1, memberId);

			int result = preparedStatement.executeUpdate();
			
			System.out.println("\n Number of records affected : " + result);
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
	
	public Rating getRatingById(Integer ratingId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RATING_BY_ID);) {
			preparedStatement.setInt(1, ratingId);
			ResultSet rs = preparedStatement.executeQuery();
			Rating rating = new Rating();
			if(rs.next()) {
				rating.setRateName(rs.getString("rate_name"));
				rating.setMemberId(rs.getInt("member_id"));
				rating.setFoodId(rs.getInt("food_id"));
				return rating;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error! " + e);
			return null;
		}
	}
	
	public Member getMemberByEmail(String memberEmail) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_MEMBER_BY_EMAIL);) {
			preparedStatement.setString(1, memberEmail);
			ResultSet rs = preparedStatement.executeQuery();
			Member member = new Member();
			if(rs.next()) {
				member.setMemberId(rs.getInt("member_id"));
				member.setFirstName(rs.getString("first_name"));
				member.setLastName(rs.getString("last_name"));
				member.setQuestion(rs.getString("question"));
				member.setAnswer(rs.getString("answer"));
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
	
	public Rating getRatingByName(String ratingName) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RATING_BY_NAME);) {
			preparedStatement.setString(1, ratingName);
			ResultSet rs = preparedStatement.executeQuery();
			Rating rating = new Rating();
			if(rs.next()) {
				rating.setRateId(rs.getInt("rate_id"));
				rating.setMemberId(rs.getInt("member_id"));
				rating.setFoodId(rs.getInt("food_id"));
				
				return rating;
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
	
	public List<Food> getAllFood() {
		List<Food> foodList = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FOOD);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Food food = new Food();
				food.setFoodId(rs.getInt("food_id"));
				food.setFoodName(rs.getString("food_name"));
				food.setFoodPrice(rs.getInt("food_price"));
				food.setCategoryId(rs.getInt("food_category"));
				food.setFoodQuantity(rs.getInt("food_quantity"));
				
				foodList.add(food);
			}
			return foodList;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public Category getCategoryById(Integer categoryId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_ID);) {
			preparedStatement.setInt(1, categoryId);
			ResultSet rs = preparedStatement.executeQuery();
			Category category = new Category();
			if(rs.next()) {
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				return category;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error! " + e);
			return null;
		}
	}
	
	public Food getFoodById(Integer foodId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_FOOD_BY_ID);) {
			preparedStatement.setInt(1, foodId);
			ResultSet rs = preparedStatement.executeQuery();
			Food food = new Food();
			if(rs.next()) {
				food.setFoodName(rs.getString("food_name"));
				food.setFoodPrice(rs.getInt("food_price"));
				food.setCategoryId(rs.getInt("food_category"));
				food.setFoodQuantity(rs.getInt("food_quantity"));
				return food;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error! " + e);
			return null;
		}
	}
}