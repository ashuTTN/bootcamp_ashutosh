object SingleCustomer{
    var id:Int = -1

    fun registerCustomer(){
        println("custom registered")
    }

}

fun mian(){
    SingleCustomer.id = 27
    SingleCustomer.registerCustomer()
    //var singleCustomer = SingleCustomer()   //produces error
}