/*Bill Liu
 * Huffman Compression Assignment
 * Converts files a compressed format .MZIP
 * March 31, 2019
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

class Encoder {
	
	//main method to initiate program
	public static void main(String[] args) throws IOException {
		
		//gets the name of the file from the user
		System.out.println("Please enter the full complete name of the file you wish to compress:");
		Scanner input = new Scanner(System.in);
		String fileName = input.nextLine();
		
		//creates a byte reading of the entire file
		byte[] file = Files.readAllBytes((new File(fileName)).toPath());
		
		//creates a frequency table for the each of the 256 character types of ASCII
		int[] frequency = new int[256];
		for (byte b : file) {
			frequency[b]++;
		}
		
		//creates queue to build the binary tree
		PriorityQueue<Node<Byte>> queue = new PriorityQueue<Node<Byte>>();
		for (int i = 0; i < 256; i++) {
			if (frequency[i] != 0) {
				//adds a queue node holding the item of a tree node into a priority queue
				queue.enqueue(new Node<Byte>((byte)i, frequency[i], null, null), frequency[i]);
			}
		}
		
		//takes the first 2 (least frequent items) and adds them to a parent branch
		while(queue.size() > 1) {
			Node<Byte> left = queue.dequeue();
			Node<Byte> right = queue.dequeue();
			Node<Byte> parent = new Node<Byte>(null, left.getFrequency() + right.getFrequency(), left, right);//combines left and right node under a new node
			queue.enqueue(parent, parent.getFrequency());//queues back the new node with new frequency until there is only 1 node left
		}
		
		//when there is only 1 node left in queue (the head node of the tree)
		Node<Byte> head = queue.dequeue();
		
		//creates a hash map to reference the sequence and amount of 0s and 1s added to the new compressed file
		HashMap<Byte, String> map = new HashMap<Byte, String>();
		
		//calls recursive function to create visual tree and assign hash map values
		String binaryTreeString = build(head, map, "");
		
		//creates the string of the compressed file
		String toPrint = "";
		for (byte b : file) {
			toPrint += map.get(b);//adds binary value based on hash map
		}
		
		//for ending case where there is not a full byte, fills the empty byte with 0s
		int endCount = (8 - (toPrint.length()%8))%8;
		for (int i = 0; i < endCount; i++) {
			toPrint += "0";
		}
		
		//writes out everything to a new compressed .MZIP file
		FileOutputStream output = new FileOutputStream(new File("Output.MZIP"));
		output.write(binaryTreeString.getBytes());//adds tree
		output.write("\r\n".getBytes());//adds line
		output.write(48 + endCount);//adds number of 0s at the end of the file
		output.write("\r\n".getBytes());//adds line
		while (toPrint.length() > 0) {//adds each byte of compressed data as an integer
			output.write(Integer.parseInt(toPrint.substring(0, 8), 2));
			toPrint = toPrint.substring(8);
		}
		output.close();
	}
	
	
	
	
	//recursive function to build the visual representation of the tree while also adding the compressed binary values to the hashmap
	private static String build(Node<Byte> node, HashMap<Byte, String> map, String value) {
		String toAdd = "";
		if (node == null) {
			return toAdd;//returns empty if tree is empty
		} else if (node.isLeaf()) {//leaf nodes contain values
			map.put(node.getItem(), value);//add binary value to hash map
			System.out.println(node.getItem()+":"+value);//for debugging
			return toAdd;//nothing new to add
		} else {
			toAdd += "(";
			if (node.getLeft().isLeaf()) {//traverses left nodes first
				toAdd += node.getLeft().getItem().toString() + " ";//adds node to string output
			}
			toAdd += build(node.getLeft(), map, value + "0");//to let function add binary value to hash map
			toAdd += build(node.getRight(), map, value + "1");
			if (node.getRight().isLeaf()) {
				toAdd += node.getRight().getItem().toString();//adds node to string output
			}
			toAdd += ")";
			return toAdd;
		}
	}//end of build function
}



