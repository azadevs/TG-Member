package org.tg_member.core.utils

import org.telegram.messenger.R
import org.tg_member.core.model.SpinnerTypeData

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


fun getTypes(): List<SpinnerTypeData> {
    return listOf(
        SpinnerTypeData(
            Types.Premium.name,
            R.drawable.vip_svgrepo_com
        ),
        SpinnerTypeData(
            Types.Member.name,
            R.drawable.ic_person_member
        ),
        SpinnerTypeData(
            Types.View.name,
            R.drawable.msg_views
        ),
        SpinnerTypeData(
            Types.Reaction.name,
            R.drawable.msg_reactions
        )
    )
}