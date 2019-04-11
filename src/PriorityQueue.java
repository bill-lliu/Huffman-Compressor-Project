//****************class for the priority queue
class PriorityQueue<E> {
	queueNode<E> head;
	
	//constructor
	PriorityQueue(){
		head = null;
	}
	
	//function to add a node to queue (adds to front)
	public void enqueue(E item, int frequency){
		queueNode<E> newNode = new queueNode<E>(item, frequency, null);//creates node to be added
		if (head == null) {//if queue is empty
			head = newNode;
		} else if (frequency <= head.getFrequency()) {//if this node has the new highest priority
			newNode.setNext(head);
			head = newNode;
		} else {//continues through the queue until it finds the right place
			queueNode<E> tempNode = head;
			while (frequency > tempNode.getFrequency() && tempNode.getNext() != null) {
				tempNode = tempNode.getNext();
			}
			if (tempNode.getNext() == null){//case with 1 item or end of queue is reached - adds to end
				tempNode.setNext(newNode);
				return;
			} else if (tempNode.getNext().getFrequency() >= frequency) {//case with 2 or more items - adds in between 2 nodes
				queueNode<E> nextNode = tempNode.getNext();
				tempNode.setNext(newNode);
				newNode.setNext(nextNode);
				return;
			}
		}
	}
	
	//removes a node from the queue (removes from front)
	public E dequeue() {
		if (head == null) {//if the queue is empty
			return null;
		}
		E returnedItem = head.getItem();//returns the item of the head
		head = head.getNext();
	    return returnedItem;
	}
	
	//method to return the size of the queue
	public int size() {
		queueNode<E> tempNode = head;
		int count = 0;
		if (head == null) {//case where the queue is empty
			return 0;
		}
		while (tempNode != null) {//adds another count as long as this node is not null
			count++;
			tempNode = tempNode.getNext();
		}
		return count;
	}
}//end of priority queue class