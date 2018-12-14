package model.algorythms;

/**
 * This class contains 7 sorting algorithms.
 * 
 * @author DigitalVilla
 *
 * @param <T>
 *            An object that implements Comparable
 */
/**
 * @author DigitalVilla
 *
 * @param <T>
 */
/**
 * @author DigitalVilla
 *
 * @param <T>
 */
public class SortAlgorithm<T extends Comparable<T>> {
	/**
	 * the algorithm to use in the sort
	 */
	private char algorithm;
	/**
	 * the largest element in a sorted array
	 */
	private T largest;
	/**
	 * the smallest element in a sorted array
	 */
	private T smallest;
	/**
	 * The array of unsorted/sorted elements.
	 */
	private T[] array;

	/**
	 * @param array     The array to sort
	 * @param algorithm b:Bubble | i:Insertion | s:Selection | m:Merge | q:Quick
	 * @param order     1 Ascending | -1 Descending
	 */
	public SortAlgorithm(T[] array, char algorithm, int order) {
		this.array = array;
		this.algorithm = algorithm;
	}

	// GETTERS AND SETTERS
	/**
	 * @param array to be sorted
	 */
	public void setArray(T[] array) {
		this.array = array;
	}

	/**
	 * @return sorted array
	 */
	public T[] getArray() {
		return array;
	}

	/**
	 * sets the type of algorithm to use
	 * 
	 * @param algorithm quick: 'q', merge: 'm', heap: 'h', java: 'j', insertion:
	 *                  'i', selection: 's', bubble: 'b'.
	 */
	public void setAlgorithm(char algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * @return the smallest element in a sorted array
	 */
	public T getSmallest() {
		return smallest;
	}

	/**
	 * @return the largest element in a sorted array
	 */
	public T getLargest() {
		return largest;
	}

	/**
	 * This method calls the correct sorting algorithm using the algorithm
	 * parameter.
	 */
	public void sortArray() {
		switch (algorithm) {
		case 'j':
			simpleSort();
			break;
		case 'b':
			bubbleSort();
			break;
		case 'i':
			insertionSort();
			break;
		case 's':
			selectionSort();
			break;
		case 'm':
			mergeSort(array, 0, array.length);
			break;
		case 'q':
			quickSort(array, 0, array.length - 1);
			break;
		case 'h':
			heapSort();
		}
	}

	/**
	 * Quick sort implements the divide and conquer mentality. A pivot is selected
	 * (many implementations) the array is parted in two halves, a greater and a
	 * smaller than the pivot. it iterates from beginning and end simultaneously and
	 * swaps elements to match the correct half it will then move to the halves and
	 * follow the same process
	 * 
	 */
	private void quickSort(T[] arr, int low, int high) {
		// the iteration is over
		if (low > high)
			return;
		int mid = low + (high - low) / 2;
		T pivot = arr[mid];
		int i = low;
		int j = high;

		while (i <= j) {
			// continue until it finds an element larger than pivot
			while (arr[i].compareTo(pivot) < 0)
				i++; // increase until a larger element is found
			// continue until it finds an element smaller than pivot
			while (arr[j].compareTo(pivot) > 0)
				j--; // increase until a smaller element is found
			// swap elements
			if (i <= j) {
				T temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				j--;
				i++;
			}
		}
		// Repeat process with halves
		// left side 0 to j
		if (low < j)
			quickSort(arr, low, j);
		// right side i to end
		if (high > i)
			quickSort(arr, i, high);
	}

	/**
	 * Merge sort is also a divide an conquer algorithm it divides the array over
	 * and over until it is each once is only one element long. At the merge phase
	 * they are put together. It is a non-in-place algorithm as many arrays are
	 * created
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	private void mergeSort(T[] arr, int start, int end) {
		// the array is one element only
		if (end - start < 2)
			return;
		// find middle point and divide
		int mid = (start + end) / 2;
		// for the partition always pass the limit index plus one
		mergeSort(arr, start, mid);
		mergeSort(arr, mid, end);
		// join the arrays together
		merge(arr, start, mid, end);

	}

	/**
	 * This method is use by merge sort to join arrays back together
	 * 
	 * @param arr
	 * @param start
	 * @param mid   Is always the first index in the right side. And 1 index bigger
	 *              than the left side
	 * @param end
	 */
	private void merge(T[] arr, int start, int mid, int end) {
		// if last el of left side is equal or smaller than
		// first el of right side, the array is sorted.
		// implementation skips a needless merge of sorted mids
		if (arr[mid - 1].compareTo(arr[mid]) <= 0) {
			return;
		}

		int i = start;
		int j = mid;
		int tempIndx = 0;
		// hold partitions
		Object[] temp = new Object[end - start];
		// do not go over your half
		while (i < mid && j < end) {
			// compare i(left) with j(right) and keep smaller (el) in temp
			temp[tempIndx++] = (arr[i].compareTo(arr[j]) <= 0) ? arr[i++] : arr[j++];
		}
		// copy elements up to the number of elements inside temparray(tempindx);
		System.arraycopy(arr, i, arr, start + tempIndx, mid - i);
		System.arraycopy(temp, 0, arr, start, tempIndx);

	}

	/**
	 * Selection sort finds the smallest element in an array and swaps it with the
	 * element in the first position. It will move the index and will repeat the
	 * process until the array is sorted
	 */
	private void selectionSort() {
		int len = array.length;
		// iterate to (length - 1) as j is always i+1
		for (int i = 0; i < len - 1; i++) {
			// save
			int indx = i;
			for (int j = i + 1; j < len; j++) {
				// find the smallest index in the entire array
				if (array[j].compareTo(array[indx]) < 0) {
					indx = j;
				}
			}
			// when found swap with i;
			if (indx != i) {
				T small = array[indx];
				array[indx] = array[i];
				array[i] = small;
			}
		}
	}

	/**
	 * Insertion sort traverses the array to find largest element. When found it is
	 * Set in last position. repeat decreasing the length of the lopp
	 */
	private void insertionSort() {
		// ui = unsorted index
		int len = array.length;
		for (int ui = 1; ui < len; ui++) {
			// save element
			T temp = array[ui];
			int i;

			// search for the correct position
			for (i = ui; i > 0 && array[i - 1].compareTo(temp) > 0; i--) {
				// find largest element in array
				// swap elements
				array[i] = array[i - 1];
			}
			array[i] = temp;
		}
	}

	/**
	 * Bubble sort works by swapping the largest element to the end it repeats the
	 * process with length-1.
	 */
	private void bubbleSort() {
		int len = array.length;
		T temp = null;
		for (int i = 0; i < len; i++) {
			// iterate through each element
			for (int j = 1; j < (len - i); j++) {
				// if element is larger swap it
				if (array[j - 1].compareTo(array[j]) > 0) {
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	/**
	 * This method sets the values of the largest and smallest value;
	 */
	public void setValues() {
		smallest = array[0];
		largest = array[array.length - 1];
	}

	// Code Modified from Java OpenJDK doSort().
	/**
	 * Author: Oracle This algorithm is Java's faster version of insertion sort. I
	 * found it by looking into the JDK code in my first semester. The "break"
	 * inside loop actually makes a difference in speed.
	 */
	public void simpleSort() {
		int right = array.length - 1;
		int left = 0;

		for (int i = left, j = i; i < right; j = ++i) {
			T temp = array[i + 1];
			while (temp.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				if (j-- == left) {
					break;
				}
			}
			array[j + 1] = temp;
		}
	}

	//////////////// Heap SORT ///////////////////////
	/**
	 * tracker for the limit of heap
	 */
	private static int total;

	/**
	 * Heap Sort manager/caller
	 */
	private void heapSort() {
		total = array.length - 1;
		for (int i = total / 2; i >= 0; i--)
			heapify(array, i);
		for (int i = total; i > 0; i--) {
			swapify(array, 0, i);
			total--;
			heapify(array, 0);
		}
	}

	/**
	 * Heap action of breaking the array in nodes
	 * 
	 * @param arr array
	 * @param i   starting index
	 */
	public void heapify(T[] arr, int i) {
		int lft = i * 2;
		int rgt = i * 2 + 1;
		int grt = i;
		if (rgt <= total && arr[lft].compareTo(arr[grt]) >= 0)
			grt = lft;
		if (rgt <= total && arr[rgt].compareTo(arr[grt]) >= 0)
			grt = rgt;
		if (grt != i) {
			swapify(arr, i, grt);
			heapify(arr, grt);
		}
	}

	/**
	 * This method does the common swapping between two elements in an array.
	 * 
	 * @param arr array
	 * @param i   first element
	 * @param j   second element
	 */
	private void swapify(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
