package service;

import cache.Cache;
import servicelib.AvailabilityResponse;
import servicelib.Request;
import servicelib.ReservationResponse;
import util.ServiceLogger;
import servicelib.HttpService;

public class ReservationService {
	/**
	 * 
	 */
	private Cache cache;

	public ReservationService() {
		this.cache = Cache.getInstance();
	}
	
	/**
	 * check validity of request 
	 * correct cities, if there is bus route
	 * @param request
	 * @return
	 */
	public boolean validateRequest(Request request) {
		// TODO check the validity of the request 
		return true;
	}

	/**
	 * check availability, reserve return resonse object 
	 * @param request
	 * @return
	 */
	synchronized public ReservationResponse processRequest(Request request) {
		/**
		 * syncronized added until the temporary seat hold is implemented
		 * in availability checking
		 */
		
		if (!validateRequest(request)) {
			// TODO send bad request, response
		}
		ServiceLogger.LOGGER.info("Reservation request validity cheking done");
		
		try {
			ServiceLogger.LOGGER.info("Availability request sending...");
			
			AvailabilityResponse res1 = (AvailabilityResponse) HttpService
					.sendGet("http://localhost:8080/availability-service/check", request);
			
			ServiceLogger.LOGGER.info("Availability response done");

			if (res1.getAvailability()) {
				// if available send reservation request

				ServiceLogger.LOGGER.info("Reservation request sending...");
				
				AvailabilityResponse res2 = (AvailabilityResponse) HttpService
						.sendPost("http://localhost:8080/availability-service/check", request);
				
				ServiceLogger.LOGGER.info("Reservation response done");
				
				return createResponse(request, res2.getTotalPrice());
			} else {
				return new ReservationResponse();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * create response using data from cache
	 * @param request
	 * @param totalPrice
	 * @return
	 */
	synchronized public ReservationResponse createResponse(Request request, int totalPrice) {
		ServiceLogger.LOGGER.info("Reservation response creating...");
		
		ReservationResponse resp = new ReservationResponse(this.cache.getReservationId(), request.getOriginCity(),
				request.getDestinationCity(), request.getNumOfTickets(), totalPrice);

		resp.setSeatNumbers(cache.getSeats(request));

		return resp;
	}
}
