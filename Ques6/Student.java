package Ques6;
class Student{
    String Name;
    Double Score;
    Double Age;

    public Student(String name, Double score, Double age) {
        Name = name;
        Score = score;
        Age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", Score=" + Score +
                ", Age=" + Age +
                '}';
    }
}