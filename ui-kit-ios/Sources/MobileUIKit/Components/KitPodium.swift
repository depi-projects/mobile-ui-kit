import SwiftUI

public struct KitPodiumPlace: Identifiable, Equatable {
    public let id: String
    public let rankIndex: Int
    public let name: String
    public let imageURL: URL?
    public let valueText: String
    public let isSelf: Bool

    public init(
        id: String,
        rankIndex: Int,
        name: String,
        imageURL: URL? = nil,
        valueText: String,
        isSelf: Bool = false
    ) {
        self.id = id
        self.rankIndex = rankIndex
        self.name = name
        self.imageURL = imageURL
        self.valueText = valueText
        self.isSelf = isSelf
    }
}

/// Top three in the order 2 — 1 — 3. The value string is supplied by the app.
public struct KitPodium: View {
    private let places: [KitPodiumPlace]
    private let onSelect: (KitPodiumPlace) -> Void
    @Environment(\.kitScheme) private var scheme
    @Environment(\.colorScheme) private var colorScheme

    public init(places: [KitPodiumPlace], onSelect: @escaping (KitPodiumPlace) -> Void) {
        self.places = places
        self.onSelect = onSelect
    }

    public var body: some View {
        HStack(alignment: .bottom, spacing: KitSpace.xs) {
            ForEach(ordered) { place in
                slot(place)
            }
        }
        .frame(maxWidth: .infinity, alignment: .bottom)
    }

    private var ordered: [KitPodiumPlace] {
        [1, 0, 2].compactMap { rank in places.first { $0.rankIndex == rank } }
    }

    private func slot(_ place: KitPodiumPlace) -> some View {
        let rank = place.rankIndex
        let colors = rankColors(rank)
        let avatar: CGFloat = rank == 0 ? 44 : 38
        let bar: CGFloat = rank == 0 ? 48 : (rank == 1 ? 36 : 28)
        return SwiftUI.Button {
            onSelect(place)
        } label: {
            VStack(spacing: KitSpace.xxs) {
                KitAsyncImage(url: place.imageURL, contentDescription: place.name)
                    .frame(width: avatar, height: avatar)
                    .background(colors.background)
                    .clipShape(Circle())
                    .overlay {
                        Circle().stroke(place.isSelf ? colors.accent : scheme.outline.opacity(0.2), lineWidth: place.isSelf ? 2 : 1)
                    }
                Text(place.name)
                    .font(KitFont.labelMedium)
                    .foregroundStyle(scheme.onSurface)
                    .lineLimit(1)
                Text(place.valueText)
                    .font(KitFont.labelLarge)
                    .foregroundStyle(KitHex.color("FFD54F"))
                    .lineLimit(1)
                Text(rank == 0 ? "1" : (rank == 1 ? "2" : "3"))
                    .font(KitFont.titleMedium)
                    .foregroundStyle(colors.accent)
                    .frame(maxWidth: .infinity)
                    .frame(height: bar)
                    .background(colors.background)
                    .clipShape(TopRoundedRectangle(radius: 10))
            }
        }
        .buttonStyle(.plain)
        .frame(maxWidth: .infinity)
    }

    private func rankColors(_ rank: Int) -> (accent: Color, background: Color) {
        let dark = colorScheme == .dark
        switch rank {
        case 0:
            return (KitHex.color("FFD54F"), dark ? KitHex.color("3D3500") : KitHex.color("FFF8E1"))
        case 1:
            return (KitHex.color("B0BEC5"), dark ? KitHex.color("2A3238") : KitHex.color("ECEFF1"))
        default:
            return (KitHex.color("CD7F32"), dark ? KitHex.color("3A2418") : KitHex.color("FBE9E7"))
        }
    }
}

private struct TopRoundedRectangle: Shape {
    var radius: CGFloat

    func path(in rect: CGRect) -> Path {
        let curve = min(radius, min(rect.width, rect.height) / 2)
        var path = Path()
        path.move(to: CGPoint(x: rect.minX, y: rect.maxY))
        path.addLine(to: CGPoint(x: rect.minX, y: rect.minY + curve))
        path.addQuadCurve(
            to: CGPoint(x: rect.minX + curve, y: rect.minY),
            control: CGPoint(x: rect.minX, y: rect.minY)
        )
        path.addLine(to: CGPoint(x: rect.maxX - curve, y: rect.minY))
        path.addQuadCurve(
            to: CGPoint(x: rect.maxX, y: rect.minY + curve),
            control: CGPoint(x: rect.maxX, y: rect.minY)
        )
        path.addLine(to: CGPoint(x: rect.maxX, y: rect.maxY))
        path.closeSubpath()
        return path
    }
}
