package com.example.core_navigation

sealed class NavRoutes(val routes: String) {
    object Main : NavRoutes("main")
    object AddNewOrChangeInfoChild : NavRoutes("addNewOrChangeInfoChild/{childId}") {
        fun passId(childId: Int?) = "addNewOrChangeInfoChild/$childId"
    }

    object AddNewOrChangeInfoUser : NavRoutes("addNewOrChangeInfoUser")
    object Calendar : NavRoutes("calendar")
    object ChildInfo : NavRoutes("childInfo/{childId}") {
        fun passId(childId: Int?) = "childInfo/$childId"
    }

    object UpcomingDates : NavRoutes("upcomingDates")
    object UserProfile : NavRoutes("userProfile")
}