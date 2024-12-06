package org.tg_member.core.utils

import javax.inject.Singleton


@Singleton
object TgMemberStr {
    var languageCode = "en"

    var titlesLanguages = ArrayList<TitleLanguages>()

    init {
        titlesLanguages.add(TitleLanguages(1, "Buy", "Sotib olish", "Купить"))
        titlesLanguages.add(TitleLanguages(2, "Transfer", "Ko'chirish", "Передача"))
        titlesLanguages.add(TitleLanguages(3, "Gift", "Sovg'a", "Подарок"))
        titlesLanguages.add(TitleLanguages(4, "There is no orders", "Buyurtmalar yo'q", "Нет заказов"))
    }

    fun getStr(code: Int): String {
        return titlesLanguages.find { it.code == code }?.let { title ->
            when (languageCode) {
                "en" -> title.en
                "uz" -> title.uz
                "ru" -> title.ru
                else -> title.en
            }
        } ?: ""
    }
}

class TitleLanguages(val code: Int, val en: String, val uz: String, val ru: String)