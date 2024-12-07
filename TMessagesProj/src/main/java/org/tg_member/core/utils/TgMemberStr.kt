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
        titlesLanguages.add(TitleLanguages(5, "Enter email and VIP number for transfer", "Ko'chirish uchun electron pochta va VIP lar sonini kiriting", "Введите адрес электронной почты и количество VIP-персон для переноса."))
        titlesLanguages.add(TitleLanguages(6, "Enter email", "Electron pochta kiriting", "Введите адрес электронной почты"))
        titlesLanguages.add(TitleLanguages(7, "Enter VIP number", "VIP lar sonini kiriting", "Введите количество VIP-персон"))
        titlesLanguages.add(TitleLanguages(8, "Transfer", "Ko'chirish", "Передача"))
        titlesLanguages.add(TitleLanguages(9, "Transfer history", "Ko'chirishlar tarixi", "История переводов"))
        titlesLanguages.add(TitleLanguages(10, "Enter gift code", "Sovg'a kodini kiriting", "Введите подарочный код"))
        titlesLanguages.add(TitleLanguages(11, "Confirmation", "Tasdiqlash", "Подтверждение"))
        titlesLanguages.add(TitleLanguages(12, "Type", "Turi", "Тип"))
        titlesLanguages.add(TitleLanguages(13, "Automatic Operation", "Avtomatik Ishlash", "Автоматическая работа"))
        titlesLanguages.add(TitleLanguages(14, "Registration", "Roʻyxatdan oʻtish", "Регистрация"))
        titlesLanguages.add(TitleLanguages(14, "Registration", "", "Регистрация"))
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