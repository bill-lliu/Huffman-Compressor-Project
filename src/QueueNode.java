//********private class for the nodes of the queue
class queueNode<E>{
	private E item;
	private int frequency;//frequency = priority
	private queueNode<E> next;
	
	//node constructor
	queueNode(E item, int frequency, queueNode<E> next) {
		this.item=item;
	   	this.frequency=frequency;
	   	this.next=next;
	}
	
	//getters and setters
	public E getItem(){
		return item;
	}
	public void setItem(E item){
		this.item=item;
	}
	public int getFrequency(){
		return this.frequency;
	}
	public void setFrequency(int frequency){
		this.frequency=frequency;
	}
	public queueNode<E> getNext() {
		return this.next;
	}
	public void setNext(queueNode<E> next) {
		this.next=next;
	}
}//end of private inner node class