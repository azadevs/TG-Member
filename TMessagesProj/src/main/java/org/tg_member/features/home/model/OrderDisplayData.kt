package org.tg_member.features.home.model

import androidx.annotation.DrawableRes

data class OrderDisplayData(
    val count:Int,
    val priceVip:Int,
    val discount:Int=0,
    @DrawableRes val icon:Int
)