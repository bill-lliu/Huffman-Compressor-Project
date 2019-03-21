class BinaryTreeNode<T> {
	private T item;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	//constructor
	public BinaryTreeNode(T item, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
		this.item=item;
		this.left=left;
		this.right=right;
	}
	
	public BinaryTreeNode<T> getLeft() {
		return this.left;
	}
	
	public BinaryTreeNode<T> getRight() {
		return this.right;
	}
	
	public void setLeft(BinaryTreeNode<T> left) {
		this.left=left;
	}
	
	public void setRight(BinaryTreeNode<T> right) {
		this.right=right;
	}
	
	public T getItem() {
		return this.item;
	}
	
	public void setItem(T item) {
		this.item=item;
	}
	
	public boolean isLeaf() {
		if (this.left==null && this.right==null) {
			return true;
		} else {
			return false;
		}
	}
}