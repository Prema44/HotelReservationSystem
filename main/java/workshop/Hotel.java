package workshop;

import java.time.DayOfWeek;

public class Hotel {
	private String hotelName;
	private int regularWD;
	private int regularWK;
	private int ratings;
	private int rewardsWD;
	private int rewardsWK;

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
	
	public Hotel(String hotelName, int regularWD, int regularWK, int ratings, int rewardsWD, int rewardsWK) {
		this.hotelName = hotelName;
		this.regularWD = regularWD;
		this.regularWK = regularWK;
		this.ratings = ratings;
		this.rewardsWD = rewardsWD;
		this.rewardsWK = rewardsWK;
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
	
	public int getRewardsWD() {
		return rewardsWD;
	}

	public void setRewardsWD(int rewardsWD) {
		this.rewardsWD = rewardsWD;
	}

	public int getRewardsWE() {
		return rewardsWK;
	}

	public void setRewardsWE(int rewardsWE) {
		this.rewardsWK = rewardsWE;
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

	public int calculateRent(String type, String day) {
		if (type.equals("Regular") && day.equals("Weekday")) {
			return regularWD;
		} else if (type.equals("Regular") && day.equals("Weekend")) {
			return regularWK;
		} else if (type.equals("Reward") && day.equals("Weekday")) {
			return rewardsWD;
		} else if (type.equals("Reward") && day.equals("Weekend")) {
			return rewardsWK;
		}
		return 0;
	}

}

