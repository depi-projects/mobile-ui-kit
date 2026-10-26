import SwiftUI

public enum KitChipVariant {
    case filter
    case assist
}

/// Filter chip selects. Assist chip only acts. Selected filter has no checkmark.
public struct KitChip: View {
    private let label: String
    private let variant: KitChipVariant
    private let selected: Bool
    private let enabled: Bool
    private let action: () -> Void
    @Environment(\.kitScheme) private var scheme

    public init(
        _ label: String,
        variant: KitChipVariant = .filter,
        selected: Bool = false,
        enabled: Bool = true,
        action: @escaping () -> Void
    ) {
        self.label = label
        self.variant = variant
        self.selected = selected
        self.enabled = enabled
        self.action = action
    }

    public var body: some View {
        SwiftUI.Button(action: action) {
            Text(label)
                .font(KitFont.labelLarge)
                .foregroundStyle(foreground)
                .lineLimit(1)
                .padding(.horizontal, KitSpace.sm)
                .frame(maxWidth: .infinity, minHeight: 32)
                .background(background)
                .clipShape(Capsule())
        }
        .buttonStyle(.plain)
        .disabled(!enabled)
        .opacity(enabled ? 1 : KitDisabledAlpha.content)
    }

    private var foreground: Color {
        switch variant {
        case .filter:
            return selected ? scheme.onPrimary : scheme.onSurface
        case .assist:
            return scheme.onSurfaceVariant
        }
    }

    private var background: Color {
        switch variant {
        case .filter:
            return selected ? scheme.primary : scheme.surfaceVariant
        case .assist:
            return scheme.surfaceVariant
        }
    }
}
