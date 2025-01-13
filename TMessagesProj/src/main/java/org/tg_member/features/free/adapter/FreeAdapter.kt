package org.tg_member.features.free.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.ItemAccountBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.features.free.model.AccountData
import kotlin.math.abs

class FreeAdapter(
    private val onItemClick: (AccountData) -> Unit
) : RecyclerView.Adapter<FreeAdapter.FreeViewHolder>() {

    private val accounts = mutableListOf<AccountData>()

    inner class FreeViewHolder(private val binding: ItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(accountData: AccountData) {
            binding.apply {
                tvUserName.text = "${accountData.firstName} ${accountData.lastName}"
                tvUserPhone.text = accountData.number
                binding.tvUserPhone.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
                binding.tvUserName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

                root.setOnClickListener {
                    onItemClick.invoke(accountData)
                }
                val textLabel=accountData.firstName.first() + if(accountData.lastName.isNotEmpty()) accountData.lastName.first().toString() else ""
                tvNameLabel.text=textLabel
                tvNameLabel.setTextColor(Color.WHITE)
                ivUser.setBackgroundColor(Theme.getColor(Theme.keys_avatar_nameInMessage[abs((accountData.id % Theme.keys_avatar_background.size).toDouble()).toInt()]))
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