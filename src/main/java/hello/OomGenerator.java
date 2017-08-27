package hello;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.List;
import java.util.ArrayList;

import java.security.SecureRandom;
 
public class OomGenerator implements Runnable {
	private String id;
	private int bytes;
	private int increment;
	private double sleep;
	private boolean cpuHeavy;

	private List<int[]> allocatedLists;

	public OomGenerator(String id, int bytes, int increment, double sleep, boolean cpuHeavy) {
		this.id = id;
		this.bytes = bytes;
		this.increment = increment;
		this.sleep = sleep;
		this.cpuHeavy = cpuHeavy;

		this.allocatedLists = new ArrayList<int[]>();
	}

	public void run() {
		try {
			int iteratorValue = bytes;
			int outerIterator = 1;
			log("=================> OOM test started...");
			while(true) {
				log("Iteration " + outerIterator + " Max Mem: " + Runtime.getRuntime().maxMemory() + " Free Mem: " + Runtime.getRuntime().freeMemory());
				int loop1 = iteratorValue - 1;
				int[] memoryFillIntVar = new int[iteratorValue];

				do {
					memoryFillIntVar[loop1] = cpuHeavy == true ? SecureRandom.getInstance("SHA1PRNG").nextInt() : 0;
					loop1--;
				} while (loop1 >= 0);

				allocatedLists.add(memoryFillIntVar);

				iteratorValue = iteratorValue * increment;
				log("Required Memory for next loop: " + iteratorValue);
				Thread.sleep((long)(sleep * 1000));
				outerIterator++;
			}
		} catch(Exception ex) {
			log(ex.getMessage());
		}
	}

	private void log(String str) {
		System.out.println(id + ": " + str);
	}

	@Override
	public String toString() {
		return " id: " + id +
			   " bytes: " + bytes +
			   " increment: " + increment +
			   " sleep: " + sleep +
			   " cpuHeavy: " + cpuHeavy;
	}
}