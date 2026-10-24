package ru.mobile.uikit.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.mobile.uikit.catalog.ui.CatalogHomeScreen
import ru.mobile.uikit.catalog.ui.CatalogPlaygroundSheet
import ru.mobile.uikit.catalog.ui.CatalogSectionScreen
import ru.mobile.uikit.catalog.ui.CatalogThemeSurface
import ru.mobile.uikit.catalog.ui.rememberCatalogPlaygroundController

private object Routes {
    const val Home = "home"
    const val Section = "section/{sectionId}"

    fun section(id: String) = "section/$id"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogApp() {
    val navController = rememberNavController()
    val playground = rememberCatalogPlaygroundController()
    var showPlayground by rememberSaveable { mutableStateOf(false) }
    val sectionId = navController.currentBackStackEntry?.arguments?.getString("sectionId")
    val sectionTitle = sectionId?.let { CatalogRegistry.section(it)?.title }

    CatalogThemeSurface(playground) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            when {
                                sectionTitle != null -> sectionTitle
                                else -> "Mobile UI Kit"
                            },
                        )
                    },
                    navigationIcon = {
                        if (sectionTitle != null) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = { showPlayground = true }) {
                            Icon(Icons.Default.Tune, contentDescription = "Playground")
                        }
                    },
                )
            },
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Routes.Home,
                modifier = Modifier.padding(padding),
            ) {
                composable(Routes.Home) {
                    CatalogHomeScreen(onOpenSection = { id ->
                        navController.navigate(Routes.section(id))
                    })
                }
                composable(Routes.Section) {
                    val id = it.arguments?.getString("sectionId") ?: return@composable
                    CatalogSectionScreen(sectionId = id)
                }
            }
        }

        if (showPlayground) {
            CatalogPlaygroundSheet(
                controller = playground,
                onDismiss = { showPlayground = false },
            )
        }
    }
}
