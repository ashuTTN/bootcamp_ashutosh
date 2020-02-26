package Ques4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
public class Ques4 {
    public static void main(String[] args) {
        HashMap<String, String> Map = new HashMap<>();
        LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        Map.put("5", "Ashutosh");
        Map.put("8", "Anupam");
        Map.put("3", "Sub");
        Map.put("7", "lada");
        Map.put("2", "hooe");

        for (Map.Entry<String, String> entry : Map.entrySet()) {
            list.add(entry.getValue());
        }

        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : list) {
            for (Entry<String, String> entry : Map.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sortedMap.put(entry.getKey(), str);
                }
            }
        }
        System.out.println(sortedMap);
    }
}
