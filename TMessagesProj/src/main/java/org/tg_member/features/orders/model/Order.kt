package org.tg_member.features.orders.model
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
