package Ques7;

import java.util.*;

//Print the elements of an array in the decreasing
// frequency if 2 numbers have same frequency then print the one which came first.
public class Ques7 {
    public static void main(String[] args) {
        int[] arr= new int[]{7,1,3,4,7,1,4,5,1,9,3};
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],0);
        }
        for (int i = 0; i < arr.length; i++) {
            int count = map.get(arr[i]);
            map.put(arr[i],count+1);
        }
        System.out.println(map);
        ArrayList<Integer> sortedElements = new ArrayList<>();


        map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> {
                    for(int i = 1; i <= entry.getValue(); i++)
                        sortedElements.add(entry.getKey());
                });

        System.out.println("Unsorted Array Elements: ");

        System.out.println("Input Array :"+ Arrays.toString(arr) +"\n");

        System.out.println("Sorted Array Elements In Descending Order Of their Frequency: ");

        System.out.println(sortedElements);
    }

}
