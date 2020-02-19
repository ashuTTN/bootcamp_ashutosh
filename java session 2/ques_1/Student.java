package ques_1;

public class Student extends Library {
    String studentName;
    int rollNum;
    int deptID;
    int bookCount = 0 ;
    String deptName;

    public Student(String studentName, int rollNum, int deptID, String deptName){
        this.deptID = deptID;
        this.deptName = deptName;
        this.rollNum = rollNum;
        this.studentName = studentName;

    }
    public void issueBook(int bookID , String bookName){
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookCount++;
    }

    public void getDetails(){
        System.out.println("Dept ID = "+this.deptID);
        System.out.println("Dept Name = "+this.deptName);
        System.out.println("Roll Number = " + this.rollNum);
        System.out.println("Student Name = " + this.studentName);
        System.out.println("book ID = " + this.bookID);
        System.out.println("book name = "+ this.bookName);
        System.out.println("book count = " + this.bookCount);
    }

}
