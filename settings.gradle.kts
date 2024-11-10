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

rootProject.name = "NoteFlow"
include(":app")
include(":feature:tasks:data")
include(":feature:tasks:domain")
include(":feature:tasks:presentation")
include(":feature:notes:data")
include(":feature:notes:domain")
include(":feature:notes:presentation")
include(":feature:checklist:data")
include(":feature:checklist:domain")
include(":feature:checklist:presentation")
