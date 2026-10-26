import SwiftUI

public struct KitPriceTag: View {
    private let amountUsd: Double
    private let partial: Bool

    public init(amountUsd: Double, partial: Bool = false) {
        self.amountUsd = amountUsd
        self.partial = partial
    }

    public var body: some View {
        Text(label)
            .font(KitFont.labelLarge)
            .foregroundStyle(Palette.tacticalOrange)
            .padding(.horizontal, 10)
            .padding(.vertical, 5)
            .background(Palette.tacticalOrange.opacity(0.22))
            .overlay {
                RoundedRectangle(cornerRadius: KitRadius.sm, style: .continuous)
                    .stroke(Palette.tacticalOrange.opacity(0.55), lineWidth: 1)
            }
            .clipShape(RoundedRectangle(cornerRadius: KitRadius.sm, style: .continuous))
    }

    private var label: String {
        var text = String(format: "$%.2f", amountUsd)
        if partial { text += " ~" }
        return text
    }
}

public struct KitPriceTagText: View {
    private let text: String
    @Environment(\.kitScheme) private var scheme

    public init(_ text: String) {
        self.text = text
    }

    public var body: some View {
        Text(text)
            .font(KitFont.labelMedium)
            .foregroundStyle(scheme.secondary)
            .padding(.horizontal, KitSpace.xs)
            .padding(.vertical, KitSpace.xxs)
            .background(scheme.secondaryContainer.opacity(0.35))
            .clipShape(RoundedRectangle(cornerRadius: KitRadius.sm, style: .continuous))
    }
}

public struct KitRarityChip: View {
    private let label: String
    private let hex: String?

    public init(label: String, hex: String?) {
        self.label = label
        self.hex = hex
    }

    public var body: some View {
        if let color = Self.color(hex), !label.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
            Text(label)
                .font(KitFontFace.font(size: 11, style: "semibold"))
                .foregroundStyle(color.opacity(0.95))
                .lineLimit(1)
                .padding(.horizontal, 8)
                .padding(.vertical, 3)
                .background(color.opacity(0.18))
                .clipShape(RoundedRectangle(cornerRadius: 8, style: .continuous))
        }
    }

    public static func color(_ hex: String?) -> Color? {
        guard let hex, !hex.isEmpty else { return nil }
        let raw = hex.hasPrefix("#") ? hex : "#\(hex)"
        return KitHex.color(raw)
    }
}

public struct KitCatalogItemCard: View {
    private let title: String
    private let imageURL: URL?
    private let rarityHex: String?
    private let rarityName: String?
    private let subtitle: String?
    private let priceText: String?
    @Environment(\.kitScheme) private var scheme

    public init(
        title: String,
        imageURL: URL? = nil,
        rarityHex: String? = nil,
        rarityName: String? = nil,
        subtitle: String? = nil,
        priceText: String? = nil
    ) {
        self.title = title
        self.imageURL = imageURL
        self.rarityHex = rarityHex
        self.rarityName = rarityName
        self.subtitle = subtitle
        self.priceText = priceText
    }

    public var body: some View {
        VStack(spacing: 0) {
                accent.frame(height: 3)
                HStack(spacing: KitSpace.sm) {
                    KitAsyncImage(url: imageURL, fit: .fit, contentDescription: title)
                        .frame(width: 76, height: 68)
                        .background(scheme.surfaceVariant)
                        .clipShape(RoundedRectangle(cornerRadius: 10, style: .continuous))
                    VStack(alignment: .leading, spacing: KitSpace.xxs) {
                        Text(title)
                            .font(KitFont.titleSmall)
                            .foregroundStyle(scheme.onSurface)
                            .multilineTextAlignment(.leading)
                            .lineLimit(2)
                        if showsRarityChip, let rarityName {
                            KitRarityChip(label: rarityName, hex: rarityHex)
                        }
                        if let subtitle, !subtitle.isEmpty, subtitle != rarityName {
                            Text(subtitle)
                                .font(KitFont.bodySmall)
                                .foregroundStyle(scheme.onSurfaceVariant)
                                .lineLimit(1)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    if let priceText, !priceText.isEmpty {
                        KitPriceTagText(priceText)
                    }
                }
                .padding(KitSpace.sm)
                .frame(minHeight: 92)
            }
            .background(scheme.surface)
            .clipShape(RoundedRectangle(cornerRadius: KitRadius.panel, style: .continuous))
            .kitShadow(.md)
    }

    private var showsRarityChip: Bool {
        guard let rarityName, !rarityName.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty else { return false }
        return KitRarityChip.color(rarityHex) != nil
    }

    private var accent: Color {
        KitRarityChip.color(rarityHex) ?? scheme.primary
    }
}

public struct KitCategoryTile: View {
    private let title: String
    private let detail: String?
    private let imageURL: URL?
    private let enabled: Bool
    @Environment(\.kitScheme) private var scheme

    public init(
        title: String,
        detail: String? = nil,
        imageURL: URL? = nil,
        enabled: Bool = true
    ) {
        self.title = title
        self.detail = detail
        self.imageURL = imageURL
        self.enabled = enabled
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.xs) {
                KitAsyncImage(url: imageURL, fit: .fit, contentDescription: title)
                    .frame(maxWidth: .infinity)
                    .frame(height: 72)
                    .background(scheme.surfaceVariant)
                    .clipShape(RoundedRectangle(cornerRadius: KitRadius.sm, style: .continuous))
                Text(title)
                    .font(KitFont.titleSmall)
                    .foregroundStyle(scheme.onSurface)
                    .lineLimit(2)
                    .multilineTextAlignment(.leading)
                if let detail, !detail.isEmpty {
                    Text(detail)
                        .font(KitFont.bodySmall)
                        .foregroundStyle(scheme.onSurfaceVariant)
                        .lineLimit(2)
                }
            }
            .padding(KitSpace.sm)
            .frame(maxWidth: .infinity, minHeight: 148, alignment: .topLeading)
            .background(scheme.surface)
            .clipShape(RoundedRectangle(cornerRadius: KitRadius.panel, style: .continuous))
            .kitShadow(.sm)
            .opacity(enabled ? 1 : KitDisabledAlpha.content)
    }
}
