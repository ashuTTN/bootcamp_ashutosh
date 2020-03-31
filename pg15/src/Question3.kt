import java.util.*

//Question 3 - Write a program to find the number of occurrences of a character in a string without using loop.
fun numOfOccurence(inputString: String, alphabet: Char): Int {
    return(inputString.length - (inputString.replace(alphabet.toString(),"").length))

}

fun main() {
    println("Enter String :")
    var inputString: String = readLine()!!
    println("Enter Character :")
//    val scanner = Scanner(System.`in`)
//    val alphabet:Char = scanner.nextLine().get(0)
    var alphabet = readLine()!!
  //  print(numOfOccurence(inputString, alphabet))
    //or
    inputString = inputString.replace(alphabet,"")
    println(inputString)

}