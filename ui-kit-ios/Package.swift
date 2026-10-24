// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "MobileUIKit",
    platforms: [.iOS(.v16)],
    products: [
        .library(name: "MobileUIKit", targets: ["MobileUIKit"]),
    ],
    targets: [
        .target(name: "MobileUIKit", path: "Sources/MobileUIKit"),
    ]
)
