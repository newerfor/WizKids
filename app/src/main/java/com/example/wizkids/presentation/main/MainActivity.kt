package com.example.wizkids.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wizkids.domain.model.DomainDataChild
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.domain.model.DomainDocumentsData
import com.example.wizkids.ui.theme.WizKidsTheme
import com.example.wizkids.presentation.utilPresentation.Header
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MainHeadInfoInfoSelection
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MainHeadInfoNameSelection
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildScreenPaddingHorizontal
import com.example.wizkids.presentation.main.constant.MainViewConstant.MainActivityChildScreenWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        setContent {
            WizKidsTheme {
                Column(Modifier) {
                        Header().HeadInfo(MainHeadInfoNameSelection, MainHeadInfoInfoSelection)
                    Column(Modifier.weight(MainActivityChildScreenWeight)) {
                        ChildScreen()
                    }
                }
            }
        }
    }

    @Composable
    fun ChildScreen() {
       /* val allchild = listOf(
            DomainDataChild(
                id = 1,
                foto = "foto_url_1",
                name = "User One",
                Info = "Info about User One",
                happybithday = "01.01.2001",
                docs = listOf(
                    DomainDocumentsData("Passport", "Passport info", listOf("image1_url", "image2_url")),
                    DomainDocumentsData(
                        "Birth Certificate",
                        "Birth certificate info",
                        listOf("image3_url")
                    )
                ),
                workStage = listOf("Intern", "Junior Developer"),
                DateInfo = listOf(
                    DomainDateInfo("01.10.2025", "09:00", "Meeting", "Confirmed", "Initial meeting"),
                    DomainDateInfo("02.10.2025", "14:00", "Review", "Pending", "Code review session")
                ),
                price = 11221,
                balance = 23412
            ),
            DomainDataChild(
                id = 2,
                foto = "foto_url_2",
                name = "User Two",
                Info = "Info about User Two",
                happybithday = "12.05.1995",
                docs = listOf(
                    DomainDocumentsData("ID Card", "ID Card info", listOf("image4_url")),
                    DomainDocumentsData("Driver License", "License info", listOf("image5_url"))
                ),
                workStage = listOf("Junior Developer", "Middle Developer"),
                DateInfo = listOf(
                    DomainDateInfo("10.11.2025", "10:00", "Workshop", "Confirmed", "Training workshop"),
                    DomainDateInfo("11.11.2025", "11:00", "Interview", "Scheduled", "Candidate interview")
                ),
                price = 11221,
                balance = 23412
            ),
            DomainDataChild(
                id = 3,
                foto = "foto_url_3",
                name = "User Three",
                Info = "Info about User Three",
                happybithday = "22.08.1988",
                docs = listOf(
                    DomainDocumentsData("Work Contract", "Contract info", listOf("image6_url")),
                    DomainDocumentsData("Certificate", "Certificate info", listOf("image7_url"))
                ),
                workStage = listOf("Senior Developer", "Team Lead"),
                DateInfo = listOf(
                    DomainDateInfo("10.09.2025", "15:00", "Planning", "Completed", "Sprint planning"),
                    DomainDateInfo("16.092025", "16:00", "Demo", "Completed", "Feature demo")
                ),
                price = 11221,
                balance = 23412
            ),
            DomainDataChild(
                id = 4,
                foto = "foto_url_4",
                name = "User Four",
                Info = "Info about User Four",
                happybithday = "05.03.1992",
                docs = listOf(
                    DomainDocumentsData("Contract", "Contract details", listOf("image8_url")),
                    DomainDocumentsData("Certification", "Certification details", listOf("image9_url"))
                ),
                workStage = listOf("Tester", "QA Engineer"),
                DateInfo = listOf(
                    DomainDateInfo("01.12.2025", "08:00", "Test Run", "Scheduled", "Test execution"),
                    DomainDateInfo("02.12.2025", "13:00", "Bug Review", "Planned", "Review bugs")
                ),
                price = 11221,
                balance = 23412
            ),
            DomainDataChild(
                id = 5,
                foto = "foto_url_5",
                name = "User Five",
                Info = "Info about User Five",
                happybithday = "30.07.1990",
                docs = listOf(
                    DomainDocumentsData("Diploma", "Diploma details", listOf("image10_url")),
                    DomainDocumentsData(
                        "Recommendations",
                        "Recommendation letters",
                        listOf("image11_url")
                    )
                ),
                workStage = listOf("Developer", "Project Manager"),
                DateInfo = listOf(
                    DomainDateInfo(
                        "20.10.2025",
                        "09:30",
                        "Kickoff",
                        "Confirmed",
                        "Project kickoff meeting"
                    ),
                    DomainDateInfo(
                        "21.10.2025",
                        "11:30",
                        "Client Call",
                        "Scheduled",
                        "Call with the client"
                    )
                ),
                price = 11221,
                balance = 23412
            )
        )
        Column(Modifier.fillMaxWidth().padding(horizontal = ChildScreenPaddingHorizontal).verticalScroll(ScrollState(1))) {
            MainActFilters().Filters()
            AllChildScreen().AllChuild(allchild = allchild)
        }
        */
    }
}

