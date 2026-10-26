import SwiftUI

/// Wrapper over `ShareLink`. Sends plain text through the system share sheet.
public struct KitShareLink: View {
    private let title: String
    private let message: String
    private let variant: KitButtonVariant
    @Environment(\.kitScheme) private var scheme

    public init(title: String, message: String, variant: KitButtonVariant = .secondary) {
        self.title = title
        self.message = message
        self.variant = variant
    }

    public var body: some View {
        ShareLink(item: message, subject: Text(title)) {
            Text(title)
                .font(KitFont.labelLarge)
                .foregroundStyle(foreground)
                .frame(maxWidth: .infinity)
                .padding(.horizontal, KitSpace.lg)
                .padding(.vertical, KitSpace.md)
                .background(background)
                .clipShape(RoundedRectangle(cornerRadius: KitRadius.button, style: .continuous))
                .overlay {
                    if variant == .secondary {
                        RoundedRectangle(cornerRadius: KitRadius.button, style: .continuous)
                            .stroke(scheme.outline, lineWidth: 1)
                    }
                }
        }
        .buttonStyle(.plain)
    }

    private var foreground: Color {
        switch variant {
        case .primary: scheme.onPrimary
        case .secondary, .tertiary: scheme.primary
        case .destructive: scheme.onError
        }
    }

    private var background: Color {
        switch variant {
        case .primary: scheme.primary
        case .secondary, .tertiary: .clear
        case .destructive: scheme.error
        }
    }
}
