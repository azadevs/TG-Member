package org.tg_member.features.home

import org.telegram.messenger.databinding.FragmentHomeBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.getTypes

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class HomeFragment(
    val binding: FragmentHomeBinding
) {

    fun createView() {
        binding.tvType.setTextColor(
            Theme.getColor(
                Theme.key_chats_menuItemText
            )
        )
        configureTypesSpinner()
    }

    private fun configureTypesSpinner() {
        val adapter = TypeSpinnerAdapter(
            binding.root.context,
            getTypes()
        )
        binding.spinnerType.adapter = adapter
    }

}