import SwiftUI

/// Price line. Draws nothing when there are fewer than two points.
public struct KitSparkline: View {
    private let values: [Double]
    private let height: CGFloat
    private let lineColor: Color

    public init(values: [Double], height: CGFloat = 56, lineColor: Color = Palette.orange) {
        self.values = values
        self.height = height
        self.lineColor = lineColor
    }

    public var body: some View {
        if values.count >= 2 {
            Canvas { context, size in
                let minValue = values.min() ?? 0
                let maxValue = values.max() ?? minValue
                let range = max(maxValue - minValue, max(maxValue * 0.02, 0.01))
                let padY: CGFloat = 6
                let step = size.width / CGFloat(max(values.count - 1, 1))
                func yAt(_ value: Double) -> CGFloat {
                    let t = (value - minValue) / range
                    return size.height - padY - CGFloat(t) * (size.height - padY * 2)
                }
                var line = Path()
                for (index, value) in values.enumerated() {
                    let point = CGPoint(x: CGFloat(index) * step, y: yAt(value))
                    if index == 0 {
                        line.move(to: point)
                    } else {
                        line.addLine(to: point)
                    }
                }
                var fill = line
                fill.addLine(to: CGPoint(x: size.width, y: size.height))
                fill.addLine(to: CGPoint(x: 0, y: size.height))
                fill.closeSubpath()
                context.fill(fill, with: .color(lineColor.opacity(0.18)))
                context.stroke(line, with: .color(lineColor), lineWidth: 2.5)
                if let last = values.last {
                    let dot = CGRect(
                        x: size.width - 4,
                        y: yAt(last) - 4,
                        width: 8,
                        height: 8
                    )
                    context.fill(Path(ellipseIn: dot), with: .color(lineColor))
                }
            }
            .frame(maxWidth: .infinity)
            .frame(height: height)
            .accessibilityLabel("Price history")
        }
    }
}
