package servicelib;

import java.io.Serializable;

public class AvailabilityResponse implements Serializable {
	/**
	 * class for the response of http body when cheking availability
	 */
	private static final long serialVersionUID = 1L;
	
	protected boolean availability;
	protected String originCity;
	protected String destinationCity;
	protected int numOfTickets;
	protected int totalPrice;
	
	public AvailabilityResponse(String originCity, String destinationCity, 
			int numOfTickets, int totalPrice) {
		this.availability = true;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.numOfTickets = numOfTickets;
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "AvailabilityResponse [availability=" + availability + ", originCity=" + originCity
				+ ", destinationCity=" + destinationCity + ", numOfTickets=" + numOfTickets + ", totalPrice="
				+ totalPrice + "]";
	}

	public AvailabilityResponse() {
		this.availability = false;
	}
	
	public boolean getAvailability() {
		return availability;
	}

	public String getOriginCity() {
		return originCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public int getNumOfTickets() {
		return numOfTickets;
	}

	public int getTotalPrice() {
		return totalPrice;
	}	
}
