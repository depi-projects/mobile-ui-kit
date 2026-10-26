import SwiftUI

/// Wrapper over `SwiftUI.TextField`. This is a general field, not search.
public struct KitTextField: View {
    @Binding private var text: String
    private let label: String?
    private let placeholder: String?
    private let supportingText: String?
    private let error: String?
    private let isError: Bool
    private let maxLength: Int?
    private let clearable: Bool
    private let submitLabel: SubmitLabel
    private let onClear: () -> Void
    private let onSubmit: () -> Void

    @FocusState private var focused: Bool
    @Environment(\.kitScheme) private var scheme

    public init(
        text: Binding<String>,
        label: String? = nil,
        placeholder: String? = nil,
        supportingText: String? = nil,
        error: String? = nil,
        isError: Bool? = nil,
        maxLength: Int? = nil,
        clearable: Bool = false,
        submitLabel: SubmitLabel = .done,
        onClear: @escaping () -> Void = {},
        onSubmit: @escaping () -> Void = {}
    ) {
        _text = text
        self.label = label
        self.placeholder = placeholder
        self.supportingText = supportingText
        self.error = error
        self.isError = isError ?? (error != nil)
        self.maxLength = maxLength
        self.clearable = clearable
        self.submitLabel = submitLabel
        self.onClear = onClear
        self.onSubmit = onSubmit
    }

    private var limitedText: Binding<String> {
        Binding(
            get: { text },
            set: { newValue in
                if let maxLength, newValue.count > maxLength {
                    text = String(newValue.prefix(maxLength))
                } else {
                    text = newValue
                }
            }
        )
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.xxs) {
            if let label {
                Text(label)
                    .font(KitFont.labelMedium)
                    .foregroundStyle(isError ? scheme.error : (focused ? scheme.primary : scheme.onSurfaceVariant))
            }
            HStack(spacing: KitSpace.xs) {
                SwiftUI.TextField(placeholder ?? "", text: limitedText)
                    .focused($focused)
                    .submitLabel(submitLabel)
                    .onSubmit(onSubmit)
                    .font(KitFont.bodyLarge)
                    .foregroundStyle(scheme.onSurface)
                if clearable, !text.isEmpty {
                    SwiftUI.Button {
                        text = ""
                        onClear()
                    } label: {
                        Image(systemName: "xmark.circle.fill")
                            .foregroundStyle(scheme.onSurfaceVariant)
                    }
                    .buttonStyle(.plain)
                    .accessibilityLabel("Clear")
                }
            }
            .padding(.horizontal, KitSpace.sm)
            .frame(minHeight: KitSpace.button)
            .background(scheme.surface)
            .clipShape(RoundedRectangle(cornerRadius: KitRadius.sm, style: .continuous))
            .overlay {
                RoundedRectangle(cornerRadius: KitRadius.sm, style: .continuous)
                    .stroke(isError ? scheme.error : (focused ? scheme.primary : scheme.outline), lineWidth: 1)
            }
            if let caption = error ?? supportingText {
                Text(caption)
                    .font(KitFont.bodySmall)
                    .foregroundStyle(error == nil ? scheme.onSurfaceVariant : scheme.error)
            }
        }
    }
}
