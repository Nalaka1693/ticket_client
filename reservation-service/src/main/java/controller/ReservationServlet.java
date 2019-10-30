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

import service.ReservationService;
import servicelib.Request;
import servicelib.ReservationResponse;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/reserve")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReservationService reservationService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
       this.reservationService = new ReservationService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		Gson gson = new Gson();
		
		BufferedReader reader = request.getReader();
		// convert body to object
		Request reqObj = gson.fromJson(reader.lines().collect(Collectors.joining()), Request.class);
		
		ReservationResponse resvResp = this.reservationService.processRequest(reqObj);
				
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String responseJson = gson.toJson(resvResp);
		
		PrintWriter out = response.getWriter();
		out.write(responseJson);
	}
}
