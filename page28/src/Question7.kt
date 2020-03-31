fun main() {
    val ranks = mutableMapOf<Int, String>()
    ranks.put(1, "India")
    ranks.put(2, "Australia")
    ranks.put(3, "USA")
    ranks.put(4, "Nepal")
    println("=======Printing Values=======")
    println("Values : ${ranks.values} \n")
    //or
    for(values in ranks.values){
        print("$values " )
    }

    println("\n========Printing keys========")
    println("Keys : ${ranks.keys} \n")
    //or
    for(key in ranks.keys){
        print("$key ")
    }

    println("\n========Printing  entries=======")
    println("Entries : ${ranks.entries} \n")
    //or
    for(entry in ranks.entries){
        print("$entry ")
    }
}