import SwiftUI

public enum KitButtonVariant {
    case primary, secondary, tertiary, destructive
}

public struct KitColors {
    public static let primary = Color(red: 0.11, green: 0.11, blue: 0.12)
    public static let onPrimary = Color.white
    public static let secondary = Color(red: 0.9, green: 0.53, blue: 0)
    public static let surface = Color.white
    public static let onSurface = Color(red: 0.09, green: 0.09, blue: 0.11)
    public static let onSurfaceVariant = Color(red: 0.42, green: 0.45, blue: 0.5)
    public static let error = Color(red: 0.85, green: 0.18, blue: 0.29)
    public static let outline = Color(red: 0.89, green: 0.9, blue: 0.93)
}

public struct KitSpace {
    public static let xs: CGFloat = 4
    public static let sm: CGFloat = 8
    public static let md: CGFloat = 12
    public static let lg: CGFloat = 16
    public static let buttonHeight: CGFloat = 48
}

public struct KitRadius {
    public static let md: CGFloat = 12
    public static let lg: CGFloat = 16
    public static let xl: CGFloat = 20
}
