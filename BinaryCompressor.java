

public class BinaryCompressor {
	public static void main(String[] args) {
		BinarySortTree<char> compress = new BinarySortTree<>();
		
		
	}
}

/*

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("hello.txt");
            out = new FileOutputStream("goodbye.txt");
            int c;

            while ((c = in.read()) != -1) {
              System.out.print((char)c);
                out.write(c);
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
}





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