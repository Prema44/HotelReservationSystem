package workshop;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static TreeMap<Integer, LinkedList<String>> createRentMap(String fromDate, String toDate, String type)
			throws ParseException {
		TreeMap<Integer, LinkedList<String>> rentMap = new TreeMap<>();
		int[] numOfDays = numOfDays(fromDate, toDate);
		hotelMap.forEach((k, v) -> {
			int wdRent = v.calculateRent(type, "Weekday") * numOfDays[0];
			int weRent = v.calculateRent(type, "Weekend") * numOfDays[1];
			int totalRent = wdRent + weRent;
			rentMap.computeIfAbsent(totalRent, t -> new LinkedList<>()).add(k);
		});
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
				+ createRentMap(fromDate, toDate, type).firstKey());
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
	public LinkedList<String> cheapestHotel(String fromDate, String toDate, String type) throws ParseException {
		TreeMap<Integer, LinkedList<String>> rentMap = createRentMap(fromDate, toDate, type); // Creating a rent map for
																								// hotels for the
		LinkedList<String> cheapestHotels = rentMap.get(rentMap.firstKey());
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
		System.out.println(hotelName + "," + " Total rate is " + createRentMap(fromDate, toDate, type).firstKey());
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

	/**
	 * Validating the dates for the regular expression
	 * 
	 * @param fromDate
	 * @param toDate
	 * @throws HotelException
	 */
	public void validateUserInputs(String fromDate, String toDate) throws HotelException {
		String expression = "^[0-9]{2}[A-Za-z]{3}[0-9]{4}$";
		Pattern pattern = Pattern.compile(expression);
		Matcher from = pattern.matcher(fromDate);
		Matcher to = pattern.matcher(toDate);
		if (!from.find() || !to.find()) {
			throw new HotelException("Invalid Inputs");
		}
		return;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws HotelException {
		HotelReservation hotel = new HotelReservation();
		Scanner scanner = new Scanner(System.in);
		hotel.add("Lakewood", 110, 90, 3, 80, 80);
		hotel.add("Bridgewood", 150, 50, 4, 110, 50);
		hotel.add("Ridgewood", 220, 150, 5, 100, 40);
		System.out.println("Enter the date for hotel");
		String from = scanner.nextLine();
		String to = scanner.nextLine();
		System.out.println("Enter the type of customer");
		String type = scanner.nextLine();
		hotel.validateUserInputs(from, to);
		try {
			hotel.bestRatedHotel(from, to, type);
		} catch (Exception e) {
			throw new HotelException("Invalid Date Entry");
		}
		scanner.close();
	}
}