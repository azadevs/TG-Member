package org.tg_member.features.home.model

import androidx.annotation.DrawableRes

data class OrderDisplayData(
    val count:Int,
    val price:Float,
    val discount:Int=0,
    @DrawableRes val icon:Int
)