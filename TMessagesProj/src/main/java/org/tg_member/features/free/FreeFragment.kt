package org.tg_member.features.free

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.FragmentFreeBinding
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.LaunchActivity
import org.telegram.ui.LoginActivity
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getDrawableStateList
import org.tg_member.features.dashboard.DashboardFragment
import org.tg_member.features.details.AccountDetailsFragment
import org.tg_member.features.free.adapter.FreeAdapter
import org.tg_member.features.free.model.AccountData

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class FreeFragment(private val binding: FragmentFreeBinding, val navigationBarColor: Int) {

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

    fun update(){
        adapter.submitList(getAccounts())
    }

    private fun configureUi() {
        binding.autoJoinBtn.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.autoJoinBtn.text = TgMemberStr.getStr(21)
        binding.containerBottomViews.setBackgroundColor(navigationBarColor)
        binding.autoJoinBtn.background = getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.key_dialogBackground
        )
        binding.addBtn.background=getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.key_dialogBackground
        )
        binding.addBtn.text = TgMemberStr.getStr(26)
        binding.addBtn.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
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

    fun getAccounts(): List<AccountData> {
        val accounts = mutableListOf<AccountData>()
        for (i in 0 until UserConfig.MAX_ACCOUNT_COUNT) {
            if (UserConfig.isValidAccount(i)) {
                val currentUser = UserConfig.getInstance(i).currentUser
                accounts.add(
                    AccountData(
                        name = currentUser.first_name + if (currentUser.last_name != null) {
                            currentUser.last_name
                        } else {
                            ""
                        },
                        number = currentUser.phone,
                        id = currentUser.id,
                        i
                    )
                )
            }
        }
        return accounts
    }

}