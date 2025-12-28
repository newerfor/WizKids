package com.example.wizkids.presentation.dateScreeen.calendarScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.presentation.main.constant.MainViewConstant.MainActivityChildScreenWeight
import com.example.wizkids.presentation.utilPresentation.Header
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.WizKidsTheme

class CalendarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WizKidsTheme {
                Column(Modifier) {
                    Header().HeadInfo("Календарь")
                    Column(Modifier.weight(MainActivityChildScreenWeight)) {
                        CalendarScreen()
                    }
                }
            }
        }
    }
    @Composable
    fun CalendarScreen(textFont: TextFont = TextFont()) {
        val domainDateInfoListVariant: List<DomainDateInfo> = listOf(
            DomainDateInfo(
                10,
                "03.12.2025",
                "10:00",
                "Медосмотр",
                "Завершено",
                "Профилактический осмотр",
                "CH101",
                "Иван Петров"
            ),
            DomainDateInfo(
                9,
                "08.12.2025",
                "14:30",
                "ЛФК",
                "В процессе",
                "Лечебная физкультура",
                "CH102",
                "Мария Сидорова"
            ),
            DomainDateInfo(
                8,
                "12.12.2025",
                "09:15",
                "Логопед",
                "Запланировано",
                "Развитие речи",
                "CH103",
                "Алексей Иванов"
            ),
            DomainDateInfo(
                7,
                "17.12.2025",
                "11:45",
                "Психолог",
                "Перенесено",
                "Диагностика",
                "CH104",
                "София Кузнецова"
            ),
            DomainDateInfo(
                6,
                "22.12.2025",
                "16:00",
                "Музыка",
                "Запланировано",
                "Занятие по музыке",
                "CH105",
                "Дмитрий Соловьев"
            ),
            DomainDateInfo(
                5,
                "07.01.2026",
                "08:00",
                "Завтрак",
                "Запланировано",
                "Первый день после каникул",
                "CH106",
                "Елена Воробьева"
            ),
            DomainDateInfo(
                4,
                "14.01.2026",
                "13:00",
                "Рисование",
                "Запланировано",
                "Акварельная живопись",
                "CH107",
                "Сергей Медведев"
            ),
            DomainDateInfo(
                3,
                "19.01.2026",
                "15:30",
                "Плавание",
                "Запланировано",
                "Бассейн",
                "CH108",
                "Анастасия Зайцева"
            ),
            DomainDateInfo(
                2,
                "25.01.2026",
                "12:00",
                "Английский",
                "Запланировано",
                "Разговорный клуб",
                "CH109",
                "Павел Егоров"
            ),
            DomainDateInfo(
                1,
                "31.01.2026",
                "17:00",
                "Подведение итогов",
                "Запланировано",
                "Итоги месяца",
                "CH110",
                "Татьяна Федорова"
            )
        )
        CalendarCard().Calendar(domainDateInfoListVariant,textFont)
    }
}
