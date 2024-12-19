package org.tg_member.features.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import org.telegram.messenger.databinding.BuyFragmentBinding
import org.telegram.messenger.databinding.FragmentMemberBinding
import org.telegram.messenger.databinding.FragmentReactionBinding
import org.telegram.messenger.databinding.FragmentViewBinding
import org.telegram.messenger.databinding.GiftFragmentBinding
import org.telegram.messenger.databinding.TransferFragmentBinding
import org.tg_member.features.buy.BuyFragment
import org.tg_member.features.gift.GiftFragment
import org.tg_member.features.home.model.HomeItem
import org.tg_member.features.home.pager.member.MemberFragment
import org.tg_member.features.home.pager.reaction.ReactionFragment
import org.tg_member.features.home.pager.view.ViewFragment
import org.tg_member.features.transfer.TransferFragment
import org.tg_member.features.vip.model.VipItem

class HomePagerAdapter(
    val items: List<HomeItem>
) : PagerAdapter() {
    override fun getCount() = items.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (items[position]) {
            HomeItem.Member -> {
                val binding = FragmentMemberBinding.inflate(
                    LayoutInflater.from(container.context),
                    container,
                    false
                )
                MemberFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }

            HomeItem.View -> {
                val binding = FragmentViewBinding.inflate(
                    LayoutInflater.from(container.context),
                    container,
                    false
                )
                ViewFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }

            HomeItem.Reaction -> {
                val binding = FragmentReactionBinding.inflate(
                    LayoutInflater.from(container.context),
                    container,
                    false
                )
                ReactionFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        return container.removeView(`object` as View)
    }
}