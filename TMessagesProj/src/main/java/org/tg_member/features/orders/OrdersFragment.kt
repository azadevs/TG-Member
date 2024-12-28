package org.tg_member.features.orders

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentOrdersBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.TGMemberUtilities.Status
import org.tg_member.core.utils.TGMemberUtilities.Types
import org.tg_member.core.utils.TGMemberUtilities.getDrawableStateList
import org.tg_member.core.utils.TGMemberUtilities.getStatus
import org.tg_member.core.utils.TGMemberUtilities.getTypes
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.orders.adapter.OrdersAdapter
import org.tg_member.features.orders.model.Order

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class OrdersFragment {

    private lateinit var list: List<Order>
    private lateinit var adapter: OrdersAdapter

    private var lastStatus: Status = Status.AllStatus
    private var lastType: Types = Types.AllTypes

    fun createView(binding: FragmentOrdersBinding) {

        list = ArrayList()

        configureUi(binding)

        configureSpinners(binding)

        configureOrderAdapter(binding)

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
            Theme.key_iv_navigationBackground
        )
        binding.spinnerType.setPopupBackgroundDrawable(
            getDrawableStateList(
                R.drawable.cut_corners_background,
                binding.root.context,
                Theme.key_iv_navigationBackground
            )
        )

        val statusAdapter = TypeSpinnerAdapter(binding.root.context, getStatus())
        binding.spinnerStatus.adapter = statusAdapter
        binding.containerStatusSpinner.background = getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.key_iv_navigationBackground
        )
        binding.spinnerStatus.setPopupBackgroundDrawable(
            getDrawableStateList(
                R.drawable.cut_corners_background,
                binding.root.context,
                Theme.key_iv_navigationBackground
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
                    0 -> Types.AllTypes
                    1 -> Types.Premium
                    2 -> Types.Member
                    3 -> Types.View
                    4 -> Types.Reaction
                    else -> {
                        Types.AllTypes
                    }

                }
                val currList = getOrdersByStatusAndType(lastStatus.name, lastType.name)
                when (position) {
                    0 -> {
                        adapter.submitList(currList)
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    1 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus.name, lastType.name))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    2 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus.name, lastType.name))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    3 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus.name, lastType.name))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    4 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus.name, lastType.name))
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
                    0 -> Status.AllStatus
                    1 -> Status.Pending
                    2 -> Status.Completed
                    3 -> Status.Failed
                    else -> {
                        Status.AllStatus
                    }
                }

                var currList = getOrdersByStatusAndType(lastStatus.name, lastType.name)
                when (position) {
                    0 -> {
                        adapter.submitList(currList)
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    1 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus.name, lastType.name))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    2 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus.name, lastType.name))
                        invisibleBoxIfEmpty(binding, currList)
                    }

                    3 -> {
                        adapter.submitList(getOrdersByStatusAndType(lastStatus.name, lastType.name))
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
        binding.root.setBackgroundColor(Theme.getColor(Theme.key_iv_navigationBackground))

        invisibleBoxIfEmpty(binding, list)

        binding.tvEmpty.text = TgMemberStr.getStr(4)
        binding.tvEmpty.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }

    private fun getOrdersByType(type: String): List<Order> {
        return list.filter { it.type?.name == type }
    }

    private fun getOrdersByStatus(status: String): List<Order> {
        return list.filter { it.status?.name == status }
    }

    private fun getOrdersByStatusAndType(status: String, type: String): List<Order> {
        return if (status == Status.AllStatus.name && type == Types.AllTypes.name) {
            list
        } else if (status == Status.AllStatus.name) {
            getOrdersByType(type)
        } else if (type == Types.AllTypes.name) {
            getOrdersByStatus(status)
        } else {
            list.filter { it.status?.name == status && it.type?.name == type }
        }
    }

    fun invisibleBoxIfEmpty(binding: FragmentOrdersBinding, currList: List<Order>) {
        if (currList.isEmpty()) {
            binding.rvOrders.visibility = View.GONE
            binding.lottieEmpty.visibility = View.VISIBLE
            binding.tvEmpty.visibility=View.VISIBLE
        } else {
            binding.rvOrders.visibility = View.VISIBLE
            binding.lottieEmpty.visibility = View.GONE

            binding.tvEmpty.visibility=View.GONE
        }
    }

}