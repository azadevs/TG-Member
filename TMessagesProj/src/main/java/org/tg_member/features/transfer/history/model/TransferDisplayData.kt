package org.tg_member.features.transfer.history.model

data class TransferDisplayData(
    val senderEmail:String?=null,
    val receiverEmail:String,
    val vipCount:Int,
    val date:String
)
