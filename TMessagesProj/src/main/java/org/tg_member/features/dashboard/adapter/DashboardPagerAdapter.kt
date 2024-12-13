package org.tg_member.features.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import org.telegram.messenger.databinding.FragmentFreeBinding
import org.telegram.messenger.databinding.FragmentHomeBinding
import org.telegram.messenger.databinding.FragmentOrdersBinding
import org.telegram.messenger.databinding.FragmentProfileBinding
import org.telegram.messenger.databinding.FragmentVipBinding
import org.tg_member.features.dashboard.model.DashboardItem
import org.tg_member.features.free.FreeFragment
import org.tg_member.features.home.HomeFragment
import org.tg_member.features.orders.OrdersFragment
import org.tg_member.features.profile.ProfileFragment
import org.tg_member.features.vip.VipFragment

class DashboardPagerAdapter(
    val items: List<DashboardItem>,
    val navigationColor:Int,
    val actionBarColor:Int
) : PagerAdapter() {

    override fun getCount() = items.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (items[position]) {
            DashboardItem.FreeItem -> {
                val binding =
                    FragmentFreeBinding.inflate(LayoutInflater.from(container.context), container, false)
                FreeFragment(binding,navigationColor).createView()
                container.addView(binding.root)
                return binding.root
            }

            DashboardItem.HomeItem -> {
                val binding =
                    FragmentHomeBinding.inflate(LayoutInflater.from(container.context), container, false)
                HomeFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }

            DashboardItem.OrdersItem -> {
                val binding =
                    FragmentOrdersBinding.inflate(LayoutInflater.from(container.context), container, false)
                OrdersFragment().createView(binding)
                container.addView(binding.root)
                return binding.root
            }

            DashboardItem.ProfileItem -> {
                val binding =
                    FragmentProfileBinding.inflate(LayoutInflater.from(container.context), container, false)
                ProfileFragment(binding).createView()
                container.addView(binding.root)
                return binding.root
            }

            DashboardItem.VipItem -> {
                val binding =
                    FragmentVipBinding.inflate(LayoutInflater.from(container.context), container, false)
                VipFragment(binding,actionBarColor).createView()
                container.addView(binding.root)
                return binding.root
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
         return container.removeView(`object` as View)
    }

}