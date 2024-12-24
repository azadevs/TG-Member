package org.tg_member.core.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.model.SpinnerTypeData
import org.tg_member.features.free.model.AccountData

/**
 * Created by : Azamat Kalmurzaev
 * 24/12/24
 */


object TGMemberUtilities {

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

    fun getDrawableStateList(
        @DrawableRes drawable: Int,
        context: Context,
        color: Int
    ): StateListDrawable {
        val stateListDrawablePopup = ContextCompat.getDrawable(
            context, drawable
        ) as StateListDrawable
        val gradientDrawablePopup = stateListDrawablePopup.current as GradientDrawable
        gradientDrawablePopup.setColor(Theme.getColor(color))
        return stateListDrawablePopup
    }

    enum class Types {
        AllTypes,
        Premium,
        Member,
        View,
        Reaction
    }

    enum class Status {
        AllStatus,
        Pending,
        Completed,
        Failed
    }

    fun getTypes(): List<SpinnerTypeData> {
        return listOf(
            SpinnerTypeData(
                TgMemberStr.getStr(32),
                R.drawable.ic_list
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(33),
                R.drawable.ic_premium_member
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(34),
                R.drawable.ic_person
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(35),
                R.drawable.ic_view
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(36),
                R.drawable.ic_reaction
            )
        )
    }

    fun getStatus(): List<SpinnerTypeData> {
        return listOf(
            SpinnerTypeData(
                TgMemberStr.getStr(37),
                R.drawable.ic_list
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(38),
                R.drawable.ic_pending
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(39),
                R.drawable.ic_completed
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(40),
                R.drawable.ic_failed
            )
        )
    }


}