package org.tg_member.data.remote.model.response

data class LoginRes(
    val token:String,
    val vipCount:Int=0
)
