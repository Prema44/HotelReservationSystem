package workshop;

import java.time.DayOfWeek;

public class Hotel {
	private String hotelName;
	private int regularWD;
	private int regularWK;
	private int ratings;

	public Hotel(String hotelName, int regularWD) {
		this.hotelName = hotelName;
		this.regularWD = regularWD;
	}
	
	public Hotel(String hotelName, int regularWD, int regularWE) {
		this.hotelName = hotelName;
		this.regularWD = regularWD;
		this.regularWK = regularWE;

	}
	
	public Hotel(String hotelName, int regularWD, int regularWK, int ratings) {
		this.hotelName = hotelName;
		this.regularWD = regularWD;
		this.regularWK = regularWK;
		this.ratings = ratings;

	}	

	public String getHotelName() {
		return hotelName;
	}
	
	public int getRegularWK() {
		return regularWK;
	}

	public void setRegularWK(int regularWE) {
		this.regularWK = regularWE;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getRegularWD() {
		return regularWD;
	}

	public void setRegularWD(int regularWD) {
		this.regularWD = regularWD;
	}
	
	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	
	public int findDay(String day) {
		return DayOfWeek.valueOf(day.toUpperCase()).getValue();
	}

	public int calculateRent(String type, String finalDay) {
		if (type.equals("Regular") && (1 <= findDay(finalDay) && findDay(finalDay) <= 5)) {
			return regularWD;
		} else if (type.equals("Regular") && (6 <= findDay(finalDay) && findDay(finalDay) <= 7)) {
			return regularWK;
		}
		return 0;
	}

}

