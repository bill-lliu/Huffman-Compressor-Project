package Encoder;

public class PriorityQueueNode<E> implements Comparable<PriorityQueueNode<E>> {

	PriorityQueueNode<E> right, left,par;
	int prio;
	E val;

	public PriorityQueueNode(E value, int prio) {
		this.val = value;
		this.prio = prio;
	}

	public void swap(PriorityQueueNode<E> n) {
		E tmp = val;
		int tmpPrio = prio;
		val = n.val;
		prio = n.prio;
		n.val = tmp;
		n.prio = tmpPrio;
	}

	@Override
	public int compareTo(PriorityQueueNode<E> o) {
		return prio - o.prio;
	}
	
	@Override
	public String toString() {
		return "["+val+","+prio+"]";
	}

}
