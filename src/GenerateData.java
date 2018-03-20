import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class GenerateData {

	public GenerateData() {
	}

	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/temperatureDB", "root", "1234");
			PreparedStatement pState = conn.prepareStatement("insert into temperaturedb values(?,?,?,?,?)");
			
			String[] cities = {"kolkata", "mumbai","delhi","channei"};

			for (String city : cities) {
				LocalDate dt = LocalDate.of(1990, 1, 1);
				while(dt.isBefore(LocalDate.of(1995, 1, 1))) {
					pState.setString(1, city);
					pState.setInt(2, dt.getYear());
					pState.setInt(3, dt.getMonthValue());
					pState.setDouble(4, Math.random()*30);
					pState.addBatch();
					dt = dt.plusDays(1);
				}
			}
			pState.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
