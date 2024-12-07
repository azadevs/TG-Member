package org.tg_member.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.telegram.messenger.databinding.ItemTypeSpinnerBinding
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.model.SpinnerTypeData

/**
 * Created by : Azamat Kalmurzaev
 * 03/12/24
 */
class TypeSpinnerAdapter(
    val context: Context,
    val list: List<SpinnerTypeData>
) : BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemTypeSpinnerBinding = if (convertView == null) {
            ItemTypeSpinnerBinding.inflate(LayoutInflater.from(context), null, false)
        } else {
            ItemTypeSpinnerBinding.bind(convertView)
        }
        binding.tvTypeName.text = list[position].type
        binding.tvTypeName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.ivTypeImage.setColorFilter(Theme.getColor(Theme.key_chats_menuItemText))
        binding.ivTypeImage.setImageResource(list[position].icon)
        return binding.root
    }
}