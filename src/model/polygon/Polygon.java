package model.polygon;

/**
 * Abstract class to implement all the requirements for the polygon class
 * 
 * @author DigitalVilla
 *
 */
/**
 * @author DigitalVilla
 *
 */
public abstract class Polygon implements Comparable<Polygon> {
	/**
	 * the height of the Polygon
	 */
	private double height;

	/**
	 * The case to compare by = 'a' B.are, 'v' volume, 'h' height.
	 */
	private char compareType;

	/**
	 * Constructor that requires the height and the compare type.
	 * 
	 * @param height      the height of the Polygon
	 * @param compareType the case to compare by = 'a' B.are, 'v' volume, 'h'
	 *                    height.
	 */
	public Polygon(double height, char compareType) {
		this.height = height;
		this.compareType = compareType;
	}

	/**
	 * @return height
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * sets the height to a given value
	 * 
	 * @param height
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * 
	 * @return compare type
	 */
	public char getcompareType() {
		return compareType;
	}

	/**
	 * sets the compare type
	 * 
	 * @param compareType
	 */
	public void setcompareType(char compareType) {
		this.compareType = compareType;
	}

	/**
	 * calculates the base area using the corresponding formula
	 * 
	 * @return the total base area
	 */
	public abstract double calcBaseArea();

	/**
	 * calculates the volume using the corresponding formula
	 * 
	 * @return the total volume
	 */
	public abstract double calcVolume();

	@Override
	public int compareTo(Polygon poly) {
		switch (compareType) {
		case 'h':
			double h2 = poly.getHeight();
			return (height == h2) ? 0 : (height > h2) ? 1 : -1;
		case 'v':
			double v1 = calcVolume();
			double v2 = poly.calcVolume();
			return (v1 == v2) ? 0 : (v1 > v2) ? 1 : -1;
		case 'b':
			double a1 = calcBaseArea();
			double a2 = poly.calcBaseArea();
			return (a1 == a2) ? 0 : (a1 > a2) ? 1 : -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		String str = "\n" + getClass().getSimpleName() + ":  [Height]:" + height;
		str += "  [B.Area]:" + String.format("%.3f", calcBaseArea());
		str += "  [Volume]:" + String.format("%.3f", calcVolume());
		return str;
	};
}
