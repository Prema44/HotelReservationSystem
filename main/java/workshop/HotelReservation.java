package workshop;

import java.util.HashMap;
import java.util.Map;

public class HotelReservation {
	static Map<String, Hotel> hotelMap;

	public HotelReservation() {
		hotelMap = new HashMap<>();
	}

	/**
	 * Adding the hotel to map
	 * @param name
	 * @param regularWD
	 */
	public void add(String name, int regularWD) {
		Hotel hotel = new Hotel(name, regularWD);
		hotelMap.put(name, hotel);
	}

	/**
	 * Printing the hotel data
	 */
	public void printHotels() {
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			System.out
					.println("The hotel is " + entry.getKey() + " and it's rate is " + entry.getValue().getRegularWD());
		}
	}

	public int size() {
		return hotelMap.size();
	}
}	