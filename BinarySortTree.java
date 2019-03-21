public class BinarySortTree <T extends Comparable<T>>{
	private BinaryTreeNode<T> root;
	
	BinarySortTree() {
		root = null;
	}
	
	public boolean contains(T item) {
		this.containCheck(item, root);
		System.out.println("this text should not exist 1");
		return false;//this shouldn't run
	}
	private boolean containCheck(T item, BinaryTreeNode<T> tempNode) {
		if (tempNode.getItem() == item) {
			return true;
		} else if (tempNode.isLeaf() == true) {
			return false;
		} else {
			if (item.compareTo(tempNode.getItem()) > 0) {
				return this.containCheck(item, tempNode.getRight());
			} else if (item.compareTo(tempNode.getItem()) < 0) {
				return this.containCheck(item, tempNode.getLeft());
			}
		}
		System.out.println("this text should not exist 2");
		return false;//this shouldn't run
	}
	
	
	public int size() {
		return 0;
		
	}
	
	public void add(T item) {
		//* E must be Comparable!
	}
	
	public boolean remove(T item) {
		return false;
		//* make a method to display the tree contents!
	}
	
	public boolean isEmpty() {
		return false;
		//* Recursion is great for trees!
	}
}
