package org.tg_member.features.dashboard.model

sealed interface DashboardItem {
    data object HomeItem : DashboardItem
    data object FreeItem : DashboardItem
    data object VipItem : DashboardItem
    data object OrdersItem : DashboardItem
    data object ProfileItem : DashboardItem
}