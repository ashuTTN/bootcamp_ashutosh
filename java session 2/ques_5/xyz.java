package ques_5;


class xyz{
    int x ;
    int y ;
    xyz(int x, int y){
        this.x = x;
        this.y = y;
    }
    xyz(xyz copy){
        this.x = copy.x;
        this.y = copy.y;
    }
    public String  toString(){
        return("x = "+ x + "y = "+ y);
    }
}


