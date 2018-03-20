import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TemperatureProvider {

	private Connection conn;
	private Statement st;
	private String query;
	private ResultSet res;
	
	
	//CONSTRUCTOR
	public TemperatureProvider() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/temperatureDB", "root", "1234");
			this.st = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public ArrayList<String> getCities() {
		try {
			System.out.println("[TemperatureProvider: getCities ]: Begins");
			ArrayList<String> cities = new ArrayList<String>();
			String tempCity;
			this.query = "select city from temperatures group by city order by city";
			this.res = st.executeQuery(query);
			System.out.print("[");
			while(this.res.next())
			{
				tempCity=this.res.getString("city");
		        System.out.format("%s,",tempCity);
		        cities.add(tempCity);
			}
			System.out.println("]");
			System.out.println("[TemperatureProvider: getCities ]: Ends");
			return cities;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public ArrayList<String> getYears() {
		try {
			System.out.println("[TemperatureProvider: getYear ]: Begins");
			ArrayList<String> years = new ArrayList<String>();
			String tempYear;
			this.query = "select year from temperatures group by year order by year";
			this.res = st.executeQuery(query);
			System.out.print("[");
			while(this.res.next())
			{
				tempYear=this.res.getString("year");
		        System.out.format("%s,",tempYear);
		        years.add(tempYear);
			}
			System.out.println("]");
			System.out.println("[TemperatureProvider: getYear ]: Ends");
			return years;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public ArrayList<String> getMonths() {
		try {
			System.out.println("[TemperatureProvider: getMonth ]: Begins");
			ArrayList<String> months = new ArrayList<String>();
			String tempMonth;
			this.query = "select year,month from temperatures group by year,month order by year,month";
			this.res = st.executeQuery(query);
			System.out.print("[");
			while(this.res.next())
			{
				tempMonth=this.res.getString("year")+"_"+this.res.getString("month");
		        System.out.format("%s,",tempMonth);
		        months.add(tempMonth);
			}
			System.out.println("]");
			System.out.println("[TemperatureProvider: getMonth ]: Ends");
			return months;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String> getYearWhereCity(String city) {
		try {
			System.out.println("[TemperatureProvider: yearWhereCity ]: Begins");
			ArrayList<String> avg = new ArrayList<String>();
			float temp;
			this.query = "select avg(temperature) as avgtemp from temperatures where city='"+city+"' group by year order by year";
			this.res = st.executeQuery(query);
			while(this.res.next())
			{
				temp=this.res.getFloat("avgtemp");
		        System.out.format("%s,",temp);
		        avg.add(String.format("%.2f", temp));
			}
			System.out.println("[TemperatureProvider: yearWhereCity ]: Ends");
			return avg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	

	public ArrayList<String> getMonthWhereCity(String city) {
		try {
			System.out.println("[TemperatureProvider: getMonthWhereCity ]: Begins");
			ArrayList<String> avg = new ArrayList<String>();
			float temp;
			this.query = "select avg(temperature) as avgtemp from temperatures where city='"+city+"' group by year,month order by year,month";
			this.res = st.executeQuery(query);
			while(this.res.next())
			{
				temp=this.res.getFloat("avgtemp");
		        System.out.format("%s,",temp);
		        avg.add(String.format("%.2f", temp));
			}
			System.out.println("[TemperatureProvider: yearWhereCity ]: Ends");
			return avg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	
	public boolean closeStatement() {
		try {

			this.res.close();
			this.st.close();
			this.conn.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
