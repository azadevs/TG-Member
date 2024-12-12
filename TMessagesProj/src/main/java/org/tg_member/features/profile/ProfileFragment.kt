package org.tg_member.features.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import org.telegram.messenger.databinding.FragmentProfileBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TgMemberStr


/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class ProfileFragment(var binding: FragmentProfileBinding) {

    fun createView() {

        configureUi()

        binding.ivCopyEmail.setOnClickListener {
            val clipboard = getSystemService(binding.root.context, ClipboardManager::class.java)
            clipboard?.setPrimaryClip(ClipData.newPlainText("", binding.emailTv.text))
            Toast.makeText(binding.root.context, TgMemberStr.getStr(22), Toast.LENGTH_SHORT).show()
        }

    }

    private fun configureUi() {
        binding.emailTv.text = "hey@gmail.com"
        binding.emailTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.contactUs.text = TgMemberStr.getStr(0)
        binding.contactUs.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.cardEmail.setCardBackgroundColor(Theme.getColor(Theme.key_dialogBackground))
    }


}