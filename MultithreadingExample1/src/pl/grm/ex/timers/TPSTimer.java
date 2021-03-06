package pl.grm.ex.timers;

public class TPSTimer {
	private long lastTick;
	private int tpsT = 0;
	private long lastCallTime;
	private long lastTPS = 0;
	private int tps;

	public void initTime(int tps) {
		this.tps = tps;
		getDelta();
		lastCallTime = getTime();
	}

	/**
	 * Update TPS
	 */
	public void sync() {
		updateTPS();
		Sync.sync(tps);
	}

	/**
	 * Calculate the TPS and set it
	 */
	private void updateTPS() {
		long currentTime = getTime();
		if (currentTime - lastCallTime > 1000) {
			lastTPS = tpsT;
			tpsT = 0;
			lastCallTime += 1000;
		}
		tpsT++;
	}

	/**
	 * Last tps is equal current tick amount in current second so in other words
	 * is true every second
	 * 
	 * @return true if condition in description is true
	 */
	public boolean isFullCycle() {
		return getLastTps() == getTPS() || (getTime() - lastCallTime > 1000);
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 *
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastTick);
		lastTick = time;
		return delta;
	}

	/**
	 * Get the accurate system time
	 *
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return System.nanoTime() / 1000000;
	}

	/**
	 * Get the current tps count (fake)
	 * 
	 * @return tps Counter
	 */
	public int getTPS() {
		return tpsT;
	}

	/**
	 * Gets the real TPS of display renderer
	 * 
	 * @return frames per second
	 */
	public long getLastTps() {
		return lastTPS;
	}
}