# UI Catalog (Storybook-style)

## Run

Android Studio: open `mobile-ui-kit` → Run **catalog-android**.

```powershell
$env:JAVA_HOME="C:\Program Files\Microsoft\jdk-17.0.20.101-hotspot"
cd D:\MY-PROJECTS\mobile-ui-kit
.\gradlew.bat :catalog-android:installDebug
```

## Playground (toolbar tune icon)

| Control | Purpose |
|---------|---------|
| **Theme** System / Light / Dark | Catch contrast bugs |
| **Font scale** 100–150% | Accessibility / clipping |
| **Narrow 320dp** | Overflow on small phones |
| **Layout bounds** | Red frame around each story |
| **Disabled states** | Buttons / settings rows |

## Add a component / variant

1. Create or extend a file under `catalog-android/.../showcase/`.
2. Register stories in a `fun xxxStories(): List<CatalogStory>` returning `CatalogStory(id, title, …) { StoryFrame("Variant") { … } }`.
3. Wire the list in `CatalogRegistry.kt`.
4. Re-run catalog — no separate “story index” to maintain beyond the registry.

## Coverage

Stories are grouped: buttons, inputs, typography, navigation, alerts, cards, charts, skeletons, misc. Extend until every public component variant you care about has at least one `StoryFrame`.
