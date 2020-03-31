//Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters,
// Digits And Other Special Characters In A String.
fun main(){
    var lCount = 0.0
    var uCount = 0.0
    var dCount = 0.0
    var scCount = 0.0
    val inputString:String = readLine()!!
    for(i in inputString){
        if(i.isUpperCase())
            uCount++
        else if(i.isLowerCase())
            lCount++
        else if(i.isDigit())
            dCount++
        else
            scCount++
    }
    val inStringLength = inputString.length
    val lCountPercent:Double = lCount*100/inStringLength
    val uCountPercent:Double = uCount*100/inStringLength
    val dCountPercent:Double = dCount*100/inStringLength
    val scCountPercent:Double = scCount*100/inStringLength
    println("Lowercase % =  $lCountPercent% \nUppercase % = $uCountPercent% " +
            "\nDigit % = $dCountPercent% \nSpecialCharecter % = $scCountPercent%")

}