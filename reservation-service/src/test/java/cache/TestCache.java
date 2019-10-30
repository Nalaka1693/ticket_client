package cache;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import servicelib.Request;

public class TestCache extends Mockito {
	static Cache cache;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cache = Cache.getInstance();
	}

	@Test
	public void testGetSeats() {
		Request request = mock(Request.class);
		
		when(request.getOriginCity()).thenReturn("A");
        when(request.getDestinationCity()).thenReturn("D");
        when(request.getNumOfTickets()).thenReturn(4);
        
        ArrayList<String> result = cache.getSeats(request);
        String [] actual = {"[1A]", "[1B]","[1C]","[1D]"};
        
        assertArrayEquals(result.toArray(), actual);
	}

	@Test
	public void testGetReservationId() {
		
	}

}
