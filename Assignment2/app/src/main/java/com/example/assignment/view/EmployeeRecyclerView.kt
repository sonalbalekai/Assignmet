package com.example.assignment.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.data.Data
import com.example.assignment.databinding.AdapterEmployeeBinding

class EmployeeRecyclerView(private val list: MutableList<Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val itemBinding = AdapterEmployeeBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return AdpaterViewHolder(itemBinding)
    }

    class AdpaterViewHolder(private val itemBinding: AdapterEmployeeBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: Data) {
            itemBinding.name.text = data.firstName
            itemBinding.email.text = data.email
            Glide.with(itemView.context).load(data.avatar).into(itemBinding.avatar)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: Data = list.elementAt(position)
        (holder as AdpaterViewHolder).bind(model)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Data>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}