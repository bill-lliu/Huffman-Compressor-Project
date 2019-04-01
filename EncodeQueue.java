/*Bill Liu
 * Encoding Queue, used to create binary tree for hoffman compression
 * March 31, 2019
 */
// ********************** Simple Linked List class in the linked list *********
public class EncodeQueue<E> { 
    private Node<E> head;
    private Node<E> tail;
    
    //constructor for the queue
    public EncodeQueue() {
    	head = null;
    	tail = null;
    }
    
    public void enqueue (E item, int priority) {
    	Node<E> tempNode = tail;
    	
    	if (tail==null) {//if list is empty
            head = new Node<E>(item,null,null,priority);
            tail = head;
            return;
        }
    	
    	while (tempNode.getPrev()!=null) {
    		if (tempNode.getPrev().getPriority() > priority) {
    			tempNode = tempNode.getPrev();
    		}
    	}
    	
    	if (tempNode.equals(head)) {
        	tempNode.setPrev(new Node<E>(item,null,tempNode,priority));
        	head = tempNode.getPrev();
    		
    	} else {
    		tempNode.getPrev().setNext(new Node<E>(item,tempNode.getPrev(),tempNode,priority));
        	tempNode.setPrev(tempNode.getPrev().getNext());
    	}
    	
    	return;
    }
    
    
    
    
    
    public E dequeue () {
    	Node<E> tempNode = head;
    	if (head==null) {//if list is empty
            return null;
        }
    	if (head.getNext()==null) {
    		tail=null;
    	}
    	//returns first item and moves rest of stack
    	head = head.getNext();
     	return tempNode.getItem();
    }
    
    
    
    
    
    public boolean isEmpty() {
    	if (head==null) {
            return true;
        }
    	return false;
    }
    
    
    
    
    
    public void add(E item) { 
    	Node<E> tempNode = head;
      
        if (head==null) {//if list is empty
            head=new Node<E>(item,null,null,0);
            return;
        }
    
        while(tempNode.getNext()!=null) {//moves to the end of the list
            tempNode = tempNode.getNext();
        }
      
        tempNode.setNext(new Node<E>(item,null,null,0));//adds item to the end
        return;
      
    }
    
    

    
    public E get(int index) { 
    	Node<E> tempNode = head;
        
    	if (tempNode == null) {//if there's no list
    		return null;
    	}
    	
    	for (int i=0; tempNode!=null; i++) {
    		if (index==i) {
    			return tempNode.getItem();
    		}
    		tempNode = tempNode.getNext();
    	}
    	
        return null;//if the end is reached
    }
    
    
    
    
    public int indexOf(E item) { 
    	Node<E> tempNode = head;

    	if (tempNode == null) {//if there's no list
    		return -1;
    	} else if (item == tempNode.getItem()) {//first item is the item
    		return 0;
        }
    	
    	for (int i=0; tempNode!=null; i++) {
    		if (item.equals(tempNode.getItem())) {
    			return i;
    		}
    		tempNode = tempNode.getNext();
    	}
    	
    	return -1;
    }
    
    
    
    
    public E remove(int index) { 
    	Node<E> tempNode = head;
    	E tmp;
    	
    	if (tempNode == null) {//if there's no list
    		return null;
    	}
    	
    	if (index==0) {//at index == 0
    		tmp = tempNode.getItem();
            head = tempNode.getNext();//replaces the head with next node
            return tmp;
        }
    	
    	for (int i=0; tempNode.getNext()!=null; i++) {
    		if (i+1==index) {
        		tmp = (tempNode.getNext()).getItem();
        		tempNode.setNext((tempNode.getNext()).getNext());
        		return tmp;
        	}
    		tempNode = tempNode.getNext();
    	}

    	return null;
    	
    }

    

    
    public boolean remove(E item) {
    	Node<E> tempNode = head;
    	if (tempNode == null) {//if there's no list
    		return false;
    	} else if (indexOf(item) == -1) {
    		return false;
    	}
    	
    	if (remove(indexOf(item)).equals(item)) {//removed the right item
    		return true;
    	} else {//item could not be found
    		return false;
    	}
    	
    }
    
    
    
    
    public void clear() { 
    	head = null;
    }
    
    
    
    
    public int size() { 
    	Node<E> tempNode = head;
    	int count = 0;
    	
    	if (tempNode == null) {//if there's no list
    		return count;
    	}
    	
    	while(tempNode.getNext()!=null) {//moves to the end
            tempNode = tempNode.getNext();
            count++;
        }
    	
    	count++;//count this current node
    	
    	return count;
    }
}



// ********************** A Node in the queue *********
class Node<T> { 
	private T item;
	private Node<T> next;
	private Node<T> prev;
	private int priority=Integer.MAX_VALUE;

	public Node(T item, Node<T> prev, Node<T> next, int priority) {
		this.item=item;
		this.prev=prev;
		this.next=next;
		this.priority=priority;
	}
	

	public T getItem(){
		return this.item;
	}
	public Node<T> getPrev(){
		return this.prev;
	}
	public Node<T> getNext(){
		return this.next;
	}
	public int getPriority() {
		return this.priority;
	}
	
	public void setItem(T item) {
		this.item=item;
	}
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	public void setPriority(int priority) {
		this.priority=priority;
	}

}