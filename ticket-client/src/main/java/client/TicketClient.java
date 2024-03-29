package client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import servicelib.AvailabilityResponse;
import servicelib.HttpService;
import servicelib.Request;
import servicelib.ReservationResponse;

public class TicketClient {
	private static Request req = new Request("A", "B", 1);
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 4; i++) {
			Sender sender = new Sender(req);
			executorService.submit(sender);
		}
		executorService.shutdown();
	}	
	
	public static class Sender implements Runnable {
		private Request req;
		
		public Sender(Request req) {
			this.req = req;
		}
		
		public void getAvailablity(Request request) throws Exception {
			AvailabilityResponse avbRes = (AvailabilityResponse) HttpService
					.sendGet("http://localhost:8080/availability-service/check", request);
			System.out.println(avbRes.toString());
		}
		
		public void setRevervation(Request request) throws Exception {
			ReservationResponse resvRes = (ReservationResponse) HttpService
					.sendPost("http://localhost:8080/reservation-service/reserve", request);
			System.out.println(resvRes.toString());
		}
		
		public void sendBulk() {
			try {
				for (int i = 0; i < 20; i++) {
					getAvailablity(req);
					setRevervation(req);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		

		@Override
		public void run() {
			sendBulk();			
		}		
	}
}

