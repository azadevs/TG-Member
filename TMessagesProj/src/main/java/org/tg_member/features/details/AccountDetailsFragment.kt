package org.tg_member.features.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.FragmentAccountDetailsBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.JoinChannels
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getTypes

class AccountDetailsFragment : BaseFragment() {

    private var _binding: FragmentAccountDetailsBinding? = null
    private val binding get() = _binding!!

    override fun createView(context: Context?): View {
        _binding = FragmentAccountDetailsBinding.inflate(LayoutInflater.from(context), null, false)

        configureActionBar()

        configureTypesSpinner()

        configureUi()

        joinChannel()

        return binding.root

    }

    private fun configureActionBar() {
        actionBar.setTitle("Join")
        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
        actionBar.backButtonImageView.setOnClickListener {
            finishFragment()
            clearViews()
            _binding = null
        }
    }

    private fun configureUi() {
        binding.tvChannelLink.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.tvChannelName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.btnJoin.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.btnNext.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.btnNext.text = TgMemberStr.getStr(19)
        binding.btnJoin.text = TgMemberStr.getStr(20)
    }

    private fun joinChannel() {
        binding.btnJoin.setOnClickListener {
            JoinChannels.join(UserConfig.selectedAccount, -1001766948, "kunuzofficial")
        }
    }


    private fun configureTypesSpinner() {
        val adapter = TypeSpinnerAdapter(binding.root.context, getTypes())
        binding.spinnerType.adapter = adapter
    }

    override fun clearViews() {
        super.clearViews()
        _binding = null
    }
}