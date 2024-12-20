package org.tg_member.features.input_channel

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.LocaleController
import org.telegram.messenger.MessagesController
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.InputChannelFragmentBinding
import org.telegram.ui.ActionBar.AlertDialog
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.LoadSelectedChannel
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.core.utils.getAccounts
import org.tg_member.features.free.FreeFragment
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
        binding.tvRule.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.edtInputLink.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.sendBtn.setTextColor(Color.WHITE)
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
    }

    private fun checkChannelLink() {
        val channelLink = binding.edtInputLink.text.toString()
        progressDialog.setCancelDialog(true)
        if (channelLink.isNotEmpty()) {
            var userName =""
            userName = if(channelLink.contains("https")){
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
            Toast.makeText(binding.root.context, "Successfully", Toast.LENGTH_SHORT).show()
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