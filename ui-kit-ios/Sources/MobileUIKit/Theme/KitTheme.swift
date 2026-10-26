import SwiftUI
import UIKit

public enum KitHex {
    public static func color(_ hex: String) -> Color {
        var raw = hex.trimmingCharacters(in: .whitespacesAndNewlines)
        if raw.hasPrefix("#") { raw.removeFirst() }
        var value: UInt64 = 0
        Scanner(string: raw).scanHexInt64(&value)
        let alpha, red, green, blue: Double
        if raw.count == 8 {
            alpha = Double((value & 0xFF00_0000) >> 24) / 255
            red = Double((value & 0x00FF_0000) >> 16) / 255
            green = Double((value & 0x0000_FF00) >> 8) / 255
            blue = Double(value & 0x0000_00FF) / 255
        } else {
            alpha = 1
            red = Double((value & 0xFF0000) >> 16) / 255
            green = Double((value & 0x00FF00) >> 8) / 255
            blue = Double(value & 0x0000FF) / 255
        }
        return Color(.sRGB, red: red, green: green, blue: blue, opacity: alpha)
    }
}

public enum KitThemeMode: String, CaseIterable, Identifiable {
    case system
    case light
    case dark

    public var id: String { rawValue }
}

private struct KitSchemeKey: EnvironmentKey {
    static let defaultValue = KitScheme.light
}

extension EnvironmentValues {
    public var kitScheme: KitScheme {
        get { self[KitSchemeKey.self] }
        set { self[KitSchemeKey.self] = newValue }
    }
}

public extension View {
    func kitTheme(_ mode: KitThemeMode = .system) -> some View {
        modifier(KitThemeModifier(mode: mode))
    }
}

private struct KitThemeModifier: ViewModifier {
    let mode: KitThemeMode
    @Environment(\.colorScheme) private var systemScheme

    private var resolved: ColorScheme {
        switch mode {
        case .system: systemScheme
        case .light: .light
        case .dark: .dark
        }
    }

    func body(content: Content) -> some View {
        content
            .environment(\.kitScheme, resolved == .dark ? .dark : .light)
            .preferredColorScheme(preferred)
    }

    private var preferred: ColorScheme? {
        switch mode {
        case .system: nil
        case .light: .light
        case .dark: .dark
        }
    }
}

public enum KitShadowLevel {
    case none, xs, sm, md, lg, xl
}

public extension View {
    func kitShadow(_ level: KitShadowLevel) -> some View {
        modifier(KitShadowModifier(level: level))
    }
}

private struct KitShadowModifier: ViewModifier {
    let level: KitShadowLevel
    @Environment(\.colorScheme) private var colorScheme

    func body(content: Content) -> some View {
        if level == .none {
            content
        } else {
            content.shadow(color: .black.opacity(alpha), radius: radius, x: 0, y: yOffset)
        }
    }

    private var dark: Bool { colorScheme == .dark }

    private var radius: CGFloat {
        switch level {
        case .none: 0
        case .xs: 2
        case .sm: 4
        case .md: 6
        case .lg: 8
        case .xl: 12
        }
    }

    private var yOffset: CGFloat {
        switch level {
        case .none: 0
        case .xs: 1
        case .sm: 2
        case .md: 3
        case .lg: 4
        case .xl: 6
        }
    }

    private var alpha: Double {
        switch level {
        case .none: 0
        case .xs: dark ? 0.04 : 0.08
        case .sm: dark ? 0.06 : 0.10
        case .md: dark ? 0.08 : 0.12
        case .lg: dark ? 0.10 : 0.16
        case .xl: dark ? 0.12 : 0.20
        }
    }
}

public enum KitAccessibility {
    public static var reduceMotion: Bool { UIAccessibility.isReduceMotionEnabled }
}
