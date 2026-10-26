// Reads tokens/tokens.json and writes Swift constants.
// Run from the mobile-ui-kit root: node scripts/generate-swift-tokens.mjs
import { readFileSync, writeFileSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";

const root = join(dirname(fileURLToPath(import.meta.url)), "..");
const tokens = JSON.parse(readFileSync(join(root, "tokens", "tokens.json"), "utf8"));

const light = { ...tokens.colorScheme.light };
const dark = { ...tokens.colorScheme.dark };
// design-system.md lists this role for light; the JSON only stores the dark value.
if (!light.surfaceContainerLowest) light.surfaceContainerLowest = "#FFFFFF";

const schemeKeys = [...new Set([...Object.keys(light), ...Object.keys(dark)])].sort();

function camel(name) {
  return name.charAt(0).toLowerCase() + name.slice(1);
}

function colorLine(key, hex) {
  return `        ${camel(key)}: KitHex.color("${hex}")`;
}

const weights = { Bold: "bold", SemiBold: "semibold", Medium: "medium", Normal: "regular" };

const schemeProps = schemeKeys.map((key) => `    public let ${camel(key)}: Color`).join("\n");
const schemeInit = schemeKeys.map((key) => `        ${camel(key)}: Color`).join(",\n");

const fontLines = Object.entries(tokens.typographySp)
  .map(([name, spec]) => {
    const weight = weights[spec.weight];
    if (!weight) throw new Error(`Unknown weight ${spec.weight} for ${name}`);
    return `    public static var ${name}: Font { KitFontFace.font(size: ${spec.size}, style: "${weight}") }`;
  })
  .join("\n");

const spaceLines = Object.entries(tokens.spaceDp)
  .map(([name, value]) => `    public static let ${name}: CGFloat = ${value}`)
  .join("\n");

const radiusLines = Object.entries(tokens.radiusDp)
  .map(([name, value]) => `    public static let ${name}: CGFloat = ${value}`)
  .join("\n");

const elevationLines = Object.entries(tokens.elevationDp)
  .map(([name, value]) => `    public static let ${name}: CGFloat = ${value}`)
  .join("\n");

const paletteLines = Object.entries(tokens.palette)
  .map(([name, hex]) => `    public static let ${camel(name)} = KitHex.color("${hex}")`)
  .join("\n");

const out = `// Generated from tokens/tokens.json by scripts/generate-swift-tokens.mjs.
// Do not edit. Re-run the script after token changes.
import SwiftUI

${schemeProps ? "" : ""}
public struct KitScheme {
${schemeProps}

    public init(
${schemeInit}
    ) {
${schemeKeys.map((key) => `        self.${camel(key)} = ${camel(key)}`).join("\n")}
    }

    public static let light = KitScheme(
${schemeKeys.map((key) => colorLine(key, light[key] ?? dark[key])).join(",\n")}
    )

    public static let dark = KitScheme(
${schemeKeys.map((key) => colorLine(key, dark[key] ?? light[key])).join(",\n")}
    )
}

public enum Palette {
${paletteLines}
}

public enum KitSpace {
${spaceLines}
}

public enum KitRadius {
${radiusLines}

    public static let panel: CGFloat = lg
    public static let button: CGFloat = md
}

public enum KitElevation {
${elevationLines}
}

public enum KitFont {
${fontLines}
}

public enum KitMotion {
    public static let durationFast: Double = ${tokens.motion.durationFastMs / 1000}
    public static let durationMedium: Double = ${tokens.motion.durationMediumMs / 1000}
    public static let durationNav: Double = ${tokens.motion.durationNavMs / 1000}
    public static let durationExit: Double = ${tokens.motion.durationExitMs / 1000}
    public static let springStiffness: Double = ${tokens.motion.springStiffness}
    public static let springDamping: Double = ${(2 * Math.sqrt(tokens.motion.springStiffness)).toFixed(1)}
}

public enum KitDisabledAlpha {
    public static let container: Double = ${tokens.disabledAlpha.container}
    public static let content: Double = ${tokens.disabledAlpha.content}
}

public enum KitLimits {
    public static let maxFontScale: CGFloat = ${tokens.maxFontScale}
}
`;

const dest = join(root, "ui-kit-ios", "Sources", "MobileUIKit", "Generated", "KitTokens.generated.swift");
writeFileSync(dest, out);
console.log(`wrote ${dest}`);
