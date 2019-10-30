package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.AvailabilityService;
import servicelib.AvailabilityResponse;
import servicelib.Request;


/**
 * Servlet implementation class AvailabilityServlet
 */
@WebServlet("/check")
public class AvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AvailabilityService availabilityService;

    /**
     * Default constructor. 
     */
    public AvailabilityServlet() {
    	availabilityService = new AvailabilityService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Gson gson = new Gson();		
		Request reqObj = this.availabilityService.buildRequestObj(request);
		
		int totalPrice = this.availabilityService.processAvailabilityRequest(reqObj);
		AvailabilityResponse availabilityResponse = this.availabilityService.createResponse(reqObj, totalPrice);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String responseJson = gson.toJson(availabilityResponse);
		
		PrintWriter out = response.getWriter();
		out.write(responseJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Gson gson = new Gson();
		BufferedReader reader = request.getReader();
		
		Request reqObj = gson.fromJson(reader.lines().collect(Collectors.joining()), Request.class);
		
		int totalPrice = this.availabilityService.processReservationRequest(reqObj);
		AvailabilityResponse availabilityResponse = this.availabilityService.createResponse(reqObj, totalPrice);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String responseJson = gson.toJson(availabilityResponse);		
		PrintWriter out = response.getWriter();
		out.write(responseJson);
	}

}
