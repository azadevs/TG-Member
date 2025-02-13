package org.tg_member.features.details

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.net.Uri
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.telegram.messenger.AndroidUtilities
import org.telegram.messenger.LocaleController
import org.telegram.messenger.MessagesController
import org.telegram.messenger.R
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.DialogBottomAutoJoinBinding
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
import org.tg_member.core.utils.TGMemberUtilities
import org.tg_member.core.utils.TGMemberUtilities.getAccounts
import org.tg_member.core.utils.TgMemberStr
import org.tg_member.features.free.FreeFragment
import kotlin.math.abs

class AccountDetailsFragment(
    private val accountPosition: Int
) : BaseFragment() {

    private var _binding: FragmentAccountDetailsBinding? = null
    private val binding get() = _binding!!
    private var popupMenu: CustomPopupMenu? = null
    private val vipCountTextView by lazy {
        TextView(context)
    }

    private var bottomSheetDialog: BottomSheetDialog? = null

    private var animatorManager: AnimatorManager? = null

    override fun createView(context: Context?): View {
        _binding = FragmentAccountDetailsBinding.inflate(LayoutInflater.from(context), null, false)

        configureActionBar()

        configureUi()

        configureSharedPreference()

        joinChannel()

        binding.ivMenu.setOnClickListener {
            createPopUpMenu()
        }

        binding.btnLogOut.setOnClickListener {
            makeLogOutDialog(context).show()
        }
        binding.btnAutoJoin.setOnClickListener {
            needAutoJoinStart()
        }
        binding.btnStop.setOnClickListener {
            needAutoJoinStop()
        }
        animatorManager?.moveView(binding.tvPlusTwoVip, vipCountTextView)

        fragmentView = binding.root

        return fragmentView

    }

    private fun configureSharedPreference() {
        val sharedPref = context?.getSharedPreferences("mainconfig", MODE_PRIVATE)
        val isShowDialog = sharedPref?.getBoolean("isShowAutoJoinDialog", false)
        if (!isShowDialog!!) {
            sharedPref.edit().putBoolean("isShowAutoJoinDialog", true).apply()
            showBottomSheetDialog()
        }
    }

    override fun onFragmentDestroy() {
        super.onFragmentDestroy()
        _binding = null
        popupMenu = null
        animatorManager?.clearAnimation()
        animatorManager = null
        bottomSheetDialog?.hide()
        bottomSheetDialog = null
    }

    private fun configureActionBar() {
        actionBar.setTitle(TgMemberStr.getStr(45))
        actionBar.setBackButtonImage(R.drawable.msg_arrow_back)
        val menu = actionBar.createMenu()
        val linearLayout = LinearLayout(context).apply {
            layoutParams =
                LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT)
            gravity = Gravity.CENTER_VERTICAL
        }
        val vipIcon = ImageView(context)
        vipIcon.setImageResource(R.drawable.vip_svgrepo_com)
        vipCountTextView.textSize = 16f
        vipCountTextView.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_semibold))
        vipCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultTitle))
        vipIcon.setColorFilter(Theme.getColor(Theme.key_actionBarDefaultIcon))
        vipCountTextView.text = "200"
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
            animatorManager?.clearAnimation()
            animatorManager = null
            _binding = null
            popupMenu = null
            bottomSheetDialog?.hide()
            bottomSheetDialog = null
            finishFragment(true)
        }
    }

    private fun configureUi() {
        animatorManager= AnimatorManager()
        binding.apply {
            tvChannelLink.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            tvChannelName.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            tvChannelName.text = "Kun.uz | Расмий канал"
            tvChannelLink.text = "kunuzoffical"
            root.setBackgroundColor(Theme.getColor(Theme.key_iv_background))
            btnJoin.setTextColor(Theme.getColor(Theme.key_chats_menuName))
            btnNext.setTextColor(context.resources.getColor(R.color.color_telegram_background))
            btnNext.text = TgMemberStr.getStr(19)
            btnJoin.text = TgMemberStr.getStr(20)
            btnAutoJoin.setTextColor(context.resources.getColor(R.color.color_telegram_background))
            btnAutoJoin.text = TgMemberStr.getStr(21)
            btnLogOut.text = TgMemberStr.getStr(23)
            btnLogOut.setTextColor(Theme.getColor(Theme.key_chats_sentError))
            ivChannelImage.setBackgroundColor(
                Theme.getColor(
                    Theme.keys_avatar_nameInMessage[abs(
                        (1001766948 % Theme.keys_avatar_background.size).toDouble()
                    ).toInt()]
                )
            )
            btnStop.text = TgMemberStr.getStr(54)
            btnStop.setTextColor(Theme.getColor(Theme.key_chats_sentError))
            tvPlusTwoVip.setTextColor(Theme.getColor(Theme.key_chats_menuName))
            tvChannelLabel.text = "KU"
            tvChannelLabel.setTextColor(Color.WHITE)
            btnAutoJoin.setOnClickListener {
                joinChannel()
            }
        }
    }

    private fun needAutoJoinStart() {
        binding.apply {
            btnJoin.isEnabled = false
            btnJoin.background = TGMemberUtilities.getDrawableStateList(
                R.drawable.transfer_btn,
                context,
                Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon)
            )
            ivChannelImage.visibility = View.INVISIBLE
            tvChannelName.text = TgMemberStr.getStr(53)
            lottieView.visibility = View.VISIBLE
            btnNext.isEnabled = false
            btnStop.isEnabled = true
            tvChannelLink.text = TgMemberStr.getStr(53)
            tvChannelLabel.visibility = View.INVISIBLE
            lottieView.playAnimation()
            btnJoin.text = TgMemberStr.getStr(53)
            btnStop.visibility = View.VISIBLE
            btnNext.visibility = View.INVISIBLE
            btnAutoJoin.isEnabled = false
            btnAutoJoin.text = TgMemberStr.getStr(55)
            btnAutoJoin.background = TGMemberUtilities.getDrawableStateList(
                R.drawable.rounded_corners_background,
                context,
                Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon)
            )
            btnAutoJoin.setTextColor(Theme.getColor(Theme.key_chats_menuName))
        }
    }

    private fun needAutoJoinStop() {
        binding.apply {
            btnJoin.isEnabled = true
            val stateListDrawablePopup = ContextCompat.getDrawable(
                context, R.drawable.transfer_btn
            ) as StateListDrawable
            stateListDrawablePopup.current as GradientDrawable
            btnJoin.background = stateListDrawablePopup
            ivChannelImage.visibility = View.VISIBLE
            lottieView.visibility = View.GONE
            lottieView.pauseAnimation()
            btnNext.isEnabled = true
            tvChannelName.text = "Kun.uz | Расмий канал"
            tvChannelLink.text = "kunuzoffical"
            btnStop.isEnabled = false
            tvChannelLabel.visibility = View.VISIBLE
            btnJoin.text = TgMemberStr.getStr(20)
            btnAutoJoin.text = TgMemberStr.getStr(21)
            btnStop.visibility = View.INVISIBLE
            btnNext.visibility = View.VISIBLE
            btnAutoJoin.isEnabled = true
            btnAutoJoin.setBackgroundResource(R.drawable.rounded_corners_background)
            btnAutoJoin.setTextColor(context.resources.getColor(R.color.color_telegram_background))
        }
    }

    private fun joinChannel() {
        binding.btnJoin.setOnClickListener {
            JoinChannels.join(UserConfig.selectedAccount, -1001766948, "kunuzofficial")
        }
    }

    private fun makeLogOutDialog(context: Context?): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(TgMemberStr.getStr(72))
        builder.setTitle(LocaleController.getString(R.string.LogOut))
        builder.setPositiveButton(
            TgMemberStr.getStr(73)
        ) { _: DialogInterface?, _: Int ->
            UserConfig.selectedAccount = if (getAccounts().size > 1) {
                getAccounts().first {
                    it.accountPosition != accountPosition
                }.accountPosition
            } else {
                0
            }
            MessagesController.getInstance(accountPosition).performLogout(1)
            FreeFragment.instance.updateAccountAdapter()
            finishFragment()
        }
        builder.setNegativeButton(LocaleController.getString(R.string.Cancel), null)
        val alertDialog = builder.create()
        return alertDialog
    }

    private fun createPopUpMenu() {
        popupMenu = object : CustomPopupMenu(context, resourceProvider, false) {
            override fun onCreate(popupLayout: ActionBarPopupWindowLayout) {
                popupLayout.backgroundColor =
                    ColorUtils.blendARGB(Color.WHITE, Color.WHITE, 0.18f)
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
            -binding.ivMenu.measuredHeight + AndroidUtilities.dp(8f)
        )
    }

    private fun openChannelInTelegram(username: String = "kunuzofficial") {
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
            intent = Intent.createChooser(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("tg://resolve?domain=$username")
                ), "Open"
            )
        } catch (e: Exception) {
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.telegram.me/$username")
            )
        }
        startActivity(context, intent!!, null)
    }

    private fun showBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogStyle)
        DialogBottomAutoJoinBinding.inflate(LayoutInflater.from(context), null, false).apply {
            tvTitle.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            tvTitle.text = TgMemberStr.getStr(64)
            tvContent.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
            tvContent.text = TgMemberStr.getStr(65)
            btnUnderstand.setTextColor(Theme.getColor(Theme.key_chats_menuName))
            btnUnderstand.text = TgMemberStr.getStr(66)
            bottomSheetDialog?.setCancelable(false)
            bottomSheetDialog?.setContentView(root)
            btnUnderstand.setOnClickListener {
                bottomSheetDialog?.dismiss()
            }
        }
         bottomSheetDialog?.show()
    }
}