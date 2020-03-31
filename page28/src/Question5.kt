fun calculateGrade(marks:Int):String{
    if(marks in 50..59){
        return "Good"
    }
    else if (marks in 60..69){
        return "Very Good"
    }
    else if (marks in 70..79){
        return "Excellent"
    }
    else if (marks in 80..100){
        return "Rockstar"
    }
    else{
        return "Invalid marks"
    }
}
fun main(){
    println(calculateGrade(readLine()!!.toInt()))
}