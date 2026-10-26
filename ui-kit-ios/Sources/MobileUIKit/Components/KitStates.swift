import SwiftUI

public enum KitPageSkeleton {
    case generic, home, inventory, leaderboard, itemDetail, catalog, watchlist
}

public struct KitSkeleton: View {
    private let height: CGFloat
    private let width: CGFloat?
    @Environment(\.kitScheme) private var scheme
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var pulse = false

    public init(height: CGFloat = 16, width: CGFloat? = nil) {
        self.height = height
        self.width = width
    }

    public var body: some View {
        RoundedRectangle(cornerRadius: KitRadius.sm, style: .continuous)
            .fill(scheme.surfaceVariant)
            .frame(width: width, height: height)
            .frame(maxWidth: width == nil ? .infinity : width, alignment: .leading)
            .opacity(reduceMotion ? 1 : (pulse ? 0.55 : 1))
            .onAppear {
                guard !reduceMotion else { return }
                withAnimation(.easeInOut(duration: KitMotion.durationMedium).repeatForever(autoreverses: true)) {
                    pulse = true
                }
            }
            .accessibilityHidden(true)
    }
}

public struct KitLoadingState: View {
    private let message: String
    private let skeleton: KitPageSkeleton
    private let showProgressIndicator: Bool
    @Environment(\.kitScheme) private var scheme

    public init(
        message: String = "Loading…",
        skeleton: KitPageSkeleton = .generic,
        showProgressIndicator: Bool = true
    ) {
        self.message = message
        self.skeleton = skeleton
        self.showProgressIndicator = showProgressIndicator
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.sm) {
            if showProgressIndicator {
                ProgressView()
                    .progressViewStyle(.circular)
                    .tint(scheme.primary)
                    .frame(maxWidth: .infinity)
                if !message.isEmpty {
                    Text(message)
                        .font(KitFont.labelLarge)
                        .foregroundStyle(scheme.onSurfaceVariant)
                        .frame(maxWidth: .infinity)
                }
            }
            page
        }
        .padding(.horizontal, 14)
        .padding(.top, KitSpace.xs)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .accessibilityElement(children: .combine)
        .accessibilityLabel(message.isEmpty ? "Loading…" : message)
    }

    @ViewBuilder
    private var page: some View {
        switch skeleton {
        case .generic:
            KitSkeleton(height: 18)
            KitSkeleton(height: 18)
            KitSkeleton(height: 18)
        case .home:
            HomeSkeleton()
        case .inventory:
            InventorySkeleton()
        case .leaderboard:
            LeaderboardSkeleton()
        case .itemDetail:
            ItemDetailSkeleton()
        case .catalog:
            CatalogSkeleton()
        case .watchlist:
            WatchlistSkeleton()
        }
    }
}

private struct SkeletonCard<Content: View>: View {
    @Environment(\.kitScheme) private var scheme
    private let content: Content

    init(@ViewBuilder content: () -> Content) {
        self.content = content()
    }

    var body: some View {
        content
            .padding(KitSpace.md)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(scheme.surface)
            .clipShape(RoundedRectangle(cornerRadius: KitRadius.panel, style: .continuous))
    }
}

private struct SkeletonItemRow: View {
    @Environment(\.kitScheme) private var scheme

    var body: some View {
        HStack(spacing: KitSpace.sm) {
            KitSkeleton(height: 56, width: 72)
            VStack(alignment: .leading, spacing: KitSpace.xs) {
                KitSkeleton(height: 14, width: 140)
                KitSkeleton(height: 10, width: 88)
            }
            Spacer(minLength: KitSpace.sm)
            KitSkeleton(height: 22, width: 52)
        }
        .padding(KitSpace.sm)
        .background(scheme.surface)
        .clipShape(RoundedRectangle(cornerRadius: KitRadius.md, style: .continuous))
    }
}

private struct HomeSkeleton: View {
    var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.sm) {
            SkeletonCard {
                VStack(alignment: .leading, spacing: KitSpace.sm) {
                    KitSkeleton(height: 12, width: 72)
                    KitSkeleton(height: 36, width: 160)
                    KitSkeleton(height: 72)
                }
            }
            SkeletonCard {
                VStack(alignment: .leading, spacing: KitSpace.sm) {
                    KitSkeleton(height: 14, width: 120)
                    SkeletonItemRow()
                    SkeletonItemRow()
                    SkeletonItemRow()
                }
            }
        }
    }
}

private struct InventorySkeleton: View {
    var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.sm) {
            KitSkeleton(height: 12, width: 72)
            KitSkeleton(height: 32, width: 150)
            KitSkeleton(height: 72)
            KitSkeleton(height: 44)
            HStack(spacing: KitSpace.xs) {
                KitSkeleton(height: 36)
                KitSkeleton(height: 36, width: 44)
            }
            ForEach(0..<4, id: \.self) { _ in
                SkeletonItemRow()
            }
        }
    }
}

private struct LeaderboardSkeleton: View {
    var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.sm) {
            KitSkeleton(height: 44)
            HStack(alignment: .bottom, spacing: KitSpace.md) {
                ForEach(0..<3, id: \.self) { _ in
                    VStack(spacing: KitSpace.xs) {
                        KitSkeleton(height: 36, width: 36)
                        KitSkeleton(height: 52, width: 48)
                    }
                    .frame(maxWidth: .infinity)
                }
            }
            ForEach(0..<4, id: \.self) { _ in
                SkeletonItemRow()
            }
        }
    }
}

private struct ItemDetailSkeleton: View {
    var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.sm) {
            SkeletonCard {
                VStack(spacing: KitSpace.md) {
                    KitSkeleton(height: 160, width: 220)
                    KitSkeleton(height: 18, width: 180)
                }
                .frame(maxWidth: .infinity)
            }
            SkeletonCard {
                VStack(alignment: .leading, spacing: KitSpace.sm) {
                    KitSkeleton(height: 12, width: 72)
                    KitSkeleton(height: 32, width: 110)
                    KitSkeleton(height: 88)
                }
            }
        }
    }
}

private struct CatalogSkeleton: View {
    var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.sm) {
            KitSkeleton(height: 48)
            KitSkeleton(height: 14, width: 100)
            HStack(spacing: KitSpace.sm) {
                KitSkeleton(height: 72)
                KitSkeleton(height: 72)
            }
            KitSkeleton(height: 14, width: 120)
            ForEach(0..<3, id: \.self) { _ in
                SkeletonItemRow()
            }
        }
    }
}

private struct WatchlistSkeleton: View {
    var body: some View {
        VStack(alignment: .leading, spacing: KitSpace.sm) {
            KitSkeleton(height: 14, width: 140)
            ForEach(0..<4, id: \.self) { _ in
                SkeletonItemRow()
            }
        }
    }
}

public struct KitEmptyState<Action: View>: View {
    private let title: String
    private let message: String
    private let systemImage: String
    private let action: () -> Action
    @Environment(\.kitScheme) private var scheme

    public init(
        title: String,
        message: String,
        systemImage: String = "shippingbox",
        @ViewBuilder action: @escaping () -> Action
    ) {
        self.title = title
        self.message = message
        self.systemImage = systemImage
        self.action = action
    }

    public var body: some View {
        VStack(spacing: KitSpace.sm) {
            Image(systemName: systemImage)
                .font(.system(size: 28))
                .foregroundStyle(Palette.coral)
                .frame(width: 64, height: 64)
                .background(Palette.coralChip)
                .clipShape(Circle())
                .accessibilityLabel(title)
            Text(title)
                .font(KitFont.titleMedium)
                .foregroundStyle(scheme.onSurface)
                .multilineTextAlignment(.center)
            Text(message)
                .font(KitFont.bodyMedium)
                .foregroundStyle(scheme.onSurfaceVariant)
                .multilineTextAlignment(.center)
            action()
                .foregroundStyle(scheme.primary)
        }
        .padding(KitSpace.lg)
        .frame(maxWidth: .infinity)
    }
}

public extension KitEmptyState where Action == EmptyView {
    init(title: String, message: String, systemImage: String = "shippingbox") {
        self.init(title: title, message: message, systemImage: systemImage) { EmptyView() }
    }
}

public struct KitErrorState<Action: View>: View {
    private let title: String
    private let message: String
    private let systemImage: String
    private let action: () -> Action
    @Environment(\.kitScheme) private var scheme

    public init(
        message: String,
        title: String = "Something went wrong",
        systemImage: String = "exclamationmark.triangle",
        @ViewBuilder action: @escaping () -> Action
    ) {
        self.title = title
        self.message = message
        self.systemImage = systemImage
        self.action = action
    }

    public var body: some View {
        VStack(spacing: KitSpace.sm) {
            Image(systemName: systemImage)
                .font(.system(size: 28))
                .foregroundStyle(scheme.error)
                .frame(width: 64, height: 64)
                .background(scheme.error.opacity(0.12))
                .clipShape(Circle())
                .accessibilityLabel(title)
            Text(title)
                .font(KitFont.titleMedium)
                .foregroundStyle(scheme.onSurface)
                .multilineTextAlignment(.center)
            Text(message)
                .font(KitFont.bodyMedium)
                .foregroundStyle(scheme.onSurfaceVariant)
                .multilineTextAlignment(.center)
            action()
        }
        .padding(KitSpace.lg)
        .frame(maxWidth: .infinity)
    }
}

public extension KitErrorState where Action == EmptyView {
    init(message: String, title: String = "Something went wrong", systemImage: String = "exclamationmark.triangle") {
        self.init(message: message, title: title, systemImage: systemImage) { EmptyView() }
    }
}

public struct KitSearchEmptyState: View {
    private let title: String
    private let message: String

    public init(title: String = "No matches", message: String = "Try another name.") {
        self.title = title
        self.message = message
    }

    public var body: some View {
        KitEmptyState(
            title: title,
            message: message,
            systemImage: "magnifyingglass"
        )
    }
}
