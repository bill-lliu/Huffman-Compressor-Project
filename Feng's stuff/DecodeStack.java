/* public class DecodeStack<T>
 * Used for creating the BinaryTree
 */
public class DecodeStack <T>{
  private Node<T> head;
  
  /* Constructor
   * Creates the null head
   */
  public DecodeStack() {
    head = null;
  }
  
  /* public method push
   * Creates a node with an item and adds it to the stack
   * @param T item
   */
  public void push(T item){
    if (head == null) {
      head = new Node<T>(item, null);
    } else {
      Node<T> tempNode = head;
      Node<T> newHead = new Node<T>(item, tempNode);
      head = newHead;
    }
  }
  
  /* public method pop
   * Pops the head
   * @return T
   */
  public T pop() {
    if (head == null) {
      return null;
    } else {
      Node<T> returnedNode = head;
      head = head.getNext();
      return returnedNode.getItem();
    }
  }
       
  /* Private class Node<T>
   * Node used for the stack
   */
  private class Node <T> {
    private Node<T> next;
    private T item;
    
    /* Constructor
     * @param T item
     * @param Node<T> next
     */
    Node(T item, Node<T> next) {
      this.item = item;
      this.next = next;
    }
    
    /* public method getItem
     * @return T
     */
    public T getItem() {
      return item;
    }
    
    /* public method setItem
     * @param T
     */
    public void setItem(T item) {
      this.item = item;
    }
    
    /* public method getNext
     * @return Node<T>
     */
    public Node<T> getNext() {
      return this.next;
    }
    
    /* public method setNext
     * @param Node<T>
     */
    public void setNext(Node<T> next) {
      this.next = next;
    }
  }
}