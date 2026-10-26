import SwiftUI

public struct KitListRow: View {
    private let title: String
    private let subtitle: String?
    private let badge: String?
    private let showChevron: Bool
    private let action: (() -> Void)?
    @Environment(\.kitScheme) private var scheme

    public init(
        title: String,
        subtitle: String? = nil,
        badge: String? = nil,
        showChevron: Bool = true,
        action: (() -> Void)? = nil
    ) {
        self.title = title
        self.subtitle = subtitle
        self.badge = badge
        self.showChevron = showChevron
        self.action = action
    }

    public var body: some View {
        if let action {
            SwiftUI.Button(action: action) { row }
                .buttonStyle(.plain)
        } else {
            row
        }
    }

    private var row: some View {
        HStack(spacing: KitSpace.sm) {
                VStack(alignment: .leading, spacing: KitSpace.xxs) {
                    Text(title)
                        .font(KitFont.bodyLarge)
                        .foregroundStyle(scheme.onSurface)
                        .multilineTextAlignment(.leading)
                    if let subtitle, !subtitle.isEmpty {
                        Text(subtitle)
                            .font(KitFont.bodySmall)
                            .foregroundStyle(scheme.onSurfaceVariant)
                            .multilineTextAlignment(.leading)
                    }
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                if let badge, !badge.isEmpty {
                    Text(badge)
                        .font(KitFont.labelSmall)
                        .foregroundStyle(Color.white)
                        .padding(.horizontal, 6)
                        .padding(.vertical, 2)
                        .background(Palette.coral)
                        .clipShape(Capsule())
                }
                if showChevron {
                    Image(systemName: "chevron.right")
                        .font(.system(size: 13, weight: .semibold))
                        .foregroundStyle(scheme.onSurfaceVariant)
                }
            }
            .padding(.horizontal, KitSpace.md)
            .padding(.vertical, 14)
            .background(scheme.surface)
    }
}

public struct KitSectionTitle: View {
    private let title: String
    @Environment(\.kitScheme) private var scheme

    public init(_ title: String) {
        self.title = title
    }

    public var body: some View {
        Text(title)
            .font(KitFont.labelMedium)
            .foregroundStyle(scheme.onSurfaceVariant)
            .frame(maxWidth: .infinity, alignment: .leading)
    }
}
