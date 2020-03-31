
//Question 1 - WAP to replace a substring inside a string with other string..

fun replaceSubstring(enteredString:String , subString:String , stringToAdd:String): String {
    val output:String = enteredString.replace(subString,stringToAdd)
    return (output)
}

fun main(){
    println("Enter a string")
    val enteredString = readLine()!!.toLowerCase()
    println("Enter Substring to replace")
    val subString = readLine()!!.toLowerCase()
    println("Enter String to add")
    val stringToAdd = readLine()!!.toLowerCase()
    println(replaceSubstring(enteredString,subString,stringToAdd))
}