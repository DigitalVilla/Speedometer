package model.polygon;

public class Cylinder extends Polygon {
	private double radius;

	public Cylinder(double radius, double height, char compareType) {
		super(height, compareType);
		this.radius = radius;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double calcBaseArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	@Override
	public double calcVolume() {
		return (Math.PI * Math.pow(radius, 2)) * getHeight();
	}
}
