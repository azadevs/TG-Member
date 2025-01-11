package org.tg_member.features.home.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.R
import org.telegram.messenger.databinding.ItemVipPriceBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TGMemberUtilities
import org.tg_member.features.home.model.OrderDisplayData

class HomeAdapter(var list: ArrayList<OrderDisplayData>, var homeClick: HomeClick) :
    RecyclerView.Adapter<HomeAdapter.HomeVH>() {

    inner class HomeVH(private var itemVipPriceBinding: ItemVipPriceBinding) :
        RecyclerView.ViewHolder(itemVipPriceBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(orderDisplayData: OrderDisplayData) {
            itemVipPriceBinding.apply {
                root.setOnClickListener {
                    homeClick.click(orderDisplayData)
                }
                tvMemberCount.apply {
                    text = "${orderDisplayData.count}"
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                if (orderDisplayData.discount != 0) {
                    discount.visibility = View.VISIBLE
                    discount.apply {
                        text = "${orderDisplayData.discount}%"
                            background=TGMemberUtilities.getDrawableStateList(R.drawable.transfer_btn,root.context,Theme.getColor(Theme.key_item_discount))
                        setTextColor(Theme.getColor(Theme.key_chats_sentError))
                    }
                }
                price.apply {
                    text = "${orderDisplayData.priceVip}"
                    setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                }
                ivTypeImage.setImageResource(list[adapterPosition].icon)
                ivTypeImage.setColorFilter(root.context.resources.getColor(R.color.color_telegram_background))
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

    interface HomeClick {
        fun click(orderDisplayData: OrderDisplayData)
    }

}