package org.tg_member.core.model

import androidx.annotation.DrawableRes

/**
 * Created by : Azamat Kalmurzaev
 * 03/12/24
 */
data class AccountType(
    val type: String,
    @DrawableRes val icon: Int
)