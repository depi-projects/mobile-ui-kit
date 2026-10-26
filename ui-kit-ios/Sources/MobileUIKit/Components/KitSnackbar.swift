import SwiftUI

public enum KitSnackbarVariant {
    case `default`
    case success
    case error
}

public enum KitSnackbarDuration {
    case short
    case long
    case indefinite
}

/// One transient message. The next one waits until this one closes.
public struct KitSnackbarHost: View {
    private let message: String?
    private let variant: KitSnackbarVariant
    private let actionLabel: String?
    private let duration: KitSnackbarDuration
    private let onAction: (() -> Void)?
    private let onDismiss: () -> Void
    @Environment(\.kitScheme) private var scheme

    public init(
        message: String?,
        variant: KitSnackbarVariant = .default,
        actionLabel: String? = nil,
        duration: KitSnackbarDuration = .short,
        onAction: (() -> Void)? = nil,
        onDismiss: @escaping () -> Void
    ) {
        self.message = message
        self.variant = variant
        self.actionLabel = actionLabel
        self.duration = duration
        self.onAction = onAction
        self.onDismiss = onDismiss
    }

    public var body: some View {
        Group {
            if let message, !message.isEmpty {
                bar(message)
                    .padding(.horizontal, KitSpace.screen)
                    .padding(.bottom, KitSpace.md)
                    .transition(.move(edge: .bottom).combined(with: .opacity))
                    .task(id: message) { await hold() }
            }
        }
        .animation(.easeInOut(duration: KitMotion.durationFast), value: message)
        .frame(maxWidth: .infinity, alignment: .bottom)
    }

    private func bar(_ message: String) -> some View {
        HStack(alignment: .center, spacing: KitSpace.sm) {
            Text(message)
                .font(KitFont.bodyMedium)
                .foregroundStyle(textColor)
                .frame(maxWidth: .infinity, alignment: .leading)
            if let actionLabel, let onAction {
                SwiftUI.Button(action: onAction) {
                    Text(actionLabel)
                        .font(KitFont.labelLarge)
                        .foregroundStyle(actionColor)
                }
                .buttonStyle(.plain)
            }
            SwiftUI.Button(action: onDismiss) {
                Image(systemName: "xmark")
                    .font(KitFont.labelLarge)
                    .foregroundStyle(textColor)
                    .frame(width: 32, height: 32)
            }
            .buttonStyle(.plain)
            .accessibilityLabel("Close")
        }
        .padding(.horizontal, KitSpace.md)
        .padding(.vertical, KitSpace.sm)
        .background(fill)
        .clipShape(RoundedRectangle(cornerRadius: KitRadius.md, style: .continuous))
    }

    private func hold() async {
        let seconds: Double?
        switch duration {
        case .short: seconds = 4
        case .long: seconds = 10
        case .indefinite: seconds = nil
        }
        guard let seconds else { return }
        do {
            try await Task.sleep(nanoseconds: UInt64(seconds * 1_000_000_000))
        } catch {
            return
        }
        onDismiss()
    }

    private var fill: Color {
        switch variant {
        case .default: scheme.inverseSurface
        case .success: scheme.tertiaryContainer
        case .error: scheme.errorContainer
        }
    }

    private var textColor: Color {
        switch variant {
        case .default: scheme.inverseOnSurface
        case .success: scheme.onTertiaryContainer
        case .error: scheme.onErrorContainer
        }
    }

    private var actionColor: Color {
        switch variant {
        case .default: scheme.inversePrimary
        case .success: scheme.tertiary
        case .error: scheme.error
        }
    }
}
