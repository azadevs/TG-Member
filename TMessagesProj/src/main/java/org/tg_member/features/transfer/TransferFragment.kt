package org.tg_member.features.transfer

import android.graphics.Color
import org.telegram.messenger.databinding.TransferFragmentBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.transfer.history.TransferHistoryFragment

class TransferFragment(private var transferFragmentBinding: TransferFragmentBinding) {
    fun createView() {

        configureUi()

    }

    private fun configureUi() {
        transferFragmentBinding.transferInfoTv.text = TgMemberStr.getStr(5)
        transferFragmentBinding.transferInfoTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        transferFragmentBinding.transferGmailEt.hint = TgMemberStr.getStr(6)
        transferFragmentBinding.transferGmailEt.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        transferFragmentBinding.vipCountEt.hint = TgMemberStr.getStr(7)
        transferFragmentBinding.vipCountEt.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        transferFragmentBinding.transferBtn.text = TgMemberStr.getStr(8)
        transferFragmentBinding.transferBtn.setTextColor(Color.WHITE)

        transferFragmentBinding.transfersHistoryBtn.text = TgMemberStr.getStr(9)
        transferFragmentBinding.transfersHistoryBtn.setTextColor(Color.WHITE)

        transferFragmentBinding.transfersHistoryBtn.setOnClickListener {
            LaunchActivity.instance.presentFragment(TransferHistoryFragment())
        }
    }
}