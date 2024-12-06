package org.tg_member.features.free

import org.telegram.messenger.databinding.FragmentFreeBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.adapter.TypeSpinnerAdapter

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class FreeFragment(private val binding: FragmentFreeBinding) {

    fun createView() {

        configureTypesSpinner()

        configureUi()
    }

    private fun configureTypesSpinner() {
        val adapter = TypeSpinnerAdapter(binding.root.context)
        binding.spinnerType.adapter = adapter
    }

    private fun configureUi() {
        binding.autoJoinBtn.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }

}