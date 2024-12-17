package org.tg_member.core.utils

import org.telegram.messenger.R
import org.tg_member.core.model.SpinnerTypeData

/**
 * Created by : Azamat Kalmurzaev
 * 03/12/24
 */
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
            R.drawable.vip_ic
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
