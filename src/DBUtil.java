import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private static Connection connection;

	private static void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/logos", "root", "root");
		System.out.println("Connected to MySQL server");
	}

	private static void getList() throws SQLException{
		PreparedStatement preparedStatement = connection.prepareStatement("select * from author");		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			System.out.println("ID: " + resultSet.getInt("id") + " -> Name:" + resultSet.getString("name"));
		}
	}
	
	
	public static void insert (int id, String name) throws SQLException{
		PreparedStatement preparedStatement = connection.prepareStatement("insert into author (id, name) values(?, ?)");
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.executeUpdate();
	}
	
	

	private static void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DBUtil.getConnection();
		
		DBUtil.insert(77, "twain");
		DBUtil.getList();
		
		
		DBUtil.closeConnection();
	}

}
