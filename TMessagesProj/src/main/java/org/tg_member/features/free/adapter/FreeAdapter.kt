package org.tg_member.features.free.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.ItemAccountBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.features.free.model.AccountData

class FreeAdapter(
    private val onItemClick: (Long) -> Unit
) : RecyclerView.Adapter<FreeAdapter.FreeViewHolder>() {

    private val accounts = mutableListOf<AccountData>()

    inner class FreeViewHolder(private val binding: ItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(accountData: AccountData) {
            binding.apply {
                tvUserName.text = accountData.name
                tvUserPhone.text = accountData.number
                binding.tvUserPhone.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                binding.tvUserName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

                cardAccount.setCardBackgroundColor(Theme.getColor(Theme.key_dialogBackground))
                cardAccount.setOnClickListener {
                    onItemClick.invoke(accountData.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreeViewHolder {
        return FreeViewHolder(
            ItemAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = accounts.size

    override fun onBindViewHolder(holder: FreeViewHolder, position: Int) {
        holder.onBind(accounts[position])
    }

    fun submitList(list: List<AccountData>) {
        accounts.clear()
        accounts.addAll(list)
        notifyDataSetChanged()
    }
}