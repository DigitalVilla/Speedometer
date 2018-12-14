package model.polygon;

public class SquarePrism extends Prism {

	public SquarePrism(double side, double height, char compareType) {
		super(side, height, compareType);
	}

	@Override
	public double calcBaseArea() {
		return Math.pow(this.getSide(), 2);
	}

	@Override
	public double calcVolume() {
		return Math.pow(this.getSide(), 2) * getHeight();
	}
}
