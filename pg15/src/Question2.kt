//Write a program to find the number of occurrences of the duplicate words in a string and print them.

//Question 2
fun duplicateWords(inputString:String) {
    val inputString1 = inputString.split(" ")
    //hello hello how are yoou hello how
    val hashMap : HashMap<String , Int> = HashMap()
    for(word in inputString1){
        hashMap.put(word,0)
    }
    for(word in inputString1){
        var value = hashMap.get(word)!!
        hashMap[word] = ++value
    }
    println(hashMap)
    var total = 0
    for(word in inputString1){
        val value = hashMap.get(word)!!
        if(value > 1){
            total++
        }
    }
    println(total)
}

fun main(){
    val inputString:String = readLine()!!
    duplicateWords(inputString)
}