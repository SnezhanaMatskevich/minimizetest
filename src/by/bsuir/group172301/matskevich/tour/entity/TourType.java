package by.bsuir.group172301.matskevich.tour.entity;

/**
 * Tour tpe
 */
public enum TourType {
	VACATION(1), SHOPPING(2), EXCURSION(3);

	/**
	 * Type id
	 */
	private int id;

	/**
	 * Enum constructor
	 * @param id
	 */
	TourType(int id){
		this.id = id;
	}

    /**
     * Get tour id
     * @return
     */
    public int getId() {
        return id;
    }

	/**
	 * Find type by id
	 * @param id
	 * @return
	 */
	public static TourType findById(int id){
		for (TourType type: TourType.values()){
			if (type.id == id){
				return type;
			}
		}

	   return null;
	}

	public String getDisplayName(){
		return this.name().toLowerCase();
	}
}
