package org.tg_member.features.home

import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentHomeBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getDrawableStateList
import org.tg_member.core.utils.getTypes

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class HomeFragment(
    val binding: FragmentHomeBinding
) {

    fun createView() {
        configureUi()
        configureTypesSpinner()
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