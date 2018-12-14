package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

import model.algorythms.SortAlgorithm;
import model.polygon.Cone;
import model.polygon.Cylinder;
import model.polygon.OctagonalPrism;
import model.polygon.PentagonalPrism;
import model.polygon.Polygon;
import model.polygon.Pyramid;
import model.polygon.SquarePrism;
import model.polygon.TriangularPrism;
import model.stopwatchapp.Stopwatch;
import view.AppView.AppUI;

/**
 * This is a Control in an MVC architecture
 * 
 * @author DigitalVilla
 *
 */
public class AppControl {
	private char compareType;
	private char algorithm;
	private String dataFile;
	private int counter;
	private AppUI appUI;
	private SortAlgorithm<Polygon> sort;
	private Stopwatch timer;
	private Polygon[] sorted;

	/**
	 * Constructor
	 * 
	 * @param appUI an instance of the View class
	 */
	public AppControl(AppUI appUI) {
		this.appUI = appUI;
	}

	/**
	 * Sets all the parameters required for Polygon sorting
	 * 
	 * @param params a CSV String with 4 values: source file, compare type,
	 *               algorithm & line counter
	 */
	public void setParams(String params) {
		String[] param = params.split(",");
		this.dataFile = param[0];
		this.compareType = param[1].charAt(0);
		this.algorithm = param[2].charAt(0);
		this.counter = Integer.parseInt(param[3]);
		sort = new SortAlgorithm<Polygon>(getArray(), algorithm, 1);
		startSort();
	}

	/**
	 * This method starts the sort
	 */
	private void startSort() {
		timer = new Stopwatch();
		timer.start();
		sort.sortArray();
		timer.stop();
		sorted = (Polygon[]) sort.getArray();
		printDetails();
		printArray();
		appUI.getControlBTN().setDisable(false);
	}

	/**
	 * Prints the sort details to the view
	 */
	private void printDetails() {
		int len = sorted.length;
		String text = "ALGORITHM STATS:   ";
		text += "Elements: " + len + "\t" + timer.toString();
		text += "\n\nSmallest element:" + sorted[0];
		text += "\n\nLargest element:" + sorted[len - 1];
		appUI.setText(text);
	}

	/**
	 * Prints the elements of the array to the console
	 */
	private void printArray() {
		int i = 0;
		String sortedTxt = "\n\n\t\t--Sorted Array--\n" + sorted[0].toString();
		int len = sorted.length;
		for (i = 0; i < len; i += counter)
			sortedTxt += sorted[i].toString();
		if (i != len)
			sortedTxt += sorted[len - 1].toString();
		appUI.addText(sortedTxt);
	}

	/**
	 * This method obtains an array of Comparable elements
	 * 
	 * @return
	 */
	public Polygon[] getArray() {
		BufferedReader bf;
		Polygon[] polygons = null;
		try {
			InputStream input = this.getClass().getResourceAsStream("/res/data/" + dataFile);
			bf = new BufferedReader(new InputStreamReader(input));

			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int count = Integer.parseInt(st.nextToken());
			polygons = new Polygon[count];

			for (int i = 0; i < count; i++) {
				String className = st.nextToken();
				double height = Double.parseDouble(st.nextToken());
				double radside = Double.parseDouble(st.nextToken());
				Polygon myPoly = null;

				switch (className) {
				case "Cone":
					myPoly = new Cone(radside, height, compareType);
					break;
				case "Cylinder":
					myPoly = new Cylinder(radside, height, compareType);
					break;
				case "OctagonalPrism":
					myPoly = new OctagonalPrism(radside, height, compareType);
					break;
				case "PentagonalPrism":
					myPoly = new PentagonalPrism(radside, height, compareType);
					break;
				case "Pyramid":
					myPoly = new Pyramid(radside, height, compareType);
					break;
				case "SquarePrism":
					myPoly = new SquarePrism(radside, height, compareType);
					break;
				case "TriangularPrism":
					myPoly = new TriangularPrism(radside, height, compareType);
					break;
				}
				polygons[i] = myPoly;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return polygons;
	}

	/**
	 * This method obtains an array of Comparable elements using Reflect. The
	 * versatility of Reflection is a trade for speed.
	 * 
	 * @return a Comparable[] array.
	 */
	public Polygon[] getArrayR() {
		Polygon[] polygons = null;
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader("res/" + dataFile));
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int count = Integer.parseInt(st.nextToken());
			polygons = new Polygon[count];

			for (int i = 0; i < count; i++) {
				String className = "model.polygon." + st.nextToken();
				Class<?> myClass = Class.forName(className);
				Class<?> paramTypes[] = new Class[3];
				paramTypes[0] = Double.TYPE;
				paramTypes[1] = Double.TYPE;
				paramTypes[2] = Character.TYPE;
				Constructor<?> cnstr = myClass.getConstructor(paramTypes);
				Object argList[] = new Object[3];
				// height
				argList[1] = new Double(Double.parseDouble(st.nextToken()));
				// radius/side
				argList[0] = new Double(Double.parseDouble(st.nextToken()));
				argList[2] = new Character(compareType);

				Object obj = cnstr.newInstance(argList);
				polygons[i] = (Polygon) obj;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return polygons;
	}
}
