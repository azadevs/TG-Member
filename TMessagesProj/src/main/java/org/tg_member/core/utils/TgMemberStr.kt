package org.tg_member.core.utils

import javax.inject.Singleton


@Singleton
object TgMemberStr {
    var languageCode = "en"

    var titlesLanguages = ArrayList<TitleLanguages>()

    init {
        titlesLanguages.add(TitleLanguages(0, "Contact us", "Biz bilan bog'lanish", "Связаться с нами"))
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
        titlesLanguages.add(TitleLanguages(15, "Send verification code", "Tasdiqlash kodini yuborish", "Отправить код подтверждения"))
        titlesLanguages.add(TitleLanguages(16, "Enter your email address", "E-mail addressinginzni kiriting", "Введите свой адрес электронной почты"))
        titlesLanguages.add(TitleLanguages(17, "Enter verification code", "Tasdiqlash kodini kiriting", "Введите код подтверждения"))
        titlesLanguages.add(TitleLanguages(18, "Enter your email address then click on a button below then you will receive a verification code to your email account. Then you need to enter verification code. Then you will be successfully registered", "Elektron pochta manzilingizni kiriting, so'ng quyidagi tugmani bosing, keyin siz elektron pochta hisobingizga tasdiqlash kodini olasiz. Keyin tasdiqlash kodini kiritishingiz kerak. Keyin siz muvaffaqiyatli ro'yxatdan o'tasiz", "Введите свой адрес электронной почты, затем нажмите на кнопку ниже, после чего вы получите проверочный код на свой адрес электронной почты. Затем вам нужно ввести проверочный код. После этого вы будете успешно зарегистрированы."))
        titlesLanguages.add(TitleLanguages(19,"Next","Keyingi","Следующий"))
        titlesLanguages.add(TitleLanguages(20,"Join + \uD83D\uDD37","Qo'shish + \uD83D\uDD37", "Присоединяйтесь + \uD83D\uDD37"))
        titlesLanguages.add(TitleLanguages(21,"Auto Join","Avtomatik qo'shilish", "Автоматическое присоединение"))
        titlesLanguages.add(TitleLanguages(22,"Email Copied","Elektron pochtadan nusxa olindi","Электронная почта скопирована"))
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