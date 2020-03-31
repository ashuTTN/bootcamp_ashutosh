class Person(firstName:String,lastName:String,age:Int){
    var firstName:String = ""
    var lastName:String = ""
    companion object{
        fun showDetail(firstName: String,lastName: String,age: Int){
            println("From companion object..")
            println("Age = ${age} \nFirst Name = ${firstName}\nLast Name = ${lastName}")
        }
    }


    var age:Int = 0
    init {
        this.firstName = firstName
        this.lastName = lastName
        this.age =age
        println("From init block..")
        println("Age = ${this.age} \nFirst Name = ${this.firstName}\nLast Name = ${this.lastName}")
    }
}

fun main(){
    val person = Person("Ashutosh","Srivastava",20)
    Person.showDetail("Ashutosh","Srivastava",20)
}