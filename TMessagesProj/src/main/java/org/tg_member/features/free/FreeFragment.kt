package org.tg_member.features.free

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.FragmentFreeBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.adapter.TypeSpinnerAdapter
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getDrawableStateList
import org.tg_member.core.utils.getTypes
import org.tg_member.features.free.adapter.FreeAdapter
import org.tg_member.features.free.model.AccountData

/**
 * Created by : Azamat Kalmurzaev
 * 26/11/24
 */
class FreeFragment(private val binding: FragmentFreeBinding, val navigationBarColor: Int) {

    private lateinit var adapter: FreeAdapter

    fun createView() {

        configureTypesSpinner()

        configureUi()

        configureAccountAdapter()
    }

    private fun configureTypesSpinner() {
        val adapter = TypeSpinnerAdapter(binding.root.context, getTypes())
        binding.spinnerType.adapter = adapter
        binding.spinnerContainer.background = getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.key_dialogBackground
        )
        binding.spinnerType.setPopupBackgroundDrawable(
            getDrawableStateList(
                R.drawable.cut_corners_background,
                binding.root.context,
                Theme.key_dialogBackground
            )
        )

    }

    private fun configureUi() {
        binding.autoJoinBtn.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.autoJoinBtn.text = TgMemberStr.getStr(13)
        binding.containerBottomViews.setBackgroundColor(navigationBarColor)
        binding.autoJoinBtn.background = getDrawableStateList(
            R.drawable.cut_corners_background,
            binding.root.context,
            Theme.key_dialogBackground
        )
    }

    private fun configureAccountAdapter() {
        adapter = FreeAdapter { id ->

        }
        binding.rvAccount.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.rvAccount.adapter = adapter
        adapter.submitList(getAccounts())
    }

    private fun getAccounts(): List<AccountData> {
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
                        id = currentUser.id
                    )
                )
            }
        }
        return accounts
    }

}