//Check letter in string which do not have pair
fun main(){
    val input:String = readLine()!!
    val hashMap:HashMap<Char,Int> =HashMap<Char,Int>()
    var count:Int? = 0
    for(i in 0..input.length-1){
        count = hashMap.get(input[i])
        if(count == null)
            hashMap.put(input[i],1)
        else
            hashMap.put(input[i],++count)
    }
    for(i in hashMap.keys){
        if((hashMap.get(i)!!)%2 != 0){
            println(i)
        }
    }
//    for(i in 0..input.length-1){
//        if((hashMap.get(input[i])!!)%2 != 0){
//            println(input[i])
//        }
//    }
    println(hashMap)
}