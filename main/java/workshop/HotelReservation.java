package workshop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	public static Map<String, Integer> createRentMap(String[] arguments) throws ParseException {
		HashMap<String, Integer> rentMap = new HashMap<>();
		int tempValue;
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			int rent = 0;
			for (int i = 0; i < arguments.length; i++) {
				String input_date = arguments[i];
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = dateFormat.parse(input_date);
				DateFormat date_Format = new SimpleDateFormat("EEEE");
				String dayOfWeek = date_Format.format(date);
				tempValue = entry.getValue().calculateRent("Regular", dayOfWeek);
				rent = tempValue + rent;
			}
			rentMap.put(entry.getValue().getHotelName(), rent);
		}
		return rentMap;
	}

	/**
	 * Finding the cheapest hotel from different hotel
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	public String cheapestHotel(String fromDate, String toDate) throws ParseException {
		String[] arguments = { fromDate, toDate };
		String hotelName = "";
		Map<String, Integer> rentMap = createRentMap(arguments); 						//Creating a rent map for hotels for the dates
		List<Integer> hotelRates = new ArrayList<>();
		hotelRates = rentMap.values().stream().collect(Collectors.toList());			// list of hotel rates 
		Collections.sort(hotelRates);
		for (Map.Entry<String, Integer> entry : rentMap.entrySet()) {
			if (entry.getValue() == (int) hotelRates.get(0)) {							// Comparing the hotel rate with cheapest rate for hotel name
				hotelName = entry.getKey();
			}
		}
		return hotelName;
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