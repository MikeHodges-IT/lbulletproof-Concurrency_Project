package application;
/**
  * AddArray this class extends Thread.
  * 
 */
public class AddArray extends Thread {
	int arraySize;
	int arrayTotal;
	int startPos, finishPos;
	int[] arrayToSum;
	/** 
	* Class constructor.
	* 
	* @param arrayToSum of number to be added 
	* @param startPos starting position of the array.
	* @param finishPos ending  position of the array.
	* 
	*/
	public AddArray(int[] arrayToSum, int startPos, int finishPos) {
		this.arrayToSum = arrayToSum;
		this.startPos = startPos;
		this.finishPos = finishPos;
	}

	/**
	* Time how long it take to add array elements using one or multiple threads.
	* 
	* @param arr of number to be added 
	* @param numOfThreads of threads to perform task
	* 
	* @return time elapsed to process in nanoTime
	* 
	*/
	public static long timeTestThreads(int arr[], int numOfThreads) {
//    start  Start time in nanoseconds.
//    timeElapsed Elapsed time in nanoseconds.
		long start = System.nanoTime();
		AddArray.arraySumWithTreads(arr, numOfThreads);
		long timeElapsed = System.nanoTime() - start;
		System.out.println(numOfThreads + " Threads Time  = " + timeElapsed + "  ");
		return timeElapsed;
	}
	/**
	* Adds the array elements using one or multiple threads.
	* 
	* @param arrayToSum of number to be added 
	* @param numberOfThreads of threads to perform task
	* 
	* @return Array elements sum total
	* 
	*/
	public static int arraySumWithTreads(int[] arrayToSum, int numberOfThreads) {
		int arrayThreadTotal = 0;
		int sectionSize = (int) Math.floor(arrayToSum.length / numberOfThreads);
		AddArray[] addArray = new AddArray[numberOfThreads];
		for (int i = 0; i < numberOfThreads; i++) {
			addArray[i] = new AddArray(arrayToSum, i * sectionSize,(numberOfThreads == i+1 ? arrayToSum.length : (i + 1) * sectionSize));
			addArray[i].start();
		}
		for (int i = 0; i < numberOfThreads; i++) {
				try {
					addArray[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		for (int i = 0; i < numberOfThreads; i++) {
			arrayThreadTotal += addArray[i].getArrayTotal();
		}
		System.out.println("Array elements sum total  = " + arrayThreadTotal );
		return arrayThreadTotal;
	}
	/**
	* run() method
	*/
	public void run() {
		addArrayElemants();
	}
	/**
	* Adds the elements of an array. 
	* 
	*/
	public void addArrayElemants() {
		for (int i = startPos; i < finishPos; i++) {
			arrayTotal += arrayToSum[i];
		}
	}
	/**
	* @return  the total of all the elements in an array.
	*/
	public int getArrayTotal() {
		return arrayTotal;
	}
}
