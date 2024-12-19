package org.tg_member.features.home

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentHomeBinding
import org.telegram.messenger.databinding.ItemTypeTabBinding
import org.telegram.messenger.databinding.TabItemBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.home.adapters.HomeAdapter
import org.tg_member.features.home.adapters.HomePagerAdapter
import org.tg_member.features.home.model.HomeItem
import org.tg_member.features.home.model.OrderDisplayData
import org.tg_member.features.vip.adapter.VipPagerAdapter
import org.tg_member.features.vip.model.VipItem

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class HomeFragment(
    val binding: FragmentHomeBinding
) {
    private lateinit var homePagerAdapter: HomePagerAdapter
    var pages = arrayListOf(HomeItem.Member, HomeItem.View, HomeItem.Reaction)

    fun createView() {
        configureUi()
//        configureTypesSpinner()
//        setFakeData()
//        setUpAdapter()

        configureViewPagerAndTab()
    }

    private fun configureViewPagerAndTab() {
        homePagerAdapter = HomePagerAdapter(pages)
        binding.viewPager.adapter = homePagerAdapter
        binding.viewPager.setBackgroundColor(LaunchActivity.instance.navigationBarColor)
        binding.tab.setupWithViewPager(binding.viewPager)
        binding.tab.setBackgroundColor(Theme.getColor(Theme.key_myColor))

        configureCustomTab()

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val indicator = tab?.customView?.findViewById<View>(R.id.indicator)
                indicator?.visibility = View.VISIBLE
                val stateListDrawable = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.vip_tab_item_bg
                ) as StateListDrawable
                val gradientDrawable = stateListDrawable.current as GradientDrawable
                gradientDrawable.setColor(Theme.getColor(Theme.key_actionBarTabActiveText))
                indicator?.background = stateListDrawable
                tab?.customView?.findViewById<TextView>(R.id.tvTitle)
                    ?.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))

                tab?.customView?.findViewById<ImageView>(R.id.ivTypeImage)?.setColorFilter(Theme.getColor(Theme.key_actionBarTabActiveText))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val indicator = tab?.customView?.findViewById<View>(R.id.indicator)
                indicator?.visibility = View.INVISIBLE
                indicator?.setBackgroundResource(0)
                tab?.customView?.findViewById<TextView>(R.id.tvTitle)
                    ?.setTextColor(Theme.getColor(Theme.key_actionBarTabUnactiveText))

                tab?.customView?.findViewById<ImageView>(R.id.ivTypeImage)?.setColorFilter(Theme.getColor(Theme.key_actionBarTabUnactiveText))
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

            tabBinding.tvTitle.text =  when(pages[i]){
                is HomeItem.Member -> TgMemberStr.getStr(34)
                is HomeItem.View -> TgMemberStr.getStr(35)
                is HomeItem.Reaction -> TgMemberStr.getStr(36)
            }

            tabBinding.ivTypeImage.setImageResource(when(pages[i]){
                HomeItem.Member -> R.drawable.ic_person
                HomeItem.Reaction -> R.drawable.ic_reaction
                HomeItem.View -> R.drawable.ic_view
            })

            tab?.customView = tabBinding.root

            if (i == 0){
                tabBinding.indicator.visibility = View.VISIBLE
                val stateListDrawable = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.vip_tab_item_bg
                ) as StateListDrawable
                val gradientDrawable = stateListDrawable.current as GradientDrawable
                gradientDrawable.setColor(Theme.getColor(Theme.key_actionBarTabActiveText))
                tabBinding.indicator.background = stateListDrawable
                tabBinding.ivTypeImage.setColorFilter(Theme.getColor(Theme.key_actionBarTabActiveText))
                tabBinding.tvTitle.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            }else{
                tabBinding.indicator.visibility = View.INVISIBLE
                tabBinding.indicator.setBackgroundResource(0)
                tabBinding.ivTypeImage.setColorFilter(Theme.getColor(Theme.key_actionBarTabUnactiveText))
                tabBinding.tvTitle.setTextColor(Theme.getColor(Theme.key_actionBarTabUnactiveText))
            }
        }
    }

//    private fun setUpAdapter() {
//        homeAdapter = HomeAdapter(orderMemberToMoneyList,object :HomeAdapter.HomeClick{
//            override fun click(orderMemberToMoney: OrderMemberToMoney) {
//                LaunchActivity.instance.presentFragment(InputChannelFragment(orderMemberToMoney))
//            }
//        })
//        binding.rvVips.layoutManager = LinearLayoutManager(binding.root.context)
//        binding.rvVips.adapter = homeAdapter
    }



    private fun configureUi() {
//        binding.tvType.text = TgMemberStr.getStr(12)
//        binding.tvType.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }

//    private fun configureTypesSpinner() {
//        val adapter = TypeSpinnerAdapter(
//            binding.root.context,
//            getTypes()
//        )
//        binding.spinnerType.adapter = adapter
//        binding.containerTypeSpinner.background = getDrawableStateList(
//            R.drawable.cut_corners_background,
//            binding.root.context,
//            Theme.key_dialogBackground
//        )
//        binding.spinnerType.setPopupBackgroundDrawable(
//            getDrawableStateList(
//                R.drawable.cut_corners_background,
//                binding.root.context,
//                Theme.key_dialogBackground
//            )
//        )
//    }

