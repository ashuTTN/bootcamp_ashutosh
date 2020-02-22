package Ques1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Ques1 {
    //Write Java code to define List .
    // Insert 5 floating point numbers in List, and using an iterator,
    // find the sum of the numbers in List.

    public static void main(String[] args) {
        List<Float> list = new ArrayList<Float>();
        list.add(1.5f);
        list.add(2.2f);
        list.add(3.54f);
        list.add(4.23f);
        list.add(5.75f);
        float sum = 0;
        Iterator it = list.iterator();
        while(it.hasNext()){
            sum = sum + (float)it.next();
        }
        System.out.println(sum);
    }
}