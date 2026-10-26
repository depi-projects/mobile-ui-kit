import SwiftUI

/// Page column. The bottom bar sits in a safe-area inset; status bar and keyboard stay with the screen.
public struct KitScaffold<Content: View, BottomBar: View>: View {
    private let content: () -> Content
    private let bottomBar: () -> BottomBar

    public init(
        @ViewBuilder content: @escaping () -> Content,
        @ViewBuilder bottomBar: @escaping () -> BottomBar
    ) {
        self.content = content
        self.bottomBar = bottomBar
    }

    public var body: some View {
        content()
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .background(KitScreenBackground())
            .safeAreaInset(edge: .bottom, spacing: 0) {
                bottomBar()
            }
    }
}

public extension KitScaffold where BottomBar == EmptyView {
    init(@ViewBuilder content: @escaping () -> Content) {
        self.init(content: content, bottomBar: { EmptyView() })
    }
}
