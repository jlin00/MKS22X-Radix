@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets = new MyLinkedList[10]; //makes ten buckets for digits 0-9
    int passes = max(data); //finds number of digits in maximum value of data
  }

  public static int max(int[] data){
    if (data.length == 0) return 0; //if no elements, zero passes
    int max = data[0]; //temporary storage
    for (int i = 0; i < data.length; i++){
      if (data[i] > max) max = data[i]; //find max
     }
    return (int)Math.log10(max) + 1; //integer max + 1
  }

  public static void main(String[] args) {
    int[] data = {4, 43, 12, 0, 100, 1, 2, 3, 90};
    radixsort(data);
  }
}
