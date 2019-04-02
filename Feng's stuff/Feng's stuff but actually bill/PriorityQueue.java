public class PriorityQueue<T>{
  Node<T> head;
  
  PriorityQueue(){
    head = null;
  }
  public void enqueue(T item, int priority){
    Node<T> newNode = new Node<T>(item, null, priority);
    if (head == null) {
      head = newNode;
    } else if (priority <= head.getPriority()) {
      newNode.setNext(head);
      head = newNode;
    } else {
      Node<T> tempNode = head;
      while (priority > tempNode.getPriority()) {
        tempNode = tempNode.getNext();
        if (tempNode == null){
          return;
        } else if (tempNode.getNext() == null){
          tempNode.setNext(newNode);
          return;
        } else if (tempNode.getNext().getPriority() >= priority) {
          Node<T> nextNode = tempNode.getNext();
          tempNode.setNext(newNode);
          newNode.setNext(nextNode);
          return;
        }
      }
    }
  }
  
  public T dequeue() {
    if (head == null) {
      return null;
    }
    T returnedItem = head.getItem();
    head = head.getNext();
    return returnedItem;
  }
  
  public boolean isSizeOne(){
    if (head == null){
    return false;
    } else if (head.getNext() == null) {
      return true;
    } else {
      return false;
    }
  }
  
  private class Node<T>{
    private T item;
    private Node<T> next;
    private int priority;
    
    Node(T item, Node<T> next, int priority) {
      this.item = item;
      this.next = next;
      this.priority = priority;
    }
    
     public T getItem(){
      return item;
    }
    
    public void setItem(T item){
      this.item = item;
    }
    
     public int getPriority(){
      return priority;
    }
    
    public void setPriority(int priority){
      this.priority = priority;
    }
    
    
    public Node<T> getNext() {
        return next;
    }


    public void setNext(Node<T> next) {
        this.next = next;
    }
  }
}