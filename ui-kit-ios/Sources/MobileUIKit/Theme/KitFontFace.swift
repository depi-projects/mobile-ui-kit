import CoreText
import SwiftUI
import UIKit

/// Families from the design system. `Manrope` is the default.
public enum KitFontChoice: String, CaseIterable, Identifiable {
    case manrope
    case inter
    case onest
    case montserrat
    case ibmPlexSans = "ibm_plex_sans"
    case spaceGrotesk = "space_grotesk"
    case sourceSans3 = "source_sans_3"
    case satoshi

    public var id: String { rawValue }

    public static let previewText = "The quick brown fox"

    public static var current: KitFontChoice = .manrope

    public static func apply(_ choice: KitFontChoice) {
        current = choice
    }

    public var displayName: String {
        switch self {
        case .manrope: "Manrope"
        case .inter: "Inter"
        case .onest: "Onest"
        case .montserrat: "Montserrat"
        case .ibmPlexSans: "IBM Plex Sans"
        case .spaceGrotesk: "Space Grotesk"
        case .sourceSans3: "Source Sans 3"
        case .satoshi: "Satoshi"
        }
    }

    fileprivate var variableFace: String? {
        switch self {
        case .manrope: nil
        case .inter: "Inter-Regular"
        case .onest: "Onest-Regular"
        case .montserrat: "Montserrat-Thin"
        case .ibmPlexSans: "IBMPlexSans-Regular"
        case .spaceGrotesk: "SpaceGrotesk-Light"
        case .sourceSans3: "SourceSans3-Roman"
        case .satoshi: "SatoshiVariable-Bold"
        }
    }

    fileprivate var weightRange: ClosedRange<CGFloat> {
        switch self {
        case .spaceGrotesk: 300...700
        case .satoshi: 300...900
        case .sourceSans3: 200...900
        case .ibmPlexSans: 100...700
        default: 100...900
        }
    }
}

public enum KitFontFace {
    public static func font(size: CGFloat, style: String) -> Font {
        font(size: size, style: style, choice: KitFontChoice.current)
    }

    public static func font(size: CGFloat, style: String, choice: KitFontChoice) -> Font {
        registerIfNeeded()
        if choice.variableFace == nil {
            return Font.custom(manropeName(style), size: size)
        }
        let weight = styleWeight(style).clamped(to: choice.weightRange)
        return variableFont(name: choice.variableFace ?? "Inter-Regular", size: size, weight: weight)
    }

    private static var registered = false

    private static func registerIfNeeded() {
        guard !registered else { return }
        registered = true
        let files = [
            "manrope_regular", "manrope_medium", "manrope_semibold", "manrope_bold",
            "inter_var", "onest_var", "montserrat_var", "ibmplexsans_var",
            "space_grotesk_var", "sourcesans3_var", "satoshi_var",
        ]
        for file in files {
            guard let url = Bundle.module.url(forResource: file, withExtension: "ttf") else { continue }
            CTFontManagerRegisterFontsForURL(url as CFURL, .process, nil)
        }
    }

    private static func manropeName(_ style: String) -> String {
        switch style {
        case "bold": "Manrope-Bold"
        case "semibold": "Manrope-SemiBold"
        case "medium": "Manrope-Medium"
        default: "Manrope-Regular"
        }
    }

    private static func styleWeight(_ style: String) -> CGFloat {
        switch style {
        case "bold": 700
        case "semibold": 600
        case "medium": 500
        default: 400
        }
    }

    private static func variableFont(name: String, size: CGFloat, weight: CGFloat) -> Font {
        let base = CTFontCreateWithName(name as CFString, size, nil)
        let variations = NSDictionary(
            object: NSNumber(value: Float(weight)),
            forKey: NSNumber(value: 2003265652)
        )
        let attributes = NSDictionary(object: variations, forKey: kCTFontVariationAttribute as NSString)
        let varied = CTFontCreateCopyWithAttributes(base, size, nil, attributes)
        return Font(varied as UIFont)
    }
}

public extension View {
    /// Applies the kit family. Call it on the root so every `KitFont` role picks it up.
    func kitFont(_ choice: KitFontChoice) -> some View {
        KitFontChoice.apply(choice)
        return self
    }
}

private extension CGFloat {
    func clamped(to range: ClosedRange<CGFloat>) -> CGFloat {
        Swift.min(Swift.max(self, range.lowerBound), range.upperBound)
    }
}
