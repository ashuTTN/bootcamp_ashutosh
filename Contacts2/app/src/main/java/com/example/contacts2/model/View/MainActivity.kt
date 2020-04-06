package com.example.contacts2.model.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.concom.example.contacts2.model.Viewtacts2.model.View.RecyclerAdapter
import com.example.contacts2.R
import com.example.contacts2.ViewModel.PersonViewModel
import com.example.contacts2.model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity(), NewPersonFragment.NewPersonDialogListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var personViewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        personViewModel.getAll().observe(this, Observer { persons ->
            persons?.let {
                recyclerAdapter.setPersons(it)
            }
        })

        recyclerView = findViewById(R.id.recyclerView)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        floatingActionButton.setOnClickListener {
            openDialog()
        }

//        floatingActionButton.setOnClickListener {
//            val intent = Intent(this,NewPersonActivity::class.java)
//            startActivityForResult(intent, NEW_PERSON_ACTIVITY_RESULT_CODE)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == NEW_PERSON_ACTIVITY_RESULT_CODE && resultCode == RESULT_SAVE){
//            data?.let {
//                val person = Person(it.getStringExtra(EXTRA_ENTERED_NAME),it.getStringExtra(
//                    EXTRA_ENTERED_EMAIL),it.getStringExtra(EXTRA_ENTERED_PHONE))
//                personViewModel.insertPerson(person)
//            }
//        }
//        else if(requestCode == NEW_PERSON_ACTIVITY_RESULT_CODE && resultCode == RESULT_ERROR){
//            Toast.makeText(this,"Empty Person not saved",Toast.LENGTH_LONG).show()
//        }
//    }

    }

    fun openDialog() {
        val newPersonDialog: NewPersonFragment = NewPersonFragment()
        newPersonDialog.show(getSupportFragmentManager(), "example dialog")

    }

    override fun applyTexts(phone: String, name: String, email: String) {
        val person: Person = Person(name, email, phone)
        personViewModel.insertPerson(person)
    }
}
