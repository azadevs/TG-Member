package org.tg_member.features.transfer.history.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.ItemTransferHistoryBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.features.transfer.history.model.TransferDisplayData

class TransferAdapter(
    private val transferList:List<TransferDisplayData>
) : RecyclerView.Adapter<TransferAdapter.TransferViewHolder>() {

    inner class TransferViewHolder(val binding:ItemTransferHistoryBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(data:TransferDisplayData){
            binding.apply {
                tvTransferDate.text=data.date
                tvTransferVip.text="${data.vipCount} Vip"
                tvTransferEmail.text=data.receiverEmail
                tvTransferDate.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                tvTransferVip.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                tvTransferEmail.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferViewHolder {
        return TransferViewHolder(
            ItemTransferHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: TransferViewHolder, position: Int) {
        holder.onBind(
            transferList[position]
        )
    }

    override fun getItemCount(): Int = transferList.size
}