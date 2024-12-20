package org.tg_member.core.utils

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import org.telegram.messenger.AndroidUtilities
import org.telegram.messenger.ApplicationLoader
import org.telegram.messenger.LocaleController
import org.telegram.messenger.LocaleController.LocaleInfo
import org.telegram.messenger.LocaleController.getString
import org.telegram.messenger.R
import org.telegram.ui.ActionBar.AlertDialog
import org.telegram.ui.ActionBar.Theme
import org.telegram.ui.Cells.RadioColorCell
import org.tg_member.features.dashboard.DashboardFragment
import javax.inject.Singleton


@Singleton
object TgMemberStr {
    var languageCode = "en"

    var sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("db", Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun changeLanguage(code:String) {
        val info =
            LocaleController.getInstance().languages.find { it.shortName == code }
        if (info != null) {
            LocaleController.getInstance().applyLanguage(
                info, true, false, false, true, 0
            ) {}
        }
        DashboardFragment.instance.recreate(4)
    }

    fun changeLanguageDialog(context: Context){
        val selected: Int = when (languageCode) {
            "en" -> 0
            "uz" -> 1
            "ru" -> 2
            else -> 0
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getStr(25))
        val items = arrayOf("en", "uz", "ru")
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        builder.setView(linearLayout)

        for (a in items.indices) {
            val cell = RadioColorCell(context)
            cell.setPadding(AndroidUtilities.dp(4f), 0, AndroidUtilities.dp(4f), 0)
            cell.tag = a
            cell.setCheckColor(
                Theme.getColor(Theme.key_radioBackground),
                Theme.getColor(Theme.key_dialogRadioBackgroundChecked)
            )
            val title = when (items[a]) {
                "en" -> "English"
                "uz" -> "Uzbek"
                "ru" -> "Russian"
                else -> "English"
            }
            cell.setTextAndValue(title, selected == a)
            linearLayout.addView(cell)
            cell.setOnClickListener { v: View ->
                builder.dismissRunnable.run()
                val which = v.tag as Int
                changeLanguage(items[which])
            }
        }
        builder.setNegativeButton(getString(R.string.Cancel), null)
        builder.show()
    }

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
        titlesLanguages.add(TitleLanguages(13, "Auto Join", "Avto qo'shish", "Автоприсоединение"))
        titlesLanguages.add(TitleLanguages(14, "Registration", "Roʻyxatdan oʻtish", "Регистрация"))
        titlesLanguages.add(TitleLanguages(15, "Send verification code", "Tasdiqlash kodini yuborish", "Отправить код подтверждения"))
        titlesLanguages.add(TitleLanguages(16, "Enter your email address", "E-mail addressinginzni kiriting", "Введите свой адрес электронной почты"))
        titlesLanguages.add(TitleLanguages(17, "Enter verification code", "Tasdiqlash kodini kiriting", "Введите код подтверждения"))
        titlesLanguages.add(TitleLanguages(18, "Enter your email address then click on a button below then you will receive a verification code to your email account. Then you need to enter verification code. Then you will be successfully registered", "Elektron pochta manzilingizni kiriting, so'ng quyidagi tugmani bosing, keyin siz elektron pochta hisobingizga tasdiqlash kodini olasiz. Keyin tasdiqlash kodini kiritishingiz kerak. Keyin siz muvaffaqiyatli ro'yxatdan o'tasiz", "Введите свой адрес электронной почты, затем нажмите на кнопку ниже, после чего вы получите проверочный код на свой адрес электронной почты. Затем вам нужно ввести проверочный код. После этого вы будете успешно зарегистрированы."))
        titlesLanguages.add(TitleLanguages(19,"Next","Keyingi","Следующий"))
        titlesLanguages.add(TitleLanguages(20,"Join + \uD83D\uDD37","Qo'shish + \uD83D\uDD37", "Присоединяйтесь + \uD83D\uDD37"))
        titlesLanguages.add(TitleLanguages(21,"Auto Join", "Avto qo'shish", "Автоприсоединение"))
        titlesLanguages.add(TitleLanguages(22,"Email Copied","Elektron pochtadan nusxa olindi","Электронная почта скопирована"))
        titlesLanguages.add(TitleLanguages(23,"Log Out","Hisobdan chiqish","Выход"))
        titlesLanguages.add(TitleLanguages(24,"Language","Til","Язык"))
        titlesLanguages.add(TitleLanguages(25,"Select Language","Tilni Tanlang","Выберите язык"))
        titlesLanguages.add(TitleLanguages(26,"Add Account","Account qo'shish","Добавить аккаунт"))
        titlesLanguages.add(TitleLanguages(27,"Home","Bosh oyna","Главный"))
        titlesLanguages.add(TitleLanguages(28,"Free","Ishlash","Работа"))
        titlesLanguages.add(TitleLanguages(29,"Order","Buyurtma","Заказ"))
        titlesLanguages.add(TitleLanguages(30,"VIP","VIP","ВИП"))
        titlesLanguages.add(TitleLanguages(31,"Profile","Hisob","Профиль"))
        titlesLanguages.add(TitleLanguages(32,"All types","Barcha tur","Все типы"))
        titlesLanguages.add(TitleLanguages(33,"Premium","Premium","Премиум"))
        titlesLanguages.add(TitleLanguages(34,"Member","Azo","Член"))
        titlesLanguages.add(TitleLanguages(35,"View","Ko'rish","Просмотр"))
        titlesLanguages.add(TitleLanguages(36,"Reaction","Reaksiya","Реакция"))
        titlesLanguages.add(TitleLanguages(37,"All Status","Barcha holat","Все статусы"))
        titlesLanguages.add(TitleLanguages(38,"Pending","Kutilmoqda","В ожидании"))
        titlesLanguages.add(TitleLanguages(39,"Completed","Tugallangan","Завершенный"))
        titlesLanguages.add(TitleLanguages(40,"Failed","Muvaffaqiyatsiz","Неуспешный"))
        titlesLanguages.add(TitleLanguages(41,"Enter your channel link or username","Kanalingiz havolasini yoki username kiriting","Введите ссылку на ваш канал или имя пользователя"))
        titlesLanguages.add(TitleLanguages(42,"Channel not found","Kanal topilmadi","Канал не найден"))
        titlesLanguages.add(TitleLanguages(43,"Send","Yuborish","Отправлять"))
        titlesLanguages.add(TitleLanguages(44,"Are you sure you want to send this channel?","Ushbu kanalni yuborishga ishonchingiz komilmi?","Вы уверены, что хотите отправить этот канал?"))
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