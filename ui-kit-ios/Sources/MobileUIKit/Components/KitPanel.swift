import SwiftUI

public struct KitPanel<Content: View>: View {
    private let showAccentStripe: Bool
    private let accent: Color?
    private let content: () -> Content
    @Environment(\.kitScheme) private var scheme

    public init(
        showAccentStripe: Bool = false,
        accent: Color? = nil,
        @ViewBuilder content: @escaping () -> Content
    ) {
        self.showAccentStripe = showAccentStripe
        self.accent = accent
        self.content = content
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            if let accent {
                accent.frame(height: 3)
            } else if showAccentStripe {
                LinearGradient(
                    colors: [Palette.orange, Palette.navLime],
                    startPoint: .leading,
                    endPoint: .trailing
                )
                .frame(height: 3)
            }
            content()
                .padding(KitSpace.md)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(scheme.surface)
        .clipShape(RoundedRectangle(cornerRadius: KitRadius.panel, style: .continuous))
        .kitShadow(.md)
    }
}

public struct KitScreenBackground: View {
    @Environment(\.kitScheme) private var scheme

    public init() {}

    public var body: some View {
        scheme.background.ignoresSafeArea()
    }
}
