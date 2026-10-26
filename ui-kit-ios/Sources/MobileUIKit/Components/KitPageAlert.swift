import SwiftUI

public enum KitAlertType {
    case info, success, warning, error
}

public struct KitPageAlert<Action: View>: View {
    private let type: KitAlertType
    private let message: String
    private let title: String?
    private let detail: String?
    private let action: () -> Action
    @Environment(\.kitScheme) private var scheme

    public init(
        type: KitAlertType,
        message: String,
        title: String? = nil,
        detail: String? = nil,
        @ViewBuilder action: @escaping () -> Action
    ) {
        self.type = type
        self.message = message
        self.title = title
        self.detail = detail
        self.action = action
    }

    public var body: some View {
        if message.isEmpty {
            EmptyView()
        } else {
            VStack(alignment: .leading, spacing: KitSpace.xs) {
                HStack(alignment: .top, spacing: KitSpace.sm) {
                    Image(systemName: iconName)
                        .font(.system(size: 22))
                        .foregroundStyle(iconColor)
                        .accessibilityHidden(true)
                    VStack(alignment: .leading, spacing: KitSpace.xxs) {
                        if let title {
                            Text(title)
                                .font(KitFont.titleSmall.bold())
                                .foregroundStyle(titleColor)
                        }
                        Text(message)
                            .font(KitFont.bodyMedium)
                            .foregroundStyle(scheme.onSurface)
                        if let detail {
                            Text(detail)
                                .font(KitFont.bodySmall)
                                .foregroundStyle(scheme.onSurfaceVariant)
                        }
                    }
                }
                action()
            }
            .padding(.horizontal, 14)
            .padding(.vertical, KitSpace.sm)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(fill)
            .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
            .overlay {
                RoundedRectangle(cornerRadius: 14, style: .continuous)
                    .stroke(stroke, lineWidth: 1)
            }
        }
    }

    private var iconName: String {
        switch type {
        case .info: "info.circle"
        case .success: "checkmark.circle"
        case .warning: "exclamationmark.triangle"
        case .error: "exclamationmark.circle"
        }
    }

    private var iconColor: Color {
        switch type {
        case .info, .error: tint
        case .success: scheme.tertiary
        case .warning: scheme.secondary
        }
    }

    private var titleColor: Color {
        switch type {
        case .info: scheme.primary
        case .success: scheme.onTertiaryContainer
        case .warning: scheme.onSecondaryContainer
        case .error: scheme.error
        }
    }

    private var tint: Color {
        switch type {
        case .info: scheme.primary
        case .success: scheme.tertiary
        case .warning: scheme.secondary
        case .error: scheme.error
        }
    }

    private var fill: Color {
        switch type {
        case .info: scheme.primaryContainer.opacity(0.35)
        case .success: scheme.tertiaryContainer.opacity(0.45)
        case .warning: scheme.secondaryContainer.opacity(0.45)
        case .error: scheme.errorContainer.opacity(0.45)
        }
    }

    private var stroke: Color {
        switch type {
        case .info: scheme.primary.opacity(0.20)
        case .success: scheme.tertiary.opacity(0.35)
        case .warning: scheme.secondary.opacity(0.35)
        case .error: scheme.error.opacity(0.35)
        }
    }
}

public extension KitPageAlert where Action == EmptyView {
    init(type: KitAlertType, message: String, title: String? = nil, detail: String? = nil) {
        self.init(type: type, message: message, title: title, detail: detail) { EmptyView() }
    }
}
