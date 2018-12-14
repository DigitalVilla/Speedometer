
package model.stopwatchapp;

/**
 * This class is a stop watch utility
 * 
 * @author DigitalVilla
 *
 */
public class Stopwatch {
	private long runtime;
	private long startTime;
	private long stopTime;

	/**
	 * @return runtime total
	 */
	public long getRuntime() {
		return runtime;
	}

	/**
	 * Start counting the milliseconds
	 */
	public void start() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * Stop counting milliseconds
	 */
	public void stop() {
		stopTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		runtime = stopTime - startTime;
		return "Runtime seconds: " + (runtime / 1000.0);
	}
}