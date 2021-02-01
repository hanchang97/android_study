package com.example.networkstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonRecyclerAdapter (
    val personList : Array<Person>,
    val inflater : LayoutInflater
) : RecyclerView.Adapter<PersonRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView
        val age : TextView
        val intro : TextView

        init{
            name = itemView.findViewById(R.id.tv_person_name)
            age = itemView.findViewById(R.id.tv_person_age)
            intro = itemView.findViewById(R.id.tv_person_intro)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.person_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(personList.get(position).name ?: "")  // null인 경우 공백 입력
        holder.age.setText(personList.get(position).age.toString() ?: "")
        holder.intro.setText(personList.get(position).intro ?: "")
    }

    override fun getItemCount(): Int {
        return personList.size
    }


}