package org.tg_member.features.home

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentHomeBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getDrawableStateList
import org.tg_member.core.utils.getTypes
import org.tg_member.features.home.adapters.HomeAdapter
import org.tg_member.features.home.model.OrderMemberToMoney
import org.tg_member.features.input_channel.InputChannelFragment

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class HomeFragment(
    val binding: FragmentHomeBinding
) {
    lateinit var orderMemberToMoneyList:ArrayList<OrderMemberToMoney>
    lateinit var homeAdapter: HomeAdapter

    fun createView() {
        configureUi()
        configureTypesSpinner()
        setFakeData()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        homeAdapter = HomeAdapter(orderMemberToMoneyList,object :HomeAdapter.HomeClick{
            override fun click(orderMemberToMoney: OrderMemberToMoney) {
                LaunchActivity.instance.presentFragment(InputChannelFragment(orderMemberToMoney))
            }
        })
        binding.rvVips.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvVips.adapter = homeAdapter
    }

    private fun setFakeData() {
        orderMemberToMoneyList = ArrayList()
        orderMemberToMoneyList.add(OrderMemberToMoney(100, 200F, 0))
        orderMemberToMoneyList.add(OrderMemberToMoney(200, 300F, 10))
        orderMemberToMoneyList.add(OrderMemberToMoney(300, 400F, 0))
        orderMemberToMoneyList.add(OrderMemberToMoney(400, 500F, 30))
    }

    private fun configureUi() {
        binding.tvType.text = TgMemberStr.getStr(12)
        binding.tvType.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }

    private fun configureTypesSpinner() {
        val adapter = TypeSpinnerAdapter(
            binding.root.context,
            getTypes()
        )
        binding.spinnerType.adapter = adapter
        binding.containerTypeSpinner.background = getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.key_dialogBackground
        )
        binding.spinnerType.setPopupBackgroundDrawable(
            getDrawableStateList(
                R.drawable.cut_corners_background,
                binding.root.context,
                Theme.key_dialogBackground
            )
        )
    }

}