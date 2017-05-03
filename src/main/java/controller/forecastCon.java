package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ApiClient;
import model.Weather;

class forecastCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiClient api = new ApiClient();
		
		String province = request.getParameter("province");
		ArrayList<Weather> wForecast = new ArrayList<Weather>();
	}

}
