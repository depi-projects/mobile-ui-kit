# mobile-ui-kit

Shared Jetpack Compose UI (extracted from Steam CS2 `designsystem`). Consumed by apps via Gradle **composite build** (`includeBuild`).

## JDK / Gradle sync (Android Studio)

Modules use **Java 17** (`jvmToolchain(17)`). If sync fails with *Cannot find a Java installation … languageVersion=17*:

1. **Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK** → **17** (e.g. Microsoft OpenJDK 17), **not** Embedded JBR 21/25 only.
2. **Sync** again — the project also enables [Foojay](https://github.com/gradle/foojay-toolchains) to auto-download JDK 17 (`org.gradle.java.installations.auto-download=true`).
3. Or in user `~/.gradle/gradle.properties`:  
   `org.gradle.java.home=C\:\\Program Files\\Microsoft\\jdk-17.0.20.101-hotspot`

## Modules

| Module | Role |
|--------|------|
| `ui-kit-android` | Compose + Material 3 wrappers (`Button`, `Panel`, `TextField`, …) |
| `catalog-android` | Debug app to preview components |

Maven coordinates for substitution: **`ru.mobile:ui-kit-android`**

## Local dev (with an app)

In the app’s `local.properties`:

```properties
uiKit.dir=D\:\\MY-PROJECTS\\mobile-ui-kit
```

Pull latest kit → Gradle sync in the app → rebuild. No submodule SHA.

## UI catalog (Storybook-style)

App module **`catalog-android`** — live gallery of components and variants.

- **Sections** — buttons, inputs, typography, navigation, alerts, cards, charts, skeletons, …
- **Playground** (toolbar tune icon) — light / dark, font scale 100–150%, narrow 320dp, layout bounds, disabled states
- One theme for every app. App-specific looks are **variants** in the kit, not a second brand.
Run on emulator/device:

```bash
./gradlew :catalog-android:installDebug
```

Or **Run → catalog-android** in Android Studio (module `mobile-ui-kit`).

## Source of truth

Инвентарь публичного API (параметры, variants, размеры, цвета): [docs/design-system.md](docs/design-system.md).

Префикс `Cs2` с публичного API снят. Имена `Companion*` ещё остаются. Оригинал в `STEAM_APPS` не менялся.
