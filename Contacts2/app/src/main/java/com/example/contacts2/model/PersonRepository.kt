package com.example.contacts2.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class PersonRepository(application: Application) {
    private val personDao: PersonDao?
    private val allPersons:LiveData<List<Person>>?

    init {
        val db = PersonDatabase.getInstance(application)
        personDao = db?.personDao()
        allPersons = personDao?.getAll()
    }
    fun insert(person:Person){
        InsertAsyncTask(personDao!!).execute(person)
    }
    fun getAll(): LiveData<List<Person>>{
        return allPersons!!
    }
    private class  InsertAsyncTask(private val dao:PersonDao):AsyncTask<Person,Void,Void>(){
        override fun doInBackground(vararg params: Person): Void? {
            dao.insert(params[0])
            return null
        }
    }
}