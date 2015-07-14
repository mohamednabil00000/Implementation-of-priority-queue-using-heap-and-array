import java.util.Iterator;
import java.util.NoSuchElementException;

public class priorityQueue<K> {

	private int size;
	private K pq[];
	private int initCapicity;

	priorityQueue() {
		size = 0;
		initCapicity = 1<<10;
		pq = (K[]) new Object[initCapicity];
	}

	void insert(K val) {
		if (size+1 == initCapicity) {
			initCapicity *= 2;
			resize(initCapicity);
		}
		pq[++size] = val;
		swim(size);
	}

	private void resize(int newSize) {

		K temp[] = (K[]) new Object[newSize];
		for (int i = 0; i <= size; i++) {
			temp[i] = pq[i];
		}
		pq=(K[])new Object[newSize];
		pq = temp.clone();
	}

	K poll() {
		K val = null;
		if (size > 0) {
			val = pq[1];
			pq[1] = pq[size];
			size--;
			sink(1);
			return val;

		}
		throw new NoSuchElementException();
	}

	K peek() {
		if (size > 0)
			return pq[1];

		throw new NoSuchElementException();

	}

	boolean contains(K val) {
		return find(1, val);
	}

	private boolean find(int p, K val) {
		if (p > size)
			return false;
		if (pq[p].equals(val))
			return true;

		if (less(val, pq[p]))
			return false;
		boolean a = find(p * 2, val);
		boolean b = find(p * 2 + 1, val);
		if (!a)
			return b;
		else
			return a;

	}

	private int index(int p, K val) {
		if (p > size)
			return -1;
		if (pq[p].equals(val))
			return p;
		if (less(val, pq[p]))
			return -1;

		int a = index(p * 2, val);
		if (a != -1)
			return a;
		else
			return index(p * 2 + 1, val);
	}

	void clear() {
		size = 0;
		initCapicity = 100;
		pq = (K[]) new Object[initCapicity];
	}

	int size() {
		return size;
	}

	boolean isEmpty() {
		return size == 0 ? true : false;
	}

	boolean remove(K val) {
		int idx = index(1, val);
		if (idx != -1) {
			pq[idx] = pq[size];
			size--;
			sink(idx);
			return true;
		} else {
			return false;
		}

	}

	K[] toArray() {
		K arr[] = (K[]) new Object[size];
		for (int i = 1; i <= size; i++) {
			arr[i - 1] = pq[i];
		}
		return arr;
	}

	private void exchange(int a, int b) {
		K temp = pq[a];
		pq[a] = pq[b];
		pq[b] = temp;
	}

	private void sink(int p) {

		while (p * 2 <= size) {
			if (p * 2 + 1 <= size && less(pq[p * 2 + 1], pq[p * 2])
					&& less(pq[p * 2 + 1], pq[p])) {
				exchange(p, p * 2 + 1);
				p = p * 2 + 1;
			} else if (less(pq[p * 2], pq[p])) {
				exchange(p, p * 2);
				p = p * 2;
			} else
				break;

		}

	}

	private void swim(int p) {

		while (p / 2 > 0 && less(pq[p], pq[p / 2])) {
			exchange(p / 2, p);
			p /= 2;
		}

	}

	private boolean less(K i, K j) {
		return ((Comparable<K>) i).compareTo(j) < 0;
	}

}
