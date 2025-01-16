package org.tg_member.features.login

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import org.telegram.messenger.ApplicationLoader
import org.telegram.messenger.R
import org.telegram.messenger.databinding.LoginFragmentBinding
import org.telegram.ui.ActionBar.ActionBar
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.Components.LayoutHelper
import org.telegram.ui.LaunchActivity
import org.tg_member.core.utils.TGMemberUtilities.isValidEmail
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.dashboard.DashboardFragment


class LoginFragment : BaseFragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private var email: String = ""
    override fun createView(context: Context?): View {
        _binding = LoginFragmentBinding.inflate(LayoutInflater.from(context), null, false)


        configureUi()

        configureActionBar()

        fragmentView = binding.root

        return fragmentView
    }


    private fun checkUserCredentials() {

        if (binding.codeEt.visibility == View.VISIBLE) {
            checkInputCode()
        } else {
            val inputEmail = binding.gmailEt.text.toString()
            email=inputEmail
            if (isValidEmail(inputEmail)) {
                EmailCodeSender.sendEmail(inputEmail)
                needInputCodeScreen()
            } else {
                Toast.makeText(context, TgMemberStr.getStr(52), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkInputCode(){
        val editor =
            ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", MODE_PRIVATE)
                .edit()
        var password = 0
        try {
            password = binding.codeEt.text.toString().toInt()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        if (password != 0) {
            if (EmailCodeSender.checkCode(password)) {
//                editor.putBoolean("isLogged", true).apply()
                editor.putString("userEmail", binding.gmailEt.text.toString()).apply()
                presentFragment(DashboardFragment(), true)
                finishFragment()
            } else {
                Toast.makeText(context, TgMemberStr.getStr(50), Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, TgMemberStr.getStr(51), Toast.LENGTH_SHORT).show()
        }
    }

    private fun needInputEmailScreen() {
        binding.codeEt.visibility = View.INVISIBLE
        binding.loginBtn.text = TgMemberStr.getStr(15)
    }

    private fun needInputCodeScreen() {
        binding.codeEt.visibility = View.VISIBLE
        binding.loginBtn.text = TgMemberStr.getStr(14)
    }

    override fun onFragmentDestroy() {
        _binding = null
        super.onFragmentDestroy()
    }

    private fun configureUi() {
        binding.loginInfo.text = TgMemberStr.getStr(18)
        binding.loginInfo.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.root.setBackgroundColor(navigationBarColor)

        binding.gmailEt.hint = TgMemberStr.getStr(16)
        binding.gmailEt.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.gmailEt.setHintTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        binding.codeEt.hint = TgMemberStr.getStr(17)
        binding.codeEt.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.codeEt.setHintTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        binding.loginBtn.text = TgMemberStr.getStr(15)
        binding.loginBtn.setTextColor(Theme.getColor(Theme.key_chats_menuName))

        binding.loginBtn.setOnClickListener {
//            checkUserCredentials()
//            observerEmailEditText()
            presentFragment(DashboardFragment(), true)
        }
    }

    private fun observerEmailEditText() {
        binding.gmailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(TextUtils.equals(s,email)){
                    needInputCodeScreen()
                }else{
                    needInputEmailScreen()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(14))
        actionBar.backgroundColor = actionBar.backgroundColor
        val menu = actionBar.createMenu()
        val linearLayout = LinearLayout(context).apply {
            layoutParams =
                LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT)
            gravity = Gravity.CENTER_VERTICAL
        }
        val languageIcon = ImageView(context)
        val languageTextView=TextView(context)
        languageIcon.setImageResource(R.drawable.msg_language)
        languageTextView.textSize = 16f
        languageTextView.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_medium))
        languageTextView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultTitle))
        languageIcon.setColorFilter(Theme.getColor(Theme.key_actionBarDefaultIcon))
        languageTextView.text = TgMemberStr.languageCode
        linearLayout.addView(
            languageTextView,
            LayoutHelper.createLinear(
                LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT,
                Gravity.RIGHT or Gravity.CENTER,
                0,
                0,
                7,
                0
            )
        )
        linearLayout.addView(
            languageIcon,
            LayoutHelper.createLinear(
                20,
                20,
                Gravity.RIGHT or Gravity.CENTER,
                0,
                0,
                16,
                0
            )

        )
        menu.addView(linearLayout)
        linearLayout.setOnClickListener {
            TgMemberStr.changeLanguageDialog(binding.root.context) {
                LaunchActivity.instance.rebuildAllFragments(true)
            }
        }
    }

}