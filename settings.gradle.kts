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

        // 🔹 Aquí NO hace falta Mapbox, pero si lo quieres dejar no pasa nada
        maven { url = uri("https://api.mapbox.com/downloads/v2/releases/maven") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        // 🔹 AQUÍ SÍ ES OBLIGATORIO Mapbox
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
        }
    }
}

rootProject.name = "UniLocal"
include(":app")
