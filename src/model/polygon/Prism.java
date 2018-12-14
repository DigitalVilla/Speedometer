package model.polygon;

/**
 * this class extends Polygon and implements the calcVolume() formula and
 * aggregates the side parameter as these are equal in all prisms
 * 
 * @author DigitalVilla
 *
 */
public abstract class Prism extends Polygon {
	private double side;

	/**
	 * Constructor that adds height and type to super class and side to current
	 * class
	 * 
	 * @param side
	 * @param height
	 * @param compareType
	 */
	public Prism(double side, double height, char compareType) {
		super(height, compareType);
		this.setSide(side);
	}

	/**
	 * @return the side
	 */
	public double getSide() {
		return side;
	}

	/**
	 * @param side the side to set
	 */
	public void setSide(double side) {
		this.side = side;
	}

//	@Override
//	public double calcVolume() {
//		return calcBaseArea() * getHeight();
//	}
}
