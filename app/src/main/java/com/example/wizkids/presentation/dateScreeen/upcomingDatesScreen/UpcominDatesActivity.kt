package com.example.wizkids.presentation.dateScreeen.upcomingDatesScreen

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

class UpcominDatesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            com.example.wizkids.ui.theme.WizKidsTheme {
                Column(Modifier) {
                    Header().HeadInfo("Ближайшие посещения")
                    Column(Modifier.weight(MainActivityChildScreenWeight)) {
                        UpcomingDatesScreen()
                    }
                }
            }
        }
    }
    @Composable
    fun UpcomingDatesScreen(textFont: TextFont = TextFont()){
        val domainDateInfoList: List<DomainDateInfo> = listOf(
            // Сегодня - 27.12.2025
            DomainDateInfo(
                dateId = 1,
                date = "27.12.2025",
                time = "09:00",
                name = "Утренняя зарядка",
                status = "Запланировано",
                info = "Ежедневная утренняя активность",
                childId = "CH001",
                childName = "Александр Петров"
            ),
            DomainDateInfo(
                dateId = 2,
                date = "27.12.2025",
                time = "11:30",
                name = "Занятие математикой",
                status = "Запланировано",
                info = "Решение задач на сложение",
                childId = "CH002",
                childName = "Мария Иванова"
            ),
            DomainDateInfo(
                dateId = 3,
                date = "27.12.2025",
                time = "14:00",
                name = "Творческая мастерская",
                status = "Запланировано",
                info = "Рисование акварелью",
                childId = "CH003",
                childName = "Иван Сидоров"
            ),
            DomainDateInfo(
                dateId = 4,
                date = "27.12.2025",
                time = "16:45",
                name = "Музыкальное занятие",
                status = "Запланировано",
                info = "Игра на детских инструментах",
                childId = "CH004",
                childName = "Ольга Кузнецова"
            ),

            // Завтра - 28.12.2025
            DomainDateInfo(
                dateId = 5,
                date = "28.12.2025",
                time = "10:15",
                name = "Подготовка к празднику",
                status = "Запланировано",
                info = "Изготовление новогодних украшений",
                childId = "CH005",
                childName = "Дмитрий Смирнов"
            ),
            DomainDateInfo(
                dateId = 6,
                date = "28.12.2025",
                time = "13:20",
                name = "Развивающие игры",
                status = "Запланировано",
                info = "Конструкторы и пазлы",
                childId = "CH006",
                childName = "Екатерина Попова"
            ),
            DomainDateInfo(
                dateId = 7,
                date = "28.12.2025",
                time = "15:30",
                name = "Чтение книг",
                status = "Запланировано",
                info = "Сказки народов мира",
                childId = "CH007",
                childName = "Андрей Васильев"
            ),

            // Послезавтра - 29.12.2025
            DomainDateInfo(
                dateId = 8,
                date = "29.12.2025",
                time = "08:45",
                name = "Логопедическое занятие",
                status = "Запланировано",
                info = "Развитие речи и артикуляции",
                childId = "CH008",
                childName = "София Морозова"
            ),
            DomainDateInfo(
                dateId = 9,
                date = "29.12.2025",
                time = "12:10",
                name = "Английский язык",
                status = "Запланировано",
                info = "Изучение цветов и животных",
                childId = "CH009",
                childName = "Кирилл Новиков"
            ),
            DomainDateInfo(
                dateId = 10,
                date = "29.12.2025",
                time = "17:00",
                name = "Физкультура",
                status = "Запланировано",
                info = "Подвижные игры на улице",
                childId = "CH010",
                childName = "Анна Федорова"
            )
        )
        UpcomingDatesCard().UpcomingDates(domainDateInfoList,textFont)
    }
}