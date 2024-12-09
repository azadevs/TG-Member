package org.tg_member.core.utils

import org.telegram.messenger.MessagesController
import org.telegram.messenger.UserConfig
import org.telegram.tgnet.ConnectionsManager
import org.telegram.tgnet.TLRPC
import org.telegram.tgnet.TLObject

object JoinChannels {

    fun join(currentAccount: Int, chatId: Long, username: String) {
        LoadChatData.loadChannel(username, currentAccount) {
            if (it) {
                val req: TLRPC.TL_channels_joinChannel = TLRPC.TL_channels_joinChannel()
                req.channel = MessagesController.getInputChannel(LoadChatData.getChat(chatId));
                ConnectionsManager.getInstance(currentAccount).sendRequest(
                    req
                ) { response: TLObject?, error: TLRPC.TL_error? ->
                    response
                }
            }
        }
    }

//    var accountsNum = 0
//
//    fun joinAllAccount(chatId: Long, username: String) {
//        if (accountsNum < UserConfig.MAX_ACCOUNT_COUNT) {
//            if (UserConfig.isValidAccount(accountsNum)) {
//                LoadChatData.loadChannel(username, accountsNum) {
//                    if (it) {
//                        val req: TLRPC.TL_channels_joinChannel = TLRPC.TL_channels_joinChannel()
//                        req.channel = MessagesController.getInstance(accountsNum)
//                            .getInputChannel(if (chatId < 0) chatId * -1 else chatId)
//                        ConnectionsManager.getInstance(accountsNum).sendRequest(
//                            req
//                        ) { response: TLObject?, error: TLRPC.TL_error? ->
//                            accountsNum++
//                            joinAllAccount(chatId, username)
//                        }
//                    } else {
//
//                    }
//                }
//            } else {
//                accountsNum++
//                joinAllAccount(chatId, username)
//            }
//        } else {
//            accountsNum = 0
//        }
//    }

}