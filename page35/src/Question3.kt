//WAP to create sealed Base class and 3 subclasses of Base class,
//write a function which will have base
//class object as an argument and it will return name of the subclass based argument type.

sealed class Base{
    class A(val a:Int):Base()
    class B(val b: Int,val c:Int):Base()
    class C(val a:Int,val b: Int,val c:Int):Base()
}

fun evaluate(e:Base){
    when(e){
        is Base.A -> println("Subclass A called")
        is Base.B -> println("Subclass B called")
        is Base.C -> println("Subclass C called")
    }
}

fun main(){
    val objA = Base.A(2)
    val objB = Base.B(2,4)
    val objC = Base.C(2,5,8)

    evaluate(objA)
    evaluate(objB)
    evaluate(objC)
}