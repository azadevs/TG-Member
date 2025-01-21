package org.tg_member.features.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import org.telegram.messenger.ApplicationLoader
import org.telegram.messenger.LocaleController
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentProfileBinding
import org.telegram.ui.ActionBar.AlertDialog
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TGMemberUtilities.changeTheme
import org.tg_member.core.utils.TGMemberUtilities.isValidEmail
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.dashboard.DashboardFragment
import org.tg_member.features.login.LoginFragment


/**
 * Created by : Azamat Kalmurzaev
 * 30/11/24
 */
class ProfileFragment(var binding: FragmentProfileBinding) {

    fun createView() {

        configureUi()

        binding.ivCopyEmail.setOnClickListener {
            copyUserEmail()
        }

        binding.languageCard.setOnClickListener {
            TgMemberStr.changeLanguageDialog(binding.root.context) {
                DashboardFragment.instance.recreate(4)
            }
        }

        binding.contactUs.setOnClickListener {
            openSupporterChatInTelegram()
        }

        binding.ivEditEmail.setOnClickListener {
            enableEmailEditText()
        }

        binding.cardLogOut.setOnClickListener {
            logOutDialog(binding.root.context).show()
        }

        binding.ivCheckEmail.setOnClickListener {
            if (isValidEmail(binding.edtEmail.text.toString())) {
                disableEmailEditText()
                updateUserEmail()
                Toast.makeText(binding.root.context, TgMemberStr.getStr(57), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(binding.root.context, TgMemberStr.getStr(52), Toast.LENGTH_SHORT)
                    .show()
            }

        }

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            configureThemeSwitch(isChecked)
        }
    }

    private fun configureThemeSwitch(isChecked: Boolean) {
        val dayThemeName = "Blue"
        val nightThemeName = "Night"
        if (isChecked) {
            changeTheme(
                themeInfo = Theme.getTheme(nightThemeName),
                view = binding.themeSwitch,
                toDark = true
            )
            binding.themeSwitch.text = TgMemberStr.getStr(59)
            ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", MODE_PRIVATE)
                .edit().putBoolean("isSwitch", true).apply()
            DashboardFragment.instance.recreate(4)
        } else {
            changeTheme(
                themeInfo = Theme.getTheme(dayThemeName),
                view = binding.themeSwitch,
                toDark = false
            )
            binding.themeSwitch.text = TgMemberStr.getStr(58)
            ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", MODE_PRIVATE)
                .edit().putBoolean("isSwitch", false).apply()
            DashboardFragment.instance.recreate(4)
        }
    }

    private fun updateUserEmail() {
        binding.edtEmail.setSelection(0)
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", MODE_PRIVATE)
            .edit().putString("userEmail", binding.edtEmail.text.toString()).apply()
        // Send to server userEmail...
    }

    private fun copyUserEmail() {
        val clipboard = getSystemService(binding.root.context, ClipboardManager::class.java)
        clipboard?.setPrimaryClip(ClipData.newPlainText("", binding.edtEmail.text.toString()))
        Toast.makeText(binding.root.context, TgMemberStr.getStr(22), Toast.LENGTH_SHORT).show()
    }

    private fun configureUi() {
        binding.apply {
            edtEmail.setText(
                ApplicationLoader.applicationContext.getSharedPreferences(
                    "mainconfig",
                    MODE_PRIVATE
                ).getString("userEmail", "")
            )
            themeSwitch.isChecked = ApplicationLoader.applicationContext.getSharedPreferences(
                "mainconfig",
                MODE_PRIVATE
            ).getBoolean("isSwitch", false)
            edtEmail.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            contactUs.text = TgMemberStr.getStr(0)
            root.setBackgroundColor(Theme.getColor(Theme.key_iv_background))
            contactUs.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            cardEmail.setCardBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite))
            languageCard.setCardBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite))
            languageId.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
            languageTv.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            languageTv.text = TgMemberStr.getStr(24)
            ivCopyEmail.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
            ivSupport.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
            ivCheckEmail.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
            ivEditEmail.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
            cardTheme.setCardBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite))
            cardLogOut.setCardBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite))
            tvLogOut.setTextColor(Theme.getColor(Theme.key_color_red))
            tvLogOut.text=TgMemberStr.getStr(23)
            ivLogOut.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
            themeSwitch.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            themeSwitch.text =
                if (themeSwitch.isChecked) TgMemberStr.getStr(58) else TgMemberStr.getStr(59)
        }
        disableEmailEditText()
    }

    private fun disableEmailEditText() {
        binding.apply {
            edtEmail.isFocusable = false
            edtEmail.isEnabled = false
            edtEmail.isActivated = false
            edtEmail.isFocusableInTouchMode = false
            edtEmail.isSingleLine = true
            edtEmail.keyListener = null
            edtEmail.ellipsize = TextUtils.TruncateAt.END
            ivCheckEmail.visibility = View.GONE
            ivEditEmail.visibility = View.VISIBLE
        }
    }

    private fun enableEmailEditText() {
        binding.apply {
            edtEmail.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            edtEmail.isFocusableInTouchMode = true
            edtEmail.isEnabled = true
            edtEmail.isActivated = true
            edtEmail.ellipsize = null
            edtEmail.requestFocus()
            edtEmail.setSelection(edtEmail.text.length)
            ivEditEmail.visibility = View.GONE
            ivCheckEmail.visibility = View.VISIBLE
        }
    }

    private fun openSupporterChatInTelegram(username: String = "Habibullohedu") {
        var intent: Intent?
        try {
            try {
                binding.root.context.packageManager.getPackageInfo(
                    "org.telegram.messenger",
                    0
                )
            } catch (e: Exception) {
                binding.root.context.packageManager.getPackageInfo(
                    "org.thunderdog.challegram",
                    0
                )
            }
            intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=$username"))
        } catch (e: Exception) {
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://t.me/$username")
            )
        }
        startActivity(binding.root.context, intent!!, null)
    }

    private fun logOutDialog(context: Context?): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(TgMemberStr.getStr(67))
        builder.setTitle(LocaleController.getString(R.string.LogOut))
        builder.setPositiveButton(
           TgMemberStr.getStr(23)
        ) { _: DialogInterface?, _: Int ->
            ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", MODE_PRIVATE)
                .edit().putBoolean("isLogged", false).apply()
            ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", MODE_PRIVATE)
                .edit().putString("userEmail", "").apply()
            LaunchActivity.instance.presentFragment(LoginFragment(), true,false)
        }
        builder.setNegativeButton(LocaleController.getString(R.string.Cancel), null)
        val alertDialog = builder.create()
        return alertDialog
    }


}