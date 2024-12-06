package org.tg_member.features.orders

import android.view.View
import org.telegram.messenger.databinding.FragmentOrdersBinding
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getStatus
import org.tg_member.core.utils.getTypes

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class OrdersFragment {

    fun createView(binding: FragmentOrdersBinding) {

        configureUi(binding)
        configureSpinners(binding)

    }

    private fun configureSpinners(binding: FragmentOrdersBinding) {
        val typeAdapter = TypeSpinnerAdapter(binding.root.context, getTypes())
        binding.spinnerType.adapter = typeAdapter

        val statusAdapter = TypeSpinnerAdapter(binding.root.context, getStatus())
        binding.spinnerStatus.adapter = statusAdapter
    }

    private fun configureUi(binding: FragmentOrdersBinding) {
        binding.spinnerStatus.setSelection(0)
        binding.spinnerType.setSelection(0)

        binding.rvOrders.visibility = View.GONE
        binding.emptyCaseLl.visibility = View.VISIBLE

        binding.emptyCaseTv.text = TgMemberStr.getStr(4)
    }


}