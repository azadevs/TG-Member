package org.tg_member.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import org.telegram.messenger.databinding.FragmentHomeBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.adapter.TypeSpinnerAdapter

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class HomeFragment(
    val binding: FragmentHomeBinding
) {

    fun createView() {
        binding.cardSpinner.setCardBackgroundColor(
            Theme.getColor(
                Theme.key_chats_menuBackground
            )
        )

        binding.tvType.setTextColor(
            Theme.getColor(
                Theme.key_chats_menuItemText
            )
        )

        configureTypesSpinner()
    }

    private fun configureTypesSpinner() {
        val adapter = TypeSpinnerAdapter(binding.root.context)
        binding.spinnerType.adapter = adapter
    }

}