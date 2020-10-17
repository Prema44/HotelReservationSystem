package workshop;

import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import org.junit.jupiter.api.Test;
import java.util.*;

class HotelReservationTest {

	/**
	 * Usecase1 for adding hotel's data
	 */
	@Test
	public void givenHotelData_WhenAddedToMap_ShouldPassResult() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110);
		hotel.add("Bridgewood", 150);
		hotel.add("Ridgewood", 220);
		hotel.printHotels();
		int result = hotel.size();
		assertEquals(3, result);
	}

	/**
	 * Usecase3 for weekend and weekday rates
	 */
	@Test
	public void givenHotelDatawithWeekDayRates_WhenAddedToMap_ShouldPassResult() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90);
		hotel.add("Bridgewood", 150, 50);
		hotel.add("Ridgewood", 220, 150);
		int result = hotel.size();
		assertEquals(3, result);
	}

	/**
	 * Usecase2 for finding cheapest hotel, Modified for weekend rates
	 * 
	 */
	@Test
	public void givenHotelData_WhenAddedToMap_ShouldReturnCheapestHotel() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90);
		hotel.add("Bridgewood", 150, 50);
		hotel.add("Ridgewood", 220, 150);
		List<String> result = new ArrayList<>();
		try {
			result = hotel.cheapestHotel("12Sep2020", "13Sep2020", "Regular");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Bridgewood", result.get(0));
	}

	/**
	 * Usecase2 for finding cheapest hotel, Modified for weekend rates
	 */
	@Test
	public void givenNewHotelData_WhenAddedToMap_ShouldReturnCheapestHotel() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 330, 120);
		hotel.add("Bridgewood", 160, 60);
		hotel.add("Ridgewood", 440, 180);
		List<String> result = new ArrayList<>();
		try {
			result = hotel.cheapestHotel("10Sep2020", "11Sep2020", "Regular");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Bridgewood", result.get(0));
	}

	/**
	 * Usecase4 for finding the two cheapest hotels
	 */
	@Test
	public void givenHotelData_WhenAddedToMap_ShouldReturnListOfCheapestHotel() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90);
		hotel.add("Bridgewood", 150, 50);
		hotel.add("Ridgewood", 220, 150);
		List<String> result = new ArrayList<>();
		try {
			result = hotel.cheapestHotel("11Sep2020", "12Sep2020", "Regular");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Bridgewood", result.get(0));
		assertEquals("Lakewood", result.get(1));
	}

	/**
	 * Usecase5 for adding data along with ratings for hotel
	 */
	@Test
	public void givenHotelDatawithRatings_WhenAddedToMap_ShouldPassResult() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90, 3);
		hotel.add("Bridgewood", 150, 50, 4);
		hotel.add("Ridgewood", 220, 150, 5);
		int result = hotel.size();
		assertEquals(3, result);
	}

	/**
	 * Usecase6 for finding cheapest best rated hotel
	 */
	@Test
	public void givenHotelDatawithRatings_WhenAddedToMap_ShouldReturnBestRated() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90, 3);
		hotel.add("Bridgewood", 150, 50, 4);
		hotel.add("Ridgewood", 220, 150, 5);
		String result = "";
		try {
			result = hotel.cheapestBestRated("11Sep2020", "12Sep2020", "Regular");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Bridgewood", result);
	}

	/**
	 * Finding cheapest best rated hotel(handled for out of bound exception )
	 */
	@Test
	public void givenNewDatawithRatings_WhenAddedToMap_ShouldReturnCheapestBestRated() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 200, 90, 3);
		hotel.add("Bridgewood", 180, 50, 4);
		hotel.add("Ridgewood", 150, 150, 5);
		String result = "";
		try {
			result = hotel.cheapestBestRated("11Sep2020", "12Sep2020", "Regular");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Bridgewood", result);
	}

	/**
	 * Usecase7 for finding best rated hotel
	 */
	@Test
	public void givenNewDatawithRatings_WhenAddedToMap_ShouldReturnBestRated() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 200, 90, 3);
		hotel.add("Bridgewood", 180, 50, 4);
		hotel.add("Ridgewood", 220, 150, 5);
		String result = "";
		try {
			result = hotel.bestRatedHotel("11Sep2020", "12Sep2020", "Regular");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Ridgewood", result);
	}

	/**
	 * Usecase8 for assigning rates for reward customer
	 */
	@Test
	public void givenHotelDataForRewardCustomer_WhenAddedToMap_ShouldPassResult() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90, 3, 80, 80);
		hotel.add("Bridgewood", 150, 50, 4, 110, 50);
		hotel.add("Ridgewood", 220, 150, 5, 100, 40);
		int result = hotel.size();
		assertEquals(3, result);
	}

	/**
	 * Usecase9 finding cheapest hotel for reward customer
	 */
	@Test
	public void givenHotelData_WhenAddedToMap_ShouldReturnCheapestBestRated() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90, 3, 80, 80);
		hotel.add("Bridgewood", 150, 50, 4, 110, 50);
		hotel.add("Ridgewood", 220, 150, 5, 100, 40);
		String result = "";
		try {
			result = hotel.cheapestBestRated("11Sep2020", "13Sep2020", "Reward");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Ridgewood", result);
	}

	/**
	 * Usecase9 for taking inputs for date range and type of customer and finding
	 * cheapest best rated hotel for reward customer
	 * 
	 * @throws HotelException
	 */
	@Test
	public void takingDataFromUser_WhenAddedToMap_ShouldReturnCheapestBestRated() throws HotelException {
		Scanner scanner = new Scanner(System.in);
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 110, 90, 3, 80, 80);
		hotel.add("Bridgewood", 150, 50, 4, 110, 50);
		hotel.add("Ridgewood", 220, 150, 5, 100, 40);
		System.out.println("Enter the date range: ");
		String from = "";
		String to = "";
		try {
			from = scanner.nextLine();
			to = scanner.nextLine();
		} catch (Exception e) {
			throw new HotelException("Invalid date range");
		}
		System.out.println("Enter the type of customer");
		String type = "";
		try {
			type = scanner.nextLine();
		} catch (Exception e) {
			throw new HotelException("Invalid type");
		}
		String result = "";
		try {
			result = hotel.cheapestBestRated(from, to, type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Ridgewood", result);
		scanner.close();
	}

	/**
	 * Usecase11 for finding cheapest best rated hotel for Reward customer
	 * 
	 * @throws ParseException
	 */
	@Test
	void cheapestBestRated_ShouldReturnTrue() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add("Lakewood", 110, 90, 3, 80, 80);
		hotelReservation.add("Bridgewood", 150, 50, 4, 110, 50);
		hotelReservation.add("Ridgewood", 220, 150, 5, 100, 40);
		assertEquals("Ridgewood", hotelReservation.cheapestBestRated("11Sep2020", "12Sep2020", "Reward"));
	}

	/**
	 * Giving invalid inputs for custom exception
	 */
	@Test
	void whenInvalidEntriesAreGiven_shouldThrow_InvalidEntryException() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.add("Lakewood", 110, 90, 3, 80, 80);
		hotelReservation.add("Bridgewood", 150, 50, 4, 110, 50);
		hotelReservation.add("Ridgewood", 220, 150, 5, 100, 40);
		assertThrows(HotelException.class, () -> {
			hotelReservation.validateUserInputs("11 Sep 2020", "12 Sep 2020");
		});
	}
}

