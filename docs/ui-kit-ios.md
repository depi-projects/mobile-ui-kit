# ui-kit-ios

Swift Package `MobileUIKit`. Приложение CS2 не рисует свои кнопки, карточки и цвета: оно берёт типы отсюда.

База, которую оборачиваем:

- **SwiftUI** — кнопки, поля, прогресс, каркас экрана.
- **Kingfisher** — картинки с дисковым кешем. `SwiftUI.AsyncImage` в ките нет.

Публичные обёртки: `KitButton`, `KitChip`, `KitIconButton`, `KitTextField`, `KitPanel`, `KitPageAlert`, `KitDialog`, `KitSnackbarHost`, `KitLoadingState` (скелетон страницы: home, inventory, leaderboard, item, catalog, watchlist), `KitEmptyState`, `KitErrorState`, `KitScaffold`, `KitNavigationBar`, `KitAsyncImage`, `KitCatalogItemCard`, `KitRarityChip`, `KitCategoryTile`, `KitPriceTag`, `KitListRow`, `KitPodium`, `KitSegmentedControl`, `KitShareLink`, `KitSparkline`, `KitTheme`.

Роли `KitFont` читают текущее семейство `KitFontChoice`: Manrope, Inter, Onest, Montserrat, IBM Plex Sans, Space Grotesk, Source Sans 3, Satoshi. По умолчанию Manrope. Файл токенов генерируется и задаёт только размер и насыщенность.

Цвета, отступы, радиусы и шрифтовые роли генерируются из `tokens/tokens.json`:

```bash
node scripts/generate-swift-tokens.mjs
```

Файл `Sources/MobileUIKit/Generated/KitTokens.generated.swift` руками не правят.

Соответствие теней и анимации: [`platform-mapping.md`](platform-mapping.md). Контракт имён и вариантов: [`design-system.md`](design-system.md).
