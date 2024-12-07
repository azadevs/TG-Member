package org.tg_member.features.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import org.telegram.messenger.R
import org.telegram.messenger.databinding.LoginFragmentBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.dashboard.DashboardFragment


class LoginFragment : BaseFragment() {


    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    override fun createView(context: Context?): View {
        _binding = LoginFragmentBinding.inflate(LayoutInflater.from(context), null, false)

        configureUi()

        configureActionBar()

        binding.loginBtn.setOnClickListener {
            presentFragment(DashboardFragment())
        }

        return binding.root
    }

    private fun configureUi() {
        binding.loginInfo.text = TgMemberStr.getStr(18)
        binding.loginInfo.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        binding.gmailEt.hint = TgMemberStr.getStr(16)
        binding.gmailEt.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.gmailEt.setHintTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        binding.codeEt.hint = TgMemberStr.getStr(17)
        binding.codeEt.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.codeEt.setHintTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        binding.loginBtn.text = TgMemberStr.getStr(15)
        binding.loginBtn.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(14))
        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
        actionBar.backButtonImageView.setOnClickListener {
            finishFragment()
            clearViews()
            _binding = null
        }
    }

}