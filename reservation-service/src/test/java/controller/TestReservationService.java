package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import service.ReservationService;
import servicelib.ReservationResponse;

public class TestReservationService extends Mockito {
	HttpServletRequest request;       
    HttpServletResponse response; 
    ReservationService reservationService;
    
	
	@Before
	public void setUp() throws Exception {
		request = mock(HttpServletRequest.class);   
		response = mock(HttpServletResponse.class); 
		reservationService = mock(ReservationService.class);
	}
	
	// !!! rerun server to run these tests 

	@Test
	public void testServletSingle() throws IOException, ServletException {	
		String test = "{\"originCity\":\"A\",\"destinationCity\":\"B\",\"numOfTickets\":1}";
		Reader inputString = new StringReader(test);
        when(request.getReader()).thenReturn(new BufferedReader(inputString));
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReservationServlet().doPost(request, response);

        verify(request, atLeast(1)).getReader();
        writer.flush();
        
        Gson gson = new Gson();
        ReservationResponse res = gson.fromJson(stringWriter.toString(), ReservationResponse.class);
        System.out.println(stringWriter.toString());
        assertTrue(res.getAvailability());
        assertEquals(res.getTotalPrice(), 50);
	}
	
	@Test
	public void testServletMax() throws IOException, ServletException {	
		String test = "{\"originCity\":\"A\",\"destinationCity\":\"D\",\"numOfTickets\":4}";
		Reader inputString = new StringReader(test);
        when(request.getReader()).thenReturn(new BufferedReader(inputString));
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReservationServlet().doPost(request, response);

        verify(request, atLeast(1)).getReader();
        writer.flush();
        
        Gson gson = new Gson();
        ReservationResponse res = gson.fromJson(stringWriter.toString(), ReservationResponse.class);
        System.out.println(stringWriter.toString());
        assertTrue(res.getAvailability());
        assertEquals(res.getTotalPrice(), 600);
	}

}
