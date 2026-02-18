package com.example.wizkids.presentation.addNewOrChangeUserInformation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.wizkids.R
import com.example.wizkids.presentation.sharedUI.NavHelper
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.viewModel.user.UserViewModel
import com.example.wizkids.ui.theme.WizKidsTheme
import org.koin.androidx.compose.koinViewModel

class AddNewOrChangeUserInformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        setContent {
            WizKidsTheme {
                val context = LocalContext.current

                Column(Modifier) {
                    NavHelper().Header(
                        stringResource(R.string.add_user_activity_title),
                        stringResource(R.string.add_user_activity_subtitle),
                        isBackActivity = true,
                        context = context
                    ) {
                        finish()
                    }
                    Column(
                        Modifier
                            .weight(1f)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        AddOrChangeUserInformationScreen(context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun AddOrChangeUserInformationScreen(
    userViewModel: UserViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    context: Context
) {
    val userUiState by userViewModel.userUiState.collectAsState()
    UserInfoControlState().ControlState(userUiState, textFont, context, userViewModel)
}