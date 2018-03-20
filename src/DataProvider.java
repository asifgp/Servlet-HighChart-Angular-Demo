

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DataProvider
 */
@WebServlet("/HighChart")
public class DataProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemperatureProvider tp;
	private String requestURL;
	private FormatJSON json;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataProvider() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	this.tp = new TemperatureProvider();
    	this.json = new FormatJSON(this.tp);
    	
		this.requestURL = request.getRequestURI();
		System.out.println("[Servlet]: Request URL :"+this.requestURL);
		if(this.requestURL.contains("yearly")) {
			response.getWriter().print(json.yAxisYearlyData());
		}
		else if(this.requestURL.contains("monthly")){
			response.getWriter().print(json.yAxisMonthlyData());
		}
		else if(this.requestURL.contains("cities")) {
			response.getWriter().println(json.getArrayInJSONFormat(tp.getCities(),true));
		}
		else if(this.requestURL.contains("years")) {
			response.getWriter().println(json.getArrayInJSONFormat(tp.getYears(),false));
		}
		else if(this.requestURL.contains("months")) {
			response.getWriter().println(json.getArrayInJSONFormat(tp.getMonths(),false));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
