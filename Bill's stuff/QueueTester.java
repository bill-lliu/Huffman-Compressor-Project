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