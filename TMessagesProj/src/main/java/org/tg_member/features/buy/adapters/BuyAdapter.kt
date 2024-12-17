package org.tg_member.features.buy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.BuyVipItemBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.features.vip.model.VipPriceModule

class BuyAdapter(var vipPriceList: ArrayList<VipPriceModule>) :
    RecyclerView.Adapter<BuyAdapter.BuyVH>() {

    inner class BuyVH(var buyVipItemBinding: BuyVipItemBinding) :
        RecyclerView.ViewHolder(buyVipItemBinding.root) {
        fun onBind(vipPriceModule: VipPriceModule) {
            buyVipItemBinding.vip.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
            buyVipItemBinding.vipCount.apply {
                text = vipPriceModule.vipCount
                setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            }
            buyVipItemBinding.vipPrice.apply {
                text = vipPriceModule.price
                setTextColor(Theme.getColor(Theme.key_chat_fieldOverlayText))
            }
            buyVipItemBinding.discount.apply {
                text = vipPriceModule.discount
                setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyVH {
        return BuyVH(BuyVipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BuyVH, position: Int) {
        holder.onBind(vipPriceList[position])
    }

    override fun getItemCount(): Int {
        return vipPriceList.size
    }

}