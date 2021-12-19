package com.example.rssfeedparsing.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedparsing.databinding.RowBinding
import com.example.rssfeedparsing.model.Question

class MyAdapter(var questions: ArrayList<Question>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    class MyHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val question = questions[position]
        holder.binding.apply {
            titleTv.text = question.title
            authorTv.text = question.author
        }
    }

    override fun getItemCount(): Int = questions.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: ArrayList<Question>){
        questions = newList
        notifyDataSetChanged()
    }
}