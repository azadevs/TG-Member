package org.tg_member.features.home.pager.member

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import org.telegram.messenger.R
import org.telegram.messenger.databinding.DialogBottomAutoJoinBinding
import org.telegram.messenger.databinding.DialogNeedVipBinding
import org.telegram.messenger.databinding.FragmentMemberBinding
import org.telegram.messenger.databinding.ItemTabCountryBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TGMemberUtilities
import org.tg_member.core.utils.TGMemberUtilities.showNotEnoughMoneyDialog
import org.tg_member.features.dashboard.DashboardFragment
import org.tg_member.features.home.HomeFragment
import org.tg_member.features.home.adapters.HomeAdapter
import org.tg_member.features.home.model.OrderDisplayData
import org.tg_member.features.home.pager.member.model.CountryDisplayData
import org.tg_member.features.input_channel.InputChannelFragment


class MemberFragment(
    val binding: FragmentMemberBinding
) {
    private lateinit var orderDisplayDataList: ArrayList<OrderDisplayData>
    private lateinit var homeAdapter: HomeAdapter
    private val pages = mutableListOf(
        CountryDisplayData(
            R.drawable.ic_flag_uz,
            "Uzbekistan"
        ),
        CountryDisplayData(
            R.drawable.ic_flag_india,
            "India"
        ),
        CountryDisplayData(
            R.drawable.ic_flag_tr,
            "Turkey"
        ),
    )

    fun createView() {
        setFakeData()
        configureCustomTab()
        configureAdapter()
    }
    private fun configureAdapter() {
        homeAdapter = HomeAdapter(orderDisplayDataList, object : HomeAdapter.HomeClick {
            override fun click(orderDisplayData: OrderDisplayData) {
                if(HomeFragment.instance.getVipCount()<orderDisplayData.priceVip){
                    showNotEnoughMoneyDialog(binding.root.context)
                }else {
                    LaunchActivity.instance.presentFragment(InputChannelFragment(orderDisplayData))
                }
            }
        })
        binding.rvMembers.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvMembers.adapter = homeAdapter
    }


    private fun setFakeData() {
        orderDisplayDataList = ArrayList()
        orderDisplayDataList.add(OrderDisplayData(100, 100, 0, R.drawable.ic_person))
        orderDisplayDataList.add(OrderDisplayData(200, 200, 10, R.drawable.ic_person))
        orderDisplayDataList.add(OrderDisplayData(300, 400, 0, R.drawable.ic_person))
        orderDisplayDataList.add(OrderDisplayData(400, 500, 30, R.drawable.ic_person))
    }

    private fun configureCustomTab() {
        for (i in 0 until pages.size) {
            binding.tab.addTab(
                binding.tab.newTab()
            )
        }
        val tabCount = binding.tab.tabCount
        for (i in 0 until tabCount) {
            val tabBinding = ItemTabCountryBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val tab = binding.tab.getTabAt(i)
            tabBinding.tvCountryName.text = pages[i].country
            tabBinding.ivCountryFlag.setImageResource(pages[i].icon)

            tab?.customView = tabBinding.root

            if (i == 0) {
                tabBinding.indicator.visibility = View.VISIBLE
                val stateListDrawable = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.cut_corners_filled_background
                ) as StateListDrawable
                val gradientDrawable = stateListDrawable.current as GradientDrawable
                gradientDrawable.setColor(Theme.getColor(Theme.key_actionBarDefault))
                tabBinding.indicator.background = stateListDrawable
                tabBinding.tvCountryName.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            } else {
                tabBinding.indicator.visibility = View.GONE
                tabBinding.tvCountryName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            }
        }
        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val indicator = tab?.customView?.findViewById<View>(R.id.indicator)
                indicator?.visibility = View.VISIBLE
                val stateListDrawable = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.cut_corners_filled_background
                ) as StateListDrawable
                val gradientDrawable = stateListDrawable.current as GradientDrawable
                gradientDrawable.setColor(Theme.getColor(Theme.key_actionBarDefault))
                indicator?.background = stateListDrawable
                tab?.customView?.findViewById<TextView>(R.id.tvCountryName)
                    ?.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val indicator = tab?.customView?.findViewById<View>(R.id.indicator)
                indicator?.visibility = View.GONE
                tab?.customView?.findViewById<TextView>(R.id.tvCountryName)
                    ?.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}