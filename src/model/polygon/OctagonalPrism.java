package model.polygon;

public class OctagonalPrism extends Prism {

	public OctagonalPrism(double side, double height, char compareType) {
		super(side, height, compareType);
	}

	@Override
	public double calcBaseArea() {
		return 2 * (1 + Math.sqrt(2)) * Math.pow(getSide(), 2);
	}

	@Override
	public double calcVolume() {
		return (2 * (1 + Math.sqrt(2)) * Math.pow(getSide(), 2)) * getHeight();
	}
}
