package org.tg_member.features.free

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.FragmentFreeBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.telegram.ui.LoginActivity
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getAccounts
import org.tg_member.features.details.AccountDetailsFragment
import org.tg_member.features.free.adapter.FreeAdapter

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class FreeFragment(private val binding: FragmentFreeBinding) {

    private lateinit var adapter: FreeAdapter

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: FreeFragment
    }


    fun createView() {

        instance = this
        
        configureUi()

        configureAccountAdapter()
    }

    fun updateAccountAdapter(){
        adapter.submitList(getAccounts())
    }

    private fun configureUi() {
        binding.root.setBackgroundColor(Theme.getColor(Theme.key_iv_navigationBackground))
        binding.autoJoinBtn.setTextColor(Color.WHITE)
        binding.autoJoinBtn.text = TgMemberStr.getStr(21)
        binding.containerBottomViews.setBackgroundColor(LaunchActivity.instance.navigationBarColor)
        binding.addBtn.text = TgMemberStr.getStr(26)
        binding.addBtn.setTextColor(Color.WHITE)
        binding.addBtn.setOnClickListener {
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
            }else{

            }
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