// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "MobileUIKit",
    platforms: [.iOS(.v16)],
    products: [
        .library(name: "MobileUIKit", targets: ["MobileUIKit"]),
    ],
    dependencies: [
        .package(url: "https://github.com/onevcat/Kingfisher.git", from: "8.1.0"),
    ],
    targets: [
        .target(
            name: "MobileUIKit",
            dependencies: [
                .product(name: "Kingfisher", package: "Kingfisher"),
            ],
            path: "Sources/MobileUIKit",
            resources: [
                .process("Fonts"),
            ]
        ),
    ]
)
