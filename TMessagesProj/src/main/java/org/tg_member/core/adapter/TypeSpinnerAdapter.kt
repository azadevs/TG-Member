package org.tg_member.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.telegram.messenger.databinding.ItemTypeSpinnerBinding
import org.tg_member.core.util.getTypes

/**
 * Created by : Azamat Kalmurzaev
 * 03/12/24
 */
class TypeSpinnerAdapter(
    val context: Context
) : BaseAdapter() {

    val types = getTypes()

    override fun getCount(): Int = types.size

    override fun getItem(position: Int): Any = types[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemTypeSpinnerBinding = if (convertView == null) {
            ItemTypeSpinnerBinding.inflate(LayoutInflater.from(context), null, false)
        } else {
            ItemTypeSpinnerBinding.bind(convertView)
        }
        binding.tvTypeName.text = types[position].type
        binding.ivTypeImage.setImageResource(
            types[position].icon
        )
        return binding.root
    }
}