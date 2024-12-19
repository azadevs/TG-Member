package org.tg_member.features.home.model

sealed interface HomeItem {
    data object Member : HomeItem
    data object View : HomeItem
    data object Reaction : HomeItem
}