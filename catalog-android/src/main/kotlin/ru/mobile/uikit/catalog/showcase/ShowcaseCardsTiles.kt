package ru.mobile.uikit.catalog.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.steamapps.companion.designsystem.components.CompanionAvatar
import com.steamapps.companion.designsystem.components.CompanionCatalogItemCard
import com.steamapps.companion.designsystem.components.CompanionCategoryTile
import com.steamapps.companion.designsystem.components.CompanionHomeStyleItemCard
import com.steamapps.companion.designsystem.components.CompanionItemValueText
import com.steamapps.companion.designsystem.components.CompanionMediaTile
import com.steamapps.companion.designsystem.components.ItemCard
import com.steamapps.companion.designsystem.components.PriceTag
import com.steamapps.companion.designsystem.components.PriceTagText
import ru.mobile.uikit.catalog.model.CatalogStory
import ru.mobile.uikit.catalog.ui.StoryFrame

fun cardsTilesStories(): List<CatalogStory> = listOf(
    CatalogStory(
        id = "price-tag",
        title = "PriceTag",
        keywords = listOf("price", "money"),
    ) {
        StoryFrame("Variants") {
            PriceTag(amountUsd = 129.99)
            PriceTag(amountUsd = 12.5, partial = true)
            PriceTagText(text = "€ 42,00")
        }
    },
    CatalogStory(
        id = "category-tile",
        title = "CompanionCategoryTile",
        keywords = listOf("tile", "category"),
    ) {
        StoryFrame("With placeholder art") {
            CompanionCategoryTile(
                title = "Rifles",
                description = "42 items",
                onClick = {},
                placeholder = {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                    )
                },
            )
        }
        StoryFrame("Disabled") {
            CompanionCategoryTile(
                title = "Locked",
                description = "Coming soon",
                onClick = {},
                enabled = false,
                placeholder = { Box(Modifier.fillMaxSize()) },
            )
        }
    },
    CatalogStory(
        id = "item-cards",
        title = "Item / catalog / home cards",
        keywords = listOf("card", "item"),
    ) {
        StoryFrame("ItemCard") {
            ItemCard(
                title = "AK-47 | Redline",
                subtitle = "Field-Tested",
            )
        }
        StoryFrame("CompanionCatalogItemCard") {
            CompanionCatalogItemCard(
                title = "AWP | Dragon Lore",
                rarityName = "Covert",
                rarityColorHex = "#EB4B4B",
                subtitle = {
                    Text(
                        "Factory New",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                trailing = { CompanionItemValueText(usd = 4200.0) },
            )
        }
        StoryFrame("CompanionHomeStyleItemCard") {
            CompanionHomeStyleItemCard(
                title = "M4A1-S | Printstream",
                imageUrl = null,
                priceDisplay = "$118.50",
                typeLabel = "Rifle",
                rarityName = "Covert",
                rarityColorHex = "#EB4B4B",
                onClick = {},
            )
        }
    },
    CatalogStory(
        id = "media-avatar",
        title = "Avatar / media / value text",
    ) {
        StoryFrame("CompanionAvatar") {
            CompanionAvatar(imageUrl = null, contentDescription = "Player")
        }
        StoryFrame("CompanionItemValueText") {
            CompanionItemValueText(usd = 1024.0)
            CompanionItemValueText(usd = null, fallback = "—")
        }
        StoryFrame("CompanionMediaTile") {
            CompanionMediaTile(
                title = "Highlight",
                subtitle = "Clip",
                imageUrl = null,
            )
        }
    },
)
