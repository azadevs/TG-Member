package org.tg_member.features.orders

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentOrdersBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.features.orders.adapter.TypeSpinnerAdapter
import org.tg_member.features.utils.TGMemberUtilities.getDrawableStateList
import org.tg_member.features.utils.TGMemberUtilities.getStatus
import org.tg_member.features.utils.TGMemberUtilities.getTypes
import org.tg_member.features.utils.TgMemberStr
import org.tg_member.features.orders.adapter.OrdersAdapter
import org.tg_member.features.orders.model.Order

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class OrdersFragment {

    private lateinit var list: MutableList<Order>
    private lateinit var adapter: OrdersAdapter

    private var lastStatus: String = TgMemberStr.getStr(37)
    private var lastType: String = TgMemberStr.getStr(32)

    fun createView(binding: FragmentOrdersBinding) {

        list = ArrayList()

        configureUi(binding)

        configureSpinners(binding)

        configureOrderAdapter(binding)

        loadData()

    }

    private fun configureOrderAdapter(binding: FragmentOrdersBinding) {
        adapter = OrdersAdapter()
        adapter.submitList(list)
        binding.rvOrders.adapter = adapter
        binding.rvOrders.layoutManager = LinearLayoutManager(binding.root.context)
    }

    private fun configureSpinners(binding: FragmentOrdersBinding) {
        val typeAdapter = TypeSpinnerAdapter(binding.root.context, getTypes())
        binding.spinnerType.adapter = typeAdapter
        binding.containerTypeSpinner.background = getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.getColor(Theme.key_iv_background)
        )
        binding.spinnerType.setPopupBackgroundDrawable(
            getDrawableStateList(
                R.drawable.cut_corners_background,
                binding.root.context,
                Theme.getColor(Theme.key_iv_background)
            )
        )

        val statusAdapter = TypeSpinnerAdapter(binding.root.context, getStatus())
        binding.spinnerStatus.adapter = statusAdapter
        binding.containerStatusSpinner.background = getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.getColor(Theme.key_iv_background)
        )
        binding.spinnerStatus.setPopupBackgroundDrawable(
            getDrawableStateList(
                R.drawable.cut_corners_background,
                binding.root.context,
                Theme.getColor(Theme.key_iv_background)
            )
        )

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                lastType = when (position) {
                    0 -> TgMemberStr.getStr(32)
                    1 -> TgMemberStr.getStr(33)
                    2 -> TgMemberStr.getStr(34)
                    3 -> TgMemberStr.getStr(35)
                    4 -> TgMemberStr.getStr(36)
                    else -> {
                        TgMemberStr.getStr(32)
                    }

                }
                val currList = getOrdersByStatusAndType(lastStatus, lastType)
                when (position) {
                    0 -> {
                        adapter.submitList(currList)
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    1 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus, lastType))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    2 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus, lastType))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    3 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus, lastType))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    4 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus, lastType))
                        invisibleBoxIfEmpty(binding, currList)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        binding.spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                lastStatus = when (position) {
                    0 -> TgMemberStr.getStr(37)
                    1 -> TgMemberStr.getStr(38)
                    2 -> TgMemberStr.getStr(39)
                    3 -> TgMemberStr.getStr(40)
                    else -> {
                        TgMemberStr.getStr(37)
                    }
                }

                val currList = getOrdersByStatusAndType(lastStatus, lastType)
                when (position) {
                    0 -> {
                        adapter.submitList(currList)
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    1 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus, lastType))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    2 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus, lastType))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    3 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus, lastType))
                        invisibleBoxIfEmpty(binding, currList)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    private fun configureUi(binding: FragmentOrdersBinding) {
        binding.spinnerStatus.setSelection(0)
        binding.spinnerType.setSelection(0)
        binding.root.setBackgroundColor(Theme.getColor(Theme.key_iv_background))

        invisibleBoxIfEmpty(binding, list)

        binding.tvEmpty.text = TgMemberStr.getStr(4)
        binding.tvEmpty.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }

    private fun getOrdersByType(type: String): List<Order> {
        return list.filter { it.type == type }
    }

    private fun getOrdersByStatus(status: String): List<Order> {
        return list.filter { it.status == status }
    }

    private fun getOrdersByStatusAndType(status: String, type: String): List<Order> {
        return if (status == TgMemberStr.getStr(37) && type == TgMemberStr.getStr(32)) {
            list
        } else if (status == TgMemberStr.getStr(37)) {
            getOrdersByType(type)
        } else if (type == TgMemberStr.getStr(32)) {
            getOrdersByStatus(status)
        } else {
            list.filter { it.status == status && it.type == type }
        }
    }

    fun invisibleBoxIfEmpty(binding: FragmentOrdersBinding, currList: List<Order>) {
        if (currList.isEmpty()) {
            binding.rvOrders.visibility = View.GONE
            binding.lottieEmpty.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.rvOrders.visibility = View.VISIBLE
            binding.lottieEmpty.visibility = View.GONE
            binding.tvEmpty.visibility = View.GONE
        }
    }

    private fun loadData() {
        for (i in 0 until 10) {
            list.add(
                Order(
                    type = TgMemberStr.getStr(33),
                    status = TgMemberStr.getStr(38),
                    count = 100 * (i + 1)
                )
            )
        }
    }

}