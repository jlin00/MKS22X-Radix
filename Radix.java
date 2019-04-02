@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets = new MyLinkedList[10]; //makes ten buckets for digits 0-9
    MyLinkedList<Integer> list = new MyLinkedList<Integer>(); //all the buckets go in here eventually
    for (int el : data){
      list.add(el);
    }
    int passes = max(data); //finds number of digits in maximum value of data
    for (int i = 1; i < passes; i++){ //loops through number of passes
      for (int j = 0; j < list.size(); j++){
        int num = list.removeFront();
        int idx = (int)(num / Math.pow(10, i - 1)) % 10;
        buckets[idx].add(num);
      }
      for (int k = 1; k < 10; k++){
        buckets[0].extend(buckets[k]);
      }
      list = buckets[0];
      buckets[0].clear();
    }
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
