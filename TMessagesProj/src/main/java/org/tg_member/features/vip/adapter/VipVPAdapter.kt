package org.tg_member.features.vip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager.widget.PagerAdapter
import org.telegram.messenger.databinding.BuyFragmentBinding
import org.telegram.messenger.databinding.GiftFragmentBinding
import org.telegram.messenger.databinding.TransferFragmentBinding
import org.tg_member.features.buy.BuyFragment
import org.tg_member.features.vip.model.VipItem
import org.tg_member.features.gift.GiftFragment
import org.tg_member.features.transfer.TransferFragment

class VipVPAdapter(
    val items: List<VipItem>
) : RecyclerView.Adapter<ViewHolder>() {

    inner class BuyViewHolder(private val buyFragmentBinding: BuyFragmentBinding) :
        ViewHolder(buyFragmentBinding.root) {
        fun onBind() {
            BuyFragment(buyFragmentBinding).createView()
        }
    }

    inner class TransferViewHolder(private val transferFragmentBinding: TransferFragmentBinding) :
        ViewHolder(transferFragmentBinding.root) {
        fun onBind() {
            TransferFragment(transferFragmentBinding).createView()
        }
    }

    inner class GiftViewHolder(private val giftFragmentBinding: GiftFragmentBinding) :
        ViewHolder(giftFragmentBinding.root) {
        fun onBind() {
            GiftFragment(giftFragmentBinding).createView()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> {
                val binding =
                    BuyFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BuyViewHolder(binding)
            }

            1 -> {
                val binding =
                    TransferFragmentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                TransferViewHolder(binding)
            }

            2 -> {
                val binding =
                    GiftFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GiftViewHolder(binding)
            }

            else -> {
                val binding =
                    BuyFragmentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                BuyViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is VipItem.BuyItem -> 0
            is VipItem.TransferItem -> 1
            VipItem.GiftItem -> 2
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is BuyViewHolder-> {
                holder.onBind()
            }

            is TransferViewHolder -> {
                holder.onBind()
            }

            is GiftViewHolder -> {
                holder.onBind()
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}