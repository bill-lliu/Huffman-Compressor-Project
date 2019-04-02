public class BinaryEncodeTree {
  Node<Integer> root;
  PriorityQueue<Node<Integer>> pq;
  
  public static void main(String[] args){
    BinaryEncodeTree tree = new BinaryEncodeTree();
    //tree.parseFile(); This is where you should do the frequencies stuff
    tree.createTree();
  }
  
  BinaryEncodeTree(){
    root = null;
  }

  private void parseFile(){
    pq = new PriorityQueue<>();
  }
  
  private void createTree() {
    while(!pq.isSizeOne()){
      Node<Integer> nodeRight = pq.dequeue();
      Node<Integer> nodeLeft = pq.dequeue();
      Node<Integer> newNode = new Node<>(null, nodeLeft, nodeRight, nodeRight.getFrequency() + nodeLeft.getFrequency());
      pq.enqueue(newNode, newNode.getFrequency());
    }
    root = pq.dequeue();
  }
  
  
  private class Node<T> {
    T item;
    Node<T> left;
    Node<T> right;
    int frequency;
    
    Node(T item, Node<T> left, Node<T> right, int frequency) {
      this.item = item;
      this.left = left;
      this.right = right;
    }
    
    public T getItem(){
      return item;
    }
    
    public void setItem(){
      this.item = item;
    }
    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public int getFrequency() {
      return frequency;
    }
    
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public boolean isLeaf(){
        return ((left==null) && (right==null)); // true if it has no children
    }
  }
}