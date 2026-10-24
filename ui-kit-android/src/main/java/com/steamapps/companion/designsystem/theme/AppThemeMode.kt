package com.steamapps.companion.designsystem.theme

enum class AppThemeMode(val storageId: String) {
    System("system"),
    Light("light"),
    Dark("dark"),
    ;

    fun resolveDarkTheme(systemInDarkTheme: Boolean): Boolean = when (this) {
        Light -> false
        Dark -> true
        System -> systemInDarkTheme
    }

    companion object {
        val default: AppThemeMode = System

        fun fromStorageId(id: String?): AppThemeMode = when (id?.trim()?.lowercase()) {
            Light.storageId -> Light
            Dark.storageId -> Dark
            System.storageId, null, "" -> System
            else -> System
        }
    }
}
