package com.example.contacts2.model.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.contacts2.R

class NewPersonFragment : AppCompatDialogFragment() {

    private lateinit var enteredName1: EditText
    private lateinit var enteredPhone1: EditText
    private lateinit var enteredEmail1: EditText
    private lateinit var listner: NewPersonDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(getActivity())
        val inflater: LayoutInflater = getActivity()!!.getLayoutInflater()
        val view = inflater.inflate(R.layout.new_person_fragment, null)

        enteredEmail1 = view.findViewById(R.id.enteredEmail1)
        enteredName1 = view.findViewById(R.id.enteredName1)
        enteredPhone1 = view.findViewById(R.id.enteredPhone1)

        builder.setView(view)
            .setTitle("Enter Person Information...")
            .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    val name: String = enteredName1.text.toString()
                    val phone: String = enteredPhone1.text.toString()
                    val email: String = enteredEmail1.text.toString()
                    listner.applyTexts(phone, name, email)
                }
            })
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listner = context as NewPersonDialogListener
    }

    interface NewPersonDialogListener {
        fun applyTexts(phone: String, name: String, email: String)
    }
}