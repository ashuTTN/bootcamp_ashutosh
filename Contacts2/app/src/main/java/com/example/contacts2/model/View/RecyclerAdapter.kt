package com.example.concom.example.contacts2.model.Viewtacts2.model.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts2.R
import com.example.contacts2.model.Person

class RecyclerAdapter(private val mContext: Context):RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private lateinit var mPersons: List<Person>
    fun getPersons() = mPersons
    fun setPersons(persons:List<Person>){
        mPersons = persons
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var nameText: TextView = itemView.findViewById(R.id.nameText)
        var phoneText:TextView = itemView.findViewById(R.id.phoneText)
        var emailText:TextView = itemView.findViewById(R.id.emailText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return mPersons.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.RecyclerViewHolder, position: Int) {
        val currentPerson = mPersons[position]
        holder.nameText.text = currentPerson.name
        holder.emailText.text = currentPerson.email
        holder.phoneText.text = currentPerson.phone
    }
}