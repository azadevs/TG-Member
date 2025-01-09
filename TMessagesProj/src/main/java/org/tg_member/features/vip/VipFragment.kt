package org.tg_member.features.vip

import android.content.Context
import org.telegram.messenger.databinding.FragmentVipBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.vip.adapter.VipPagerAdapter
import org.tg_member.features.vip.model.VipItem

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class VipFragment(
    private val binding: FragmentVipBinding, private val actionBarColor: Int
) {
    private lateinit var vipVPAdapter: VipPagerAdapter
    var pages = arrayListOf(VipItem.BuyItem, VipItem.TransferItem, VipItem.GiftItem)
    var context: Context = binding.root.context

    fun createView() {

        configureViewPagerAndTab()

    }

    private fun configureViewPagerAndTab() {
        vipVPAdapter = VipPagerAdapter(pages)
        binding.vipVp.adapter = vipVPAdapter
        binding.root.setBackgroundColor(Theme.getColor(Theme.key_iv_background))
        binding.vipTab.setBackgroundColor(actionBarColor)
        binding.vipTab.setupWithViewPager(binding.vipVp)
        binding.vipTab.setSelectedTabIndicatorColor(Theme.getColor(Theme.key_actionBarTabActiveText))
        binding.vipTab.setTabTextColors(Theme.getColor(Theme.key_actionBarTabUnactiveText),Theme.getColor(Theme.key_actionBarTabActiveText))

        for (i in 0 until binding.vipTab.tabCount) {
            binding.vipTab.getTabAt(i)?.text = when (pages[i]) {
                is VipItem.BuyItem -> TgMemberStr.getStr(1)
                is VipItem.TransferItem -> TgMemberStr.getStr(2)
                is VipItem.GiftItem -> TgMemberStr.getStr(3)
            }
        }
    }
}