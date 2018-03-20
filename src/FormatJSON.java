import java.util.ArrayList;


public class FormatJSON {

	private TemperatureProvider tp;

	//CONSTRUCTOR
	public FormatJSON(TemperatureProvider tp) {
		this.tp = tp;
	}
	
	public String getArrayInJSONFormat(ArrayList<String> data,boolean isString) {
		String jsonData="[";
		for (String d : data) {
			if(isString)
				jsonData = jsonData +"\""+ d +"\",";
			else
				jsonData = jsonData + d +",";
		}
		jsonData = jsonData.substring(0, jsonData.length()-1) + "]";
		System.out.println("[FormatJSON: getArrayInJSONFormat]: "+jsonData);
		return jsonData;
	}
	
	public String yAxisYearlyData() {
		ArrayList<String> cities = this.tp.getCities();
		String series="[";
		for(String city : cities) {
			String seriesObj = "{\"name\":\"" + city + "\",\"data\":" + getArrayInJSONFormat(this.tp.getYearWhereCity(city),false) + "}";
			series = series + seriesObj +",";
		}
		series = series.substring(0, series.length()-1) + "]";
		return series;
	}

	
	public String yAxisMonthlyData() {
		ArrayList<String> cities = this.tp.getCities();
		String series="[";
		for(String city : cities) {
			String seriesObj = "{\"name\":\"" + city + "\",\"data\":" + getArrayInJSONFormat(this.tp.getMonthWhereCity(city),false) + "}";
			series = series + seriesObj +",";
		}
		series = series.substring(0, series.length()-1) + "]";
		return series;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static String getInJSONFormat(ArrayList<RawData> md, MonthlyData m) {
		String label="label: [",data="data: [";
		for (RawData rawData : md) {
			MonthlyData monthlyData = (MonthlyData)rawData;
			label = label+monthlyData.month+",";
			data = data+monthlyData.temperature+",";
		}
		label = label+"]";
		data = data+"]";
		System.out.println("[FormatJSON: Yearly]: "+"{"+label+","+data+"}");
		return "{"+label+","+data+"}";
	}
	
	public static String getInJSONFormat(ArrayList<RawData> yd, YearlyData y) {
		String label="label: [",data="data: [";
		for (RawData rawData : yd) {
			YearlyData yearlyData = (YearlyData)rawData;
			label = label+yearlyData.year+",";
			data = data+yearlyData.temperature+",";
		}
		label = label+"]";
		data = data+"]";		
		System.out.println("[FormatJSON: Monthly]: "+"{"+label+","+data+"}");
		return "{"+label+","+data+"}";
	}
	*/
}
