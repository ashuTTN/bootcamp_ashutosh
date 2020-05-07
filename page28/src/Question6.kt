fun main(){
    val list1 = mutableListOf<Int>(13,4,32,7,19,25,85)
    println("List = : $list1")
    println("Enter index to replace: ")
    val index = Integer.valueOf(readLine())
    println("Enter value for replacement: ")
    val value = Integer.valueOf(readLine())

    list1.set(index,value)
    println("New List =  $list1")
}