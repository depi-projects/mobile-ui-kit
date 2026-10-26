import SwiftUI

/// Confirmation surface. Confirm and dismiss are text buttons on `surface`.
public struct KitDialogModifier: ViewModifier {
    private let visible: Bool
    private let title: String
    private let message: String?
    private let confirmLabel: String
    private let dismissLabel: String?
    private let destructive: Bool
    private let loading: Bool
    private let error: String?
    private let dismissOnBack: Bool
    private let dismissOnClickOutside: Bool
    private let onDismiss: () -> Void
    private let onConfirm: () -> Void
    @Environment(\.kitScheme) private var scheme
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    public init(
        visible: Bool,
        title: String,
        message: String? = nil,
        confirmLabel: String,
        dismissLabel: String? = nil,
        destructive: Bool = false,
        loading: Bool = false,
        error: String? = nil,
        dismissOnBack: Bool = true,
        dismissOnClickOutside: Bool = true,
        onDismiss: @escaping () -> Void,
        onConfirm: @escaping () -> Void
    ) {
        self.visible = visible
        self.title = title
        self.message = message
        self.confirmLabel = confirmLabel
        self.dismissLabel = dismissLabel
        self.destructive = destructive
        self.loading = loading
        self.error = error
        self.dismissOnBack = dismissOnBack
        self.dismissOnClickOutside = dismissOnClickOutside
        self.onDismiss = onDismiss
        self.onConfirm = onConfirm
    }

    public func body(content: Content) -> some View {
        content
            .navigationBarBackButtonHidden(visible && dismissOnBack)
            .overlay {
                if visible {
                    ZStack {
                        Color.black.opacity(0.45)
                            .ignoresSafeArea()
                            .onTapGesture {
                                if dismissOnClickOutside, !loading { onDismiss() }
                            }
                        VStack(alignment: .leading, spacing: KitSpace.sm) {
                            Text(title)
                                .font(KitFont.titleMedium)
                                .foregroundStyle(scheme.onSurface)
                            if let message, !message.isEmpty {
                                Text(message)
                                    .font(KitFont.bodyMedium)
                                    .foregroundStyle(scheme.onSurfaceVariant)
                            }
                            if let error, !error.isEmpty {
                                Text(error)
                                    .font(KitFont.bodySmall)
                                    .foregroundStyle(scheme.error)
                            }
                            HStack(spacing: KitSpace.sm) {
                                Spacer(minLength: 0)
                                if let dismissLabel {
                                    textButton(dismissLabel, color: scheme.onSurfaceVariant, action: onDismiss)
                                }
                                if loading {
                                    ProgressView()
                                        .progressViewStyle(.circular)
                                        .tint(destructive ? scheme.error : scheme.primary)
                                } else {
                                    textButton(confirmLabel, color: destructive ? scheme.error : scheme.primary, action: onConfirm)
                                }
                            }
                        }
                        .padding(KitSpace.lg)
                        .frame(maxWidth: 420, alignment: .leading)
                        .background(scheme.surface)
                        .clipShape(RoundedRectangle(cornerRadius: KitRadius.panel, style: .continuous))
                        .padding(KitSpace.screen)
                    }
                    .transition(reduceMotion ? .opacity : .opacity.combined(with: .scale(scale: 0.98)))
                }
            }
            .animation(reduceMotion ? nil : .easeInOut(duration: KitMotion.durationFast), value: visible)
    }

    private func textButton(_ title: String, color: Color, action: @escaping () -> Void) -> some View {
        SwiftUI.Button(action: action) {
            Text(title)
                .font(KitFont.labelLarge)
                .foregroundStyle(color)
                .padding(.horizontal, KitSpace.xs)
                .padding(.vertical, KitSpace.xxs)
        }
        .buttonStyle(.plain)
        .disabled(loading)
    }
}

public extension View {
    func kitDialog(
        visible: Bool,
        title: String,
        message: String? = nil,
        confirmLabel: String,
        dismissLabel: String? = nil,
        destructive: Bool = false,
        loading: Bool = false,
        error: String? = nil,
        dismissOnBack: Bool = true,
        dismissOnClickOutside: Bool = true,
        onDismiss: @escaping () -> Void,
        onConfirm: @escaping () -> Void
    ) -> some View {
        modifier(
            KitDialogModifier(
                visible: visible,
                title: title,
                message: message,
                confirmLabel: confirmLabel,
                dismissLabel: dismissLabel,
                destructive: destructive,
                loading: loading,
                error: error,
                dismissOnBack: dismissOnBack,
                dismissOnClickOutside: dismissOnClickOutside,
                onDismiss: onDismiss,
                onConfirm: onConfirm
            )
        )
    }
}
