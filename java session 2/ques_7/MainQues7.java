package ques_7;
public class MainQues7 {
    public static void main(String[] args) {
        int input=206784;
        int numberOfDays;
        int numberOfHours;
        int numberOfMinutes;
        int numberOfSeconds;

        numberOfDays = input / 86400;
        numberOfHours = (input % 86400 ) / 3600 ;
        numberOfMinutes = ((input % 86400 ) % 3600 ) / 60;
        numberOfSeconds = ((input % 86400 ) % 3600 ) % 60;

        System.out.println("Number of days: " + numberOfDays);
        System.out.println("Number of hours: "+ numberOfHours);
        System.out.println("Number of minutes: " + numberOfMinutes);
        System.out.println("Number of seconds: " + numberOfSeconds);
    }
}


