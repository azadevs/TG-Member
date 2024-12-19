package org.tg_member.data.remote.model.request

data class TransferReq(
    val senderEmail:String,
    val receiverEmail:String,
    val vipCount:Int
)
