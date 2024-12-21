package org.tg_member.features.transfer.history

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentTransferHistoryBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.Components.LayoutHelper
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.transfer.history.adapter.TransferAdapter
import org.tg_member.features.transfer.history.model.TransferDisplayData

class TransferHistoryFragment : BaseFragment() {
    private var _binding: FragmentTransferHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var transferAdapter: TransferAdapter
    private lateinit var transferHistoryList: MutableList<TransferDisplayData>

    override fun createView(context: Context?): View {
        _binding = FragmentTransferHistoryBinding.inflate(LayoutInflater.from(context), null, false)

        loadTransferHistoryData()

        configureTransferAdapter()

        configureUi()

        configureActionBar()

        return binding.root
    }

    private fun configureUi(){
        binding.tvHistory.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
    }

    private fun configureTransferAdapter() {
        transferAdapter = TransferAdapter(
            transferHistoryList
        )
        binding.rvTransfer.adapter=transferAdapter
        binding.rvTransfer.layoutManager=LinearLayoutManager(context)
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(9))
        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
        actionBar.backgroundColor = Theme.getColor(Theme.key_myColor)
        actionBar.backButtonImageView.setOnClickListener {
            _binding=null
            finishFragment()
        }
        val menu= actionBar.createMenu()
        val linearLayout= LinearLayout(binding.root.context).apply {
            layoutParams= LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT)
            gravity= Gravity.CENTER_VERTICAL
        }
        val vipCountTextView= TextView(binding.root.context)
        val vipIcon= ImageView(binding.root.context)
        vipIcon.setImageResource(R.drawable.vip_svgrepo_com)
        vipCountTextView.textSize=16f
        vipCountTextView.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_semibold))
        vipCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
        vipIcon.setColorFilter(Theme.getColor(Theme.key_actionBarTabActiveText))
        vipCountTextView.text="2000"
        linearLayout.addView(
            vipCountTextView,
            LayoutHelper.createLinear(
                LayoutHelper.WRAP_CONTENT,
                LayoutHelper.WRAP_CONTENT,
                Gravity.RIGHT or Gravity.CENTER,
                0,
                0,
                7,
                0
            )
        )
        linearLayout.addView(
            vipIcon,
            LayoutHelper.createLinear(
                20,
                20,
                Gravity.RIGHT or Gravity.CENTER,
                0,
                0,
                16,
                0
            )

        )
        menu.addView(linearLayout)
    }

    private fun loadTransferHistoryData() {
        transferHistoryList = mutableListOf()
        transferHistoryList.add(
            TransferDisplayData(
                receiverEmail = "asdada@gmail.com",
               vipCount =  -100,
               date =  "18 Dec 18:51"
            )
        )
        transferHistoryList.add(
            TransferDisplayData(
                receiverEmail = "nnnnnnn@gmail.com",
              vipCount =   -222,
               date =  "18 Dec 00:34"
            )
        )
        transferHistoryList.add(
            TransferDisplayData(
                receiverEmail = "sssssss@gmail.com",
                vipCount = -53,
                date = "11 Nov 08:11"
            )
        )
        transferHistoryList.add(
            TransferDisplayData(
                receiverEmail = "qqqqqqqqqq@gmail.com",
              vipCount =   -23,
              date =   "08 Mar 14:34"
            )
        )
    }
}