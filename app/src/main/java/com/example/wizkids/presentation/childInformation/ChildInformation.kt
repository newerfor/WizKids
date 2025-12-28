package com.example.wizkids.presentation.childInformation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.wizkids.ui.theme.WizKidsTheme
import com.example.wizkids.presentation.utilPresentation.Header

class ChildInformation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WizKidsTheme {
                var name = remember { mutableStateOf("") }
                var years = remember { mutableStateOf("") }
                var id = remember { mutableStateOf("") }
                Column(Modifier) {
                    Header().HeadInfo(
                        NameSelection = "${name.value}",
                        InfoSelection = "${years.value} • №${id.value}"
                    )
                    Column(Modifier.weight(1f).verticalScroll(
                        rememberScrollState()
                    )) {
                        ChildInfoScreen(name,years,id)
                    }
                }
            }
        }
    }
    @Composable
    fun ChildInfoScreen(
        name: MutableState<String>,
        years: MutableState<String>,
        id: MutableState<String>
    ) {
        /*val sampleChild = DomainDataChild(
            id = 101,
            foto = "https://i.pinimg.com/originals/55/65/3b/55653b553083f2eb2bb91ad416482cf0.jpg",
            name = "Петрова Мария Ивановна",
            Info = "Талантливая ученица 3-Б класса. Победитель олимпиады по литературе. Посещает кружок рисования и хореографии. Любит читать книги и заниматься творчеством.",
            happybithday = "10.08.2014",
            docs = listOf(
                DomainDocumentsData(
                    name = "Паспорт родителя",
                    Info = "Для оформления документов",
                    image = listOf("https://i.pinimg.com/originals/55/65/3b/55653b553083f2eb2bb91ad416482cf0.jpg","https://media.ixbt.site/1600x900/smart/ixbt-data/1105352/01KAG05D4803SYDYTVG0JQ9J2P.jpg")
                ),
                DomainDocumentsData(
                    name = "Заявление",
                    Info = "На дополнительные занятия",
                    image = listOf("https://i.pinimg.com/originals/55/65/3b/55653b553083f2eb2bb91ad416482cf0.jpg","https://media.ixbt.site/1600x900/smart/ixbt-data/1105352/01KAG05D4803SYDYTVG0JQ9J2P.jpg")
                ),
                DomainDocumentsData(
                    name = "Грамоты и награды",
                    Info = "Достижения за 2023-2024 учебный год",
                    image = listOf("https://i.pinimg.com/originals/55/65/3b/55653b553083f2eb2bb91ad416482cf0.jpg","https://media.ixbt.site/1600x900/smart/ixbt-data/1105352/01KAG05D4803SYDYTVG0JQ9J2P.jpg")
                )
            ),
            workStage = listOf(
                "Литература: прочитана \"Маленький принц\"",
                "Математика: изучение таблицы умножения",
                "Рисование: подготовка к выставке",
                "Хореография: разучивание нового танца",
                "Английский: слова на тему \"Семья\""
            ),
            DateInfo = listOf(
                DomainDateInfo(
                    date = "18.03.2024",
                    time = "16:00",
                    name = "Выставка рисунков",
                    Status = "Пришёл",
                    Info = "Школьная выставка детского творчества"
                ),
                DomainDateInfo(
                    date = "22.03.2024",
                    time = "18:00",
                    name = "Концерт",
                    Status = "Не пришёл",
                    Info = "Выступление хореографического кружка"
                ),
                DomainDateInfo(
                    date = "28.03.2024",
                    time = "15:30",
                    name = "Встреча с психологом",
                    Status = "Пришёл",
                    Info = "Индивидуальная консультация"
                ),
                DomainDateInfo(
                    date = "05.04.2024",
                    time = "10:00",
                    name = "Олимпиада",
                    Status = "Назначено",
                    Info = "Городская олимпиада по русскому языку"
                )
            ),
            price = 12500,
            balance = 37500
        )
        LaunchedEffect(sampleChild) {
            name.value = sampleChild.name
            years.value = GetAge().getAgeFromDate(sampleChild.happybithday)
            id.value = sampleChild.id.toString()
        }
        ChildFullInfoScreen().ChildFullInfo(sampleChild)
         */
    }
}
