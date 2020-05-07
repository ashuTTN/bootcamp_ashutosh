package Question4

class Students(bookId: Int, bookName: String): GetDetails, Library(bookId, bookName) {

    var studentName: String = ""
    var rollNum = 0
    var deptID = 0
    var bookCount = 0
    var deptName: String = ""

    constructor(bookId: Int, bookName: String, studentName: String, rollNum: Int, deptID: Int, deptName: String)
            :this(bookId = bookId, bookName = bookName){
        this.studentName = studentName
        this.rollNum = rollNum
        this.deptID = deptID
        this.deptName = deptName
        this.bookID = bookId
        this.bookName = bookName
    }

    fun issueBook(bookId: Int, bookName: String){
        this.bookID
        this.bookName
        bookCount++
    }


    override fun details() {
        println("Dept ID = "+this.deptID)
        println("Dept Name = "+this.deptName)
        println("Roll Number = " + this.rollNum)
        println("Student Name = " + this.studentName)
        println("book ID = " + this.bookID)
        println("book name = "+ this.bookName)
        println("book count = " + this.bookCount)
        println("\n")

    }
}

fun main(args: Array<String>) {
    val students = Students(132,"Introduction to Android", "Ashutosh", 6044, 3 , "Information Technology")
    students.issueBook(133,"Advance Andriod")
    students.details()
    students.issueBook(9,"Android fundamentals")
    students.details()
}