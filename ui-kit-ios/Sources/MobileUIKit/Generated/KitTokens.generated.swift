// Generated from tokens/tokens.json by scripts/generate-swift-tokens.mjs.
// Do not edit. Re-run the script after token changes.
import SwiftUI


public struct KitScheme {
    public let background: Color
    public let error: Color
    public let errorContainer: Color
    public let inverseOnSurface: Color
    public let inversePrimary: Color
    public let inverseSurface: Color
    public let onBackground: Color
    public let onError: Color
    public let onErrorContainer: Color
    public let onPrimary: Color
    public let onPrimaryContainer: Color
    public let onSecondary: Color
    public let onSecondaryContainer: Color
    public let onSurface: Color
    public let onSurfaceVariant: Color
    public let onTertiary: Color
    public let onTertiaryContainer: Color
    public let outline: Color
    public let primary: Color
    public let primaryContainer: Color
    public let secondary: Color
    public let secondaryContainer: Color
    public let surface: Color
    public let surfaceContainer: Color
    public let surfaceContainerHigh: Color
    public let surfaceContainerHighest: Color
    public let surfaceContainerLow: Color
    public let surfaceContainerLowest: Color
    public let surfaceVariant: Color
    public let tertiary: Color
    public let tertiaryContainer: Color

    public init(
        background: Color,
        error: Color,
        errorContainer: Color,
        inverseOnSurface: Color,
        inversePrimary: Color,
        inverseSurface: Color,
        onBackground: Color,
        onError: Color,
        onErrorContainer: Color,
        onPrimary: Color,
        onPrimaryContainer: Color,
        onSecondary: Color,
        onSecondaryContainer: Color,
        onSurface: Color,
        onSurfaceVariant: Color,
        onTertiary: Color,
        onTertiaryContainer: Color,
        outline: Color,
        primary: Color,
        primaryContainer: Color,
        secondary: Color,
        secondaryContainer: Color,
        surface: Color,
        surfaceContainer: Color,
        surfaceContainerHigh: Color,
        surfaceContainerHighest: Color,
        surfaceContainerLow: Color,
        surfaceContainerLowest: Color,
        surfaceVariant: Color,
        tertiary: Color,
        tertiaryContainer: Color
    ) {
        self.background = background
        self.error = error
        self.errorContainer = errorContainer
        self.inverseOnSurface = inverseOnSurface
        self.inversePrimary = inversePrimary
        self.inverseSurface = inverseSurface
        self.onBackground = onBackground
        self.onError = onError
        self.onErrorContainer = onErrorContainer
        self.onPrimary = onPrimary
        self.onPrimaryContainer = onPrimaryContainer
        self.onSecondary = onSecondary
        self.onSecondaryContainer = onSecondaryContainer
        self.onSurface = onSurface
        self.onSurfaceVariant = onSurfaceVariant
        self.onTertiary = onTertiary
        self.onTertiaryContainer = onTertiaryContainer
        self.outline = outline
        self.primary = primary
        self.primaryContainer = primaryContainer
        self.secondary = secondary
        self.secondaryContainer = secondaryContainer
        self.surface = surface
        self.surfaceContainer = surfaceContainer
        self.surfaceContainerHigh = surfaceContainerHigh
        self.surfaceContainerHighest = surfaceContainerHighest
        self.surfaceContainerLow = surfaceContainerLow
        self.surfaceContainerLowest = surfaceContainerLowest
        self.surfaceVariant = surfaceVariant
        self.tertiary = tertiary
        self.tertiaryContainer = tertiaryContainer
    }

    public static let light = KitScheme(
        background: KitHex.color("#FFFFFF"),
        error: KitHex.color("#D92D4A"),
        errorContainer: KitHex.color("#FDE8EC"),
        inverseOnSurface: KitHex.color("#F5F5F7"),
        inversePrimary: KitHex.color("#4285F4"),
        inverseSurface: KitHex.color("#1E2024"),
        onBackground: KitHex.color("#16181D"),
        onError: KitHex.color("#FFFFFF"),
        onErrorContainer: KitHex.color("#8C1024"),
        onPrimary: KitHex.color("#FFFFFF"),
        onPrimaryContainer: KitHex.color("#174EA6"),
        onSecondary: KitHex.color("#FFFFFF"),
        onSecondaryContainer: KitHex.color("#5C3200"),
        onSurface: KitHex.color("#16181D"),
        onSurfaceVariant: KitHex.color("#6B7280"),
        onTertiary: KitHex.color("#FFFFFF"),
        onTertiaryContainer: KitHex.color("#0D3D28"),
        outline: KitHex.color("#E3E6EE"),
        primary: KitHex.color("#1A73E8"),
        primaryContainer: KitHex.color("#E8F0FE"),
        secondary: KitHex.color("#E68600"),
        secondaryContainer: KitHex.color("#FFF1DE"),
        surface: KitHex.color("#FFFFFF"),
        surfaceContainer: KitHex.color("#F3F3F5"),
        surfaceContainerHigh: KitHex.color("#EEEEF0"),
        surfaceContainerHighest: KitHex.color("#E8E8EB"),
        surfaceContainerLow: KitHex.color("#F7F7F8"),
        surfaceContainerLowest: KitHex.color("#FFFFFF"),
        surfaceVariant: KitHex.color("#F5F5F7"),
        tertiary: KitHex.color("#12A56A"),
        tertiaryContainer: KitHex.color("#DDF6EA")
    )

    public static let dark = KitScheme(
        background: KitHex.color("#1E2024"),
        error: KitHex.color("#FF6B7A"),
        errorContainer: KitHex.color("#4A1820"),
        inverseOnSurface: KitHex.color("#1E2024"),
        inversePrimary: KitHex.color("#1A73E8"),
        inverseSurface: KitHex.color("#F5F5F7"),
        onBackground: KitHex.color("#F5F5F7"),
        onError: KitHex.color("#2A0A0E"),
        onErrorContainer: KitHex.color("#FFD5DA"),
        onPrimary: KitHex.color("#062E6F"),
        onPrimaryContainer: KitHex.color("#D3E3FD"),
        onSecondary: KitHex.color("#FFFFFF"),
        onSecondaryContainer: KitHex.color("#FFD9A8"),
        onSurface: KitHex.color("#F5F5F7"),
        onSurfaceVariant: KitHex.color("#98989D"),
        onTertiary: KitHex.color("#062117"),
        onTertiaryContainer: KitHex.color("#D4F5E4"),
        outline: KitHex.color("#454850"),
        primary: KitHex.color("#AECBFA"),
        primaryContainer: KitHex.color("#004A77"),
        secondary: KitHex.color("#E68600"),
        secondaryContainer: KitHex.color("#3D2800"),
        surface: KitHex.color("#272A30"),
        surfaceContainer: KitHex.color("#272A30"),
        surfaceContainerHigh: KitHex.color("#32353C"),
        surfaceContainerHighest: KitHex.color("#3A3D44"),
        surfaceContainerLow: KitHex.color("#272A30"),
        surfaceContainerLowest: KitHex.color("#1E2024"),
        surfaceVariant: KitHex.color("#2E3138"),
        tertiary: KitHex.color("#12A56A"),
        tertiaryContainer: KitHex.color("#123828")
    )
}

public enum Palette {
    public static let navy = KitHex.color("#1B2838")
    public static let navyLight = KitHex.color("#2A475E")
    public static let charcoal = KitHex.color("#0E1419")
    public static let charcoalElevated = KitHex.color("#171D25")
    public static let charcoalPanel = KitHex.color("#1E252D")
    public static let coral = KitHex.color("#FF4D67")
    public static let tacticalOrange = KitHex.color("#FF4D67")
    public static let tacticalOrangeDim = KitHex.color("#E68600")
    public static let coralChip = KitHex.color("#FFE8EC")
    public static let mint = KitHex.color("#D4F5E4")
    public static let mintInk = KitHex.color("#0D3D28")
    public static let navLime = KitHex.color("#CBF165")
    public static let navLimeInk = KitHex.color("#1A2E0A")
    public static let navLimeChip = KitHex.color("#EEF6D4")
    public static let up = KitHex.color("#12A56A")
    public static let down = KitHex.color("#E11D48")
    public static let orange = KitHex.color("#E68600")
    public static let accentBlue = KitHex.color("#6EA8FF")
    public static let accentBlueDim = KitHex.color("#4A8FCC")
    public static let successGreen = KitHex.color("#12A56A")
    public static let errorRed = KitHex.color("#E11D48")
    public static let textMuted = KitHex.color("#8F98A0")
    public static let itemTypeMuted = KitHex.color("#7B8FA3")
    public static let borderSubtle = KitHex.color("#3D4450")
    public static let glowOrange = KitHex.color("#33E09805")
    public static let rarityUnknown = KitHex.color("#8B93A7")
    public static let gold = KitHex.color("#C9A227")
    public static let goldBright = KitHex.color("#E8C547")
    public static let goldDeep = KitHex.color("#9A7B0A")
    public static let goldFill = KitHex.color("#F5E6B8")
}

public enum KitSpace {
    public static let xxs: CGFloat = 4
    public static let xs: CGFloat = 8
    public static let sm: CGFloat = 12
    public static let md: CGFloat = 16
    public static let lg: CGFloat = 20
    public static let xl: CGFloat = 24
    public static let xxl: CGFloat = 32
    public static let screen: CGFloat = 20
    public static let button: CGFloat = 56
}

public enum KitRadius {
    public static let sm: CGFloat = 12
    public static let md: CGFloat = 16
    public static let lg: CGFloat = 20
    public static let xl: CGFloat = 28

    public static let panel: CGFloat = lg
    public static let button: CGFloat = md
}

public enum KitElevation {
    public static let none: CGFloat = 0
    public static let xs: CGFloat = 2
    public static let sm: CGFloat = 4
    public static let md: CGFloat = 6
    public static let lg: CGFloat = 8
    public static let xl: CGFloat = 12
}

public enum KitFont {
    public static var displayLarge: Font { KitFontFace.font(size: 57, style: "bold") }
    public static var displayMedium: Font { KitFontFace.font(size: 45, style: "bold") }
    public static var displaySmall: Font { KitFontFace.font(size: 36, style: "bold") }
    public static var headlineLarge: Font { KitFontFace.font(size: 40, style: "bold") }
    public static var headlineMedium: Font { KitFontFace.font(size: 24, style: "semibold") }
    public static var headlineSmall: Font { KitFontFace.font(size: 28, style: "bold") }
    public static var titleLarge: Font { KitFontFace.font(size: 22, style: "bold") }
    public static var titleMedium: Font { KitFontFace.font(size: 16, style: "bold") }
    public static var titleSmall: Font { KitFontFace.font(size: 14, style: "medium") }
    public static var bodyLarge: Font { KitFontFace.font(size: 16, style: "regular") }
    public static var bodyMedium: Font { KitFontFace.font(size: 14, style: "regular") }
    public static var bodySmall: Font { KitFontFace.font(size: 12, style: "regular") }
    public static var labelLarge: Font { KitFontFace.font(size: 14, style: "semibold") }
    public static var labelMedium: Font { KitFontFace.font(size: 12, style: "medium") }
    public static var labelSmall: Font { KitFontFace.font(size: 11, style: "medium") }
}

public enum KitMotion {
    public static let durationFast: Double = 0.16
    public static let durationMedium: Double = 0.28
    public static let durationNav: Double = 0.34
    public static let durationExit: Double = 0.22
    public static let springStiffness: Double = 200
    public static let springDamping: Double = 28.3
}

public enum KitDisabledAlpha {
    public static let container: Double = 0.12
    public static let content: Double = 0.38
}

public enum KitLimits {
    public static let maxFontScale: CGFloat = 2
}
