package Encoder;

public class TreeNode<E extends Comparable<E>> implements Comparable<TreeNode<E>> {
	private TreeNode<E> left, right;
	private E val;
	private int height;

	public TreeNode() {
	}

	public TreeNode(E val) {
		this.val = val;
	}

	public TreeNode(TreeNode<E> left, TreeNode<E> right) {
		this.left = left;
		this.right = right;
	}

	public TreeNode<E> getLeft() {
		return left;
	}

	public TreeNode<E> getRight() {
		return right;
	}

	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}

	public void setRight(TreeNode<E> right) {
		this.right = right;
	}

	public E getVal() {
		return val;
	}

	public void setVal(E val) {
		this.val = val;
	}

	public boolean isLeaf() {
		return right == null && left == null;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int compareTo(TreeNode<E> o) {
		return val.compareTo(o.getVal());
	}
}
