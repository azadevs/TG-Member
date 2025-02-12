package org.tg_member.features.details

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.core.animation.doOnStart

/**
 * Created by : Azamat Kalmurzaev
 * 10/02/25
 */
class AnimatorManager {

    private var animatorSet: AnimatorSet? = null

    fun moveView(target: View, countTextView: TextView) {
        val translateX =
            ObjectAnimator.ofFloat(target, "translationX", 0f, 0f).apply { duration = 3000 }
        val translateY =
            ObjectAnimator.ofFloat(target, "translationY", 0f, -400f).apply { duration = 3000 }
        val alpha = ObjectAnimator.ofFloat(target, "alpha", 1f, 0f).apply { duration = 3000 }
        val scaleX = ObjectAnimator.ofFloat(target, "scaleX", 1f, 2f).apply { duration = 3000 }
        val scaleY = ObjectAnimator.ofFloat(target, "scaleY", 1f, 2f).apply { duration = 3000 }
        animatorSet = AnimatorSet()
        animatorSet?.playTogether(translateX, translateY, alpha, scaleX, scaleY)
        animatorSet?.doOnStart {
                val vipCount = countTextView.text.toString().toInt()
                val animator = ValueAnimator.ofInt(vipCount, vipCount + 2)
                animator.duration = 400
                animator.addUpdateListener {
                    countTextView.text = (it.animatedValue as Int).toString()
                }
                animator.start()
            }
            animatorSet?.start()
    }

    fun clearAnimation() {
        animatorSet?.pause()
        animatorSet?.removeAllListeners()
        animatorSet = null
    }
}