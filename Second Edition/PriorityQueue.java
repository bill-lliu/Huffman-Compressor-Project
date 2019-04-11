package Encoder;

public class PriorityQueue<T> {

	private PriorityQueueNode<T> head;
	private int next = 2;
	private int size = 0;

	public boolean isEmpty() {
		return head == null;
	}

	public int getSize() {
		return size;
	}

	public void enqueue(T val, int prio) {
		size++;
		PriorityQueueNode<T> n = new PriorityQueueNode<T>(val, prio);
		if (isEmpty()) {
			head = n;
			return;
		}
		PriorityQueueNode<T> cur = head;
		int bit = 0;
		for (int i = next; i != 0; i >>= 1) {
			bit++;
		}
		for (bit -= 2; bit >= 1; bit--) {
			if (((next >> bit) & 1) == 0) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
		if ((next & 1) == 0) {
			cur.left = n;
			n.par = cur;
		} else {
			cur.right = n;
			n.par = cur;
		}
		cur = n;
		while (cur.par != null) {
			if (cur.compareTo(cur.par) < 0) {
				cur.swap(cur.par);
			}
			cur = cur.par;
		}
		next++;
	}

	public T dequeue() {
		size--;
		T ret = head.val;
		if (head.left == null && head.right == null) {
			head = null;
			return ret;
		}
		int last = next - 1;
		PriorityQueueNode<T> cur = head;
		int bit = 0;
		for (int i = last; i != 0; i >>= 1) {
			bit++;
		}
		for (bit -= 2; bit >= 0; bit--) {
			if (((last >> bit) & 1) == 0) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
		head.swap(cur);
		if ((last & 1) == 0) {
			cur.par.left = null;
		} else {
			cur.par.right = null;
		}
		cur = head;
		while (cur.left != null) {
			boolean low = true;
			boolean left = false;
			PriorityQueueNode<T> swap = null;
			if (cur.compareTo(cur.left) > 0) {
				low = false;
				left = true;
				swap = cur.left;
			}
			if (cur.right != null && cur.compareTo(cur.right) > 0) {
				low = false;
				if (cur.left.compareTo(cur.right) > 0) {
					swap = cur.right;
					left = false;
				}
			}
			if (!low) {
				cur.swap(swap);
				if (left) {
					cur = cur.left;
				} else {
					cur = cur.right;
				}
			} else {
				break;
			}
		}
		next--;
		return ret;
	}

	public int lowestPriority() {
		return head.prio;
	}

}