package com.example.contacts2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.contacts2.model.Person
import com.example.contacts2.model.PersonRepository

class PersonViewModel(application: Application): AndroidViewModel(application) {
    private val personRepository:PersonRepository
    private val allPersons:LiveData<List<Person>>
    init {
        personRepository = PersonRepository(application)
        allPersons = personRepository.getAll()
    }
    fun insertPerson(person:Person){
        personRepository.insert(person)
    }
    fun getAll(): LiveData<List<Person>> {
        return allPersons
    }
}