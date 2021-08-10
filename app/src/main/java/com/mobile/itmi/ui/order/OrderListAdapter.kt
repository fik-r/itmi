package com.mobile.itmi.ui.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.databinding.ItemOrderBinding
import com.mobile.itmi.extension.setStatus
import formatPrice
import formatToDate

class OrderListAdapter(private val onOrderClicked: (id: String) -> Unit) :
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = medicationList[position]
        holder.binding.apply {
            textName.text = model.fullName
            textDate.text = model.updatedAt.formatToDate()
            textOrderNumber.text = "#${model.id}"
            textPrice.text = model.totalPrice.formatPrice(model.currency)
            textStatus.setStatus(model.status ?: "")
            root.setOnClickListener {
                onOrderClicked.invoke(model.id)
            }
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