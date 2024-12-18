package org.tg_member.features.login

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
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
//            if (binding.codeEt.visibility == View.VISIBLE){
//                var password = 0
//                try {
//                    password = binding.codeEt.text.toString().toInt()
//                }catch (e:Exception){}
//                if (password != 0) {
//                    if (EmailCodeSender.checkCode(password)){
//                        presentFragment(DashboardFragment())
//                    }else{
//                        Toast.makeText(context, "Pasrol xato", Toast.LENGTH_SHORT).show()
//                    }
//                }else{
//                    Toast.makeText(context, "parolni kiriting", Toast.LENGTH_SHORT).show()
//                }
//            }else{
//                val email = binding.gmailEt.text.toString()
//                if (email != "") {
//                    EmailCodeSender.sendEmail(email)
//                }
//                binding.codeEt.visibility = View.VISIBLE
//                binding.loginBtn.text = "Verify"
//            }

            presentFragment(DashboardFragment(),true)
        }

        fragmentView = binding.root

        return fragmentView
    }

    override fun onFragmentDestroy() {
        _binding = null
        super.onFragmentDestroy()
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
        binding.loginBtn.setTextColor(Color.WHITE)
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(14))
        actionBar.backgroundColor=Theme.getColor(Theme.key_myColor)
//        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
//        actionBar.backButtonImageView.setOnClickListener {
//            finishFragment()
//            clearViews()
//            _binding = null
//        }
    }

}