import SwiftUI

public struct KitNavTab: Identifiable, Equatable {
    public let route: String
    public let label: String
    public let systemImage: String

    public var id: String { route }

    public init(route: String, label: String, systemImage: String) {
        self.route = route
        self.label = label
        self.systemImage = systemImage
    }
}

/// Floating pill plus an optional circular search button. Wraps plain buttons, not `TabView`.
public struct KitNavigationBar: View {
    private let tabs: [KitNavTab]
    private let currentRoute: String?
    private let onTabSelected: (String) -> Void
    private let searchTab: KitNavTab?
    private let searchSelected: Bool
    private let centerHighlightRoute: String?
    @Environment(\.kitScheme) private var scheme

    public init(
        tabs: [KitNavTab],
        currentRoute: String?,
        onTabSelected: @escaping (String) -> Void,
        searchTab: KitNavTab? = nil,
        searchSelected: Bool = false,
        centerHighlightRoute: String? = nil
    ) {
        self.tabs = tabs
        self.currentRoute = currentRoute
        self.onTabSelected = onTabSelected
        self.searchTab = searchTab
        self.searchSelected = searchSelected
        self.centerHighlightRoute = centerHighlightRoute
    }

    public var body: some View {
        HStack(spacing: 10) {
            HStack(spacing: 0) {
                ForEach(tabs) { tab in
                    tabButton(tab)
                }
            }
            .padding(.horizontal, KitSpace.xs)
            .padding(.vertical, 6)
            .frame(height: 52)
            .frame(maxWidth: .infinity)
            .background(scheme.surface)
            .clipShape(Capsule())
            .kitShadow(.xl)

            if let searchTab {
                SwiftUI.Button {
                    onTabSelected(searchTab.route)
                } label: {
                    Image(systemName: searchTab.systemImage)
                        .font(.system(size: 22, weight: .semibold))
                        .foregroundStyle(searchSelected ? Color.white : scheme.onSurface)
                        .frame(width: 56, height: 56)
                        .background(searchSelected ? scheme.onSurface : scheme.surface)
                        .clipShape(Circle())
                }
                .buttonStyle(.plain)
                .kitShadow(searchSelected ? .lg : .xl)
                .accessibilityLabel(searchTab.label)
                .accessibilityAddTraits(searchSelected ? .isSelected : [])
            }
        }
        .padding(.horizontal, KitSpace.md)
        .padding(.top, KitSpace.sm)
        .padding(.bottom, 10)
    }

    private func tabButton(_ tab: KitNavTab) -> some View {
        let selected = currentRoute == tab.route
        let center = centerHighlightRoute == tab.route
        return SwiftUI.Button {
            onTabSelected(tab.route)
        } label: {
            Image(systemName: tab.systemImage)
                .font(.system(size: selected ? 22 : 20, weight: .regular))
                .foregroundStyle(selected ? scheme.primary : scheme.onSurfaceVariant.opacity(0.45))
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background {
                    if selected {
                        Capsule()
                            .fill(scheme.primary.opacity(center ? 0.22 : 0.14))
                            .padding(.horizontal, center ? 4 : 6)
                            .padding(.vertical, 2)
                    }
                }
        }
        .buttonStyle(.plain)
        .accessibilityLabel(tab.label)
        .accessibilityAddTraits(selected ? .isSelected : [])
    }
}
