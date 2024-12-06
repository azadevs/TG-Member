package org.tg_member.features.vip.model

sealed interface VipItem {
    data object BuyItem : VipItem
    data object TransferItem : VipItem
    data object GiftItem : VipItem
}