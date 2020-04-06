package com.example.contacts2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Person::class),version = 2)
abstract class PersonDatabase:RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile   // indicates our app to always refer to its instance from the memory
        var database:PersonDatabase? = null
        fun getInstance(context: Context):PersonDatabase?{
            if(database == null){
                synchronized(PersonDatabase::class.java){
                    if(database == null){
                        database = Room.databaseBuilder(context.applicationContext,PersonDatabase::class.java,"person_database").fallbackToDestructiveMigration().build()
                    }
                }
            }
            return database
        }
    }
}