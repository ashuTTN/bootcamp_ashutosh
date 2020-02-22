package Ques5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Write a program to sort Employee objects based on highest salary using Comparator.
// Employee class{ Double Age; Double Salary; String Name
public class Ques5 {
    public static void main(String[] args) {
        List<Employee> emp = new ArrayList<>();
        emp.add(new Employee(1.0,55.45,"Ashutosh Sriv"));
        emp.add(new Employee(2.0,85.35,"ashutosh"));
        emp.add(new Employee(3.0,95.3450,"anupam"));
        emp.add(new Employee(4.0,35.3243,"subarno"));
        emp.add(new Employee(5.0,45.346,"chirag"));
        

        Comparator<Employee> com = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if(o1.salary<o2.salary){
                    return 1;
                }
                return -1;
            }
        };
        Collections.sort(emp,com);

        for(Employee e:emp){
            System.out.println(e);
        }


    }

}
