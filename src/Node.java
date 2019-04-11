//********************************TREE NODE CLASS********************************
	
//****************node class for the binary tree nodes
class Node<E> implements Comparable<Node<E>>{
	private E item;
	private int frequency;
	private Node<E> left;
	private Node<E> right;
	
	//node constructor
	Node(E item, int frequency, Node<E> left, Node<E> right) {
	   	this.item=item;
	   	this.frequency=frequency;
	   	this.left=left;
	   	this.right=right;
	}
	
	//checks if this node is a leaf
	public boolean isLeaf() {
		return (this.left==null && this.right==null);
	}
	
	//compares frequencies
	public int compareTo(Node<E> otherNode) {
		int compareValue = Integer.compare(this.frequency, otherNode.getFrequency());
		//if one frequency is larger than another
		if (compareValue != 0) {
			return compareValue;
		}
		//if they have the same frequency, compare characters instead
		return Integer.compare(this.frequency, otherNode.getFrequency());
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
	public Node<E> getLeft() {
		return this.left;
	}
	public void setLeft(Node<E> left) {
		this.left=left;
	}
	public Node<E> getRight() {
		return this.right;
	}
	public void setRight(Node<E> right) {
		this.right=right;
	}
}