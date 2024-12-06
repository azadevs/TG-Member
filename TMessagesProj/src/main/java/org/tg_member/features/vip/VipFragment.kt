package org.tg_member.features.vip

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentVipBinding
import org.telegram.messenger.databinding.TabItemBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.vip.adapter.VipVPAdapter
import org.tg_member.features.vip.model.VipItem

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class VipFragment(
    private val binding: FragmentVipBinding, val barColor:Int
) {
    lateinit var vipVPAdapter: VipVPAdapter
    var pages = arrayListOf(VipItem.BuyItem, VipItem.TransferItem, VipItem.GiftItem)
    var context: Context = binding.root.context

    fun createView() {

        configureViewPagerAndTab()

    }

    private fun configureViewPagerAndTab() {
        vipVPAdapter = VipVPAdapter(pages)
        binding.vipVp.adapter = vipVPAdapter
        binding.vipTab.setBackgroundColor(barColor)

        TabLayoutMediator(binding.vipTab, binding.vipVp) { tab, position ->
            val tabView = TabItemBinding.inflate(LayoutInflater.from(context), binding.root, false)
            tab.customView = tabView.root

            tabView.titleTv.text = when (pages[position]) {
                is VipItem.BuyItem -> TgMemberStr.getStr(1)
                is VipItem.TransferItem -> TgMemberStr.getStr(2)
                VipItem.GiftItem -> TgMemberStr.getStr(3)
            }

            if (position == 0) {
                tabView.indicator.visibility = View.VISIBLE
                val stateListDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.vip_tab_item_bg) as StateListDrawable
                val gradientDrawable = stateListDrawable.current as GradientDrawable
                gradientDrawable.setColor(Theme.getColor(Theme.key_actionBarTabActiveText))
                tabView.indicator.background = stateListDrawable
                tabView.titleTv.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            } else {
                tabView.indicator.visibility = View.INVISIBLE
                tabView.indicator.setBackgroundResource(0)
                tabView.titleTv.setTextColor(Theme.getColor(Theme.key_actionBarTabUnactiveText))
            }
        }.attach()

        binding.vipTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val indicator = tab?.customView?.findViewById<View>(R.id.indicator)
                indicator?.visibility = View.VISIBLE
                val stateListDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.vip_tab_item_bg) as StateListDrawable
                val gradientDrawable = stateListDrawable.current as GradientDrawable
                gradientDrawable.setColor(Theme.getColor(Theme.key_actionBarTabActiveText))
                indicator?.background = stateListDrawable
                tab?.customView?.findViewById<TextView>(R.id.title_tv)?.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val indicator = tab?.customView?.findViewById<View>(R.id.indicator)
                indicator?.visibility = View.INVISIBLE
                indicator?.setBackgroundResource(0)
                tab?.customView?.findViewById<TextView>(R.id.title_tv)?.setTextColor(Theme.getColor(Theme.key_actionBarTabUnactiveText))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}