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

    inner class BuyVH(private var buyVipItemBinding: BuyVipItemBinding) :
        RecyclerView.ViewHolder(buyVipItemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(vipDisplayData: VipDisplayData) {
            buyVipItemBinding.apply {
                ivVipImage.setImageResource(R.drawable.vip_svgrepo_com)
                ivVipImage.setColorFilter(root.context.resources.getColor(R.color.color_telegram_background))
                tvVipCount.apply {
                    text = vipDisplayData.vipCount.toString()
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                vipPrice.apply {
                    text = "$${vipDisplayData.price}"
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                tvDiscount.apply {
                    if (vipDisplayData.discount != 0) {
                        tvDiscount.visibility = View.VISIBLE
                        text = "${vipDisplayData.discount}%"
                        background = TGMemberUtilities.getDrawableStateList(
                            R.drawable.transfer_btn,
                            root.context,
                            Theme.getColor(Theme.key_item_discount)
                        )
                        setTextColor(Theme.getColor(Theme.key_chats_sentError))
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyVH {
        return BuyVH(
            BuyVipItemBinding.inflate(
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