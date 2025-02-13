package org.tg_member.features.input.post

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.snackbar.Snackbar
import org.telegram.messenger.R
import org.telegram.messenger.databinding.FragmentInputPostBinding
import org.telegram.ui.ActionBar.BaseFragment
import org.telegram.ui.ActionBar.Theme
import org.tg_member.core.utils.TGMemberUtilities
import org.tg_member.core.utils.TgMemberStr

/**
 * Created by : Azamat Kalmurzaev
 * 20/01/25
 */
class InputPostFragment(private val postCount: Int) : BaseFragment() {

    private var _binding: FragmentInputPostBinding? = null

    private val binding get() = _binding!!

    private val editTextViews = arrayListOf<EditText>()

    private lateinit var firstRuleTextView: TextView

    private lateinit var button: Button

    override fun createView(context: Context?): View {

        _binding = FragmentInputPostBinding.inflate(LayoutInflater.from(context), null, false)

        for (i in 0 until postCount) {
            createEditText()
        }

        createActionbar()

        configureUi()

        button.setOnClickListener {
            if (checkEditTextIsEmpty()) {
                // TODO: send post
            } else {
                Snackbar.make(
                    binding.root,
                    TgMemberStr.getStr(69),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        fragmentView = binding.root

        return fragmentView

    }

    private fun createActionbar() {
        actionBar.setTitle(TgMemberStr.getStr(29))
        actionBar.setBackButtonImage(R.drawable.ic_ab_back)
        actionBar.backButtonImageView.setOnClickListener {
            finishFragment()
        }
    }

    private fun configureUi() {
        binding.tvPostLabel.text = TgMemberStr.getStr(68)
        binding.tvPostLabel.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        createTextViews()
        createButton()
    }

    private fun createEditText() {
        val constraintSet = ConstraintSet()
        val edittext = EditText(context)
        edittext.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, convertDpToPx(50))
        edittext.textSize = 14f
        edittext.setBackgroundColor(Theme.getColor(Theme.key_iv_background))
        edittext.background = TGMemberUtilities.getDrawableStateList(
            R.drawable.cut_corners_background,
            context,
            Theme.getColor(Theme.key_iv_background)
        )
        edittext.setTextAppearance(context,R.style.CustomTextStyle)
        edittext.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        edittext.setHintTextColor(Theme.getColor(Theme.key_dialogTextHint))
        edittext.hint = TgMemberStr.getStr(68)
        edittext.setPadding(convertDpToPx(12), 0, convertDpToPx(12), 0)
        binding.constraint.addView(edittext)
        edittext.id = View.generateViewId()
        constraintSet.clone(binding.constraint)
        val previousViewId =
            if (editTextViews.isEmpty()) binding.tvPostLabel.id else editTextViews.last().id
        constraintSet.connect(
            edittext.id,
            ConstraintSet.TOP,
            previousViewId,
            ConstraintSet.BOTTOM,
            convertDpToPx(16)
        )
        constraintSet.connect(
            edittext.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        constraintSet.connect(
            edittext.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END
        )
        constraintSet.applyTo(binding.constraint)
        editTextViews.add(edittext)
    }

    private fun convertDpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }

    private fun createButton() {
        button = Button(context)
        button.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, convertDpToPx(50))
        button.isAllCaps = false
        button.setTextAppearance(context,R.style.CustomTextStyle)
        button.textSize=15f
        button.setTextColor(Theme.getColor(Theme.key_chats_menuName))
        button.background = TGMemberUtilities.getDrawableStateList(
            R.drawable.transfer_btn,
            context,
            Theme.getColor(Theme.key_telegramColor))
        button.text = TgMemberStr.getStr(43)
        binding.constraint.addView(button)
        button.id = View.generateViewId()
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraint)
        constraintSet.connect(
            button.id,
            ConstraintSet.TOP,
            firstRuleTextView.id,
            ConstraintSet.BOTTOM,
            convertDpToPx(24)
        )
        constraintSet.connect(
            button.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END
        )

        constraintSet.applyTo(binding.constraint)
    }

    private fun createTextViews() {
        val ruleTextView = TextView(context)
        ruleTextView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        ruleTextView.textSize = 14f
        ruleTextView.setTextAppearance(context,R.style.CustomTextStyle)
        ruleTextView.text = TgMemberStr.getStr(70)
        ruleTextView.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.constraint.addView(ruleTextView)
        ruleTextView.id = View.generateViewId()
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraint)
        constraintSet.connect(
            ruleTextView.id,
            ConstraintSet.TOP,
            editTextViews.last().id,
            ConstraintSet.BOTTOM,
            convertDpToPx(20)
        )
        constraintSet.connect(
            ruleTextView.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )

        constraintSet.applyTo(binding.constraint)

        firstRuleTextView = TextView(context)
        firstRuleTextView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        firstRuleTextView.textSize = 14f
        firstRuleTextView.text = TgMemberStr.getStr(71)
        firstRuleTextView.setTextColor(Theme.getColor(Theme.key_chats_menuItemText))
        binding.constraint.addView(firstRuleTextView)
        firstRuleTextView.id = View.generateViewId()
        constraintSet.clone(binding.constraint)
        constraintSet.connect(
            firstRuleTextView.id,
            ConstraintSet.TOP,
            ruleTextView.id,
            ConstraintSet.BOTTOM,
            convertDpToPx(16)
        )
        constraintSet.applyTo(binding.constraint)

    }

    private fun checkEditTextIsEmpty(): Boolean {
        for (editText in editTextViews) {
            if (editText.text.toString().isEmpty()) {
                return false
            }
        }
        return true
    }

    override fun onFragmentDestroy() {
        _binding = null
        super.onFragmentDestroy()
    }


}