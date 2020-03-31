//Create 3 sub class of bank SBI,BOI,ICICI all 4 should have method
// called getDetails which provide there specific details like rateofinterest etc,print details of every bank.

open class Bank(roi: Int, name: String, add: String) {
    var rateOfInterest = roi
    var bankName: String? = name
    var bankAddress: String? = add
}

class SBI(roi: Int,name: String,add: String) : Bank(roi,name,add) {
    fun getDetails() {
            println("Rate of interest = $rateOfInterest")
            println("Bank Name = $bankName")
            println("Bank Address = $bankAddress")
    }
}

class BOI(roi: Int,name: String,add: String) : Bank(roi,name,add) {
    fun getDetails() {
        println("Rate of interest = $rateOfInterest")
        println("Bank Name = $bankName")
        println("Bank Address = $bankAddress")
    }
}

class ICICI(roi: Int,name: String,add: String) : Bank(roi,name,add){
    fun getDetails() {
        println("Rate of interest = $rateOfInterest")
        println("Bank Name = $bankName")
        println("Bank Address = $bankAddress")
    }
}
fun main(){
    val iciciBank = ICICI(7,"ICICI","Noida")
    iciciBank.getDetails()

    val sbiBank = SBI(8,"SBI","Delhi")
    sbiBank.getDetails()

    val boiBank = BOI(6,"BOI","Greater Noida")
    boiBank.getDetails()

}
