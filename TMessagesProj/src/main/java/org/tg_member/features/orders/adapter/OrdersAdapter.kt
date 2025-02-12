package org.tg_member.features.orders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.R
import org.telegram.messenger.databinding.ItemOrderBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.orders.model.Order

/**
 * Created by Usmon
 * 2024/12/06
 */

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    val items : MutableList<Order> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrdersViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.orderIconIv.setColorFilter(binding.root.context.resources.getColor(R.color.color_telegram_background))
        binding.orderCountTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.orderStatusTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.horizontalLine.setBackgroundColor(Theme.getColor(Theme.key_dialogGrayLine))
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
            binding.orderCountTv.text = order.count.toString()
            binding.orderStatusTv.text = order.status
            binding.orderIconIv.setImageResource(
                when (order.type) {
                    TgMemberStr.getStr(32) -> {
                        R.drawable.ic_list
                    }

                    TgMemberStr.getStr(33) -> {
                        R.drawable.ic_premium_member
                    }

                    TgMemberStr.getStr(34) -> {
                        R.drawable.ic_person
                    }

                    TgMemberStr.getStr(35) -> {
                        R.drawable.ic_view
                    }

                    TgMemberStr.getStr(36) -> {
                        R.drawable.ic_reaction
                    }
                    else -> {
                        R.drawable.ic_failed
                    }
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