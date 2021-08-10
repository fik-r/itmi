package com.mobile.itmi.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.databinding.ItemOrderBinding

class OrderListAdapter() :
    RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    var medicationList = listOf<Medication>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = medicationList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemOrderBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = medicationList[position]
        holder.binding.apply {
            textName.text = model.fullName
            textDate.text = model.updatedAt
            textOrderNumber.text = model.id
            textPrice.text = model.totalPrice
            textStatus.text = model.status
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position

    }

    class ViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)
}