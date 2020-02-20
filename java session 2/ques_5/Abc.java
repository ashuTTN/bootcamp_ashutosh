package ques_5;




class Abc implements Cloneable
{
    int i;
    int j;

    public String toString(){
        return "Abc("+" i = "+ i +"j="+j+")";
    }
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}

