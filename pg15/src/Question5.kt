//Find common elements between two arrays.
fun main(){
    val array1 = arrayOf(5,1,6,3,2,7,4,5,5,5)
    val array2 = arrayOf(5,6,7,8,9,10,2,2,3)   // common elements -- 2,3,5,6,7

    val hashSet1 = HashSet<Int>(array1.size)
    val hashSet2 = HashSet<Int>(array2.size)
    val hashMap: HashMap<Int,Int> = HashMap()

    for(element in array1){
        hashSet1.add(element)
    }
    for(element in array2){
        hashSet2.add(element)
    }

    var count:Int? = 0
    for(i in 0..hashSet1.size-1){
        count = hashMap.get(hashSet1.elementAt(i))
        if(count == null)
            hashMap.put(hashSet1.elementAt(i),1)
        else
            hashMap.put(hashSet1.elementAt(i),count)
    }
    println(hashMap)
    count = 0
    for(i in 0..hashSet2.size-1){
        count = hashMap.get(hashSet2.elementAt(i))
        if(count!=null){
            println("repeating elemnt : ${hashSet2.elementAt(i)}")
        }
    }

    //O(n^2)
//    for (i in array1){
//        for (j in  array2){
//            if(i == j){
//                println(j)
//            }
//        }
//    }

}