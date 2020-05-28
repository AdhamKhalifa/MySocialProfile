/**
 * @author 	Adham Khalifa
 	COM212 Final Project
 * Submitted on 05/13/2020
 */
import java.time.LocalDateTime;	//getting local computer time


	/**
	 *This class includes the implementation of the priority queue.
	 *@param capacity queue's capacity.
	 */

	public class ArrayQueue {
	Event[] A;					//An object A of type Event[]
	int n;




	public ArrayQueue(int capacity) {
		A = new Event[capacity];
		n = 0;
	}

		/**
	 * 	returns priority queue's size
		@return size
	 * 
	 */

		public int size() {
			return n;
		}

		/**
	 * 	Checks if it's empty.
		@return False or True
	 * 	
	 */

		public boolean isEmpty() {
			return n == 0;
		}

		/**
	 * 	gets the element with minimum priority.
		@return the element of object of type Event
	 * 
	 */

		public Event getMin() {
		return A[1];		//return the first possible element
	}

		/**
	 * 	gets all the elements in the Event
		@return all the elements
	 * 
	 */
		
		public Event[] getAll()
		{
			return A;
		}

		/**
	 * 	Inserts values to the Events
	 * 	@param k
	 */

		public void insert(Event k) {
			A[n+1] = k;
			n++;
		int cIndex = n;					//child index equals n
		int pIndex = parentIndex(n);
		//while child is not root and child is smaller than parent
		while (pIndex >= 1 && A[cIndex].Time.isBefore(A[pIndex].Time)) {
			swap(cIndex,pIndex);		//swap them
			cIndex = pIndex;
			pIndex = parentIndex(cIndex);
		}
	}

		/**
	 * 	Extracts the minimum priority element.
		@return the following to the minimum priority element
	 */

		public Event extractMin() {
			swap(1,n);
			n--;
			int pIndex = 1;
			int lCIndex;
			int rCIndex;
			while (hasSmallerChild(pIndex)) {
				lCIndex = rightChildIndex(pIndex);
				rCIndex = leftChildIndex(pIndex);
			//No right child, then swap parent with left child
				if (rCIndex > n) {
					swap(pIndex, lCIndex);
					pIndex = lCIndex;
				}
			else { //Two children, swap with the smaller child
				if (A[lCIndex].Time.isBefore(A[rCIndex].Time)) {
					swap(pIndex,lCIndex);
					pIndex = lCIndex;
				}
				else {
					swap(pIndex,rCIndex);
					pIndex = rCIndex;
				}
			}
		}
		return A[n+1];
	}

		/**
	 * 	returns childIndex/2
		@return cIndex/2
	 * 	@param cIndex
	 */

		private int parentIndex(int cIndex) {
			return cIndex/2;
		}

		/**
	 * 	returns ParentIndex * 2
		@return pIndex * 2
	 * 	@param pIndex
	 */

		private int leftChildIndex(int pIndex) {
			return pIndex * 2;
		}

		/**
	 * 	Returns the right child index
		@return (PIndex * 2) + 1.
	 * 	@param PIndex
	 */

		private int rightChildIndex(int pIndex) {
			return (pIndex * 2) + 1;
		}

		/**
	 * 	Swaps parent and child indices.
	 * 	@param pIndex, cIndex
	 */

		private void swap(int pIndex, int cIndex) {
			Event temp = A[cIndex];
			A[cIndex] = A[pIndex];
			A[pIndex] = temp;
		}

		/**
	 * 	Checks if a parent index has a smaller child.
		@return true or false
	 * 	@param value
	 */

		private boolean hasSmallerChild(int pIndex) {
			int rCIndex = rightChildIndex(pIndex);
			int lCIndex = leftChildIndex(pIndex);
			if (rCIndex <= n && A[rCIndex].Time.isBefore(A[pIndex].Time))
				return true;
			else if (lCIndex <= n && A[lCIndex].Time.isBefore(A[pIndex].Time))
				return true;
			else
				return false;
		}
	}
