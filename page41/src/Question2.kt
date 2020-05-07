fun main(){
    val hmap:HashMap<String,Int> = HashMap()
    hmap.put("Ashutosh",20)
    hmap.put("Anupam",33)
    hmap.put("Tanvi",89)
    hmap.put("Subarno",40)
    hmap.put("Lakshya",21)

    println("Employees with age > 30")
    for (i in hmap.keys){
        if (hmap[i]!! > 30){
            println(i)
        }
    }

}
