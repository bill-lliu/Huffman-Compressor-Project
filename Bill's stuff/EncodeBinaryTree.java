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

//********************************Tree Class********************************
public class EncodeBinaryTree {
	private Node<Integer> root;
	
	//initiates class
	EncodeBinaryTree() {
		root = null;
		getFile();
	}
  

	//method to get file
	private void getFile() {
		Scanner scanner = new Scanner(System.in);
		String textFile = scanner.nextLine();
		parseFile(textFile);
	}
	
	
	
	
	
	
	//method to combine 2 nodes
	private Node<Integer> combine(Node<Integer> left, Node<Integer> right) {
		return new Node<>(left, right);
	}
	
	
	
	//********************************Node Class********************************
	private class Node<T> {
		private T item;
		private Node<T> left;
		private Node<T> right;
		
		//constructor
		public Node(T item, Node<T> left, Node<T> right) {
			this.item=item;
			this.left=left;
			this.right=right;
		}
		
		//getters
		public T getItem() {
			return this.item;
		}
		public Node<T> getLeft() {
			return this.left;
		}
		public Node<T> getRight() {
			return this.right;
		}
		
		//setters
		public void setItem(T item) {
			this.item=item;
		}
		public void setLeft(Node<T> left) {
			this.left=left;
		}
		public void setRight(Node<T> right) {
			this.right=right;
		}
		
		//check leaf function
		public boolean isLeaf() {
			if (this.left==null && this.right==null) {
				return true;
			} else {
				return false;
			}
		}
	}
	



  
  
  /* Private method parseFile
   * Takes the .MZIP file and parses it and also decodes it
   * @param String fileName
   */
  private void parseFile(String fileName) {
    try {
      //Initialize in and out streams
      FileInputStream in = new FileInputStream(fileName);
      FileOutputStream out;
      //c is the input
      int c;
      //Line counter checks to see what information we require
      int lineCounter = 0;
      //String of new file name and description of the tree
      String newFileName = "";
      String treeString = "";
      //Ignore needs to be initialized with a value so default is 0
      int ignore = 0;
      boolean exit = false;
      while (!exit) {
        c = in.read();
        if (c == 13) {
          //Whenever c = 13, it is the end of the end line carriage, so i read the line feed and then increase the line counter
          in.read();
          lineCounter++;
          if (lineCounter == 3) {
            exit = true; //We have all the string information and can now exit this loop
          }
        } else if (lineCounter == 0) {
          newFileName += Character.toString((char)c);
        } else if (lineCounter == 1) {
          treeString += Character.toString((char)c);
        } else if (lineCounter == 2) {
          ignore = Integer.parseInt(Character.toString((char)c));
        } 
      }
      //Creates the tree and also output stream
      create(treeString);
      out = new FileOutputStream(newFileName);
      //I want to have an integer next so I know that the current integer I am reading is the last one, which must ignore a certain number of bits
      int next = in.read();
      //nodeCheck is the node I either check for leaf or traverse through
      Node<Integer> nodeCheck = root;
      //c just has to be next because next will update at the beginning of each loop through
      c = next;
      //If c is the last integer, length = 8(bits) - ignore
      int forLoopLength;
      while (c != -1) {
        next = in.read();
        if (next != -1) {
          forLoopLength = 8;
        } else {
          forLoopLength = 8-ignore;
        }
        for (int i=0; i<forLoopLength; i++) {
          if ((c&128) != 128) {
            //If the first bit of c is a 0
            nodeCheck = nodeCheck.getLeft();
          } else {
            //If it's not a 0, then it has to be a 1
            nodeCheck = nodeCheck.getRight();
          }
          if (nodeCheck.isLeaf()) {
            //Writes to output if the node is a leaf and then resets the node to the root to traverse again
            out.write(nodeCheck.getItem());
            nodeCheck = root;
          }
          //Bit shift left
          c = c<<1;
        }
        //c is now the next integer
        c = next;
      }
      //Close in and out streams
      out.close();
      in.close();
      System.out.println("File decoded successfully");
    } catch (IOException e) {
      System.out.println("File not found!");
    }
  }
  
  
  /* private method create
   * Creates the Binary Tree using a Stack and a string that describes the tree
   * @param String tree
   */
  public void create(String tree) {
    //Stack of the nodes of the tree
    DecodeStack<Node<Integer>> stack = new DecodeStack<>();
    //String representative of the number to add
    String numberToAdd = "";
    for (int i=0; i<tree.length(); i++) {
      if (tree.substring(i,i+1).equals("(") || tree.substring(i,i+1).equals(" ")) {
        //If it is a ( or space, then there is nothing to be done except check to see if it comes after number, which pushes the number
        if (!numberToAdd.equals("")) {
          stack.push(new Node<>(Integer.parseInt(numberToAdd)));
          numberToAdd = "";
        }
      } else if (tree.substring(i,i+1).equals(")")) {
        //If it is a ), I push the previous number, pop the last two numbers in the stack and create a node that has both, pushing it back onto the stack
        if (!numberToAdd.equals("")) {
          stack.push(new Node<>(Integer.parseInt(numberToAdd)));
          numberToAdd = "";
        }
        stack.push(combine(stack.pop(), stack.pop()));
      } else {
        //If it's not ( ) or a space, then it has to be a number that I have to try and parse
        try {
          Integer.parseInt(tree.substring(i,i+1));
          numberToAdd += tree.substring(i,i+1);
        } catch (NumberFormatException e) {
          System.out.println("THERE IS SOMETHING WRONG WITH YOUR TREE"); //Something broke lol
        }
      }
    }
    root = stack.pop(); //The root is just the last node
  }
  
  /* private method combine
   * @param Node<Integer> right
   * @param Node<Integer> left
   * @return Node<Integer>
   */
  private Node<Integer> combine(Node<Integer> right, Node<Integer> left) {
    //When I pop, the right node comes before the left node, hence the order
    //Returns a new node with null item and the left and right nodes
    return new Node<>(left, right);
  }
  
  /* Private class Node<T>
   * Node for the Binary Tree
   */
  private class Node<T> {
    private T item;
    private Node<T> left;
    private Node<T> right;
    
    /* Constructor
     * Creates a new node with an item but no branches
     * @param T item
     */
    Node(T item) {
      this.item = item;
      this.left = null;
      this.right = null;
    }
    
    /* Constructor
     * Creates a new node with an item and branches
     * @param T item
     * @param Node<T> left
     * @param Node<T> right
     */
    Node(T item, Node<T> left, Node<T> right) {
      this.item = item;
      this.left = left;
      this.right = right;
    }
    
    /* Constructor
     * Creates a new node with no item but both branches
     * @param T item
     * @param Node<T> left
     * @param Node<T> right
     */
    Node(Node<T> left, Node<T> right) {
      this.item = null;
      this.left = left;
      this.right = right;
    }
    
    /* public method getItem
     * @return T 
     */
    public T getItem() {
      return item;
    }
    
    /* public method setItem
     * @param T item 
     */
    public void setItem(T item) {
      this.item = item;
    }
    
    /* public method getLeft
     * @return Node<T> left 
     */
    public Node<T> getLeft() {
      return left;
    }
    
    /* public method setLeft
     * @param Node<T> left 
     */
    public void setLeft(Node<T> left) {
      this.left = left;
    }
    
    /* public method getRight
     * @return Node<T> right
     */
    public Node<T> getRight() {
      return right;
    }
    
    /* public method setRight
     * @param Node<T> right
     */
    public void setRight(Node<T> right) {
      this.right = right;
    }
    
    /* public method isLeaf
     * Checks to see if the node has branches
     * @return boolean isLead 
     */
    public boolean isLeaf() {
      if (left == null && right == null) {
        return true;
      } else {
        return false;
      }
    }
  }
}

/*



public class BitOperations {
  
  
  public static void main(String[] args) { 
    
    char c = 'A';
    int v = (int)c;
    
    System.out.println("Character: "+c);    
    System.out.println("Decimal/ASCII Value: "+v);
    OutputBinaryEquivalent(c);
    
    
    
    System.out.println("\n\nBit Operations");
    
    System.out.println("\nAND: A & a - 01000001 & 01100010: ");
    System.out.println("Result: "+ ((char)('A'&'b')));
    OutputBinaryEquivalent((char)('A'&'b'));
    
    System.out.println("\n\nOR: A | a - 01000001 | 01100010: ");
    System.out.println("Result: "+ ((char)('A'|'b')));
    OutputBinaryEquivalent((char)('A'|'b'));
    
    System.out.println("\n\nXOR: A ^ a - 01000001 ^ 01100010: ");
    System.out.println("Result: "+ ((char)('A'^'b')));
    OutputBinaryEquivalent((char)('A'^'b'));

    System.out.println("\n\nBIT SHIFT LEFT: A << 1, A=01000001");
    System.out.println("Result: "+ ((char)('A'<< 1)));
    OutputBinaryEquivalent((char)('A'<< 1));
    
    System.out.println("\n\nBIT SHIFT RIGHT: A >> 3, A=01000001");
    System.out.println("Result: "+ ((char)('A'>> 3)));
    OutputBinaryEquivalent((char)('A'>> 3));

    
  }
  
  
  
  
  public static void OutputBinaryEquivalent(char c) { 
    
    System.out.print("Binary equivalent: ");
    
    for (int i=7;i>=0;i--) { 
      if (( c & (char)Math.pow(2,i)  ) > 0) {
        System.out.print("1");
      }else {
        System.out.print("0");
      }
    }
  }
  
}


*/