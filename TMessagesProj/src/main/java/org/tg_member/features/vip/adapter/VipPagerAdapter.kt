package org.tg_member.features.vip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import org.telegram.messenger.databinding.BuyFragmentBinding
import org.telegram.messenger.databinding.GiftFragmentBinding
import org.telegram.messenger.databinding.TransferFragmentBinding
import org.tg_member.features.buy.BuyFragment
import org.tg_member.features.gift.GiftFragment
import org.tg_member.features.transfer.TransferFragment
import org.tg_member.features.vip.model.VipItem

class VipPagerAdapter(
    val items: List<VipItem>
) : PagerAdapter() {
    override fun getCount() = items.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (items[position]) {
            VipItem.BuyItem -> {
                val binding = BuyFragmentBinding.inflate(
                    LayoutInflater.from(container.context),
                    container,
                    false
                )
                BuyFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }

            VipItem.GiftItem -> {
                val binding = GiftFragmentBinding.inflate(
                    LayoutInflater.from(container.context),
                    container,
                    false
                )
                GiftFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }

            VipItem.TransferItem -> {
                val binding = TransferFragmentBinding.inflate(
                    LayoutInflater.from(container.context),
                    container,
                    false
                )
                TransferFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        return container.removeView(`object` as View)
    }
}