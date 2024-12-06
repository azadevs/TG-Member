package org.tg_member.features.orders

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.databinding.FragmentOrdersBinding
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.Status
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.Types
import org.tg_member.core.utils.getStatus
import org.tg_member.core.utils.getTypes
import org.tg_member.features.orders.adapter.OrdersAdapter
import org.tg_member.features.orders.model.Order

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class OrdersFragment {

    private lateinit var list: List<Order>
    private lateinit var adapter: OrdersAdapter

    fun createView(binding: FragmentOrdersBinding) {

        initializeList()

        configureUi(binding)

        configureSpinners(binding)

        configureOrderAdapter(binding)

        configureSpinnerListeners(binding)
    }

    private fun configureSpinnerListeners(binding: FragmentOrdersBinding) {

    }

    private fun initializeList() {
        list = listOf<Order>(
            Order(type = Types.View, status = Status.Pending, count = "100"),
            Order(type = Types.Reaction, status = Status.Completed, count = "200"),
            Order(type = Types.Member, status = Status.Failed, count = "590"),
            Order(type = Types.Premium, status = Status.Failed, count = "10"),
            Order(type = Types.Reaction, status = Status.Pending, count = "7")
        )
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

        val statusAdapter = TypeSpinnerAdapter(binding.root.context, getStatus())
        binding.spinnerStatus.adapter = statusAdapter

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        adapter.submitList(list)
                    }

                    1 -> adapter.submitList(getOrdersByType(Types.Premium.name))
                    2 -> adapter.submitList(getOrdersByType(Types.Member.name))
                    3 -> adapter.submitList(getOrdersByType(Types.View.name))
                    4 -> adapter.submitList(getOrdersByType(Types.Reaction.name))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }

        binding.spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        adapter.submitList(list)
                    }

                    1 -> adapter.submitList(getOrdersByStatus(Status.Pending.name))
                    2 -> adapter.submitList(getOrdersByStatus(Status.Completed.name))
                    3 -> adapter.submitList(getOrdersByStatus(Status.Failed.name))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }

    }

    private fun configureUi(binding: FragmentOrdersBinding) {
        binding.spinnerStatus.setSelection(0)
        binding.spinnerType.setSelection(0)

        if (list == null || list.isEmpty()) {
            binding.rvOrders.visibility = View.GONE
            binding.emptyCaseLl.visibility = View.VISIBLE
        } else {
            binding.rvOrders.visibility = View.VISIBLE
            binding.emptyCaseLl.visibility = View.GONE
        }

        binding.emptyCaseTv.text = TgMemberStr.getStr(4)
    }

    private fun getOrdersByType(type: String): List<Order> {
        return list.filter { it.type?.name == type }
    }

    private fun getOrdersByStatus(status: String): List<Order> {
        return list.filter { it.status?.name == status }
    }


}