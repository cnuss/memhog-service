package hello;

import java.io.BufferedWriter;
import java.io.FileWriter;
 
public class OomGenerator implements Runnable {
	private String id;
	private String logFile;	
	private int increment;
	private int sleep;

	public OomGenerator(String id, String logFile, int increment, int sleep) {
		this.id = id;
		this.logFile = logFile;
		this.increment = increment;
		this.sleep = sleep;
	}

	public void run() {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(logFile, true));

			int iteratorValue = 20;
			int outerIterator = 1;
			log("=================> OOM test started...", writer);
			while(true) {
				log("Iteration " + outerIterator + " Max Mem: " + Runtime.getRuntime().maxMemory() + " Free Mem: " + Runtime.getRuntime().freeMemory(), writer);
				int loop1 = 2;
				int[] memoryFillIntVar = new int[iteratorValue];
				// feel memoryFillIntVar array in loop..
				do {
					memoryFillIntVar[loop1] = 0;
					loop1--;
				} while (loop1 > 0);
				iteratorValue = iteratorValue * increment;
				log("Required Memory for next loop: " + iteratorValue, writer);
				Thread.sleep(sleep * 1000);
				outerIterator++;
			}
		} catch(Exception ex) {
			log(ex.getMessage(), writer);
			ex.printStackTrace(System.out);
		}
	}

	private void log(String str, BufferedWriter writer) {
		System.out.println(id + ": " + str);
		try {
			writer.append(id + ": " + str + "\n");
			writer.flush();
		} catch(Exception ex) { 
			System.out.println(id + ": " + ex.getMessage());
			ex.printStackTrace(System.out);
		}
	}
}