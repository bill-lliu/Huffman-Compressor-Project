//**********A template  for a simple linked list ********

class QueueList {
  public static void main(String[] args) {
    SimpleQueuedList<String> q = new SimpleQueuedList<>();
    q.enqueue("1", 1);
    q.enqueue("5", 12);
    q.enqueue("2", 1);
    q.enqueue("3", 2);
    q.enqueue("4", 2);
    while(!q.isEmpty()) {
      System.out.println(q.dequeue());
    } 
  }
}

// ********************** Simple Linked List class in the linked list *********
class SimpleQueuedList<E> { 
    private Node<E> head;
    private Node<E> tail;
    
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
    	
    	if (tempNode == head) {
        	tempNode.setPrev(new Node<E>(item,null,tempNode,priority));
        	head = tempNode.getPrev();
    		
    	} else {
    		tempNode.getPrev().setNext(new Node<E>(item,tempNode.getPrev(),tempNode,priority));
        	tempNode.setPrev(tempNode.getPrev().getNext());
    	}
    	
    	return;
    }
    
    public E dequeue () {
    	Node<E> tempNode = head.getNext();
    	if (head==null) {//if list is empty
            return null;
        }
    	//returns first item and moves rest of stack
    	head = tempNode;
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
            head=new Node<E>(item,null);
            return;
        }
    
        while(tempNode.getNext()!=null) {//moves to the end of the list
            tempNode = tempNode.getNext();
        }
      
        tempNode.setNext(new Node<E>(item,null));//adds item to the end
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



// ********************** A Node in the linked list *********
class Node<T> { 
	private T item;
	private Node<T> next;
	private Node<T> prev;
	private int priority=Integer.MAX_VALUE;

	public Node(T item) {
		this.item=item;
		this.next=null;
	}
	public Node(T item, Node<T> next) {
		this.item=item;
		this.next=next;
	}
	public Node(T item, Node<T> next, Node<T> prev) {
		this.item=item;
		this.next=next;
		this.prev=prev;
	}
	public Node(T item, Node<T> next, Node<T> prev, int priority) {
		this.item=item;
		this.next=next;
		this.prev=prev;
		this.priority=priority;
	}
	public Node(T item, int priority) {
		this.item=item;
		this.priority=priority;
	}
	

	public Node<T> getNext(){
		return this.next;
	}
	public void setNext(Node<T> next){
		this.next = next;
	}

	public Node<T> getPrev(){
		return this.prev;
	}
	public void setPrev(Node<T> prev){
		this.prev = prev;
	}
	
	public T getItem(){
		return this.item;
	}
	
	public int getPriority(){
		return this.priority;
	}

}