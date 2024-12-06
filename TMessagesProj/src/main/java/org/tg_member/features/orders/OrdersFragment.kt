package org.tg_member.features.orders

import org.telegram.messenger.databinding.FragmentOrdersBinding
import org.tg_member.core.adapter.TypeSpinnerAdapter

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class OrdersFragment {

    fun createView(binding: FragmentOrdersBinding) {
        configureTypesSpinner(binding)
    }

    private fun configureTypesSpinner(binding: FragmentOrdersBinding) {
        val adapter = TypeSpinnerAdapter(binding.root.context)
        binding.spinnerType.adapter = adapter
    }

}