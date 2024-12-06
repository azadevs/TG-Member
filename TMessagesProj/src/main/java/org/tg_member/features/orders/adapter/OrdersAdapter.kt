package org.tg_member.features.orders.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.telegram.messenger.databinding.ItemOrderBinding

/**
 * Created by Usmon
 * 2024/12/06
 */

class OrdersAdapter(
    val context: Context,
    val items: List<String>
) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any? = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val binding: ItemOrderBinding = if (convertView == null) {
            ItemOrderBinding.inflate(LayoutInflater.from(context), null, false)
        } else {
            ItemOrderBinding.bind(convertView)
        }

        return binding.root
    }
}