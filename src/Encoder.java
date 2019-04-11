/*Bill Liu
 * Binary Tree with nodes, reader and writer for the hoffman compression
 * March 31, 2019
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Encoder {
	
	
	//MAIN METHOD TO INITIATE PROGRAM
	public static void main(String[] args) {
		String input = "abcdeffg";
		System.out.println(compress(input));
	}
	
	
	
	//****************main method run to compress a file****************
	public static result compress(String data) {
		
		int[] frequencyTable = checkFrequency(data);//gets frequency table of characters
		
		Node<Integer> root = buildTree(frequencyTable);//builds huffman tree
		
		Map<Character, String> lookupTable = buildLookupTable(root);//creates lookup table for the tree
		
		return new result(generateData(data, lookupTable), root);//returns compressed result
	}
	
	
	
	
	//****************method to generate the encoded data
	public static String generateData(String data, Map<Character, String> lookupTable) {
		
		//creates the string builder
		StringBuilder builder = new StringBuilder();
		for (char c : data.toCharArray()) {//adds the characters encoded
			builder.append(lookupTable.get(c));
		}
		return builder.toString();
	}
	
	
	
	
	//****************method to count the frequency of each letter
	public static int[] checkFrequency(String data) {		
		int[] charFrequency = new int[256];//ASCII has 256 characters
		//looking through the entire data
		for (char c : data.toCharArray()) {
			charFrequency[c]++;
		}
		return charFrequency;
	}
			
	
	
	//****************method to map binary encoding for each character
	//alternatively, I could use an Array or ArrayList to store these values,
	//but a hashmap would be the fastest and easiest to implement
	private static Map<Character, String> buildLookupTable(Node<Integer> root){
		
		//creates the map
		Map<Character, String> lookupTable = new HashMap<Character, String>();
		buildLookupTableCheck(root, "", lookupTable);
		return lookupTable;
	}
	
	//helper method to recursively check on the hash map
	private static void buildLookupTableCheck(Node<Integer> node, String output, Map<Character, String> lookupTable) {
		if (node.isLeaf() != true) {
			if (node.getLeft() != null) {
				buildLookupTableCheck(node.getLeft(), output+"0", lookupTable);
			}
			if (node.getRight() != null) {
				buildLookupTableCheck(node.getRight(), output+"1", lookupTable);
			}
		} else {
			lookupTable.put((char)node.getItem().intValue(), output);
		}
	}
	
	
	
	//****************method to build the huffman tree
	private static Node<Integer> buildTree(int[] frequencyTable) {
		
		//creating a new priority queue of type "Node<Integer>", with E = "Node<Integer>" as the object stored
		PriorityQueue<Node<Integer>> queue = new PriorityQueue<>();
		
		//adds all characters counted that have a value greater than 0
		for (int i=0; i<256; i++) {
			if (frequencyTable[i]>0) {
				queue.enqueue(new Node<Integer>(i, frequencyTable[i], null, null), frequencyTable[i]);
			}
		}
		
		//debugging
		if (queue.size() == 0) {
			System.out.println("the queue was empty");
			return null;
		}
		
		//takes the first 2 (least frequent items) and adds them to a parent branch
		while(queue.size() > 1) {
			Node<Integer> left = queue.dequeue();
			Node<Integer> right = queue.dequeue();
			Node<Integer> parent = new Node<Integer>(null, left.getFrequency() + right.getFrequency(), left, right);
			queue.enqueue(parent, parent.getFrequency());//queues back the parent branch
		}
		
		//this should automatically return when queue size reduced to 1
		return queue.dequeue();
	}
			
	
	
	//********method to return the final result of the program
	//used for debugging
	static class result{
		
		private Node<Integer> root;
		private String encodedData;

		//constructor
		result(String encodedData, Node<Integer> root) {
			this.encodedData = encodedData;
			this.root = root;
		}
	}
	
	
	
	//****************method to print out the tree used
	public static void printTree() {
		//print tree used to the console
	}
	
}



