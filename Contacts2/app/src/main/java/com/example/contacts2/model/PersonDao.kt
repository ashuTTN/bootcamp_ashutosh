package com.example.contacts2.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person)
    @Query("SELECT * FROM Person")
    fun getAll():LiveData<List<Person>>

}