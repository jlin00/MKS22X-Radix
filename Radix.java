import java.util.*;
@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20]; //makes ten buckets for digits 0-19 (negative and positive)
    for (int i = 0; i < buckets.length; i++){
      buckets[i] = new MyLinkedList<Integer>(); //initializes each bucket
    }
    int passes = max(data); //finds number of digits in maximum value of data
    //System.out.println(passes);
    for (int i = 0; i < passes; i++){ //loops through number of passes
      //adds data to buckets
      for (int j = 0; j < data.length; j++){ //for each number in the data set
        int num = data[j]; //temporary storage for the number
        //divides number by a power of ten in increasing order and retrieves last digit
        int idx = Math.abs((int)(num / (Math.pow(10, i))) % 10); //absolute value of digit retrieved
        //System.out.println(idx);
        if (num >= 0) buckets[idx + 10].add(num); //if positive add to buckets 10-19
        else buckets[9 - idx].add(num); //else add to buckets 0-9 in inverted order
      }
      for (int k = 1; k < 20; k++){ //combine all 20 buckets into first bucket, which clears the rest
        buckets[0].extend(buckets[k]);
      }
      for (int l = 0; l < data.length; l++){ //copies bucket into data
        data[l] = buckets[0].removeFront(); //copies over value in exact order
      }
      buckets[0].clear(); //clears last bucket
    }
  }

  public static int max(int[] data){
    if (data.length == 0) return 0; //if no elements, zero passes
    int max = Math.abs(data[0]); //temporary storage
    for (int i = 0; i < data.length; i++){
      if (Math.abs(data[i]) > max) max = Math.abs(data[i]); //find max
     }
    return (int)Math.log10(max) + 1; //integer max + 1
  }

  public static void main(String[] args) {
    int[] data = {4, 43, 12, 0, 100, 1, 2, 3, 90};
    radixsort(data);
    System.out.println(Arrays.toString(data));

    int[] data1 = {0, 1, 0, 100000, -121, -200012, 3231, 0};
    radixsort(data1);
    System.out.println(Arrays.toString(data1));

    int[] data2 = {0, 0, 0, 0, 1, 3, 2, -4, -5, -55, -21, -100, 32, 1999, -20100};
    radixsort(data2);
    System.out.println(Arrays.toString(data2));
  }
}
