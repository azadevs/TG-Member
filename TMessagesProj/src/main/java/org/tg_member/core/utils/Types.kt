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
            "All types",
            R.drawable.ic_list
        ),
        SpinnerTypeData(
            Types.Premium.name,
            R.drawable.vip_svgrepo_com
        ),
        SpinnerTypeData(
            Types.Member.name,
            R.drawable.ic_person
        ),
        SpinnerTypeData(
            Types.View.name,
            R.drawable.ic_view
        ),
        SpinnerTypeData(
            Types.Reaction.name,
            R.drawable.ic_reaction
        )
    )
}

fun getStatus(): List<SpinnerTypeData> {
    return listOf(
        SpinnerTypeData(
            "All Status",
            R.drawable.ic_list
        ),
        SpinnerTypeData(
            Status.Pending.name,
            R.drawable.ic_pending
        ),
        SpinnerTypeData(
            Status.Completed.name,
            R.drawable.ic_completed
        ),
        SpinnerTypeData(
            Status.Failed.name,
            R.drawable.ic_failed
        )
    )
}
