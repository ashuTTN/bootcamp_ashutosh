package java_session.Ques_11;
/*Create 3 sub class of bank SBI,BOI,ICICI all 4 should have method called getDetails
which provide there specific details like rateofinterest etc,print details of every banks*/
public class ICICI extends bank {
    public void setDetails(int roi , String name , String add){
        this.rateOfInterest = roi;
        this.bankName = name;
        this.bankAddress = add;
    }

    public void getDetails(){
        System.out.println("Rate of interest = " + this.rateOfInterest);
        System.out.println("Bank Name = "+ this.bankName);
        System.out.println("Bank Address = "+ this.bankAddress);
    }
}
