package com.example.wizkids.presentation.userProfile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.wizkids.R
import com.example.wizkids.presentation.UserProfile.constant.UserProfileViewConstant.USER_PROFILE_MAIN_CONTENT_WEIGHT
import com.example.wizkids.presentation.UserProfile.ui.UserProfileControlState
import com.example.wizkids.presentation.sharedUI.NavHelper
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.viewModel.user.UserViewModel
import com.example.wizkids.ui.theme.WizKidsTheme
import org.koin.androidx.compose.koinViewModel

class UserProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        setContent {
            WizKidsTheme {
                val context = LocalContext.current
                Column(Modifier.fillMaxSize()) {
                    NavHelper().Header(
                        nameSelection = stringResource(R.string.user_profile_title),
                        infoSelection = stringResource(R.string.user_profile_subtitle),
                        context = context
                    )
                    Column(Modifier.weight(USER_PROFILE_MAIN_CONTENT_WEIGHT)) {
                        UserProfileScreen(context = context)
                    }
                    Column(
                        Modifier.fillMaxWidth(),
                        Arrangement.Bottom,
                        Alignment.CenterHorizontally
                    ) {
                        NavHelper().Footer(context = context)
                    }
                }
            }
        }
    }

    @Composable
    fun UserProfileScreen(
        userViewModel: UserViewModel = koinViewModel(),
        textFont: TextFont = TextFont(),
        context: Context
    ) {
        val userUiState by userViewModel.userUiState.collectAsState()
        UserProfileControlState().ControlState(userUiState, textFont, context, userViewModel)
    }
}
