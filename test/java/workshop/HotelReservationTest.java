package workshop;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HotelReservationTest {

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
			result = hotel.cheapestHotel("12Sep2020", "13Sep2020");
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
			result = hotel.cheapestHotel("10Sep2020", "11Sep2020");
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
			result = hotel.cheapestHotel("11Sep2020", "12Sep2020");
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
			result = hotel.cheapestBestRated("11Sep2020", "12Sep2020");
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
			result = hotel.cheapestBestRated("11Sep2020", "12Sep2020");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Bridgewood", result);
	}
	@Test
	public void givenNewDatawithRatings_WhenAddedToMap_ShouldReturnBestRated() {
		HotelReservation hotel = new HotelReservation();
		hotel.add("Lakewood", 200, 90, 3);
		hotel.add("Bridgewood", 180, 50, 4);
		hotel.add("Ridgewood", 220, 150, 5);
		String result = "";
		try {
			result = hotel.bestRatedHotel("11Sep2020", "12Sep2020");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Ridgewood", result);
	}

}

