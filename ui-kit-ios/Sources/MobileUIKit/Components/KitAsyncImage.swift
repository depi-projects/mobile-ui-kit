import Kingfisher
import SwiftUI

public enum KitImageFit {
    case crop
    case fit
}

/// Image slot backed by Kingfisher (disk cache). Do not use `SwiftUI.AsyncImage`.
public struct KitAsyncImage: View {
    private let url: URL?
    private let fit: KitImageFit
    private let contentDescription: String?

    public init(url: URL?, fit: KitImageFit = .crop, contentDescription: String? = nil) {
        self.url = url
        self.fit = fit
        self.contentDescription = contentDescription
    }

    public var body: some View {
        KFImage(url)
            .placeholder { KitSkeleton() }
            .resizable()
            .aspectRatio(contentMode: fit == .crop ? .fill : .fit)
            .accessibilityLabel(contentDescription ?? "")
    }
}
