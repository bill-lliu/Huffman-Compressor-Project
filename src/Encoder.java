/*Bill Liu
 * Binary Tree with nodes, reader and writer for the hoffman compression
 * March 31, 2019
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Encoder {
	
	//method for compressing a file
	public result compress(String data) {
		
		//variable to record frequency of each character
		int[] charFrequency = new int[256];
		//looking through the entire data
		for (char c : data.toCharArray()) {
			charFrequency[c]++;
		}
		
		
		private static Node buildTree(int[] freq) {
			return Node;
		}
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	static class result{
		//empty rn
	}
	
	public static void main(String[] args) {
		String test = "abcdefgg";
		int[] ftable = checkFrequency(test);
		System.out.println(ftable);
	}
}
