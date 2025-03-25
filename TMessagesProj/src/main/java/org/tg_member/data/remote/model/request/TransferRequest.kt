package org.tg_member.data.remote.model.request

data class TransferRequest(
    val senderEmail: String = "",
    val receiverEmail: String = "",
    val vipCount: Int = 0
)
