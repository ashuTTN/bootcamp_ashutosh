package Ques6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//rite a program to sort the Student objects based on Score ,
// if the score are same then sort on First Name . Class Student{ String Name; Double Score; Double Age
public class Ques6 {
    public static void main(String[] args) {
        List<Student> st = new ArrayList<>();
        st.add(new Student("CAshutosh",99.0,20.0));
        st.add(new Student("BAshutosh",99.0,20.0));
        st.add(new Student("Dashutosh",99.0,20.0));
        st.add(new Student("Anupam",74.0,20.0));
        st.add(new Student("Subarno",54.0,20.0));
        st.add(new Student("John",23.0,20.0));
        st.add(new Student("Doe",98.0,20.0));
        st.add(new Student("Foo",45.0,20.0));
        st.add(new Student("Dashutosh",45.0,20.0));

        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {

                if(o1.Score > o2.Score){
                    System.out.println("");
                    return 1;
                }
                else if (o1.Score <o2.Score){
                    return -1;
                }
                else {
                        return o1.Name.compareTo(o2.Name);
                }
            }
        };
        Collections.sort(st,comparator);
        for (Student s:st) {
            System.out.println(s);
        }
    }

}
