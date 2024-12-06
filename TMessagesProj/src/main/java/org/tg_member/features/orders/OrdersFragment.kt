package org.tg_member.features.orders

import org.telegram.messenger.databinding.FragmentOrdersBinding
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.getTypes

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class OrdersFragment {

    fun createView(binding: FragmentOrdersBinding) {
        configureTypesSpinner(binding)
    }

    private fun configureTypesSpinner(binding: FragmentOrdersBinding) {
        val adapter = TypeSpinnerAdapter(binding.root.context, getTypes())
        binding.spinnerType.adapter = adapter
    }

}