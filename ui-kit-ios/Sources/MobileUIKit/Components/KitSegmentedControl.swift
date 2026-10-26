import SwiftUI

public struct KitSegment: Identifiable, Hashable {
    public let id: String
    public let title: String

    public init(id: String, title: String) {
        self.id = id
        self.title = title
    }
}

/// Wrapper over `Picker` with the segmented style.
public struct KitSegmentedControl: View {
    private let segments: [KitSegment]
    @Binding private var selection: String

    public init(segments: [KitSegment], selection: Binding<String>) {
        self.segments = segments
        _selection = selection
    }

    public var body: some View {
        Picker("", selection: $selection) {
            ForEach(segments) { segment in
                Text(segment.title).tag(segment.id)
            }
        }
        .pickerStyle(.segmented)
        .labelsHidden()
    }
}
