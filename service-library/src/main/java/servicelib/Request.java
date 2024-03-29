package servicelib;

import java.io.Serializable;

public class Request implements Serializable {
	/**
	 * class for requesting both availablity and reservation in http body
	 */
	private static final long serialVersionUID = 1L;
	
	private String originCity;
	private String destinationCity;
	private int numOfTickets;
	
	public Request(String originCity, String destinationCity, int numOfTickets) {
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.numOfTickets = numOfTickets;
	}

	public Request() {}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public int getNumOfTickets() {
		return numOfTickets;
	}

	public void setNumOfTickets(int numOfTickets) {
		this.numOfTickets = numOfTickets;
	}
}
