package controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import servicelib.AvailabilityResponse;

public class TestAvailabilityServlet extends Mockito {
	HttpServletRequest request;       
    HttpServletResponse response; 
	
	@Before
	public void setUp() throws Exception {
		request = mock(HttpServletRequest.class);   
		response = mock(HttpServletResponse.class); 
	}

	@Test
	public void testServletSingle() throws IOException, ServletException {
        when(request.getParameter("originCity")).thenReturn("A");
        when(request.getParameter("destinationCity")).thenReturn("B");
        when(request.getParameter("numOfTickets")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new AvailabilityServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("originCity");
        writer.flush();
        
        Gson gson = new Gson();
        AvailabilityResponse res = gson.fromJson(stringWriter.toString(), AvailabilityResponse.class);
        assertTrue(res.getAvailability());
        assertEquals(res.getTotalPrice(), 50);
	}
	
	@Test
	public void testServletMaxReq() throws IOException, ServletException {
        when(request.getParameter("originCity")).thenReturn("A");
        when(request.getParameter("destinationCity")).thenReturn("D");
        when(request.getParameter("numOfTickets")).thenReturn("40");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new AvailabilityServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("originCity");
        writer.flush();
        
        Gson gson = new Gson();
        AvailabilityResponse res = gson.fromJson(stringWriter.toString(), AvailabilityResponse.class);
        assertTrue(res.getAvailability());
        assertEquals(res.getTotalPrice(), 6000);
	}
	
	@Test
	public void testServletExceed() throws IOException, ServletException {
        when(request.getParameter("originCity")).thenReturn("A");
        when(request.getParameter("destinationCity")).thenReturn("D");
        when(request.getParameter("numOfTickets")).thenReturn("41");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new AvailabilityServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("originCity");
        writer.flush();
        
        Gson gson = new Gson();
        AvailabilityResponse res = gson.fromJson(stringWriter.toString(), AvailabilityResponse.class);
        assertFalse(res.getAvailability());
        assertEquals(res.getTotalPrice(), 0);
	}
}
