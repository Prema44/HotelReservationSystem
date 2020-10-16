package workshop;

public class Hotel {
	private String hotelName;
	private int regularWD;

	public Hotel(String hotelName, int regularWD) {
		this.hotelName = hotelName;
		this.regularWD = regularWD;
	}

	public String getHotelName() {
		return hotelName;
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

}

