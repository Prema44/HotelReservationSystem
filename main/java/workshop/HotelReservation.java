package workshop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {
	static Map<String, Hotel> hotelMap;

	public HotelReservation() {
		hotelMap = new HashMap<>();
	}

	/**
	 * Adding the hotel to map
	 * 
	 * @param name
	 * @param regularWD
	 */
	public void add(String name, int regularWD) {
		Hotel hotel = new Hotel(name, regularWD);
		hotelMap.put(name, hotel);
	}

	/**
	 * Adding the hotel to map
	 * 
	 * @param name
	 * @param regularWD
	 */
	public void add(String name, int regularWD, int regularWE) {
		Hotel hotel = new Hotel(name, regularWD, regularWE);
		hotelMap.put(name, hotel);
	}
	
	public void add(String name, int regularWD, int regularWE, int rating) {
		Hotel hotel = new Hotel(name, regularWD, regularWE, rating);
		hotelMap.put(name, hotel);
	}

	public static String dayOfWeek(String args) throws ParseException {
		String input_date = args;
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		Date date = dateFormat.parse(input_date);
		DateFormat date_Format = new SimpleDateFormat("EEEE");
		String dayOfWeek = date_Format.format(date);
		return dayOfWeek;
	}

	public static Map<String, Integer> createRentMap(String[] arguments) throws ParseException {
		HashMap<String, Integer> rentMap = new HashMap<>();
		int tempValue;
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			int rent = 0;
			for (int i = 0; i < arguments.length; i++) {
				tempValue = entry.getValue().calculateRent("Regular", dayOfWeek(arguments[i]));
				rent = tempValue + rent;
			}
			rentMap.put(entry.getValue().getHotelName(), rent);
		}
		return rentMap;
	}
	
	public String cheapestBestRated(String fromDate, String toDate) throws ParseException {
		List<String> cheapHotels = cheapestHotel(fromDate, toDate);
		int rating = 0;
		int count = 0;
		String hotelName = "";
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			if (entry.getKey().equals(cheapHotels.get(count))) {
				if (entry.getValue().getRatings() > rating) {
					rating = entry.getValue().getRatings();
					hotelName = entry.getKey();
					if (cheapHotels.size() != 1) {
						count++;
					}
				}
			}
		}
		System.out.println();
		return hotelName;
	}

	/**
	 * Finding the cheapest hotel from different hotel
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	public List<String> cheapestHotel(String fromDate, String toDate) throws ParseException {
		String[] arguments = { fromDate, toDate };
		int count = 0;
		Map<String, Integer> rentMap = createRentMap(arguments); // Creating a rent map for hotels for the dates
		List<Integer> hotelRates = new ArrayList<>();
		hotelRates = rentMap.values().stream().collect(Collectors.toList()); // list of hotel rates
		Collections.sort(hotelRates);
		List<String> cheapestHotels = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : rentMap.entrySet()) {
			if (entry.getValue() == (int) hotelRates.get(0)) { // Comparing the hotel rate with cheapest rate for hotel
																// // name
				cheapestHotels.add(entry.getKey());
				count++;
			}
		}
		for (int i = 0; i < count; i++) {
			System.out.print(cheapestHotels.get(i) + " - ");
		}
		System.out.println(" with The Total Rates $" + hotelRates.get(0));
		return cheapestHotels;
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