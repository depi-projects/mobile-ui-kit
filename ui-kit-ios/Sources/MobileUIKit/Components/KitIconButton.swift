import SwiftUI

/// Small icon button. Selected uses the orange mark, idle stays muted.
public struct KitIconButton: View {
    private let systemImage: String
    private let selected: Bool
    private let accessibilityLabel: String
    private let action: () -> Void
    @Environment(\.kitScheme) private var scheme

    public init(
        systemImage: String,
        selected: Bool = false,
        accessibilityLabel: String,
        action: @escaping () -> Void
    ) {
        self.systemImage = systemImage
        self.selected = selected
        self.accessibilityLabel = accessibilityLabel
        self.action = action
    }

    public var body: some View {
        SwiftUI.Button(action: action) {
            Image(systemName: systemImage)
                .font(.system(size: 18, weight: .semibold))
                .foregroundStyle(selected ? Palette.orange : scheme.onSurfaceVariant.opacity(0.35))
                .frame(width: 40, height: 40)
        }
        .buttonStyle(.plain)
        .accessibilityLabel(accessibilityLabel)
    }
}
