package org.tg_member.core.utils

import org.telegram.tgnet.ConnectionsManager
import org.telegram.tgnet.TLObject
import org.telegram.tgnet.TLRPC
import java.util.Locale
import javax.inject.Singleton
@Singleton
object LoadSelectedChannel {

    var channels = ArrayList<TLRPC.Chat?>()

    fun loadChannel(username: String, currentAccount: Int, callback: (Boolean) -> Unit) {
        var req: TLRPC.TL_contacts_resolveUsername = TLRPC.TL_contacts_resolveUsername()
        req.username = username.lowercase(Locale.getDefault()).trim()
        ConnectionsManager.getInstance(currentAccount).sendRequest(
            req
        ) { response: TLObject?, error: TLRPC.TL_error? ->
            channels.clear()
            if (error == null) {
                var res: TLRPC.TL_contacts_resolvedPeer =
                    response as TLRPC.TL_contacts_resolvedPeer
                if (!res.chats.isEmpty()) {
                    val countChat = res.chats.size
                    for (a in 0 until countChat) {
                        val chat: TLRPC.Chat = res.chats[a]
                        channels.add(chat)
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
}