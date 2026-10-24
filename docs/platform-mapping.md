# Соответствие платформ

Числа берутся из `tokens/tokens.json`. Этот файл говорит, во что они превращаются на SwiftUI. Kotlin остаётся ручным, пока генератор не подключён к Gradle. Swift-константы вручную не пишут.

## Цвет

`inverseSurface`, `inverseOnSurface`, `inversePrimary` заданы в обеих схемах. Snackbar не берёт инверсию, которую посчитал Material.

`parseHexColor`: 6 знаков — `#RRGGBB`, альфа FF. 8 знаков — `#AARRGGBB`. `Palette.GlowOrange` — `#33E09805`.

Тёмная тема на iOS: `colorScheme == .dark`. Android-хелпер `isDarkAppearance()` смотрит яркость `surface` (`luminance() < 0.5`) и нужен только там, где поверхность не совпадает с системной темой.

Роли схемы — состояния интерфейса. `Palette.Up` и `Palette.Down` — данные и финансы. `Palette.Down` не подменяет `ColorScheme.error`.

## Elevation → тень

`Elevation` — dp подъёма. На SwiftUI это `.shadow`. В тёмной теме тень слабее: альфа из колонки Dark.

| Токен | dp | Light: radius, y, alpha | Dark: radius, y, alpha |
| --- | --- | --- | --- |
| `none` | 0 | нет тени | нет тени |
| `xs` | 2 | 2, 1, 8% | 2, 1, 4% |
| `sm` | 4 | 4, 2, 10% | 4, 2, 6% |
| `md` | 6 | 6, 3, 12% | 6, 3, 8% |
| `lg` | 8 | 8, 4, 16% | 8, 4, 10% |
| `xl` | 12 | 12, 6, 20% | 12, 6, 12% |

Цвет тени — чёрный. `Fab` использует `md`.

## Motion → Animation

`FastOutSlowInEasing` — `cubic-bezier(0.4, 0.0, 0.2, 1.0)`.

На SwiftUI: `.timingCurve(0.4, 0, 0.2, 1, duration:)`.

| Токен | Длительность |
| --- | --- |
| `durationFast` | 0.16 с |
| `durationMedium` | 0.28 с |
| `durationNav` | 0.34 с |
| `durationExit` | 0.22 с |

`Spring.StiffnessMediumLow` = 200, damping ratio = 1 (без отскока). На SwiftUI: `.interpolatingSpring(mass: 1, stiffness: 200, damping: 28.3)`. 28.3 — это `2 * sqrt(200)` для критического затухания.

`LocalReduceMotion` на Android: `Settings.Global.ANIMATOR_DURATION_SCALE == 0`. На iOS: `UIAccessibility.isReduceMotionEnabled`. При флаге переходы навигации длятся 0. `Snackbar`, `Dialog` и `BottomSheet` флаг сами не читают: если их анимацию нужно гасить, экран оборачивает их тем же флагом.

`maxFontScale = 2` — множитель к размеру по умолчанию, не case Dynamic Type. `DynamicTypeSize.xxxLarge` около 1.35× и сюда не подходит. На iOS масштаб режется: `min(systemScale, 2)`.

## Формы

`Radius.sm/md/lg/xl` = 12/16/20/28. `Shapes` и `ButtonShape` / `PanelShape` — `RoundedRectangle` с тем же радиусом. Отступы в контракте — `start`/`end`, не `left`/`right`. Иконка Back в RTL зеркалится.

## Картинки

Android: Coil. iOS: Kingfisher, дисковый кеш включён. Слоты те же: placeholder, error, `onLoading` / `onSuccess` / `onError`, `ImageFit.Crop` или `Fit`. Системный SwiftUI `AsyncImage` не используется: у него нет дискового кеша.

## Snackbar

Одна запись на экране. Следующая ждёт закрытия и не заменяет текущую.

| `SnackbarDuration` | Поведение |
| --- | --- |
| `Short` | 4 с |
| `Long` | 10 с |
| `Indefinite` | пока не нажмут action или dismiss |

## Инсеты

Kit не снимает status bar, cutout, home indicator и клавиатуру. Экран добавляет safe area и отступ под IME. `CompanionScaffold` на iOS — колонка плюс `.safeAreaInset` для `bottomBar`. Имя Scaffold на iOS не копируется.

## Навигация

| Функция | Движение | iOS |
| --- | --- | --- |
| `detailEnter` | opacity + сдвиг на 1/4 ширины справа, spring stiffness 200 | `.opacity` + `.offset` на четверть ширины, `.interpolatingSpring(mass: 1, stiffness: 200, damping: 28.3)` |
| `detailExit` | opacity + сдвиг на −1/10 ширины, 0.22 с | `.opacity` + `.offset`, `.timingCurve` 0.22 с |
| `detailPopEnter` | opacity + сдвиг на −1/5 ширины, 0.34 с | то же кривой 0.34 с |
| `detailPopExit` | opacity 0.22 с + сдвиг на 1/4 ширины за 0.34 с | два совмещённых перехода |
| `tabEnter` | opacity 0.28 с + scale с 0.96 за 0.34 с | `.opacity` + `.scale(0.96)` |
| `tabExit` | opacity + scale до 0.98 за 0.16 с | `.opacity` + `.scale(0.98)` |

При reduced motion все шесть длятся 0. Тип `EnterTransition` на iOS не переносится.

## Доступность форм

`Switch`, `Checkbox`, `Radio` с `label` — один узел: роль и подпись вместе. `Tabs` помечает выбранную вкладку как selected. `NavigationBar` должен делать то же для выбранного пункта; системный `TabView` это даёт сам, кастомный бар — нет, его помечает реализация.

Строки вроде `"Loading…"`, `"No matches"`, `"Could not load"` пока зашиты по-английски. Это не локализованный контракт.
