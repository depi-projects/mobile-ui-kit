import SwiftUI

public enum KitButtonVariant {
    case primary, secondary, tertiary, destructive
}

/// Wrapper over `SwiftUI.Button`. Screens use this instead of a raw button.
public struct KitButton<Label: View, Icon: View>: View {
    private let variant: KitButtonVariant
    private let enabled: Bool
    private let loading: Bool
    private let fillWidth: Bool
    private let action: () -> Void
    private let label: () -> Label
    private let leadingIcon: () -> Icon

    @Environment(\.kitScheme) private var scheme

    public init(
        variant: KitButtonVariant = .primary,
        enabled: Bool = true,
        loading: Bool = false,
        fillWidth: Bool = true,
        action: @escaping () -> Void,
        @ViewBuilder leadingIcon: @escaping () -> Icon,
        @ViewBuilder label: @escaping () -> Label
    ) {
        self.variant = variant
        self.enabled = enabled
        self.loading = loading
        self.fillWidth = fillWidth
        self.action = action
        self.leadingIcon = leadingIcon
        self.label = label
    }

    public var body: some View {
        SwiftUI.Button(action: action) {
            HStack(spacing: KitSpace.xs) {
                if loading {
                    ProgressView()
                        .progressViewStyle(.circular)
                        .controlSize(.small)
                        .tint(foreground)
                } else {
                    leadingIcon()
                    label()
                }
            }
            .font(KitFont.labelLarge)
            .frame(maxWidth: fillWidth ? .infinity : nil, minHeight: fillWidth ? KitSpace.button : 40)
            .padding(.horizontal, KitSpace.md)
            .foregroundStyle(shownForeground)
            .background(shownBackground)
            .clipShape(RoundedRectangle(cornerRadius: KitRadius.button, style: .continuous))
            .overlay {
                if variant == .secondary {
                    RoundedRectangle(cornerRadius: KitRadius.button, style: .continuous)
                        .stroke(scheme.outline.opacity(interactive ? 1 : KitDisabledAlpha.content), lineWidth: 1)
                }
            }
        }
        .buttonStyle(.plain)
        .disabled(!interactive)
    }

    private var interactive: Bool { enabled && !loading }

    private var foreground: Color {
        switch variant {
        case .primary: scheme.onPrimary
        case .secondary: scheme.onSurface
        case .tertiary: scheme.primary
        case .destructive: scheme.onError
        }
    }

    private var background: Color {
        switch variant {
        case .primary: scheme.primary
        case .secondary: scheme.surface
        case .tertiary: .clear
        case .destructive: scheme.error
        }
    }

    private var shownForeground: Color {
        interactive ? foreground : foreground.opacity(KitDisabledAlpha.content)
    }

    private var shownBackground: Color {
        if variant == .tertiary { return .clear }
        if interactive { return background }
        let disabledBase = variant == .secondary ? scheme.onSurface : background
        return disabledBase.opacity(KitDisabledAlpha.container)
    }
}

public extension KitButton where Icon == EmptyView {
    init(
        variant: KitButtonVariant = .primary,
        enabled: Bool = true,
        loading: Bool = false,
        fillWidth: Bool = true,
        action: @escaping () -> Void,
        @ViewBuilder label: @escaping () -> Label
    ) {
        self.init(
            variant: variant,
            enabled: enabled,
            loading: loading,
            fillWidth: fillWidth,
            action: action,
            leadingIcon: { EmptyView() },
            label: label
        )
    }
}

public struct KitSignOutButton: View {
    private let title: String
    private let enabled: Bool
    private let action: () -> Void
    @Environment(\.kitScheme) private var scheme

    public init(_ title: String, enabled: Bool = true, action: @escaping () -> Void) {
        self.title = title
        self.enabled = enabled
        self.action = action
    }

    public var body: some View {
        SwiftUI.Button(action: action) {
            Text(title)
                .font(KitFont.labelLarge)
                .frame(maxWidth: .infinity)
                .padding(.horizontal, KitSpace.lg)
                .padding(.vertical, KitSpace.md)
                .foregroundStyle(enabled ? scheme.onError : scheme.onError.opacity(KitDisabledAlpha.content))
                .background(enabled ? scheme.error : scheme.error.opacity(KitDisabledAlpha.container))
                .clipShape(RoundedRectangle(cornerRadius: 22, style: .continuous))
        }
        .buttonStyle(.plain)
        .disabled(!enabled)
        .kitShadow(.xs)
    }
}
