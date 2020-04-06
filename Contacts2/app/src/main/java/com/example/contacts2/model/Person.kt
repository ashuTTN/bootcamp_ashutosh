package com.example.contacts2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor



@Entity(tableName = "Person")
class Person(
    @ColumnInfo(name = "name")
    val name: String="",
    @ColumnInfo(name = "email")
    val email: String="",
    @ColumnInfo(name = "phone")
    val phone: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}





//@Entity(tableName = "Person")
//class Person(
//    @PrimaryKey
//    val name: String,
//    @ColumnInfo(name = "email")
//    val email: String,
//    @ColumnInfo(name = "phone")
//    val phone: String
//)