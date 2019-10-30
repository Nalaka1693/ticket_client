package cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.RequestProcessor;
import servicelib.Request;

public class Cache {
	/**
	 * Database to hold seat utilization and seat number handeling
	 */
	private static Cache instance = null;
	
	private static int reservationId = 1;

	private ArrayList<Seat> seatList;
	private RequestProcessor reqProcessor;
	private Map<UnitJourney, Integer> unitJourneySeatCountMap;
	
	private Cache() {
		this.seatList = new ArrayList<Seat>();
		this.reqProcessor = new RequestProcessor();
		this.unitJourneySeatCountMap = new HashMap<UnitJourney, Integer>();
		
		for (int i = 1; i <= 10; i++) {
			seatList.add(new Seat(i, 'A'));
			seatList.add(new Seat(i, 'B'));
			seatList.add(new Seat(i, 'C'));
			seatList.add(new Seat(i, 'D'));
		}
		
		populateJourSeatMap("");
	}
	
	public static Cache getInstance() {
		if (instance == null) {
			instance = new Cache();
		}
		
		return instance;
	}
	
	/**
	 * create unit journey price map by reading a CSV
	 * assuming price between nearest cities are given
	 * and price accumilated along the path 
	 * TODO read from a CSV file and update map
	 * @param csvFileName
	 */
	public void populateJourSeatMap(String csvFileName) {
		UnitJourney j1 = new UnitJourney("A", "B");
		UnitJourney j2 = new UnitJourney("B", "C");
		UnitJourney j3 = new UnitJourney("C", "D");
		
		unitJourneySeatCountMap.put(j1, 0);
		unitJourneySeatCountMap.put(j2, 0);
		unitJourneySeatCountMap.put(j3, 0);
	}
	
	/**
	 * get requested seat numbers for a request from the remaining seats
	 * @param request
	 * @return
	 */
	synchronized public ArrayList<String> getSeats(Request request) {
		ArrayList<String> allocatedSeats = new ArrayList<String>();		
		ArrayList<UnitJourney> unitJournies = this.reqProcessor.getUnitJournies(request);
		
		int numOfSeats = request.getNumOfTickets();
		int nextSeat = unitJourneySeatCountMap.get(unitJournies.get(0));
		
		System.out.println(nextSeat);		
		for (int i = 0; i < numOfSeats; i++) {
			allocatedSeats.add(seatList.get(nextSeat++).toString());
		}
		
		for (UnitJourney unitJourney : unitJournies) {
			int currntSeat = unitJourneySeatCountMap.get(unitJourney);
			unitJourneySeatCountMap.put(unitJourney, currntSeat + numOfSeats);
		}		
		
		return allocatedSeats;
	}
	
	synchronized public int getReservationId() {
		return reservationId++;
	}
}

