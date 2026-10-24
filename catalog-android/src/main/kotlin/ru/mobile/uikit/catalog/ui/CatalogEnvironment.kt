package ru.mobile.uikit.catalog.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.steamapps.companion.designsystem.theme.AppThemeMode
import com.steamapps.companion.designsystem.theme.CompanionTheme
import ru.mobile.uikit.catalog.model.CatalogFontScale

data class CatalogPlaygroundState(
    val themeMode: AppThemeMode = AppThemeMode.Light,
    val fontScale: CatalogFontScale = CatalogFontScale.Default,
    val narrowWidth: Boolean = false,
    val showLayoutBounds: Boolean = false,
    val disabledStates: Boolean = false,
)

val LocalCatalogPlayground = compositionLocalOf { CatalogPlaygroundState() }

class CatalogPlaygroundController(initial: CatalogPlaygroundState = CatalogPlaygroundState()) {
    var state by mutableStateOf(initial)
        private set

    fun update(transform: (CatalogPlaygroundState) -> CatalogPlaygroundState) {
        state = transform(state)
    }
}

@Composable
fun rememberCatalogPlaygroundController(): CatalogPlaygroundController {
    val saver = listSaver<CatalogPlaygroundController, Any>(
        save = {
            listOf(
                it.state.themeMode.name,
                it.state.fontScale.name,
                it.state.narrowWidth,
                it.state.showLayoutBounds,
                it.state.disabledStates,
            )
        },
        restore = {
            CatalogPlaygroundController(
                CatalogPlaygroundState(
                    themeMode = AppThemeMode.valueOf(it[0] as String),
                    fontScale = CatalogFontScale.valueOf(it[1] as String),
                    narrowWidth = it[2] as Boolean,
                    showLayoutBounds = it[3] as Boolean,
                    disabledStates = it[4] as Boolean,
                ),
            )
        },
    )
    return rememberSaveable(saver = saver) { CatalogPlaygroundController() }
}

@Composable
fun CatalogThemeSurface(
    controller: CatalogPlaygroundController,
    content: @Composable () -> Unit,
) {
    val playground = controller.state
    val configuration = LocalConfiguration.current
    val scaledConfig = Configuration(configuration).apply {
        fontScale = configuration.fontScale * playground.fontScale.scale
    }
    val scaledFontScale = configuration.fontScale * playground.fontScale.scale
    CompositionLocalProvider(
        LocalCatalogPlayground provides playground,
        LocalConfiguration provides scaledConfig,
        LocalDensity provides Density(
            density = LocalDensity.current.density,
            fontScale = scaledFontScale,
        ),
    ) {
        CompanionTheme(themeMode = playground.themeMode, content = content)
    }
}
