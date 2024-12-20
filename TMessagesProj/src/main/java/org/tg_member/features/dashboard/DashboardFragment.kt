package org.tg_member.features.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import org.intellij.lang.annotations.JdkConstants.FontStyle
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentDashboardBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.Components.LayoutHelper
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.dashboard.adapter.DashboardPagerAdapter
import org.tg_member.features.dashboard.model.DashboardItem

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class DashboardFragment : BaseFragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: DashboardFragment
    }

    override fun createView(context: Context?): View {
        _binding = FragmentDashboardBinding.inflate(LayoutInflater.from(context), null, false)

        instance = this

        configureBottomNavigation()

        configureActionBar()

        configureViewPager()

        fragmentView = binding.root

        return fragmentView
    }

    fun recreate(uiPosition: Int) {

        LaunchActivity.instance.rebuildAllFragments(true)

        val item = binding.bottomNavigationView.menu.getItem(uiPosition)
        binding.bottomNavigationView.selectedItemId = item.itemId
    }

    override fun onBackPressed(): Boolean {
        val item = binding.bottomNavigationView.menu.getItem(0)

        if (binding.bottomNavigationView.selectedItemId == item.itemId) {
            finishFragment()
        } else {
            binding.bottomNavigationView.selectedItemId = item.itemId
        }
        return false
    }

    override fun onFragmentDestroy() {
        _binding = null
        super.onFragmentDestroy()
    }

    private fun configureViewPager() {
        val adapter = DashboardPagerAdapter(
            arrayListOf(
                DashboardItem.HomeItem,
                DashboardItem.FreeItem,
                DashboardItem.OrdersItem,
                DashboardItem.VipItem,
                DashboardItem.ProfileItem
            ),
            actionBar.backgroundColor
        )
        binding.viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                var item = binding.bottomNavigationView.menu.getItem(position)
                binding.bottomNavigationView.selectedItemId = item.itemId
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        binding.viewpager.adapter = adapter
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(27))
        actionBar.backgroundColor=Theme.getColor(Theme.key_myColor)
        actionBar.title
        val menu = actionBar.createMenu()
        val linearLayout=LinearLayout(binding.root.context).apply {
            layoutParams=LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT)
            gravity=Gravity.CENTER_VERTICAL
        }
        val vipCountTextView=TextView(binding.root.context)
        val vipIcon=ImageView(binding.root.context)
        vipIcon.setImageResource(R.drawable.vip_svgrepo_com)
        vipCountTextView.textSize=16f
        vipCountTextView.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_semibold))
        vipCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
        vipIcon.setColorFilter(Theme.getColor(Theme.key_actionBarTabActiveText))
        vipCountTextView.text="2000"
        linearLayout.addView(
            vipCountTextView,
            LayoutHelper.createLinear(
                LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT,
                Gravity.RIGHT or Gravity.CENTER,
                0,
                0,
                7,
                0
            )
        )
        linearLayout.addView(
            vipIcon,
            LayoutHelper.createLinear(
                20,
                20,
                Gravity.RIGHT or Gravity.CENTER,
                0,
                0,
                16,
                0
            )

        )
        menu.addView(linearLayout)
    }

    private fun configureBottomNavigation() {
        binding.bottomNavigationView.setBackgroundColor(navigationBarColor)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    binding.viewpager.currentItem = 0
                    actionBar.setTitle(TgMemberStr.getStr(27))
                }

                R.id.free -> {
                    binding.viewpager.currentItem = 1
                    actionBar.setTitle(TgMemberStr.getStr(28))
                }

                R.id.orders -> {
                    binding.viewpager.currentItem = 2
                    actionBar.setTitle(TgMemberStr.getStr(29))
                }

                R.id.vip -> {
                    binding.viewpager.currentItem = 3
                    actionBar.setTitle(TgMemberStr.getStr(30))
                }

                R.id.profile -> {
                    binding.viewpager.currentItem = 4
                    actionBar.setTitle(TgMemberStr.getStr(31))
                }
            }
            true
        }

        val menu = binding.bottomNavigationView.menu

        menu.findItem(R.id.home).title = TgMemberStr.getStr(27)
        menu.findItem(R.id.free).title = TgMemberStr.getStr(28)
        menu.findItem(R.id.orders).title = TgMemberStr.getStr(29)
        menu.findItem(R.id.vip).title = TgMemberStr.getStr(30)
        menu.findItem(R.id.profile).title = TgMemberStr.getStr(31)

    }
}