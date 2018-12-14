package model.polygon;

public class TriangularPrism extends Prism {

	public TriangularPrism(double side, double height, char compareType) {
		super(side, height, compareType);
	}

	@Override
	public double calcBaseArea() {
		return Math.pow(this.getSide(), 2) * Math.sqrt(3) / 4;
	}

	@Override
	public double calcVolume() {
		return (Math.pow(this.getSide(), 2) * Math.sqrt(3) / 4) * getHeight();
	}

}
