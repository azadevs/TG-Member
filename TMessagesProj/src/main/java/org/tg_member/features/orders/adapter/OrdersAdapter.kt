package org.tg_member.features.orders.adapter

import org.telegram.messenger.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.ItemOrderBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.Types
import org.tg_member.features.orders.model.Order

/**
 * Created by Usmon
 * 2024/12/06
 */

class OrdersAdapter(
) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    val items : MutableList<Order> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrdersViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.orderIconIv.setColorFilter(Theme.getColor(Theme.key_chat_fieldOverlayText))
        binding.orderCountTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.orderStatusTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.root.setCardBackgroundColor(Theme.getColor(Theme.key_dialogBackground))
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
        fun onBind(order: Order) {
            binding.orderCountTv.text = order.count
            binding.orderStatusTv.text = order.status?.name
            binding.orderIconIv.setImageResource(
                when (order.type) {
                    Types.AllTypes -> {
                        R.drawable.ic_list
                    }

                    Types.Premium -> {
                        R.drawable.vip_svgrepo_com
                    }

                    Types.Member -> {
                        R.drawable.ic_person
                    }

                    Types.View -> {
                        R.drawable.ic_view
                    }

                    Types.Reaction -> {
                        R.drawable.ic_reaction
                    }

                    null -> R.drawable.ic_failed
                }
            )
        }
    }

    fun submitList(list: List<Order>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}