package org.tg_member.features.input_channel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.InputChannelFragmentBinding
import org.telegram.ui.ActionBar.AlertDialog
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.LoadSelectedChannel
import org.tg_member.features.home.model.OrderMemberToMoney
import org.tg_member.features.input_channel.adapters.ChannelAdapter

class InputChannelFragment(private val orderMemberToMoney: OrderMemberToMoney) : BaseFragment() {

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
        super.onFragmentDestroy()
    }

    private fun configureAdapter() {
        adapter = ChannelAdapter(LoadSelectedChannel.channels) {
            Toast.makeText(
                context,
                "${it?.username ?: ""} + ${orderMemberToMoney.memberCount}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.rvChannels.layoutManager = LinearLayoutManager(context)
        binding.rvChannels.adapter = adapter
    }

    private fun configureUi() {
        binding.tvRule.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.edtInputLink.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.sendBtn.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.edtInputLink.setHintTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        progressDialog = AlertDialog(context, AlertDialog.ALERT_TYPE_SPINNER)
    }

    private fun configureActionBar() {
        actionBar.setTitle("Order")
        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
        actionBar.backButtonImageView.setOnClickListener {
            finishFragment()
            clearViews()
            _binding = null
        }
    }

    private fun checkChannelLink() {
        val channelLink = binding.edtInputLink.text.toString()
        if (channelLink.isNotEmpty()) {
            progressDialog.show()
            // https://t.me/kunuzofficial
            val userName =
                channelLink.substring(channelLink.lastIndexOf('/') + 1, channelLink.length)
            LoadSelectedChannel.loadChannel(userName, UserConfig.selectedAccount) { bool ->
                if (!bool)
                    Toast.makeText(binding.root.context, "No channels", Toast.LENGTH_SHORT)
                        .show()
                progressDialog.dismiss()
                parentActivity.runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
            }

        } else {
            Toast.makeText(
                binding.root.context,
                "Please enter your channel link",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onPause() {
        progressDialog.dismiss()
        super.onPause()
    }

}