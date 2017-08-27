package hello;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.List;
import java.util.ArrayList;
 
public class OomGenerator implements Runnable {
	private String id;
	private int bytes;
	private int increment;
	private double sleep;

	private List<int[]> allocatedLists;

	public OomGenerator(String id, int bytes, int increment, double sleep) {
		this.id = id;
		this.bytes = bytes;
		this.increment = increment;
		this.sleep = sleep;

		this.allocatedLists = new ArrayList<int[]>();
	}

	public void run() {
		try {
			int iteratorValue = bytes;
			int outerIterator = 1;
			log("=================> OOM test started...");
			while(true) {
				log("Iteration " + outerIterator + " Max Mem: " + Runtime.getRuntime().maxMemory() + " Free Mem: " + Runtime.getRuntime().freeMemory());
				int loop1 = 2;
				int[] memoryFillIntVar = new int[iteratorValue];

				do {
					memoryFillIntVar[loop1] = 0;
					loop1--;
				} while (loop1 > 0);

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
}