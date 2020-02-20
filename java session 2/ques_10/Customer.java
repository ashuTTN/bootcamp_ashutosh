package ques_10;
/*Customer
  - Pays the cash to the cashier and places his order, get a token number back
  - Waits for the intimation that order for his token is ready
  - Upon intimation/notification he collects the coffee and enjoys his drink
  ( Assumption:  Customer waits till the coffee is done, he wont timeout and cancel
  the order.
  Customer always likes the drink served.
   Exceptions like he not liking his coffee, he getting wrong coffee are
   not considered to keep the design simple.)
*/
class Customer{
    String name;
    long contact;
    int token_id;
    public void payCash(int amount){
        Cashier c1 = new Cashier();
        token_id = c1.token;
    }
    public void wait_for_coffee(){}
    public void collects_coffee(){}

}
