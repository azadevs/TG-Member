package org.tg_member.features.input_channel

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.LocaleController
import org.telegram.messenger.R
import org.telegram.messenger.databinding.InputChannelFragmentBinding
import org.telegram.ui.ActionBar.AlertDialog
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.Components.LayoutHelper
import org.tg_member.core.utils.LoadSelectedChannel
import org.tg_member.core.utils.TGMemberUtilities.getAccounts
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.home.model.OrderDisplayData
import org.tg_member.features.input_channel.adapters.ChannelAdapter

class InputChannelFragment(private val orderDisplayData: OrderDisplayData) : BaseFragment() {

    private var _binding: InputChannelFragmentBinding? = null

    private val binding get() = _binding!!
    lateinit var adapter: ChannelAdapter

    lateinit var progressDialog: AlertDialog

    override fun createView(context: Context?): View {
        _binding = InputChannelFragmentBinding.inflate(LayoutInflater.from(context), null, false)

        configureActionBar()

        configureAdapter()

        configureUi()

        binding.sendBtn.setOnClickListener {
            checkChannelLink()
        }

        fragmentView = binding.root

        return fragmentView
    }

    override fun onFragmentDestroy() {
        _binding = null
        adapter.list.clear()
        super.onFragmentDestroy()
    }

    private fun configureAdapter() {
        adapter = ChannelAdapter(LoadSelectedChannel.channels) {
           sureDialog(binding.root.context).show()
        }
        binding.rvChannels.layoutManager = LinearLayoutManager(context)
        binding.rvChannels.adapter = adapter
    }

    private fun configureUi() {
        binding.root.setBackgroundColor(Theme.getColor(Theme.key_iv_navigationBackground))
        binding.tvRule.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.edtInputLink.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.sendBtn.setTextColor(Theme.getColor(Theme.key_chats_menuName))
        binding.sendBtn.text = TgMemberStr.getStr(43)
        binding.edtInputLink.hint = TgMemberStr.getStr(41)
        binding.edtInputLink.setHintTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        progressDialog = AlertDialog(context, AlertDialog.ALERT_TYPE_SPINNER)
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(29))
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

    private fun checkChannelLink() {
        val channelLink = binding.edtInputLink.text.toString()
        progressDialog.setCancelDialog(true)
        if (channelLink.isNotEmpty()) {
            val userName: String = if(channelLink.contains("https")){
                channelLink.substring(channelLink.lastIndexOf('/') + 1, channelLink.length)
            }else if(channelLink.contains("@")){
                channelLink.replace("@","")
            }else{
                channelLink
            }
            progressDialog.show()
            // https://t.me/kunuzofficial

            val accounts= getAccounts()
            if (accounts.isEmpty()) {
                sureDialog(binding.root.context).show()
            } else {
                LoadSelectedChannel.loadChannel(userName, accounts[0].accountPosition) { bool ->
                    if (!bool)
                        Toast.makeText(
                            binding.root.context,
                            TgMemberStr.getStr(42),
                            Toast.LENGTH_SHORT
                        ).show()
                    progressDialog.dismiss()
                    parentActivity.runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        } else {
            Toast.makeText(
                binding.root.context,
                TgMemberStr.getStr(41),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun sureDialog(context: Context?): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("\"${binding.edtInputLink.text}\"\n${TgMemberStr.getStr(44)}")
        builder.setTitle(TgMemberStr.getStr(29))
        builder.setPositiveButton(
            TgMemberStr.getStr(43)
        ) { _: DialogInterface?, _: Int ->
            Toast.makeText(binding.root.context, TgMemberStr.getStr(48), Toast.LENGTH_SHORT).show()
            finishFragment()
        }
        builder.setNegativeButton(LocaleController.getString(R.string.Cancel), null)
        val alertDialog = builder.create()
        return alertDialog
    }

    override fun onPause() {
        progressDialog.dismiss()
        super.onPause()
    }



}