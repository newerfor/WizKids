pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WizKids"
include(":app")
include(":core:core-data")
include(":core:core-navigation")
include(":core:core-ui")
include(":core:core-util")
include(":feature:feature-main")
include(":core:core-viewModel")
include(":feature:feature-addNewOrChangeInfoChild")
include(":feature:feature-addNewOrChangeInfoUser")
include(":feature:feature-childInformation")
include(":feature:feature-userProfile")
include(":feature:feature-calendarScreen")
include(":feature:feature-upcomingDates")
include(":core:core-domain")
