fun main(){
    val str1 = "Hello"
    val str2 = "World"
    val str3 = "Hey"

    val str4 = str3.add(str1,str2)
    println(str4)

}
fun String.add(s1:String,s2:String):String{
    return this+s1+s2
}