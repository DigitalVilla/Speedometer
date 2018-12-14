package model.polygon;

public class PentagonalPrism extends Prism {

	public PentagonalPrism(double side, double height, char compareType) {
		super(side, height, compareType);
	}

	@Override
	public double calcBaseArea() {
		return (5 * Math.pow(getSide(), 2) * Math.tan(Math.toRadians(54))) / 4;
	}

	@Override
	public double calcVolume() {
		return ((5 * Math.pow(getSide(), 2) * Math.tan(Math.toRadians(54))) / 4) * getHeight();
	}
}
