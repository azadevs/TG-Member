package org.tg_member.features.home

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentHomeBinding
import org.telegram.messenger.databinding.ItemTypeTabBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.home.adapters.HomePagerAdapter
import org.tg_member.features.home.model.HomeItem

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class HomeFragment(
    val binding: FragmentHomeBinding
) {
    private lateinit var homePagerAdapter: HomePagerAdapter
    var pages = arrayListOf(HomeItem.Member, HomeItem.View, HomeItem.Reaction, HomeItem.Premium)

    fun createView() {
        configureViewPagerAndTab()
    }

    private fun configureViewPagerAndTab() {
        homePagerAdapter = HomePagerAdapter(pages)
        binding.tab.setSelectedTabIndicatorColor(Theme.getColor(Theme.key_actionBarTabActiveText))
        binding.viewPager.adapter = homePagerAdapter
        binding.viewPager.setBackgroundColor(Theme.getColor(Theme.key_iv_background))
        binding.tab.setupWithViewPager(binding.viewPager)
        binding.tab.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefault))

        configureCustomTab()

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tvTitle)
                    ?.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))

                tab?.customView?.findViewById<ImageView>(R.id.ivTypeImage)
                    ?.setColorFilter(Theme.getColor(Theme.key_actionBarTabActiveText))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tvTitle)
                    ?.setTextColor(Theme.getColor(Theme.key_actionBarTabUnactiveText))

                tab?.customView?.findViewById<ImageView>(R.id.ivTypeImage)
                    ?.setColorFilter(Theme.getColor(Theme.key_actionBarTabUnactiveText))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun configureCustomTab() {
        val tabCount = binding.tab.tabCount
        for (i in 0 until tabCount) {
            val tabBinding = ItemTypeTabBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val tab = binding.tab.getTabAt(i)

            tabBinding.tvTitle.text = when (pages[i]) {
                is HomeItem.Member -> TgMemberStr.getStr(34)
                is HomeItem.View -> TgMemberStr.getStr(35)
                is HomeItem.Reaction -> TgMemberStr.getStr(36)
                HomeItem.Premium -> TgMemberStr.getStr(33)
            }

            tabBinding.ivTypeImage.setImageResource(
                when (pages[i]) {
                    HomeItem.Member -> R.drawable.ic_person
                    HomeItem.Reaction -> R.drawable.ic_reaction
                    HomeItem.View -> R.drawable.ic_view
                    HomeItem.Premium -> R.drawable.ic_premium_member
                }
            )

            tab?.customView = tabBinding.root

            if (i == 0) {
                tabBinding.ivTypeImage.setColorFilter(Theme.getColor(Theme.key_actionBarTabActiveText))
                tabBinding.tvTitle.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            } else {
                tabBinding.ivTypeImage.setColorFilter(Theme.getColor(Theme.key_actionBarTabUnactiveText))
                tabBinding.tvTitle.setTextColor(Theme.getColor(Theme.key_actionBarTabUnactiveText))
            }
        }
    }
}

