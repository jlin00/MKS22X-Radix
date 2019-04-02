import java.util.*;
@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets = new MyLinkedList[10]; //makes ten buckets for digits 0-9
    for (int i = 0; i < buckets.length; i++){
      buckets[i] = new MyLinkedList<Integer>(); //initializes each bucket
    }
    int passes = max(data); //finds number of digits in maximum value of data
    //System.out.println(passes);
    for (int i = 0; i < passes; i++){ //loops through number of passes
      //adds data to buckets
      for (int j = 0; j < data.length; j++){ //for each number in the data set
        int num = data[j]; //temporary storage for the number
        //divides number by a power of twen in increasing order and retrieves last digit
        int idx = (int)(num / (Math.pow(10, i))) % 10;
        //System.out.println(idx);
        buckets[idx].add(num); //add number to bucket based on retrieved digit
      }
      for (int k = 1; k < 10; k++){ //combine buckets into first bucket, which clears the rest
        buckets[0].extend(buckets[k]);
      }
      for (int l = 0; l < data.length; l++){ //copies bucket into data
        data[l] = buckets[0].removeFront();
      }
      buckets[0].clear(); //clears last bucket 
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
    System.out.println(Arrays.toString(data));

    int[] data1 = {0, 1, 0, 100000, 121, 200012, 3231, 0};
    radixsort(data1);
    System.out.println(Arrays.toString(data1));
  }
}
