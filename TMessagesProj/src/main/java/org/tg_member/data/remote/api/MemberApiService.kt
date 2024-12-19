package org.tg_member.data.remote.api

import org.tg_member.data.remote.model.request.LoginReq
import org.tg_member.data.remote.model.request.TransferReq
import org.tg_member.data.remote.model.response.LoginRes
import org.tg_member.data.remote.model.response.TransferRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MemberApiService {

    @POST("email")
    fun sendEmail(@Body loginReq: LoginReq):Response<LoginRes>

    @POST("transfer")
    fun transfer(@Body transferReq: TransferReq):Response<TransferRes>


}