package org.tg_member.features.details

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import org.telegram.messenger.AndroidUtilities
import org.telegram.messenger.LocaleController
import org.telegram.messenger.MessagesController
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.FragmentAccountDetailsBinding
import org.telegram.ui.ActionBar.ActionBarMenuItem
import org.telegram.ui.ActionBar.ActionBarPopupWindow.ActionBarPopupWindowLayout
import org.telegram.ui.ActionBar.AlertDialog
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.Components.BulletinFactory
import org.telegram.ui.Components.CustomPopupMenu
import org.telegram.ui.Components.LayoutHelper
import org.tg_member.core.utils.JoinChannels
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.free.FreeFragment

class AccountDetailsFragment(var selectedAccount: Int) : BaseFragment() {

    private var _binding: FragmentAccountDetailsBinding? = null
    private val binding get() = _binding!!
    private var popupMenu: CustomPopupMenu? = null

    override fun createView(context: Context?): View {
        _binding = FragmentAccountDetailsBinding.inflate(LayoutInflater.from(context), null, false)

        configureActionBar()

        configureUi()

        joinChannel()

        binding.ivMenu.setOnClickListener {

            createPopUpMenu()
        }

        fragmentView = binding.root

        return fragmentView

    }

    override fun onFragmentDestroy() {
        _binding = null
        super.onFragmentDestroy()
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(45))
        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
        actionBar.backgroundColor = Theme.getColor(Theme.key_myColor)
        val menu = actionBar.createMenu()
        val linearLayout = LinearLayout(binding.root.context).apply {
            layoutParams =
                LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT)
            gravity = Gravity.CENTER_VERTICAL
        }
        val vipCountTextView = TextView(binding.root.context)
        val vipIcon = ImageView(binding.root.context)
        vipIcon.setImageResource(R.drawable.vip_svgrepo_com)
        vipCountTextView.textSize = 16f
        vipCountTextView.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_semibold))
        vipCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarTabActiveText))
        vipIcon.setColorFilter(Theme.getColor(Theme.key_actionBarTabActiveText))
        vipCountTextView.text = "2000"
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
        actionBar.backButtonImageView.setOnClickListener {
            finishFragment()
            clearViews()
            _binding = null
        }
    }

    private fun configureUi() {
        binding.tvChannelLink.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.tvChannelName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.root.setBackgroundColor(navigationBarColor)
        binding.btnJoin.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.btnNext.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.btnNext.text = TgMemberStr.getStr(19)
        binding.btnJoin.text = TgMemberStr.getStr(20)
        binding.autoJoin.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.autoJoin.text = TgMemberStr.getStr(21)
        binding.logOut.text = TgMemberStr.getStr(23)
        binding.logOut.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))

        binding.logOut.setOnClickListener {
            makeLogOutDialog(context).show()
        }
    }

    private fun joinChannel() {
        binding.btnJoin.setOnClickListener {
            JoinChannels.join(UserConfig.selectedAccount, -1001766948, "kunuzofficial")
        }
    }

    override fun clearViews() {
        super.clearViews()
        _binding = null
    }

    private fun makeLogOutDialog(context: Context?): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(LocaleController.getString(R.string.AreYouSureLogout))
        builder.setTitle(LocaleController.getString(R.string.LogOut))
        builder.setPositiveButton(
            LocaleController.getString(R.string.LogOut)
        ) { _: DialogInterface?, _: Int ->
            MessagesController.getInstance(selectedAccount).performLogout(1)
            FreeFragment.instance.updateAccountAdapter()
            finishFragment()
        }
        builder.setNegativeButton(LocaleController.getString(R.string.Cancel), null)
        val alertDialog = builder.create()
        return alertDialog
    }

    private fun createPopUpMenu(){
        popupMenu = object : CustomPopupMenu(context, resourceProvider, false) {
            override fun onCreate(popupLayout: ActionBarPopupWindowLayout) {
                popupLayout.backgroundColor = ColorUtils.blendARGB(Color.WHITE, Color.WHITE, 0.18f)
                var item = ActionBarMenuItem.addItem(
                    popupLayout,
                  R.drawable.ic_open_in,
                  TgMemberStr.getStr(46),
                    false,
                    resourceProvider
                )
                item.setOnClickListener {
                    if (popupMenu != null) {
                        popupMenu?.dismiss()
                    }
                    openChannelInTelegram()
                }

                item = ActionBarMenuItem.addItem(
                    popupLayout,
                   R.drawable.ic_report,
                    TgMemberStr.getStr(47),
                    false,
                    resourceProvider
                )

                item.setOnClickListener {
                    if (popupMenu != null) {
                        popupMenu?.dismiss()
                    }
                    BulletinFactory.createBanReportBulletin(
                        this@AccountDetailsFragment,
                        true
                    ).show()
                }
            }

            override fun onDismissed() {
            }
        }
        popupMenu?.show(
            binding.ivMenu,
            0,
            - binding.ivMenu.measuredHeight - AndroidUtilities.dp(8f)
        )
    }

    private fun openChannelInTelegram(){
        var intent: Intent?
        try {
            try {
                context.packageManager.getPackageInfo(
                    "org.telegram.messenger",
                    0
                )
            } catch (e: Exception) {
                context.packageManager.getPackageInfo(
                    "org.thunderdog.challegram",
                    0
                )
            }
            intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=kunuzofficial"))
        } catch (e: Exception) {
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.telegram.me/kunuzofficial")
            )
        }
        startActivity(context,intent!!,null)
    }
}