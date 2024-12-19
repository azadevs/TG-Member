package org.tg_member.features.home.pager.view

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentViewBinding
import org.telegram.messenger.databinding.ItemTabCountryBinding
import org.telegram.messenger.databinding.ItemTabViewBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.features.home.adapters.HomeAdapter
import org.tg_member.features.home.model.OrderDisplayData
import org.tg_member.features.home.pager.member.model.CountryDisplayData
import org.tg_member.features.input_channel.InputChannelFragment

class ViewFragment(
    val binding: FragmentViewBinding
) {

    private lateinit var postsPrice: ArrayList<OrderDisplayData>
    private lateinit var homeAdapter: HomeAdapter
    private val posts = mutableListOf(
        "One post",
        "5 Posts",
        "10 Posts"
    )

    fun createView() {
        setFakeData()
        configureCustomTab()
        configureAdapter()
    }

    private fun configureAdapter() {
        homeAdapter = HomeAdapter(postsPrice, object : HomeAdapter.HomeClick {
            override fun click(orderDisplayData: OrderDisplayData) {
                LaunchActivity.instance.presentFragment(InputChannelFragment(orderDisplayData))
            }
        })
        binding.rvViews.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvViews.adapter = homeAdapter
    }


    private fun configureCustomTab() {
        for (i in 0 until posts.size) {
            binding.tab.addTab(
                binding.tab.newTab()
            )
        }
        val tabCount = binding.tab.tabCount
        for (i in 0 until tabCount) {
            val tabBinding = ItemTabViewBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val tab = binding.tab.getTabAt(i)
            tabBinding.tvPostCount.text = posts[i]

            tab?.customView = tabBinding.root

            if (i == 0) {
                tabBinding.indicator.visibility = View.VISIBLE
                val stateListDrawable = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.cut_corners_filled_background
                ) as StateListDrawable
                val gradientDrawable = stateListDrawable.current as GradientDrawable
                gradientDrawable.setColor(Theme.getColor(Theme.key_myColor))
                tabBinding.indicator.background = stateListDrawable
                tabBinding.tvPostCount.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            } else {
                tabBinding.indicator.visibility = View.GONE
                tabBinding.tvPostCount.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
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
                gradientDrawable.setColor(Theme.getColor(Theme.key_myColor))
                indicator?.background = stateListDrawable
                tab?.customView?.findViewById<TextView>(R.id.tvPostCount)
                    ?.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val indicator = tab?.customView?.findViewById<View>(R.id.indicator)
                indicator?.visibility = View.GONE
                tab?.customView?.findViewById<TextView>(R.id.tvPostCount)
                    ?.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setFakeData() {
        postsPrice = ArrayList()
        postsPrice.add(OrderDisplayData(100, 200F, 0,R.drawable.ic_view))
        postsPrice.add(OrderDisplayData(200, 300F, 10,R.drawable.ic_view))
        postsPrice.add(OrderDisplayData(300, 400F, 0,R.drawable.ic_view))
        postsPrice.add(OrderDisplayData(400, 500F, 30,R.drawable.ic_view))
    }

}