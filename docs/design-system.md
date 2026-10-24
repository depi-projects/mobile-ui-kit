# Дизайн-система — актуальный стейт

Публичный API `ui-kit-android`, пакет `com.steamapps.companion.designsystem`, после закрытия аудита. Имена без префикса `Companion` — новые примитивы. Старые `Companion*` пока не переименованы.

У всех `@Composable` есть `modifier: Modifier = Modifier`, если в таблице не сказано иное.

Платформы делят **смысл** (имена, variants, состояния, цвета ролей), не исходный код. Android — Compose, iOS — SwiftUI. `ColumnScope` / `RowScope` на iOS становятся обычным содержимым view. `Modifier` на iOS не копируется: отступы и размер задаются снаружи view. Колбэки — `() -> Unit`. Иконки Compose `ImageVector` на iOS — SF Symbol или asset с тем же смыслом (`Back`, `Close`, `Share`), не общий бинарный тип.

Числовые токены записаны в `tokens/tokens.json`. Kotlin пока поддерживается вручную и должен совпадать с этим файлом. Генератор в Gradle ещё не подключён: Swift-константы руками не заводят. Соответствие тени, анимации, Dynamic Type, reduced motion и картинок — в `docs/platform-mapping.md`.

`LoadingState` по умолчанию показывает `CircularProgress`. `LoadingIndicator.Lottie` — опция Android. `SuccessState` — иконка и текст, без Lottie. `CompanionLoadingLottie` / `CompanionSuccessLottie` остаются необязательной анимацией Android.

`CompanionNavTransitions.detailEnter()` и соседние — функции, которые вызываются внутри composition и передаются в `NavHost` или `AnimatedContent`. При `LocalReduceMotion == true` они возвращают `EnterTransition.None` / `ExitTransition.None`. Predictive back и `BackHandler` остаются у экрана приложения. Исключение — видимый `Dialog(dismissOnBack = true)`: back закрывает диалог, а не экран под ним.

`LocalReduceMotion` — единственный composition local kit. `LocalPalette`, `LocalSpacing`, `LocalShapes` нет. На iOS флаг читается из `UIAccessibility.isReduceMotionEnabled`. Анимации `Snackbar`, `Dialog` и `BottomSheet` сами флаг не читают.

## Тема

### CompanionTheme

| Параметр | Тип | По умолчанию |
| --- | --- | --- |
| `font` | `AppFontChoice` | `Manrope` |
| `themeMode` | `AppThemeMode` | `System` |
| `maxFontScale` | `Float` | `2` |
| `content` | composable | — |

Системный `fontScale` режется сверху до `maxFontScale`. Текст в `sp` масштабируется в этом пределе. `Settings.Global.ANIMATOR_DURATION_SCALE == 0` выставляет `LocalReduceMotion`.

### AppThemeMode

`System`, `Light`, `Dark`. Хранение: `system` / `light` / `dark`. На Swift те же имена в lower camel case значения, тип `AppThemeMode`.

### AppFontChoice

У каждого есть `storageId`, `displayName` и `previewText` (`"The quick brown fox"`).

`Manrope`, `Inter`, `Onest`, `Montserrat`, `IbmPlexSans`, `SpaceGrotesk`, `SourceSans3`, `Satoshi`. По умолчанию `Manrope`.

### ColorScheme

Заданы явно в light и dark, включая контейнеры, которые раньше дорисовывал Material.

| Роль | Light | Dark |
| --- | --- | --- |
| `primary` / `onPrimary` | `#1C1C1E` / `#FFFFFF` | `#F5F5F7` / `#1E2024` |
| `primaryContainer` / `onPrimaryContainer` | `#F0F0F2` / `#16181D` | `#32353C` / `#F5F5F7` |
| `secondary` / `onSecondary` | `#E68600` / `#FFFFFF` | `#E68600` / `#FFFFFF` |
| `secondaryContainer` / `onSecondaryContainer` | `#FFF1DE` / `#5C3200` | `#3D2800` / `#FFD9A8` |
| `tertiary` / `onTertiary` | `#12A56A` / `#FFFFFF` | `#12A56A` / `#062117` |
| `tertiaryContainer` / `onTertiaryContainer` | `#DDF6EA` / `#0D3D28` | `#123828` / `#D4F5E4` |
| `error` / `onError` | `#D92D4A` / `#FFFFFF` | `#FF6B7A` / `#2A0A0E` |
| `errorContainer` / `onErrorContainer` | `#FDE8EC` / `#8C1024` | `#4A1820` / `#FFD5DA` |
| `background` / `onBackground` | `#FFFFFF` / `#16181D` | `#1E2024` / `#F5F5F7` |
| `surface` / `onSurface` | `#FFFFFF` / `#16181D` | `#272A30` / `#F5F5F7` |
| `surfaceVariant` / `onSurfaceVariant` | `#F5F5F7` / `#6B7280` | `#2E3138` / `#98989D` |
| `outline` | `#E3E6EE` | `#454850` |
| `surfaceContainerLowest` | `#FFFFFF` | `#1E2024` |
| `surfaceContainerLow` | `#F7F7F8` | `#272A30` |
| `surfaceContainer` | `#F3F3F5` | `#272A30` |
| `surfaceContainerHigh` | `#EEEEF0` | `#32353C` |
| `surfaceContainerHighest` | `#E8E8EB` | `#3A3D44` |

| `inverseSurface` / `inverseOnSurface` | `#1E2024` / `#F5F5F7` | `#F5F5F7` / `#1E2024` |
| `inversePrimary` | `#E68600` | `#E68600` |

### Palette

Плоский объект. Группы для ревью, в коде имена те же.

Бренд: `Navy` `#1B2838`, `NavyLight` `#2A475E`, `Charcoal` `#0E1419`, `CharcoalElevated` `#171D25`, `CharcoalPanel` `#1E252D`, `Coral` = `TacticalOrange` `#FF4D67`, `TacticalOrangeDim` `#E68600`, `CoralChip` `#FFE8EC`, `Orange` `#E68600`, `AccentBlue` `#6EA8FF`, `AccentBlueDim` `#4A8FCC`, `Gold` `#C9A227`, `GoldBright` `#E8C547`, `GoldDeep` `#9A7B0A`, `GoldFill` `#F5E6B8`.

Состояния: `Up` = `SuccessGreen` `#12A56A`, `Down` = `ErrorRed` `#E11D48`, `Mint` `#D4F5E4`, `MintInk` `#0D3D28`.

Навигационный акцент (не роль схемы): `NavLime` `#CBF165`, `NavLimeInk` `#1A2E0A`, `NavLimeChip` `#EEF6D4`.

Текст и границы: `TextMuted` `#8F98A0`, `ItemTypeMuted` `#7B8FA3`, `BorderSubtle` `#3D4450`, `GlowOrange` `#33E09805` (AARRGGBB), `RarityUnknown` `#8B93A7`.

Роли схемы — цвета состояний интерфейса (`error`, `tertiary`, `secondary`). `Palette.Up` / `Palette.Down` — цвет данных и финансов. `Palette.Down` (`#E11D48`) не равен `ColorScheme.error`. Кнопки, строки и алерты берут `error`, не `Palette.Down`.

Помощники поверхностей: `companionSoftPanelColor`, `companionSoftPanelPlaceholderColor`, `companionElevatedCardColor` — в тёмной теме `surfaceContainer*`, в светлой `surfaceVariant` с alpha 0.35 / 0.55 / 0.35.

### Типографика

`companionTypography(font)` задаёт семейство на все роли ниже. `KitTypography` — снимок шрифта по умолчанию.

| Роль | Размер | Вес | Line height |
| --- | --- | --- | --- |
| `displayLarge` | 57 | Bold | 64 |
| `displayMedium` | 45 | Bold | 52 |
| `displaySmall` | 36 | Bold | 44 |
| `headlineLarge` | 40 | Bold | 44 |
| `headlineMedium` | 24 | SemiBold | 30 |
| `headlineSmall` | 28 | Bold | — |
| `titleLarge` | 22 | Bold | 26 |
| `titleMedium` | 16 | Bold | — |
| `titleSmall` | 14 | Medium | — |
| `bodyLarge` | 16 | Normal | 24 |
| `bodyMedium` | 14 | Normal | 20 |
| `bodySmall` | 12 | Normal | 16 |
| `labelLarge` | 14 | SemiBold | — |
| `labelMedium` | 12 | Medium | — |
| `labelSmall` | 11 | Medium | — |

### Геометрия и состояние

`Space`: 4, 8, 12, 16, 20, 24, 32, `screen` 20, `button` 56.

`Radius`: 12, 16, 20, 28. `Shapes.small` = 12, `medium` = 16, `large` = 20, `extraLarge` = 28. `PanelShape` = 20, `ButtonShape` = 16, `CompanionAvatarShape` = 10.

`Elevation`: `none` 0, `xs` 2, `sm` 4, `md` 6, `lg` 8, `xl` 12.

`DisabledAlpha`: контейнер `0.12`, контент `0.38`. Оба variant кнопки и контролы форм используют эти числа.

`MotionTokens`: 160 / 280 / 340 / 220 ms, кривая `FastOutSlowInEasing`, пружина `StiffnessMediumLow`.

## Кнопки

### Button

| Параметр | Тип | По умолчанию |
| --- | --- | --- |
| `onClick` | `() -> Unit` | — |
| `variant` | `ButtonVariant` | `Primary` |
| `enabled` | `Boolean` | `true` |
| `loading` | `Boolean` | `false` |
| `fillWidth` | `Boolean` | `true` — на всю ширину и минимум 56; `false` — по содержимому, минимум 40 |
| `leadingIcon` | composable? | `null` |
| `content` | `RowScope` composable | — |

`Link(text, onClick, enabled = true)` — inline-текст `bodyMedium` цвета `primary`, без ширины родителя и без высоты 56.

`ButtonVariant`: `Primary`, `Secondary`, `Tertiary`, `Destructive`.

Размер: ширина родителя, минимальная высота `Space.button` (56), форма `ButtonShape`. Отдельной оси Small/Medium/Large нет.

`loading` блокирует повторный клик и заменяет контент на `CircularProgress` 18dp цвета содержимого.

| Variant | Фон | Текст | Обводка | Disabled |
| --- | --- | --- | --- | --- |
| `Primary` | `primary` | `onPrimary` | нет | фон ×0.12, текст ×0.38 |
| `Secondary` | `surface` | `onSurface` | `outline` 1dp | те же alpha, обводка ×0.38 |
| `Tertiary` | нет | `primary` | нет | текст `primary` ×0.38 |
| `Destructive` | `error` | `onError` | нет | фон и текст с теми же alpha |

Тень `Elevation.none`.

### IconButton

Произвольная `ImageVector`. Закрытый `CompanionChromeIcon` для этого не расширяется.

| Параметр | По умолчанию |
| --- | --- |
| `icon: ImageVector` | — |
| `onClick` | — |
| `contentDescription: String` | обязателен |
| `toggled` | `false` — фон `primary`, иконка `onPrimary` |
| `loading` | `false` — вместо иконки `CircularProgress`, клик снят |
| `containerColor` | `surfaceVariant`, либо `primary`, если `toggled` |
| `iconTint` | `onSurfaceVariant` |
| `size` | 36 |
| `iconSize` | 20 |

Круг.

### CompanionChromeIconButton / Glyph

Enum: `Back`, `Forward`, `Settings`, `Share`, `External`. Новую иконку приложения сюда не добавляют — для неё `IconButton`.

Кнопка: `contentDescription = null`, фон `surfaceVariant`, тинт `onSurfaceVariant`, size 36 / icon 20. Glyph: size 20 / icon 14, без клика.

### CompanionChromeTextButton

`label`, `onClick`, `showExternalIcon = false`. Фон `surfaceVariant`, текст `labelLarge` Bold `onSurface`, скругление 12, padding 12×8. Иконка external 16dp `onSurfaceVariant`.

### CompanionHeroIconButton

`onClick`, `contentDescription = null`, `content`. Цвет содержимого белый. Если `contentDescription` задан, он попадает в semantics. Size задаёт вызывающий `content`.

### SignOutButton

`title`, `onClick`, `enabled = true`, `icon = null`. Фон `error`, текст и иконка `onError`. Disabled: фон × `DisabledAlpha.container`, клик снят. Скругление 22, padding 20×16, тень `Elevation.xs`.

### Fab

`onClick`, `contentDescription` (обязателен), `label = null`, `containerColor = primary`, `contentColor = onPrimary`, `content`. Круг 56, тень `Elevation.md`. `label` задаёт extended: иконка `content` и текст. Положение на экране задаёт приложение или слот `fab` у `CompanionScaffold` (снизу-справа, отступ `Space.md`).

### CompanionHeaderPill

`onClick`, `label = null`, `showBackIcon = true`, `contentColor = White`, `containerColor = Charcoal 72%`. Форма `ButtonShape`, иконка 18, тень `Elevation.sm`.

## Поле

### TextField

Это общее поле, не поиск. Дефолтный label больше не `"Search items"`.

| Параметр | По умолчанию |
| --- | --- |
| `value`, `onValueChange` | — |
| `label` | `null` |
| `placeholder` | `null` |
| `supportingText` | `null` |
| `error` | `null` — текст ошибки вместо supporting |
| `isError` | `error != null` — красная рамка и без текста, если передать `true` при `error = null` |
| `maxLength` | `null` — лишние символы не попадают в `onValueChange` |
| `onClear` | пустой — вызывается вместе с очисткой, если `clearable` |
| `onFocusChanged` | пустой — `true`, когда поле в фокусе |
| `enabled` | `true` |
| `readOnly` | `false` |
| `singleLine` | `true` |
| `maxLines` | 1, если `singleLine`, иначе без лимита |
| `keyboardType` | `Text` |
| `imeAction` | `Default`; становится `Search`, если задан только `onSearch`; `Done`, если задан только `onDone` |
| `visualTransformation` | `None` |
| `leadingIcon`, `trailingIcon` | `null` |
| `clearable` | `false` — крестик с description `"Clear"`, если значение непустое |
| `onSearch`, `onDone` | `null` |

Ширина родителя, форма `Shapes.small` (12), текст `bodyLarge`. Фокус, курсор и label: `primary`. Ошибка: `error`.

## Контролы форм

Цвета зафиксированы ролями схемы, не дефолтом Material «как получится». Disabled — `DisabledAlpha`. Минимальная высота контрола — стандарт Material (hit area около 48).

| Компонент | Параметры | Цвет |
| --- | --- | --- |
| `Switch` | `checked`, `onCheckedChange`, `enabled = true`, `label = null` | включён: thumb `onPrimary`, track `primary`; выключен: thumb `outline`, track `surfaceVariant` |
| `Checkbox` | `checked`, `onCheckedChange`, `enabled = true`, `label = null` | отмечен `primary`, галочка `onPrimary`, пустой `outline` |
| `Radio` | `selected`, `onClick`, `enabled = true`, `label = null` | выбран `primary`, пустой `outline` |
| `Slider` | `value`, `onValueChange`, `enabled = true`, `valueRange = 0..1`, `steps = 0`, `label = null` | thumb и активный трек `primary`, неактивный `surfaceVariant` |

`SwitchRow` — `ListRow` плюс `Switch` без своего label.

Если у `Switch`, `Checkbox` или `Radio` задан `label`, подпись и контрол — один доступный узел (`Role.Switch` / `Role.Checkbox` / `Role.RadioButton`). Отдельной ошибки на самом контроле нет: её пишет `FormField(label, supportingText, isError, content)`.

`TextFieldValue` (курсор и выделение) в контракт не входит. `onDone` срабатывает на IME Done, не на Enter любой клавиатуры.

## Оверлеи

### Dialog

`visible`, `title`, `onDismiss`, `confirmLabel`, `onConfirm`, `message = null`, `dismissLabel = null`, `destructive = false`, `loading = false`, `error = null`, `dismissOnBack = true`, `dismissOnClickOutside = true`, `content = null`.

`content` рисуется под `message` и над текстом `error`. Подтверждение — текстовая кнопка: `primary` или `error`, если `destructive`. При `loading` кнопка подтверждения выключена. Отмена — текстовая кнопка `dismissLabel`. Фон `surface`. Это не `BottomSheet`. `dismissOnBack` — исключение из правила «back обрабатывает экран».

### Snackbar и SnackbarHost

`Snackbar(message, variant = Default, actionLabel = null, onAction = null, onDismiss = null)`.

| `SnackbarVariant` | Фон | Текст | Действие |
| --- | --- | --- | --- |
| `Default` | `inverseSurface` | `inverseOnSurface` | `inversePrimary` |
| `Success` | `tertiaryContainer` | `onTertiaryContainer` | `tertiary` |
| `Error` | `errorContainer` | `onErrorContainer` | `error` |

`onDismiss` рисует кнопку «×».

`SnackbarDuration`: `Short` 4 с, `Long` 10 с, `Indefinite` до action или dismiss. Длительность передаётся в `SnackbarHostState.showSnackbar`, не в сам `Snackbar`.

`SnackbarHost(hostState: SnackbarHostState)` показывает одну запись: следующая ждёт закрытия текущей и не заменяет её. На iOS очередь с тем же правилом, тип Material не копируется.

### Tooltip

`text`, `content`. Только долгое нажатие, `PlainTooltip`. Hover не поддерживается.

### Menu и MenuItem

`Menu(expanded, onDismiss, offset = DpOffset.Zero, content)`. Меню открывается от точки вызова со смещением `offset`. Фон `surface`. На iOS это popover от той же точки, не системное context menu.

`MenuItem(label, onClick, enabled = true, destructive = false, leadingIcon = null, trailing = null)`. Обычный текст `onSurface`, destructive — `error`.

### BottomSheet

Без изменений контракта: `visible`, `title`, `onDismiss`, `sheetState`, `content`. Фон `background`, tonal elevation 0. Это sheet, не dialog.

## Прогресс

`progress == null` — indeterminate. Иначе 0..1. Цвет `primary`, трек linear — `surfaceVariant`. `label` попадает в semantics вместе с `progressBarRangeInfo`.

| Компонент | Параметры |
| --- | --- |
| `CircularProgress` | `progress = null`, `label = null` |
| `LinearProgress` | `progress = null`, `label = null` |

`LoadingState`: `message = "Loading…"`, `modifier`, `skeleton = Generic`, `showProgressIndicator = true`, `indicator = Circular`. Скелетон и индикатор независимы: `showProgressIndicator = false` оставляет скелетон без спиннера. По умолчанию `CircularProgress`. `LoadingIndicator.Lottie` — `CompanionLoadingLottie` 56, только Android.

## Картинка

### AsyncImage

| Параметр | По умолчанию |
| --- | --- |
| `model` | — |
| `contentDescription` | — |
| `fit` | `ImageFit.Crop` (`Fit` — второй вариант) |
| `shape` | прямоугольник |
| `clipToShape` | `true` |
| `onLoading`, `onSuccess`, `onError` | пустые |
| `placeholder` | `Skeleton` на весь размер |
| `error` | прямоугольник `surfaceVariant` |

Android грузит через Coil. iOS — Kingfisher с дисковым кешем, те же слоты placeholder / error / колбэки. Системный `AsyncImage` SwiftUI без кеша не используется.

`clipToShape = false` у `CompanionItemThumbnail`, `CompanionCategoryTile` и hero в `CompanionDetailScaffold`: арт масштабируется и не должен обрезаться формой. `CompanionMediaTile` — `ImageFit.Crop`. `CompanionHomeStyleItemCard` и `CompanionCatalogItemCard` идут через thumbnail. `CompanionAvatar`: size 48, форма 10dp, `fit = Crop`.

## Выбор, чипы, вкладки

### Chip

`ChipVariant`: `Filter`, `Assist`.

`label`, `onClick`, `variant = Filter`, `selected = false`, `enabled = true`, `leadingIcon = null`. Минимальная высота 32.

Filter selected: фон `primary`, текст `onPrimary`. Filter idle: `surfaceVariant` / `onSurface`. Assist: `surfaceVariant` / `onSurfaceVariant`, без selected. Галочки у выбранного Filter нет. Варианта Input (съёмный тег) нет.

### CountBadge

`count = null`, `content`. Бейдж стоит в top-end `content` (BadgedBox), без своего offset. `null` — точка `error` / `onError`. `> 0` — число, больше 99 показывается `99+`. `0` — бейдж скрыт.

### Tabs

`TabItem(id, label, icon = null)`. `tabs`, `selectedId`, `onSelected`, `scrollable = false`, `onReselected = {}`. Фон строки `surface`. Выбранный текст `primary`, остальные `onSurfaceVariant`. Индикатор — стандарт Material: `primary`, толщина 2, без своего скругления. Повторный тап по выбранной вкладке вызывает `onReselected`. `scrollable = true` для длинного ряда, боковой отступ `Space.screen`. Выбранная вкладка объявляется `selected` для TalkBack. Это верхние вкладки экрана, не `NavigationBar` и не `CompanionSegmentedControl`.

### CompanionSegmentedControl

Без изменений: выбранный сегмент фон `primary` / текст `onPrimary`, остальные текст `onSurfaceVariant`. Трек `surfaceVariant` 65%, радиус 14.

### CompanionPeriodDropdown

Один выбранный id из списка. Это select, не `Menu`. `compact = false`: радиус 14, padding 14×10, `labelLarge`. `compact`: радиус 12, padding 8×6, `labelSmall`. Фон `surfaceVariant` 75%, обводка `outline` 25%.

### Expandable

`title`, `expanded`, `onToggle`, `content`. Заголовок `titleSmall` на `surface`. Иконка с description `Expand` / `Collapse`.

### Gap

`step: SpaceStep` — `Xxs`, `Xs`, `Sm`, `Md`, `Lg`, `Xl`, `Xxl`, `Screen`. Произвольный `Dp` не компилируется. `Space.button` в `Gap` не входит.

## Обратная связь

### CompanionPageAlert

`CompanionAlertType`: `Info`, `Success`, `Warning`, `Error`.

`type`, `message`, `title = null`, `detail = null`, `action = null`. Пустой message не рисуется. Скругление 14, padding 14×12, иконка 22. Сообщение `bodyMedium` `onSurface`, detail `bodySmall` `onSurfaceVariant`, title `titleSmall` Bold. `action` рисуется под текстом. Крестика закрытия нет.

| Type | Фон | Обводка | Иконка / title |
| --- | --- | --- | --- |
| `Info` | `primaryContainer` 35% | `primary` 20% | `primary` |
| `Success` | `tertiaryContainer` 45% | `tertiary` 35% | иконка `tertiary`, title `onTertiaryContainer` |
| `Warning` | `secondaryContainer` 45% | `secondary` 35% | иконка `secondary`, title `onSecondaryContainer` |
| `Error` | `errorContainer` 45% | `error` 35% | `error` |

### EmptyState

`title`, `message`, `icon = Inventory`, `iconTint = Palette.Coral`, `contentDescription = null`, `action = null`. Иконка объявляется как `contentDescription ?: title`. Круг 64, фон `Palette.CoralChip`. `action` — слот под текстом, цвет содержимого `primary`.

### SuccessState

`title`, `message`, `icon = CheckCircle`, `action = null`. Тот же каркас, что у `EmptyState`, иконка `tertiary`. Lottie не требуется. `CompanionSuccessLottie` остаётся отдельной анимацией Android.

### ErrorState

`message`, `title = "Something went wrong"`, `icon = ErrorOutline`, `action = null`. Иконка объявляется как `title`. Круг: фон `error` 12%, иконка `error`.

Слот `action` лежит под текстом, не на красном круге. `LocalContentColor` внутри слота — `primary`. Кнопку в слот кладут как `Button`; её variant перебивает этот цвет своими цветами.

### OfflineStyleErrorState

`message`. Это `ErrorState` с иконкой `CloudOff` и title `"Could not load"`, не пустое состояние.

### SearchEmptyState

`EmptyState` с title `"No matches"` и иконкой `SearchOff`.

## Навигация

### NavigationBar

`NavTab(route, label, icon)`. `tabs`, `currentRoute`, `onTabSelected`, `searchTab = null`, `searchSelected = false`, `centerHighlightRoute = null`.

Выбранность вкладок только через `currentRoute`. У поиска отдельный `searchSelected`, потому что он вне списка `tabs`. Пилюля высотой 52, тень `Elevation.xl`, фон `surface`. Иконка 24, выбранная 28, цвет `primary` или `onSurfaceVariant` 45%. Подложка выбранной: `primary` 14%, у центральной — 22%. Круг поиска 56: не выбран `surface` / `onSurface` / тень `xl`; выбран `onSurface` / белая иконка / тень `lg`.

`NavigationRail` и drawer в kit нет: планшетный каркас задаёт приложение, контракт вкладки тот же (`id`, label, icon, selected).

## Поверхности, которые не меняли контракт

`Panel`: `showAccentStripe = false`, `accent: Color? = null`, `content`. Полоска 3dp есть, если задан `accent` (сплошной цвет) или `showAccentStripe` (градиент `Orange` → `NavLime`). Фон `surface`, форма `PanelShape`, тень `Elevation.md`, padding `Space.md`.

`ListCard`: фон `surface`, радиус 22, тень `Elevation.sm`.

`CompanionHomeWidgetPanel`: `title`, `content`. Верхний отступ 12 снят — расстояние задаёт родитель. Фон `companionSoftPanelColor()`, обводка `outline` 18%, радиус 20, padding 16, заголовок 17sp ExtraBold `onSurface`.

`ScreenBackground`: фон `background`.

`ListRow`: `title`, `subtitle`, `icon`, `titleColor = onSurface`, `showChevron = true`, `onClick`, `enabled = true`, `trailing`. `enabled = false` снимает клик. Padding 16×14.

`PickerRow`: галочка `Palette.Coral`. `DestructiveRow`: title `error`. `ListDivider`: `outline` 35%, отступ слева 52. `ListSectionTitle`: `labelMedium` SemiBold `onSurfaceVariant`.

`CompanionScaffold` принимает `bottomBar` и `fab`. `fab` стоит снизу-справа с отступом `Space.md`. Инсеты статус-бара, выреза и клавиатуры kit не снимает — их задаёт экран. На iOS тот же каркас называется layout страницы: колонка и `safeAreaInset` для `bottomBar`.

`ThemeOptionList` / `FontOptionList`: список в `ListCard`, выбранное — галочка `Palette.Coral`. Превью шрифта может брать `AppFontChoice.previewText`.

## Скелетоны, графики, домен

Без смены параметров относительно прошлого инвентаря.

`Skeleton(height = 16, width = null, baseColor = surfaceVariant)`. Плюс `ListSkeleton(rows = 6)`, `CardSkeleton`, страничные скелетоны (`HomeDashboard`, `Inventory`, `Watchlist`, `CategoryItems`, `Catalog`, `Leaderboard`, `ItemDetail`, `HomeStyleItemCard`).

`LineChart(values, pointCaptions = empty)` высота 200. `ColumnChart(values)` высота 168. `BarChart(bars, valueSuffix = "")` — трек `BorderSubtle`, заливка `Up`.

`Sparkline(values, height = 56, lineColor = Orange, showFill = true, showEndDot = true, showAxisTicks = false, smoothCurve = false, lineWidth = 2.5)`. Вторая перегрузка `Sparkline(points)` — высота 72, цвет `Up`.

`PageSkeleton`: `Generic`, `Home`, `Inventory`, `Leaderboard`, `ItemDetail`, `Catalog`, `Watchlist`.

Доменные, всё ещё в модуле: редкости (`RarityDot` 8dp, `RarityChip`, `RarityRow` + `RarityIndicatorStyle` `Dot` / `Chip` / `SideGutter`), `PriceTag` (`TacticalOrange`), `PriceTagText` (`secondary`), `CompanionItemValueText` (`valueColor = Gold`, compact 15sp / обычный 18sp), карточки предмета, `InventoryPrivacyIcon`, `formatItemUsd`, `resolveItemRarityColor`.

`CompanionLoadingLottie(size = 72)` бесконечно. `CompanionSuccessLottie(size = 88)` один раз.

## Превью

`Previews.kt`: `Button` на light/dark (четыре variant, disabled, loading) и `TextField` с ошибкой. Полной матрицы всех компонентов нет.

## Чего в kit нет

Эти вещи из аудита не добавлены: у них нет экрана, а пустая обёртка раздует API.

`NavigationRail`, drawer, `PullToRefresh`, карусель, sticky header, search с подсказками, rating, stepper, date/time picker. Верхние вкладки закрывает `Tabs`. Подтверждение — `Dialog`. Транзиентное сообщение — `Snackbar`. Подсказка — `Tooltip`. Меню — `Menu`. Картинка — `AsyncImage`. Фильтр — `Chip`. Счётчик — `CountBadge`. Inline-прогресс — `LinearProgress` / `CircularProgress`. Формы — `Switch`, `Checkbox`, `Radio`, `Slider`.
