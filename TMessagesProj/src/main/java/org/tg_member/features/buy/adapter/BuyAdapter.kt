package org.tg_member.features.buy.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.BuyVipItemBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.features.vip.model.VipDisplayData

class BuyAdapter(private var vipPriceList: ArrayList<VipDisplayData>) :
    RecyclerView.Adapter<BuyAdapter.BuyVH>() {

    inner class BuyVH(private var buyVipItemBinding: BuyVipItemBinding) :
        RecyclerView.ViewHolder(buyVipItemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(vipDisplayData: VipDisplayData) {
            buyVipItemBinding.apply {
                vip.setColorFilter(Theme.getColor(Theme.key_myColor))
                vipCount.apply {
                    text = vipDisplayData.vipCount.toString()
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                vipPrice.apply {
                    text = "${vipDisplayData.price} USD"
                    setTextColor(Theme.getColor(Theme.key_myColor))
                }
                discount.apply {
                    if (vipDisplayData.discount != 0) {
                        discount.visibility = View.VISIBLE
                        text = "${vipDisplayData.discount} %"
                        setTextColor(Color.WHITE)
                    }
                }
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