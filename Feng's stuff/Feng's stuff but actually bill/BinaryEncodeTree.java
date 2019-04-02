import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BinaryEncodeTree {
  Node<Integer> root;
  PriorityQueue<Node<Integer>> pq;
  
  public static void main(String[] args){
    BinaryEncodeTree tree = new BinaryEncodeTree();
    
  }
  
  BinaryEncodeTree(){
    root = null;
    parseFile(); 
    createTree();
    System.out.println(treeString(root));
  }

  private void parseFile(){
    try {
    Scanner uin = new Scanner(System.in);
    String fileName = uin.nextLine();
    pq = new PriorityQueue<>();
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));
    int[] f = new int[256];
    for (int i=0; i<255; i++) {
      f[i] = 0;
    }
    int c;
    while ((c = in.read()) != -1) {
      f[c]++;
    }
    for (int i=0; i<255; i++) {
      if (f[i] > 0) {
        Node<Integer> g = new Node<>(i, null, null, f[i]);
        System.out.println(g.getFrequency());
        pq.enqueue(g, f[i]);
        
      }
    }
    uin.close();
    in.close();
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
  }
  
  private void createTree() {
    while(!pq.isSizeOne()){
      Node<Integer> nodeRight = pq.dequeue();
      Node<Integer> nodeLeft = pq.dequeue();
      Node<Integer> newNode = new Node<>(null, nodeLeft, nodeRight, nodeRight.getFrequency() + nodeLeft.getFrequency());
      System.out.println(newNode.getFrequency());
      pq.enqueue(newNode, newNode.getFrequency());
    }
    root = pq.dequeue();
  }
  
  private String treeString(Node<Integer> node) {
    if (node.isLeaf()) {
      return Integer.toString(node.getItem());
    } else {
      return "(" + treeString(node.getLeft()) + " " + treeString(node.getRight()) + ")";
    }
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
      this.frequency = frequency;
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