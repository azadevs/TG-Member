package org.tg_member.features.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.ItemVipPriceBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.features.home.model.OrderMemberToMoney

class HomeAdapter(var list: ArrayList<OrderMemberToMoney>, var homeClick:HomeClick) :
    RecyclerView.Adapter<HomeAdapter.HomeVH>() {

    inner class HomeVH(var itemVipPriceBinding: ItemVipPriceBinding) :
        RecyclerView.ViewHolder(itemVipPriceBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(orderMemberToMoney: OrderMemberToMoney) {
            itemVipPriceBinding.apply {
                root.setOnClickListener {
                    homeClick.click(orderMemberToMoney)
                }
                tvMemberCount.apply {
                    text = "${orderMemberToMoney.memberCount}"
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                discount.apply {
                    text = "${orderMemberToMoney.discount} %"
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                price.apply {
                    text = "${orderMemberToMoney.price} USD"
                    setTextColor(Theme.getColor(Theme.key_myColor))
                }
                ivPerson.setColorFilter(Theme.getColor(Theme.key_myColor))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        return HomeVH(
            ItemVipPriceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface HomeClick{
        fun click(orderMemberToMoney: OrderMemberToMoney)
    }

}