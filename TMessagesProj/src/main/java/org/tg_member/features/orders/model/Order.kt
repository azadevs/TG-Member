package org.tg_member.features.orders.model

import org.tg_member.core.utils.TGMemberUtilities.Status
import org.tg_member.core.utils.TGMemberUtilities.Types

/**
 * Created by Usmon
 * 2024/12/06
 */

data class Order(
    val id: String? = null,
    val type: String? = null,
    val status: String? = null,
    val count: Int? = null
)
