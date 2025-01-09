package org.tg_member.features.free

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.FragmentFreeBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.telegram.ui.LoginActivity
import org.tg_member.core.utils.TGMemberUtilities.getAccounts
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.details.AccountDetailsFragment
import org.tg_member.features.free.adapter.FreeAdapter

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class FreeFragment(private val binding: FragmentFreeBinding) {

    private lateinit var adapter: FreeAdapter

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: FreeFragment
    }

    fun createView() {

        instance = this

        configureUi()

        configureAccountAdapter()
    }

    fun updateAccountAdapter() {
        shouldVisibleEmptyViews()
        adapter.submitList(getAccounts())
    }

    private fun configureUi() {
        shouldVisibleEmptyViews()
        binding.root.setBackgroundColor(Theme.getColor(Theme.key_iv_background))
        binding.autoJoinBtn.setTextColor(Color.WHITE)
        binding.autoJoinBtn.text = TgMemberStr.getStr(21)
        binding.containerBottomViews.setBackgroundColor(Theme.getColor(Theme.key_iv_navigationBackground))
        binding.addBtn.text = TgMemberStr.getStr(26)
        binding.addBtn.setTextColor(Color.WHITE)
        binding.tvEmpty.text = TgMemberStr.getStr(56)
        binding.tvEmpty.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.addBtn.setOnClickListener {
            checkCanUserAddAccount()
        }
    }

    private fun checkCanUserAddAccount() {
        var freeAccounts = 0
        var availableAccount: Int? = null
        for (a in UserConfig.MAX_ACCOUNT_COUNT - 1 downTo 0) {
            if (!UserConfig.getInstance(a).isClientActivated) {
                freeAccounts++
                if (availableAccount == null) {
                    availableAccount = a
                }
            }
        }
        if (!UserConfig.hasPremiumOnAccounts()) {
            freeAccounts -= (UserConfig.MAX_ACCOUNT_COUNT - UserConfig.MAX_ACCOUNT_DEFAULT_COUNT)
        }
        if (freeAccounts > 0 && availableAccount != null) {
            LaunchActivity.instance.presentFragment(LoginActivity(availableAccount))
        }
    }

    private fun shouldVisibleEmptyViews() {
        if (getAccounts().isEmpty()) {
            binding.lottieEmpty.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.lottieEmpty.visibility = View.GONE
            binding.tvEmpty.visibility = View.GONE
        }
    }

    private fun configureAccountAdapter() {
        adapter = FreeAdapter { account ->
            LaunchActivity.instance.presentFragment(AccountDetailsFragment(account.accountPosition))
        }
        binding.rvAccount.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.rvAccount.adapter = adapter
        adapter.submitList(getAccounts())
    }

}
