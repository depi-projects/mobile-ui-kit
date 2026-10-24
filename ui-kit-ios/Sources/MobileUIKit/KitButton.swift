import SwiftUI

public struct KitButton<Label: View>: View {
    let variant: KitButtonVariant
    let action: () -> Void
    @ViewBuilder let label: () -> Label

    public init(variant: KitButtonVariant = .primary, action: @escaping () -> Void, @ViewBuilder label: @escaping () -> Label) {
        self.variant = variant
        self.action = action
        self.label = label
    }

    public var body: some View {
        Button(action: action) {
            label()
                .font(.body.weight(.semibold))
                .frame(maxWidth: .infinity, minHeight: KitSpace.buttonHeight)
                .padding(.horizontal, KitSpace.lg)
                .foregroundStyle(foreground)
                .background(background)
                .clipShape(RoundedRectangle(cornerRadius: KitRadius.lg, style: .continuous))
                .overlay {
                    if variant == .secondary || variant == .tertiary {
                        RoundedRectangle(cornerRadius: KitRadius.lg, style: .continuous)
                            .stroke(KitColors.outline, lineWidth: 1)
                    }
                }
        }
        .buttonStyle(.plain)
    }

    private var background: Color {
        switch variant {
        case .primary: KitColors.primary
        case .secondary: KitColors.surface
        case .tertiary: Color.clear
        case .destructive: KitColors.error
        }
    }

    private var foreground: Color {
        switch variant {
        case .primary, .destructive: KitColors.onPrimary
        case .secondary, .tertiary: KitColors.onSurface
        }
    }
}
