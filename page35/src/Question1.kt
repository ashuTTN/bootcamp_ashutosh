import java.lang.Exception

class Anupam1{

}
fun main(args: Array<String>) {
    val s = try {
        Class.forName("Anupam1")
    } catch (e: Exception) {
        println(e)
    }
}