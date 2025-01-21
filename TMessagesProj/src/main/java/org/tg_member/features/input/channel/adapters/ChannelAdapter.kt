package org.tg_member.features.input.channel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.tgnet.TLRPC
import org.telegram.messenger.databinding.ItemChannelBinding
import org.telegram.ui.ActionBar.Theme

class ChannelAdapter(var list: ArrayList<TLRPC.Chat?>, var callback: (TLRPC.Chat?) -> Unit) :
    RecyclerView.Adapter<ChannelAdapter.ChannelVH>() {

    inner class ChannelVH(var itemChannelBinding: ItemChannelBinding) :
        RecyclerView.ViewHolder(itemChannelBinding.root) {
        fun onBind(chat: TLRPC.Chat?) {
            itemChannelBinding.root.setOnClickListener {
                callback(chat)
            }
            itemChannelBinding.tvChannelName.text = chat?.title ?: ""
            itemChannelBinding.tvChannelUserName.text = chat?.username ?: ""
            itemChannelBinding.tvChannelName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            itemChannelBinding.tvChannelUserName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelVH {
        return ChannelVH(
            ItemChannelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChannelVH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}