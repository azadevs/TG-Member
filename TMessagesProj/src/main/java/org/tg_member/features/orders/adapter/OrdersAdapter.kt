package org.tg_member.features.orders.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.ItemOrderBinding

/**
 * Created by Usmon
 * 2024/12/06
 */

class OrdersAdapter(
    val context: Context,
    val items: List<String>
) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrdersViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OrdersViewHolder,
        position: Int
    ) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class OrdersViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(order: String) {
            binding.orderStatusTv.text = order
        }
    }
}