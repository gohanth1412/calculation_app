package com.example.calculatorapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatorapp.databinding.ViewItemBinding

class MainAdapter(
    private val list: List<String>,
    private val context: Context,
    private val onClick: (item: String) -> Unit
) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.tvItem.text = currentItem
        setColor(holder, position)
        holder.binding.layoutItem.setOnClickListener {
            onClick(currentItem)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setColor(holder: MyViewHolder, position: Int) {
        val listPositionTextBlue = listOf(0, 1, 2, 3, 7, 11, 15, 16)

        if (position in listPositionTextBlue) {
            holder.binding.tvItem.setTextColor(ContextCompat.getColor(context, R.color.blue))
        }

        if (position == list.size - 1) {
            holder.binding.tvItem.setTextColor(ContextCompat.getColor(context, R.color.white))
            holder.binding.layoutItem.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.blue
                )
            )
        }
    }
}