package com.example.wizkids

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core_navigation.NavRoutes
import com.example.feature_addneworchangeinfochild.ui.AddNewOrChangeChildInfoScreen
import com.example.feature_addneworchangeinfouser.ui.AddNewOrChangeInfoUserScreen
import com.example.feature_calendarscreen.ui.CalendarScreen
import com.example.feature_childinformation.ui.ChildInformation
import com.example.feature_main.ui.MainScreen
import com.example.feature_upcomingdates.ui.UpcomingVisitMain
import com.example.feature_userprofile.ui.UserMainScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.Main.routes) {
        composable(NavRoutes.Main.routes) {
            MainScreen({ childId ->
                navController.navigate(
                    NavRoutes.AddNewOrChangeInfoChild.passId(
                        childId
                    )
                )
            }, { childId ->
                navController.navigate(
                    NavRoutes.ChildInfo.passId(
                        childId
                    )
                )
            }, navController)
        }
        composable(NavRoutes.AddNewOrChangeInfoChild.routes) {
            val childId = it.arguments?.getString("childId")?.toIntOrNull()
            AddNewOrChangeChildInfoScreen(
                id = childId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(NavRoutes.AddNewOrChangeInfoUser.routes) {
            AddNewOrChangeInfoUserScreen(
                onBack = { navController.popBackStack() },
                onClickUserProfile = {
                    navController.navigate(
                        NavRoutes.UserProfile
                    )
                }
            )
        }
        composable(NavRoutes.Calendar.routes) {
            CalendarScreen(
                onClickUpcomingDates = {
                    navController.navigate(
                        NavRoutes.UpcomingDates
                    )
                },
                navController
            )
        }
        composable(NavRoutes.ChildInfo.routes) {
            val childId = it.arguments?.getString("childId")?.toIntOrNull()
            ChildInformation(
                id = childId,
                onClickBack = {
                    navController.popBackStack()
                },
                onClickAddInfoChild = {
                    navController.navigate(
                        NavRoutes.AddNewOrChangeInfoChild.passId(childId)
                    )
                }
            )
        }
        composable(NavRoutes.UpcomingDates.routes) {
            UpcomingVisitMain(
                onCLickGoToCalendar = {
                    navController.navigate(
                        NavRoutes.Calendar
                    )
                },
                navController
            )
        }
        composable(NavRoutes.UserProfile.routes) {
            UserMainScreen(
                onClickGoToAddUserInfo = {
                    navController.navigate(
                        NavRoutes.AddNewOrChangeInfoUser
                    )
                },
                navController
            )
        }
    }
}