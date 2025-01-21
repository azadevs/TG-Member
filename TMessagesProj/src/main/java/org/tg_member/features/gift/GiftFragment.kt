package org.tg_member.features.gift

import android.graphics.Color
import org.telegram.messenger.databinding.GiftFragmentBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TgMemberStr

class GiftFragment(var giftFragmentBinding: GiftFragmentBinding) {
    fun createView() {
        configureUi()
    }

    private fun configureUi() {
        giftFragmentBinding.giftInfoTv.text = TgMemberStr.getStr(10)
        giftFragmentBinding.giftInfoTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        giftFragmentBinding.giftCode.hint = TgMemberStr.getStr(10)
        giftFragmentBinding.giftCode.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        giftFragmentBinding.enter.text = TgMemberStr.getStr(11)
        giftFragmentBinding.enter.setTextColor(Color.WHITE)
    }
}