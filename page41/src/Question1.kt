fun main(){
    val area = {l:Int, b:Int -> (l*b)}
    println("Enter Length")
    val l= Integer.parseInt(readLine())
    println("Enter Breadth")
    val b = Integer.parseInt(readLine())
    println("Area = ${area(l,b)}")
}