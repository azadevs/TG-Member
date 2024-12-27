package org.tg_member.features.buy.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.R
import org.telegram.messenger.databinding.BuyVipItemBinding
import org.telegram.messenger.databinding.ItemVipPriceBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TGMemberUtilities
import org.tg_member.features.vip.model.VipDisplayData

class BuyAdapter(private var vipPriceList: ArrayList<VipDisplayData>) :
    RecyclerView.Adapter<BuyAdapter.BuyVH>() {

    inner class BuyVH(private var buyVipItemBinding: ItemVipPriceBinding) :
        RecyclerView.ViewHolder(buyVipItemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(vipDisplayData: VipDisplayData) {
            buyVipItemBinding.apply {
                ivTypeImage.setImageResource(R.drawable.vip_svgrepo_com)
                ivTypeImage.setColorFilter(Theme.getColor(Theme.key_myColor))
                tvMemberCount.apply {
                    text = vipDisplayData.vipCount.toString()
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                price.apply {
                    text = "$${vipDisplayData.price}"
                    setTextColor(Theme.getColor(Theme.key_myColor))
                }
                discount.apply {
                    if (vipDisplayData.discount != 0) {
                        discount.visibility = View.VISIBLE
                        text = "${vipDisplayData.discount} %"
                        background = TGMemberUtilities.getDrawableStateList(
                            R.drawable.transfer_btn,
                            root.context,
                            Theme.key_item_discount
                        )
                        setTextColor(Theme.getColor(Theme.key_chats_sentError))
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyVH {
        return BuyVH(
            ItemVipPriceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BuyVH, position: Int) {
        holder.onBind(vipPriceList[position])
    }

    override fun getItemCount(): Int {
        return vipPriceList.size
    }

}