public class MyLinkedList<E>{
 private int length;
 public Node start,end;

//constructor
 public MyLinkedList(){
   length = 0;
 }

//methods
 public int size(){
   return length;
 }

 public boolean add(E value){
   Node n = new Node(value);
   if (length == 0){ //for empty list, special case
     start = n; //set start node
     end = n; //set end node
   }
   else { //generic case
     end.setNext(n);
     n.setPrev(end);
     end = n;
   }
   length++;
   return true;
 }

 public String toString(){
   Node current = start; //start with the beginning node
   String result = "[";
   while (current != null){
     result += current;
     current = current.next();
     if (current != null) result += ", ";
   }
   return result + "]";
 }

 private Node getNode(int n){ //no need to check for exceptions, all methods that use this already do so
   Node current = start;
   for (int i = 0; i < n; i++){
     current = current.next();
   }
   return current;
 }

 public E get(int index){ //uses getNode, contains exceptions
   if (index < 0 || index >= length) throw new IndexOutOfBoundsException();
   return this.getNode(index).getData();
 }

 public E set(int index, E value){ //uses getNode, contains exceptions
   if (index < 0 || index >= length) throw new IndexOutOfBoundsException();
   E oldval = this.get(index);
   this.getNode(index).setData(value);
   return oldval;
 }

 public boolean contains(E value){
   for (int i = 0; i < length; i++){
     if (this.get(i).equals(value)) return true;
   }
   return false;
 }

 public int indexOf(E value){
   for (int i = 0; i < length; i++){
     if (this.get(i).equals(value)) return i;
   }
   return -1;
 }

 public void add(int index, E value){ //uses getNode, contains exceptions
   if (index < 0 || index > length) throw new IndexOutOfBoundsException();
   if (index == size()){ //if adding to the end
     add(value);
   }
   else if (index == 0){ //if adding to the beginning
     Node n = new Node(value);
     start.setPrev(n);
     n.setNext(start);
     start = n;
     length++;
   }
   else{ //if adding to the middle
     Node current = getNode(index);
     Node n = new Node(value, current, current.prev());
     current.prev().setNext(n);
     current.setPrev(n);
     length++;
   }
 }

 public E remove(int index){ //uses getNode, contains exceptions
   if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
   Node value = getNode(index); //stores for return later
   if (index == 0){
     if (length == 1){
       start = null;
       end = null;
     }
     else{
       start = value.next();
       start.setPrev(null);
     }
   }
   else if (index == size() - 1){
     value.prev().setNext(null);
     end = value.prev();
   }
   else{
     value.next().setPrev(value.prev());
     value.prev().setNext(value.next()); //value now no longer exists
   }
   length--;
   return value.getData();
 }

 public boolean remove(E value){ //uses indexOf
   if (!(contains(value))) return false;
   remove(indexOf(value));
   return true;
 }

 public void extend(MyLinkedList<E> other){ //constant runtime
   if (size() == 0){ //special case
     start = other.start; //copies over other
     end = other.end;
   }
   else if (other.size() > 0){
     end.setNext(other.start); //links the end and the start of the two lists
     other.start.setPrev(end);
     end = other.end; //sets end of the two lists combined to the other end
   }
   length += other.length;//adjusts length of combined lists
   other.start = null; //erases other
   other.end = null;
   other.length = 0;
 }

 class Node{
  private E data;
  private Node next, prev;

  public Node(E data){ //constructor for node without reference to other nodes
    this.data = data;
  }

  public Node(E data, Node next){ //constructor for node with reference to next node
    this.data = data;
    this.next = next;
  }

  public Node(E data, Node next, Node prev){ //constructor for double-linked lists
    this.data = data;
    this.next = next;
    this.prev = prev;
  }

 //accessor methods
  public E getData(){
    return data;
  }

  public Node next(){
    return next;
  }

  public Node prev(){
    return prev;
  }

 //mutator methods
  public void setData(E data){
    this.data = data;
  }

  public void setNext(Node other){
    next = other;
  }

  public void setPrev(Node other){
    prev = other;
  }

 //toString method
  public String toString(){
    return "" + data;
  }
 }

}
