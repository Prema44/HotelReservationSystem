package workshop;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
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
	
	public void add(String name, int regularWD, int regularWK, int ratings, int rewardsWD, int rewardsWK) {
		Hotel hotel = new Hotel(name, regularWD, regularWK, ratings, rewardsWD, rewardsWK);
		hotelMap.put(name, hotel);
	}

	/**
	 * finding the number of days in the date range
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	public static int[] numOfDays(String fromDate, String toDate) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate from = LocalDate.parse(fromDate, formatter);
		LocalDate to = LocalDate.parse(toDate, formatter);
		int numOfweekdays = 0;
		int numOfWeekends = 0;
		for (LocalDate date = from; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
			DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
			switch (day) {
			case SATURDAY:
				numOfWeekends++;
				break;
			case SUNDAY:
				numOfWeekends++;
				break;
			default:
				numOfweekdays++;
				break;
			}
		}
		return new int[] { numOfweekdays, numOfWeekends };
	}

	public static Map<String, Integer> createRentMap(String fromDate, String toDate, String type)
			throws ParseException {
		HashMap<String, Integer> rentMap = new HashMap<>();
		int[] numOfDays = numOfDays(fromDate, toDate);
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			int wdRent = entry.getValue().calculateRent(type, "Weekday") * numOfDays[0];
			int weRent = entry.getValue().calculateRent(type, "Weekend") * numOfDays[1];
			int totalRent = wdRent + weRent;
			rentMap.put(entry.getValue().getHotelName(), totalRent);
		}
		return rentMap;
	}

	public String cheapestBestRated(String fromDate, String toDate, String type) throws ParseException {
		List<String> cheapHotels = cheapestHotel(fromDate, toDate, type);
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
		System.out.println(hotelName + "," + "Rating: " + rating + " Total rate is "
				+ createRentMap(fromDate, toDate, type).get(hotelName));
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
	public List<String> cheapestHotel(String fromDate, String toDate, String type) throws ParseException {
		int count = 0;
		Map<String, Integer> rentMap = createRentMap(fromDate, toDate, type); // Creating a rent map for hotels for the
																				// dates
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

	public String bestRatedHotel(String fromDate, String toDate, String type) throws ParseException {
		int rating = 0;
		String hotelName = "";
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			if (entry.getValue().getRatings() > rating) {
				rating = entry.getValue().getRatings();
				hotelName = entry.getKey();
			}
		}
		System.out.println(hotelName + "," + " Total rate is " + createRentMap(fromDate, toDate, type).get(hotelName));
		return hotelName;
	}


	/**
	 * Printing the hotel data
	 */
	public void printHotels() {
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			System.out.println("The hotel is " + entry.getKey() + " and it's rate is " + entry.getValue().getRegularWD());
		}
	}

	public int size() {
		return hotelMap.size();
	}
}