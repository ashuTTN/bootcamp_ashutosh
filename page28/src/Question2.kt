class IntegerNumber(var _num:Int){
    val num = _num
    operator fun plus(p: IntegerNumber){
        println("Integer Sum =  ${_num+p.num}" )
    }
    operator fun times(p: IntegerNumber){
        println("Integer Multiplication = ${_num*p.num}")
    }
}

class DoubleNumber(var _num:Double){
    val num = _num
    operator fun plus(p: DoubleNumber){
        println("Double Sum =  ${_num+p.num}" )
    }
}

class Str(){
    var str1 = ""
    var str2 = ""
    constructor(data1: String) : this() {
        str1 = data1
    }
//    constructor(data1: String, data2: String) : this() {
//        str2 = data2
//    }

    operator fun plus(p: Str):String{
        return(str1+p.str1)
    }

}

fun main(){
    var a = IntegerNumber(2)
    val b = IntegerNumber(3)
    a+b
    a*b
    var a1 = DoubleNumber(7.0)
    var a2 = DoubleNumber(8.8)
    a1+a2

    var s1 = Str("Ashutosh")
    var s2 = Str("Srivastava")
    var s3 = Str("Ahutofsh")
    println(s1+s2)
    var s4 = Str(s1+s2)
    println(s3+s4)
}