import java.util.*;
@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20]; //makes ten buckets for digits 0-19 (negative and positive)
    for (int i = 0; i < buckets.length; i++){
      buckets[i] = new MyLinkedList<Integer>(); //initializes each bucket
    }
    
    int passes = 0;
    for (int i = 0; i < data.length; i++){
      if (Math.abs(data[i]) > passes){
        passes = Math.abs(data[i]);
      }
    }
    passes = (int)Math.log10(passes) + 1;
    //System.out.println(passes);
    MyLinkedList<Integer> list = new MyLinkedList<Integer>();
    for (int i = 0; i < passes; i++){ //loops through number of passes
      if (i == 0){ //first pass
        //adds data to buckets
        for (int num: data){ //for each number in the data set
          //divides number by a power of ten in increasing order and retrieves last digit
          int idx = num % 10; //absolute value of digit retrieved
          if (num >= 0) buckets[idx + 10].add(num); //if positive add to buckets 10-19
          else buckets[9 - idx].add(num); //else add to buckets 0-9 in inverted order
        }
      }

      else{
        while (list.size() > 0){
          int num = list.removeFront();
          int idx = Math.abs((int)(num / (Math.pow(10, i))) % 10); //absolute value of digit retrieved
          if (num >= 0) buckets[idx + 10].add(num); //if positive add to buckets 10-19
          else buckets[9 - idx].add(num); //else add to buckets 0-9 in inverted order
        }
      }

      for (int j = 0; j < 20; j++){ //combine all 20 buckets into first bucket, which clears the rest
        list.extend(buckets[j]);
      }
    }
    //System.out.println(list.toString());
    for (int i = 0; i < data.length; i++){
      data[i] = list.removeFront();
    }
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
