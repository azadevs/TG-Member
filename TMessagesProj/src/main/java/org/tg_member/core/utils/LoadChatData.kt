package org.tg_member.core.utils

import org.telegram.tgnet.ConnectionsManager
import org.telegram.tgnet.TLObject
import org.telegram.tgnet.TLRPC
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap


object LoadChatData {

    var users: ConcurrentHashMap<Long, TLRPC.User> =
        ConcurrentHashMap<Long, TLRPC.User>(100, 1.0f, 3)
    var chats: ConcurrentHashMap<Long, TLRPC.Chat> =
        ConcurrentHashMap<Long, TLRPC.Chat>(100, 1.0f, 2)

    fun loadChannel(username: String, currentAccount: Int, callback: (Boolean) -> Unit) {
        var req: TLRPC.TL_contacts_resolveUsername = TLRPC.TL_contacts_resolveUsername()
        req.username = username.lowercase(Locale.getDefault()).trim()
        ConnectionsManager.getInstance(currentAccount).sendRequest(
            req
        ) { response: TLObject?, error: TLRPC.TL_error? ->
            if (error == null) {
                var res: TLRPC.TL_contacts_resolvedPeer =
                    response as TLRPC.TL_contacts_resolvedPeer
                if (!res.chats.isEmpty()) {

                    val countUser = res.users.size
                    for (a in 0 until countUser) {
                        val user: TLRPC.User = res.users[a]
                        putUser(user)
                    }

                    val countChat = res.chats.size
                    for (a in 0 until countChat) {
                        val chat: TLRPC.Chat = res.chats[a]
                        putChat(chat)
                    }

                    callback(true)
                } else {
                    callback(false)
                }
            } else {
                callback(false)
            }
        }
    }

//    fun loadChannelAndUser(username: String, currentAccount: Int, callback: (Boolean) -> Unit) {
//        var req: TLRPC.TL_contacts_resolveUsername = TLRPC.TL_contacts_resolveUsername()
//        req.username = username.lowercase(Locale.getDefault()).trim()
//        ConnectionsManager.getInstance(currentAccount).sendRequest(
//            req
//        ) { response: TLObject?, error: TLRPC.TL_error? ->
//            if (error == null) {
//                var res: TLRPC.TL_contacts_resolvedPeer =
//                    response as TLRPC.TL_contacts_resolvedPeer
//                if (!res.chats.isEmpty()) {
//
//                    val countUser = res.users.size
//                    for (a in 0 until countUser) {
//                        val user: TLRPC.User = res.users[a]
//                        MessagesController.getInstance(currentAccount).putUser(user,false)
//                    }
//
//                    val countChat = res.chats.size
//                    for (a in 0 until countChat) {
//                        val chat: TLRPC.Chat = res.chats[a]
//                        MessagesController.getInstance(currentAccount).putChat(chat,false)
//                    }
//
//                    callback(true)
//                } else {
//                    callback(false)
//                }
//            } else {
//                callback(false)
//            }
//        }
//    }

    private fun putChat(chat: TLRPC.Chat) {
        chats.put(chat.id, chat)
    }

    private fun putUser(user: TLRPC.User) {
        users.put(user.id, user)
    }

    fun getChat(id: Long): TLRPC.Chat? {
        return chats[id] ?: chats[-id]
    }

    fun getUser(id: Long): TLRPC.User? {
        return users[id] ?: users[-id]
    }

}