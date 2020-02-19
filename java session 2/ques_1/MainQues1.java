package ques_1;
//1. Create Java classes having suitable attributes for Library management system.Use OOPs concepts in your design.
// Also try to use interfaces and abstract classes.
public class MainQues1 {

    public static void main(String[] args) {
        Student s1 = new Student("Ashutosh", 6, 7 , "info tech");
        s1.issueBook(8,"intro to java");
        s1.getDetails();
        s1.issueBook(9,"Intro to C++");
        s1.getDetails();

    }
}
