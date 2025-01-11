package org.tg_member.core.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.text.TextUtils
import android.util.Patterns
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.telegram.messenger.LocaleController.getString
import org.telegram.messenger.NotificationCenter
import org.telegram.messenger.R
import org.telegram.tgnet.TLRPC
import org.telegram.messenger.UserConfig
import org.telegram.messenger.databinding.DialogNeedVipBinding
import org.telegram.ui.ActionBar.ActionBar
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.ActionBar.Theme.ThemeInfo
import org.telegram.ui.Components.LayoutHelper
import org.tg_member.core.model.SpinnerTypeData
import org.tg_member.features.dashboard.DashboardFragment
import org.tg_member.features.free.model.AccountData

/**
 * Created by : Azamat Kalmurzaev
 * 24/12/24
 */


object TGMemberUtilities {

    /**
     * ---------------------------------------------------------------------------------------------
     */

    fun getAccounts(): List<AccountData> {
        val accounts = mutableListOf<AccountData>()
        for (i in 0 until UserConfig.MAX_ACCOUNT_COUNT) {
            if (UserConfig.isValidAccount(i)) {
                val currentUser = UserConfig.getInstance(i).currentUser
                accounts.add(
                    AccountData(
                        firstName = currentUser.first_name,
                        lastName = if (currentUser.last_name != null) currentUser.last_name else "",
                        number = currentUser.phone,
                        id = currentUser.id,
                        accountPosition = i,
                    )
                )
            }
        }
        return accounts
    }


//    fun getImageBitmap(): Bitmap? {
//        val accounts = mutableListOf<AccountData>()
//        var bitmap: Bitmap? = null
//        var photoPath: TLRPC.FileLocation?
//        for (i in 0 until UserConfig.MAX_ACCOUNT_COUNT) {
//            if (UserConfig.isValidAccount(i)) {
//                val currentUser = UserConfig.getInstance(i).currentUser
//                accounts.add(
//                    AccountData(
//                        firstName = currentUser.first_name,
//                        lastName = if (currentUser.last_name != null) currentUser.last_name else "",
//                        number = currentUser.phone,
//                        id = currentUser.id,
//                        accountPosition = i,
//                    )
//                )
//                if (currentUser.photo != null) {
//                    photoPath = currentUser.photo.photo_small
//                    val size = AndroidUtilities.dp(48f)
//                    val path: File = FileLoader.getInstance(i).getPathToAttach(photoPath, true)
//                    bitmap = BitmapFactory.decodeFile(path.toString())
//                    val result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
//                    result.eraseColor(Color.TRANSPARENT)
//                    Log.d("HIII", "getAccounts: ${currentUser.photo.photo_small}")
//                }
//                Log.d("HIII", "getAccounts: ${currentUser.color}")
//                Log.d("HIII", "getAccounts: ${currentUser.profile_color}")
//            }
//        }
//        return bitmap
//    }

    /**
     * ---------------------------------------------------------------------------------------------
     */

    fun getDrawableStateList(
        @DrawableRes drawable: Int,
        context: Context,
        color: Int
    ): StateListDrawable {
        val stateListDrawablePopup = ContextCompat.getDrawable(
            context, drawable
        ) as StateListDrawable
        val gradientDrawablePopup = stateListDrawablePopup.current as GradientDrawable
        gradientDrawablePopup.setColor(color)
        return stateListDrawablePopup
    }

    /**
     * ---------------------------------------------------------------------------------------------
     */

    enum class Types {
        AllTypes,
        Premium,
        Member,
        View,
        Reaction
    }

    enum class Status {
        AllStatus,
        Pending,
        Completed,
        Failed
    }

    fun getTypes(): List<SpinnerTypeData> {
        return listOf(
            SpinnerTypeData(
                TgMemberStr.getStr(32),
                R.drawable.ic_list
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(33),
                R.drawable.ic_premium_member
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(34),
                R.drawable.ic_person
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(35),
                R.drawable.ic_view
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(36),
                R.drawable.ic_reaction
            )
        )
    }

    fun getStatus(): List<SpinnerTypeData> {
        return listOf(
            SpinnerTypeData(
                TgMemberStr.getStr(37),
                R.drawable.ic_list
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(38),
                R.drawable.ic_pending
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(39),
                R.drawable.ic_completed
            ),
            SpinnerTypeData(
                TgMemberStr.getStr(40),
                R.drawable.ic_failed
            )
        )
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target?.let {
            Patterns.EMAIL_ADDRESS.matcher(it).matches()
        } == true
    }

    fun changeTheme(view: View, themeInfo: ThemeInfo, toDark: Boolean) {
//        val dayThemeName = "Blue"
//        val nightThemeName = "Night"
//
//        val themeInfo: ThemeInfo
//        var toDark: Boolean
//        if (!Theme.isCurrentThemeDark().also { toDark = it }) {
//            themeInfo = Theme.getTheme(nightThemeName)
//        } else {
//            themeInfo = Theme.getTheme(dayThemeName)
//        }

        Theme.selectedAutoNightType = Theme.AUTO_NIGHT_TYPE_NONE
        Theme.saveAutoNightThemeConfig()
        Theme.cancelAutoNightThemeCallbacks()


        val pos = IntArray(2)

        NotificationCenter.getGlobalInstance().postNotificationName(
            NotificationCenter.needSetDayNightTheme,
            themeInfo,
            false,
            pos,
            -1,
            toDark,
            view
        )
    }

    fun createActionbar(actionBar: ActionBar, context: Context, countVip: String) {
        val menu = actionBar.createMenu()
        val linearLayout = LinearLayout(context).apply {
            layoutParams =
                LinearLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT)
            gravity = Gravity.CENTER_VERTICAL
        }
        val vipCountTextView = TextView(context)
        val vipIcon = ImageView(context)
        vipIcon.setImageResource(R.drawable.vip_svgrepo_com)
        vipCountTextView.textSize = 16f
        vipCountTextView.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_semibold))
        vipCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultTitle))
        vipIcon.setColorFilter(Theme.getColor(Theme.key_actionBarDefaultIcon))
        vipCountTextView.text = countVip
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


    fun showNotEnoughMoneyDialog(context: Context) {
        val dialog = BottomSheetDialog(context, R.style.BottomSheetDialogStyle)
        val dialogBinding =
            DialogNeedVipBinding.inflate(LayoutInflater.from(context), null, false)
        dialogBinding.tvBuyVipDescription.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        dialogBinding.btnCancel.setTextColor(context.resources.getColor(R.color.color_telegram_background))
        dialogBinding.btnCancel.text= getString(R.string.Cancel)
        dialogBinding.tvNotEnoughMoneyTitle.text=TgMemberStr.getStr(63)
        dialogBinding.tvNotEnoughMoneyTitle.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        dialogBinding.btnVipStore.text=TgMemberStr.getStr(62)
        dialogBinding.tvBuyVipDescription.text=TgMemberStr.getStr(61)
        dialogBinding.btnVipStore.setTextColor(Theme.getColor(Theme.key_chats_menuName))
        dialogBinding.btnVipStore.setBackgroundDrawable(
            getDrawableStateList(
                R.drawable.transfer_btn,
                context,
                context.resources.getColor(R.color.color_telegram_background)
            )
        )
        Glide.with(context).asGif().load(R.raw.no_money).into(dialogBinding.ivNoMoney)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.btnCancel.setOnClickListener {
            dialog.hide()
        }
        dialogBinding.btnVipStore.setOnClickListener {
            dialog.hide()
            DashboardFragment.instance.changeBottomNavigationPosition(3)
        }
        dialog.show()
    }


}