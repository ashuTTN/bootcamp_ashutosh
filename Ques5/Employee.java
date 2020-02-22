package Ques5;
class Employee{
    Double age;
    Double salary;
    String name;

    public Employee(Double age, Double salary,String name) {
        this.age = age;
        this.salary = salary;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}