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
import model.Reservation;
import model.Table;
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
   
    private final String GET_TABLE_BY_ID = "SELECT * FROM tablelist where table_id = ?";
    private final String UPDATE_TABLE_AVAILABILITY = "UPDATE tablelist SET is_free = ? where table_id = ?";
    private final String GET_ALL_TABLES = "SELECT * FROM tablelist";
    
    private final String GET_ALL_FOOD = "SELECT * FROM food";
    private final String GET_CATEGORY_BY_ID = "SELECT * FROM category where category_id = ?";
    private final String GET_FOOD_BY_ID = "SELECT * FROM food where food_id = ?";
    
    private final String ADD_ORDER = "INSERT INTO orderlist (total , member_id , staff_id) VALUES (?,?,?)";
    private final String GET_ORDER_BY_ID = "SELECT * FROM orderlist where order_id = ?";
    private final String GET_ORDER_BY_MEMBERID = "SELECT * FROM orderlist where member_id = ?";
    private final String GET_ALL_ORDERS = "SELECT * FROM orderlist";
    private final String UPDATE_FOOD_AVAILABILITY = " UPDATE food SET food_quantity = ? where food_id = ?";
    
    private final String ADD_RESERVATION = "INSERT INTO reservation (member_id , table_id) VALUES (?,?)";
    private final String GET_RESERVATION_BY_ID = "SELECT * FROM reservation where reservation_id = ?";
    private final String GET_ALL_RESERVATIONS = "SELECT * FROM reservation";
    
    private final String ADD_RATING = "INSERT INTO rating (rate_name , member_id , food_id) VALUES (?,?,?)";
    private final String GET_ALL_RATINGS = "SELECT * from rating";
    private final String GET_RATING_BY_ID = "SELECT * from rating where rate_id = ?";
    private final String GET_RATING_BY_NAME = "SELECT * from rating where rate_name = ?";
    private final String DELETE_RATING = "DELETE FROM rating where rate_id = ?";
    private final String DELETE_RATING_BY_NAME = "DELETE FROM rating where rate_name = ?";
    private final String DELETE_RATING_BY_MEMBER = "DELETE FROM rating where member_id = ?";
    		
	public void addMember(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBER);)

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
	
	public void addOrder(Order order) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);)

		{
			preparedStatement.setInt(1, order.getTotal());
			preparedStatement.setInt(2, order.getMemberId());
			preparedStatement.setInt(3, order.getStaffId());
						
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}

	public void addReservation(Reservation reservation) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_RESERVATION);)

		{
			preparedStatement.setInt(1,  reservation.getMemberId());
			preparedStatement.setInt(2,  reservation.getTableId());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void addRating(Rating rating) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_RATING);)

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
	
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setMemberId(rs.getInt("member_id"));
				order.setStaffId(rs.getInt("staff_id"));
				order.setTotal(rs.getInt("total"));
				
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public List<Table> getAllTables() {
		List<Table> tables = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TABLES);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Table table = new Table();
				table.setTableID(rs.getInt("table_id"));
				table.setTableName(rs.getString("table_name"));
				table.setRestaurantId(rs.getInt("restaurant_id"));
				table.setFree(rs.getBoolean("is_free"));
				
				tables.add(table);
			}
			
			return tables;
			
		} 
		catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public List<Reservation> getAllReservations() {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RESERVATIONS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setReservationId(rs.getInt("reservation_id"));
				reservation.setMemberId(rs.getInt("member_id"));
				reservation.setTableId(rs.getInt("table_id"));
								
				reservations.add(reservation);
			}
			
			return reservations;
			
		} 
		catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public void updateMember(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEMBER);) {
			preparedStatement.setString(1, member.getFirstName());
			preparedStatement.setInt(2, member.getMemberId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void updateTableAvailability(Table table) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TABLE_AVAILABILITY);) {
			preparedStatement.setBoolean(1, table.isFree());
			preparedStatement.setInt(2, table.getTableID());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void updateFoodAvailability(Food food) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FOOD_AVAILABILITY);) {
			
			preparedStatement.setInt(1, food.getFoodQuantity());
			preparedStatement.setInt(2, food.getFoodId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void updateMemberPassword(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEMBER_PASSWORD);) {
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
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
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
	
	public Table getTableById(Integer tableId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_TABLE_BY_ID);) {
			preparedStatement.setInt(1, tableId);
			ResultSet rs = preparedStatement.executeQuery();
			
			Table table = new Table();
			if(rs.next()) {
				table.setTableID(rs.getInt("table_id"));
				table.setFree(rs.getBoolean("is_free"));
				table.setTableName(rs.getString("table_name"));
				table.setRestaurantId(rs.getInt("restaurant_id"));
			
				return table;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error! " + e);
			return null;
		}
	}
	
	public Order getOrderById(Integer orderId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);) {
			preparedStatement.setInt(1, orderId);
			ResultSet rs = preparedStatement.executeQuery();
			
			Order order = new Order();
			if(rs.next()) {
				order.setOrderId(rs.getInt("order_id"));
				order.setMemberId(rs.getInt("member_id"));
				order.setStaffId(rs.getInt("staff_id"));
				order.setTotal(rs.getInt("total"));
			
				return order;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error! " + e);
			return null;
		}
	}
	
	public Order getOrderByMemberId(Integer memberId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_MEMBERID);) {
			preparedStatement.setInt(1, memberId);
			ResultSet rs = preparedStatement.executeQuery();
			
			Order order = new Order();
			if(rs.next()) {
				order.setOrderId(rs.getInt("order_id"));
				order.setMemberId(rs.getInt("member_id"));
				order.setStaffId(rs.getInt("staff_id"));
				order.setTotal(rs.getInt("total"));
			
				return order;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error! " + e);
			return null;
		}
	}
	
	public Reservation getReservationById(Integer reservationId) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATION_BY_ID);) {
			preparedStatement.setInt(1, reservationId);
			ResultSet rs = preparedStatement.executeQuery();
			
			Reservation reservation = new Reservation();
			if(rs.next()) {
				reservation.setReservationId(rs.getInt("reservation_id"));
				reservation.setMemberId(rs.getInt("member_id"));
				reservation.setTableId(rs.getInt("table_id"));
			
				return reservation;
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
				rating.setRateId(rs.getInt("rate_id"));
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
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
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
	
	public Member getMemberByEmailPassword(Member member) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_MEMBER_BY_EMAIL_PASSWORD);) {
			preparedStatement.setString(1, member.getEmail());
			preparedStatement.setString(2, member.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			Member newMember = new Member();
			if(rs.next()) {
				newMember.setMemberId(rs.getInt("member_id"));
				newMember.setEmail(rs.getString("email"));
				newMember.setPassword(rs.getString("password"));		
				newMember.setFirstName(rs.getString("first_name"));
				newMember.setLastName(rs.getString("last_name"));
				newMember.setQuestion(rs.getString("question"));
				newMember.setAnswer(rs.getString("answer"));
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
				newAdmin.setUsername(rs.getString("username"));
				newAdmin.setPassword(rs.getString("password"));
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
				food.setFoodId(rs.getInt("food_id"));
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