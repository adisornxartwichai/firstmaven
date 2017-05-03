

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Servlet implementation class Weather
 */
public class Weather extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		
		String placeName = "ขอนแก่น";
		OkHttpClient client = new OkHttpClient();
		// 1. ก ำหนด URL ของเว็บเซอร์วิส Google
		String googleUrl = "http://data.tmd.go.th/api/WeatherForecast7Days/v1/?type=json";
		// 2. เรย ี กใช้ Web API
		Request req= new Request.Builder().url(googleUrl).build();
		Response res= client.newCall(req).execute();
		// 3. แปลข้อมูลจำก String ให้อยู่ในรูปแบบ Object ของ JSON
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(res.body().string());
		// 4. ดึงข้อมูลด้วย JSONPath
		//Double lat = JsonPath.read(document, "$.results[0].geometry.location.lat");
		//Double lng = JsonPath.read(document, "$.results[0].geometry.location.lng");
		//System.out.println(lat + ", " + lng);
		
		out.println("<form action='Weather'>");
		out.println("<input type='text' name='province'>");
		out.println("<button type='submit'>ค้นหา</button>");
		out.println("</form>");
		out.println("<br>");
		
		List<String> name = JsonPath.read(document, "$..ProvinceNameTh");
		String x = request.getParameter("province");
		
		List<Integer> maxTemp = JsonPath.read(document, "$.Provinces[?(@.ProvinceNameTh=='"+x+"')].SevenDaysForecast[*].MaxTemperature.Value");
		
		out.println("อุณหภูมิสูงสุดจังหวัด"+x+"");
		out.println("<br>");
		for(int i=0; i<maxTemp.size(); i++) {
			
			out.println("วันที่  : " + (i+1) + " คือ ");
			
			out.println(maxTemp.get(i));
			out.println("<br>");
			
			
		}
		
		
		out.println("<u>");
		out.println("จังหวัดทั้งหมดในประเทศไทย");
		out.println("</u>");
		out.print("<br>");
			
			for(int i=0; i<name.size(); i++) {
				
				out.println(name.get(i));
				out.println("<br>");
				
				
			}
			
		
	}

}
