package ques_10;
/* Cashier
  - Takes an order and payment from the customer
  - Upon payment, creates an order and places it into the order queue
  - Intimates the customer that he has to wait for his token and gives him his token
  ( Assumption: Token returned to the customer is the order id. Order queue is unlimited. With a simple modification, we can design for a limited queue size)

*/


class Cashier{
    String name;
    String Empid;
    int token;
    public void take_order(double cash){}
    public int placeOrderToQueue(Order o){
        token = o.order_id;
        return token;
    }
}