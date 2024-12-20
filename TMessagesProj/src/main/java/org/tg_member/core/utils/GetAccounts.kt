package org.tg_member.core.utils

import org.telegram.messenger.UserConfig
import org.tg_member.features.free.model.AccountData

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