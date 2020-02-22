package Ques8;
//Design a Data Structure SpecialStack that supports all the stack operations like
// push(), pop(), isEmpty(), isFull() and an additional operation getMin()
// which should return minimum element from the SpecialStack. (Expected complexity ­ O(1))


import java.util.Stack;


public class SpecialStack {
    public static Stack<Node> stack = new Stack<Node>();
    //PUSH
    Node top = new Node(0,0);
    public static void push(int num){
        if(stack.isEmpty()){
            stack.push(new Node(num,num));
            return;
        }
        int min = Math.min(stack.peek().min,num);
        stack.push(new Node(num,min));

    }
    //POP
    public static void pop(){
        if(stack.isEmpty()){
            System.out.println("Stack is empty");
            return;
        }
        stack.pop();
    }

    public static int top(){
        return stack.peek().val;
    }
    public static int getMin(){
        return stack.peek().min;
    }

    public static void main(String[] args) {
        SpecialStack s = new SpecialStack();
        s.push(7);
        s.push(8);
        s.push(9);
        s.push(10);
        s.push(2);
        s.pop();
        s.push(3);
        s.push(4);
        s.push(34);
        System.out.println(s.getMin());
    }

}
