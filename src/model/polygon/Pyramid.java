package model.polygon;

public class Pyramid extends Polygon {
	private double side;

	/**
	 * Constructor that adds height and type to super class and side to current
	 * class
	 * 
	 * @param side
	 * @param height
	 * @param compareType
	 */
	public Pyramid(double side, double height, char compareType) {
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

	@Override
	public double calcBaseArea() {
		return Math.pow(side, 2);
	}

	@Override
	public double calcVolume() {
		return Math.pow(side, 2) * this.getHeight() / 3;
	}
}
