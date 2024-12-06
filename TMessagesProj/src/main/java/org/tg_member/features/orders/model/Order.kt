package org.tg_member.features.orders.model

import org.tg_member.core.utils.Status
import org.tg_member.core.utils.Types

/**
 * Created by Usmon
 * 2024/12/06
 */

data class Order(
    val id: String? = null,
    val type: Types? = null,
    val status: Status? = null,
    val count: String? = null
)
