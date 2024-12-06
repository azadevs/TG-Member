package org.tg_member.core.utils

import org.telegram.messenger.R
import org.tg_member.core.model.AccountType

/**
 * Created by : Azamat Kalmurzaev
 * 03/12/24
 */
enum class Types {
    Premium,
    Member,
    View,
    Reaction
}

fun getTypes(): List<AccountType> {
    return listOf(
        AccountType(
            Types.Premium.name,
            R.drawable.vip_svgrepo_com
        ),
        AccountType(
            Types.Member.name,
            R.drawable.ic_person_member
        ),
        AccountType(
            Types.View.name,
            R.drawable.msg_views
        ),
        AccountType(
            Types.Reaction.name,
            R.drawable.msg_reactions
        )
    )
}