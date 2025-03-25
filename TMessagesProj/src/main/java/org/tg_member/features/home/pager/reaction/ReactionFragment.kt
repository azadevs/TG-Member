package org.tg_member.features.home.pager.reaction

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentReactionBinding
import org.telegram.messenger.databinding.ItemTabCountryBinding
import org.telegram.messenger.databinding.ItemTabViewBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.features.utils.TGMemberUtilities.showNotEnoughMoneyDialog
import org.tg_member.features.home.HomeFragment
import org.tg_member.features.home.adapters.HomeAdapter
import org.tg_member.features.home.model.OrderDisplayData
import org.tg_member.features.home.pager.member.model.CountryDisplayData
import org.tg_member.features.input.post.InputPostFragment

class ReactionFragment(
    val binding: FragmentReactionBinding
) {

    private lateinit var reactions: ArrayList<OrderDisplayData>
    private lateinit var homeAdapter: HomeAdapter

    private val reactionType = mutableListOf(
        "One Post",
        "5 Posts",
        "10 Posts"
    )

    private val countries = mutableListOf(
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

        configureAdapter()

        configureCustomReactionTab()

        configureCustomCountryTab()
    }

    private fun configureAdapter() {
        homeAdapter = HomeAdapter(reactions, object : HomeAdapter.HomeClick {
            override fun click(orderDisplayData: OrderDisplayData) {
                if (HomeFragment.instance.getVipCount() < orderDisplayData.priceVip) {
                    showNotEnoughMoneyDialog(binding.root.context)
                } else {
                    LaunchActivity.instance.presentFragment(InputPostFragment(postCount = getSelectedPostCount()))
                }
            }
        })
        binding.rvReactions.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvReactions.adapter = homeAdapter
    }

    private fun getSelectedPostCount(): Int {
        return when (reactionType[binding.tabReaction.selectedTabPosition]) {
            "One post" -> 1
            "5 Posts" -> 5
            "10 Posts" -> 10
            else -> 1
        }
    }

    private fun configureCustomReactionTab() {
        for (i in 0 until reactionType.size) {
            binding.tabReaction.addTab(
                binding.tabReaction.newTab()
            )
        }
        val tabCount = binding.tabReaction.tabCount
        for (i in 0 until tabCount) {
            val tabBinding = ItemTabViewBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val tab = binding.tabReaction.getTabAt(i)
            tabBinding.tvPostCount.text = reactionType[i]

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
                tabBinding.tvPostCount.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
            } else {
                tabBinding.indicator.visibility = View.GONE
                tabBinding.tvPostCount.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            }
        }
        binding.tabReaction.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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


    private fun configureCustomCountryTab() {
        for (i in 0 until countries.size) {
            binding.tabCountry.addTab(
                binding.tabCountry.newTab()
            )
        }
        val tabCount = binding.tabCountry.tabCount
        for (i in 0 until tabCount) {
            val tabBinding = ItemTabCountryBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val tab = binding.tabCountry.getTabAt(i)
            tabBinding.tvCountryName.text = countries[i].country
            tabBinding.ivCountryFlag.setImageResource(countries[i].icon)

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
        binding.tabCountry.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

    private fun setFakeData() {
        reactions = ArrayList()
        reactions.add(OrderDisplayData(100, 200, 0, R.drawable.ic_reaction))
        reactions.add(OrderDisplayData(200, 300, 10, R.drawable.ic_reaction))
        reactions.add(OrderDisplayData(300, 400, 0, R.drawable.ic_reaction))
        reactions.add(OrderDisplayData(400, 500, 30, R.drawable.ic_reaction))
    }

}