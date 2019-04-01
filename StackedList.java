//**********A template  for a simple linked list ********

import java.util.ArrayList;

public class StackedList {

 private static final int MAX = 10000;
 private static final boolean canSet = true;

 public static void main(String[] args) throws Exception {
  int countAdd = 0;
  int countRemoveByIndex = 0;
  int countRemoveByT = 0;
  int countSet = 0;
  int countClear = 0;

  SimpleStackedList<String> list = new SimpleStackedList<String>();
  ArrayList<String> listShadow = new ArrayList<String>();
  for (int i = 0; i < MAX; i++) {
   int oper = (int) (9 * Math.random());
   switch (oper) {
   case (0):
   case (1):
   case (2):
   case (3):
   case (4):
   case (5): {
    list.add(Integer.toString(i));
    listShadow.add(Integer.toString(i));
    countAdd++;
    if (list.size() != listShadow.size()) {
   	 System.out.println("case 1-5: " + list.size() + " " + listShadow.size());
    }
    break;
   }
   case (6): {
    if (list.size() != 0) {
     int removeIndex = (int) ((list.size() - 1) * Math.random());
     list.remove(removeIndex);
     listShadow.remove(removeIndex);
     countRemoveByIndex++;
    }
    if (list.size() != listShadow.size()) {
   	 System.out.println("case 6: " + list.size() + " " + listShadow.size());
    }
    break;
   }
   case (7): {
    if (i < MAX / 4) {
     list.clear();
     listShadow.clear();
     countClear++;
    }
    if (list.size() != listShadow.size()) {
   	 System.out.println("case 7: " + list.size() + " " + listShadow.size());
    }
    break;
   }
   case (8): {
    if (i != 0) {
     int removeT = (int) ((list.size() - 1) * Math.random());
     if(list.remove(Integer.toString(removeT))){
      countRemoveByT++;
     }
     listShadow.remove(Integer.toString(removeT));
    }
    if (list.size() != listShadow.size()) {
   	 System.out.println("case 8: " + list.size() + " " + listShadow.size());
    }
    break;
   }
   case (9): {
  /*  if (list.size() != 0 && canSet) {
     int removeIndex = (int) ((list.size() - 1) * Math.random());
     list.set(removeIndex, Integer.toString(i));
     listShadow.set(removeIndex, Integer.toString(i));
     countSet++;
    } */
    break;
   }
   }
  }

  int countCorrect = 0;
  for (int j = 0; j < list.size(); j++) {
   if (list.get(j).equals(listShadow.get(j))) {
    countCorrect++;
   }
  }
  System.out.println("Your data score: " + countCorrect + "/"
    + list.size());

  int countIndexOf = 0;
  for (int k = 0; k < MAX; k++) {
   if (list.indexOf(Integer.toString(k)) == listShadow.indexOf(Integer.toString(k))) {
    countIndexOf++;
   }
  }

  System.out.println("Your indexOf score: " + countIndexOf + "/" + MAX);

  System.out.println();
  System.out.println(countAdd + " add operations.");
  System.out.println(countRemoveByIndex + " remove by index operations.");
  System.out.println(countRemoveByT + " remove by T operations.");
  System.out.println(countClear + " clear operations.");
  System.out.println(countSet + " set operations.");
 }
}

// ********************** Simple Linked List class in the linked list *********
class SimpleStackedList<E> { 
    private Node<E> head;
    
    public void push(E item) {
    	Node<E> tempNode = head;
    	if (head==null) {//if list is empty
            head = new Node<E>(item,null);
            return;
        }
    	//creates new head
        head = new Node<E>(item,tempNode);
    	return;
    }
    
    public E pop() {
    	Node<E> tempNode = head.getNext();
    	if (head==null) {//if list is empty
            return null;
        }
    	//returns first item and moves rest of stack
    	head = tempNode;
     	return tempNode.getItem();
    }
    
    public void add(E item) { 
    	Node<E> tempNode = head;
      
        if (head==null) {//if list is empty
            head=new Node<E>(item,null);
            return;
        }
    
        while(tempNode.getNext()!=null) {//moves to the end of the list
            tempNode = tempNode.getNext();
        }
      
        tempNode.setNext(new Node<E>(item,null));//adds item to the end
        return;
      
    }
         

    
    public E get(int index) { 
    	Node<E> tempNode = head;
    	int count = 0;
        
    	if (tempNode == null) {//if there's no list
    		return null;
    	}
    	
    	for (int i=0; tempNode!=null; i++) {
    		if (index==i) {
    			return tempNode.getItem();
    		}
    		tempNode = tempNode.getNext();
    	}
    	
        return null;//if the end is reached
    }
    
    
    
    public int indexOf(E item) { 
    	Node<E> tempNode = head;

    	if (tempNode == null) {//if there's no list
    		return -1;
    	} else if (item == tempNode.getItem()) {//first item is the item
    		return 0;
        }
    	
    	for (int i=0; tempNode!=null; i++) {
    		if (item.equals(tempNode.getItem())) {
    			return i;
    		}
    		tempNode = tempNode.getNext();
    	}
    	
    	return -1;
    }
    
    
    
    public E remove(int index) { 
    	Node<E> tempNode = head;
    	E tmp;
    	
    	if (tempNode == null) {//if there's no list
    		return null;
    	}
    	
    	if (index==0) {//at index == 0
    		tmp = tempNode.getItem();
            head = tempNode.getNext();//replaces the head with next node
            return tmp;
        }
    	
    	for (int i=0; tempNode.getNext()!=null; i++) {
    		if (i+1==index) {
        		tmp = (tempNode.getNext()).getItem();
        		tempNode.setNext((tempNode.getNext()).getNext());
        		return tmp;
        	}
    		tempNode = tempNode.getNext();
    	}

    	return null;
    	
    }

    

    public boolean remove(E item) {
    	Node<E> tempNode = head;
    	if (tempNode == null) {//if there's no list
    		return false;
    	} else if (indexOf(item) == -1) {
    		return false;
    	}
    	
    	if (remove(indexOf(item)).equals(item)) {//removed the right item
    		return true;
    	} else {//item could not be found
    		return false;
    	}
    	
    }
    
    
    
    public void clear() { 
    	head = null;
    }
    
    
    
    public int size() { 
    	Node<E> tempNode = head;
    	int count = 0;
    	
    	if (tempNode == null) {//if there's no list
    		return count;
    	}
    	
    	while(tempNode.getNext()!=null) {//moves to the end
            tempNode = tempNode.getNext();
            count++;
        }
    	
    	count++;//count this current node
    	
    	return count;
    }
}


/*
// ********************** A Node in the linked list *********
class Node<T> { 
  private T item;
  private Node<T> next;

public Node(T item) {
  this.item=item;
  this.next=null;
}

public Node(T item, Node<T> next) {
  this.item=item;
  this.next=next;
}

public Node<T> getNext(){
  return this.next;
}

public void setNext(Node<T> next){
  this.next = next;
}

public T getItem(){
  return this.item;
}

}*/