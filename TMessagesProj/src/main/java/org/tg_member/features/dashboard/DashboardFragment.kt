package org.tg_member.features.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentDashboardBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.tg_member.features.dashboard.adapter.DashboardAdapter
import org.tg_member.features.dashboard.model.DashboardItem

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class DashboardFragment : BaseFragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun createView(context: Context?): View {
        _binding = FragmentDashboardBinding.inflate(LayoutInflater.from(context), null, false)

        configureBottomNavigation()

        configureActionBar()

        configureViewPager()

        fragmentView = binding.root

        return fragmentView
    }

    override fun onFragmentDestroy() {
        _binding = null
        super.onFragmentDestroy()
    }

    private fun configureViewPager() {
        val adapter = DashboardAdapter(
            arrayListOf(
                DashboardItem.HomeItem,
                DashboardItem.FreeItem,
                DashboardItem.OrdersItem,
                DashboardItem.VipItem,
                DashboardItem.ProfileItem
            ),
            actionBar.backgroundColor,
            navigationBarColor
        )
        binding.viewpager.adapter = adapter
        binding.viewpager.isUserInputEnabled = false
    }

    private fun configureActionBar() {
        actionBar.setTitle(parentActivity.getString(R.string.tg_member))
        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
        actionBar.backButtonImageView.setOnClickListener {
            finishFragment()
            clearViews()
            _binding = null
        }
    }

    private fun configureBottomNavigation() {
        binding.bottomNavigationView.setBackgroundColor(navigationBarColor)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    binding.viewpager.currentItem = 0
                }

                R.id.free -> {
                    binding.viewpager.currentItem = 1
                }

                R.id.orders -> {
                    binding.viewpager.currentItem = 2
                }

                R.id.vip -> {
                    binding.viewpager.currentItem = 3
                }

                R.id.Profile -> {
                    binding.viewpager.currentItem = 4
                }
            }
            true
        }

    }

}