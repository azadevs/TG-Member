package org.tg_member.features.free.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.telegram.messenger.databinding.ItemAccountBinding
import org.tg_member.features.free.model.AccountData

class FreeAdapter(
    private val accounts: List<AccountData>
) : RecyclerView.Adapter<FreeAdapter.FreeViewHolder>() {
    inner class FreeViewHolder(private val binding: ItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(accountData: AccountData) {
            binding.apply {
                tvUserName.text = accountData.name
                tvUserPhone.text = accountData.number
                
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
}