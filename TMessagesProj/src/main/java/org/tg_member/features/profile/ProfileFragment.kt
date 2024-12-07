package org.tg_member.features.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import org.telegram.messenger.databinding.FragmentProfileBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TgMemberStr

/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class ProfileFragment(var binding: FragmentProfileBinding) {


    fun createView() {

        configureUi()

    }

    private fun configureUi() {
        binding.emailTv.text = "hey@gmail.com"
        binding.emailTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        binding.contactUs.text = TgMemberStr.getStr(0)
        binding.contactUs.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }


}