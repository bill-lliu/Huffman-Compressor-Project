/*Bill Liu
 * tester to see if it works
 * March 31, 2019
 */
public class QueueTester {
 public static void main(String[] args) {
  EncodeQueue<String> q = new EncodeQueue<>();
  q.enqueue("1", 1);
  q.enqueue("5", 12);
  q.enqueue("2", 1);
  q.enqueue("3", 2);
  q.enqueue("4", 2);
  while(!q.isEmpty()) {
   System.out.println(q.dequeue());
  } 
 }
}


//File I/0
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//Util
import java.util.BitSet;
import java.util.Scanner;

/**
* Main.java
* Version 1.0
* @author David Bao
* March 30, 2019
* Uses a binary tree to compress data
**/
public class Main {
 // Binary code as a string for each decimal value
 private static String[] codes = new String[256];
 public static void main(String args[]) throws IOException {
     // File IO variables
     BufferedOutputStream out = null;
     BufferedInputStream in = null;
     // Queue where frequency of character is priority
     PriorityQueue queue = new PriorityQueue();
     // Array of bits
     BitSet data = new BitSet();
     // User input
     Scanner input = new Scanner(System.in);
     // 256 decimal values all this frequency 0
     int arr[] = new int[256];
     for (int i = 0; i < 256; i++){
         arr[i] = 0;
     }

     try {
         // Prompts until user enters existing file
         boolean found = false;
         String name = "";
         while(!found) {
             try {
                 System.out.println("Enter name of file (include extension): ");
                 name = input.nextLine();
                 in = new BufferedInputStream(new FileInputStream(name));
                 out = new BufferedOutputStream(new FileOutputStream(removeFileExtension(name).toUpperCase() + ".MZIP"));
                 found = true;
             } catch (Exception FileNotFoundException){
                 System.out.println("File not found.");
                 found = false;
             }
         }
         // Records frequency of each character
         int c;
         while ((c = in.read()) != -1) {
             arr[c]++;
         }
         // Adds the characters to a priority queue
         for (int i = 0; i < 256; i++){
             if (arr[i] != 0)
             queue.enqueue(new BinaryTree(new Node(arr[i], i)));
         }

         String tree ="";
         if (queue.getSize() == 0){
             System.out.println("No data in file");
         }else{
             while (queue.getSize() > 1){
                 Node left = queue.dequeue().getRoot();
                 Node right = queue.dequeue().getRoot();
                 // Combines the two least frequent characters
                 Node combined = new Node(left.getValue()+right.getValue());
                 combined.setRight(right);
                 combined.setLeft(left);
                 // Adds them to the queue as a single tree
                 queue.enqueue(new BinaryTree(combined));
             }
             Node finishedTree = queue.dequeue().getRoot();
             // Generates the string representing the tree;
             String representation = representTree(finishedTree);
             // Generates the code for each character
             getCode(finishedTree, "");
             // Bits used from codes
             int total = 0;
             // Reads through the file
             in = new BufferedInputStream(new FileInputStream(name));
             while ((c = in.read()) != -1) {
                 for(int i = 0; i < codes[c].length();i++) {
                     // Sets the bit to 1
                     if(codes[c].charAt(i) == '1'){
                         data.set(total + i, true);
                     }else{
                         // Sets the bit to 0
                         data.set(total + i, false);
                     }
                 }
                 total = total + codes[c].length();
             }
             // Calculates end zeros of bits
             int endZeros;
             if (total % 8 == 0){
                 endZeros = 0;
             } else {
                 endZeros = 8 - (total % 8);
             }
             byte[] dataByte = data.toByteArray();
             // Outputs the information to the file
             out.write(name.toUpperCase().getBytes());
             out.write(System.lineSeparator().getBytes());
             out.write(representation.getBytes());
             out.write(System.lineSeparator().getBytes());
             out.write(Integer.toString(endZeros).getBytes());
             out.write(System.lineSeparator().getBytes());
             for (int i = 0; i < dataByte.length; i++) {
                 // toByteArray reverses the bits when converted to a byte
                 // Bits are signed by default
                 if (dataByte[i] > 0) {
                     out.write((unsignedToBytes(reverseBits(dataByte[i]))));
                 } else {
                     out.write((reverseBits(unsignedToBytes(dataByte[i]))));
                 }
             }
             /* Troubleshooting
             for (int i =0; i < codes.length; i++){
                 if (codes[i] != null)
                 System.out.println(i +": " + codes[i]);
             }

             for (int i = 0; i < data.size(); i++) {
                 if (data.get(i))
                     System.out.print(1);
                 else
                     System.out.print(0);
             }*/
         }
     } finally {
         if (in != null) {
             in.close();
         }
         if (out != null) {
             out.close();
         }
     }
 }

 /**-------------------METHODS---------------------**/
 /**
  * representTree
  * Creates string for a given tree using recursion
  * @param root, Node, root of the tree
  * @return String, the current of tree representation
  */
 private static String representTree(Node root){
     if (root.getLeft() == null && root.getRight() == null){
         return Integer.toString(root.getChar());
     }
     if (!root.getLeft().isLeaf() && !root.getRight().isLeaf()){
         return ("(" + representTree(root.getLeft()) + representTree(root.getRight()) + ")");
     }
     return ("(" + representTree(root.getLeft()) + " " + representTree(root.getRight()) + ")");

 }

 /**
  * removeFileExtension
  * Removes the extension from a given file name
  * @param name, String, the full file name
  * @return String, the file name without extension
  */
 private static String removeFileExtension(String name){
     if (name.indexOf(".") > 0) {
         name = name.substring(0, name.lastIndexOf("."));
     }
     return name;
 }

 /**
  * getCode
  * Recursively traverses tree to generate codes
  * @param root, the current Node being looked at
  * @param code, the currently developed code
  * @return String, the file name without extension
  * */
 private static void getCode(Node root, String code) {
     // Leaves are characters
     if (root.getLeft() == null && root.getRight() == null) {
         codes[root.getChar()] = code;
         return;
     }
     // Will have left and right if it is not a leaf
     getCode(root.getLeft(), code + "0");
     getCode(root.getRight(), code + "1");
 }

 /**
  * unsignedToBytes
  * Turns the default java signed integer into an unsigned int
  * @param data, a byte of signed data
  * @return int, the integer value of the unsigned byte
  */
 private static int unsignedToBytes(byte data) {
     return data & 0xFF;
 }

 /**
  * reverseBits
  * Reverses the order of bits in a byte
  * @param data, byte that needs to be reversed
  * @return byte, the byte after bits are reversed
  */
 private static byte reverseBits(byte data) {
     byte current = 0;
     for(int pos = 7; pos > 0; pos--){
         current += ((data & 1) << pos);
         data >>= 1;
     }
     return current;
 }

 /**
  * reverseBits
  * Reverses the order of bits in an integer
  * @param num, the int to have bits reversed
  * @return int, the integer after the being reversed
  */
 private static int reverseBits(int num) {
     int current = 0;
     while (num != 0){
         current <<= 1;
         current |= (num & 1);
         num >>= 1;
     }
     return current;
 }
}
