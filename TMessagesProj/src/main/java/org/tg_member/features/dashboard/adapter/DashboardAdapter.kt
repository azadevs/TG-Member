package org.tg_member.features.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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

class DashboardAdapter(
    val items: List<DashboardItem>,
    val barColor:Int
) : RecyclerView.Adapter<ViewHolder>() {

    inner class HomeViewHolder(private val homeBinding: FragmentHomeBinding) :
        ViewHolder(homeBinding.root) {
        fun onBind() {
            HomeFragment(homeBinding).createView()
        }
    }

    inner class FreeViewHolder(private val freeBinding: FragmentFreeBinding) :
        ViewHolder(freeBinding.root) {
        fun onBind() {
            FreeFragment(freeBinding).createView()
        }
    }

    inner class VipViewHolder(private val vipBinding: FragmentVipBinding) :
        ViewHolder(vipBinding.root) {
        fun onBind() {
            VipFragment(vipBinding, barColor).createView()
        }
    }

    inner class OrdersViewHolder(private val ordersBinding: FragmentOrdersBinding) :
        ViewHolder(ordersBinding.root) {
        fun onBind() {
            OrdersFragment().createView(ordersBinding)
        }
    }

    inner class ProfileViewHolder(private val profile: FragmentProfileBinding) :
        ViewHolder(profile.root) {
        fun onBind() {
            ProfileFragment().createView(profile)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> {
                val binding =
                    FragmentHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HomeViewHolder(binding)
            }

            1 -> {
                val binding =
                    FragmentFreeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FreeViewHolder(binding)
            }

            2 -> {
                val binding =
                    FragmentOrdersBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                OrdersViewHolder(binding)
            }

            3 -> {
                val binding =
                    FragmentVipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VipViewHolder(binding)
            }

            else -> {
                val binding =
                    FragmentProfileBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ProfileViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DashboardItem.HomeItem -> 0
            is DashboardItem.FreeItem -> 1
            DashboardItem.OrdersItem -> 2
            DashboardItem.VipItem -> 3
            DashboardItem.ProfileItem -> 4
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                holder.onBind()
            }

            is FreeViewHolder -> {
                holder.onBind()
            }

            is VipViewHolder -> {
                holder.onBind()
            }

            is OrdersViewHolder -> {
                holder.onBind()
            }

            is ProfileViewHolder -> {
                holder.onBind()
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}