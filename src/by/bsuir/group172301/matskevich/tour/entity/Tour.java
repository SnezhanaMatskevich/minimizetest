package by.bsuir.group172301.matskevich.tour.entity;

/**
 * tour
 */
public class Tour extends Entity{

	/**
	 * tour name
	 */
	private String tourname;

	/**
	 * tour details
	 */
	private String details;

	/**
	 * tour price
	 */
	private int price;

	/**
	 * is hot
	 */
	private boolean hot;

	/**
	 * discount for regular clients
	 */
	private int regularDiscount;

	/**
	 * tour type
	 */
	private TourType type = TourType.VACATION;

	public TourType getType() {
		return type;
	}

	public void setType(TourType type) {
		this.type = type;
	}

	public String getTourname() {
		return tourname;
	}

	public void setTourname(String tourname) {
		this.tourname = tourname;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}

	public int getRegularDiscount() {
		return regularDiscount;
	}

	public void setRegularDiscount(int regularDiscount) {
		this.regularDiscount = regularDiscount;
	}
}
