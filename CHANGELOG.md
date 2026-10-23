# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.61.0] - 2026-10-23

### Added

- Java API for the remaining Lucide customisation knobs:
  `setFill(String)` / `getFill()` (via `--lucide-fill`, default `none`),
  `setStrokeLinecap(LucideLineCap)` / `getStrokeLinecap()` (via
  `--lucide-stroke-linecap`; type-safe with `LucideLineCap.BUTT`, `ROUND`,
  `SQUARE`), and `setStrokeLinejoin(LucideLineJoin)` /
  `getStrokeLinejoin()` (via `--lucide-stroke-linejoin`; type-safe with
  `LucideLineJoin.BEVEL`, `MITER`, `ROUND`). All values inherit into the
  `<vaadin-icon>` shadow DOM under Vaadin 24.x and 25.x alike.
- SVG paint model: default paint attributes are parameterized with CSS
  custom properties on every shape element (`fill` → `--lucide-fill`,
  `stroke-width` → `--vaadin-icon-stroke-width`, plus stroke line cap/line
  join custom properties) instead of relying on the root `<svg>` attributes.
  Shape-level attributes are required because Vaadin 24.x copies only
  `viewBox` and the inner shapes from the source SVG — never the root paint
  attributes — and the fixed fallbacks keep standalone `<img>` usage
  rendering correctly.
- Apache 2.0 `LICENSE` and third-party `NOTICE` (Lucide ISC, Feather MIT/Cole Bemis),
  both packaged into the JAR `META-INF/`.
- Standalone Python icon generator (`scripts/generate_icons.py`) replacing the
  external Node.js/npm-based generator toolchain; regenerates all SVGs, the
  `LucideIcon` enum, and `NOTICE` deterministically from an exact
  `lucide-static` version.
- `setAriaLabel(String)` / `getAriaLabel()` convenience methods on
  `LucideSvgIcon`.
- Maven Wrapper (`mvnw`) for reproducible builds.
- `RELEASE.md` documenting the Lucide sync and release procedure.

### Changed

- Synced icon set with `lucide-static` 1.61.0 (2,171 icons; added
  `circle-chevrons-down`, `circle-chevrons-left`, `circle-chevrons-right`,
  `circle-chevrons-up`).
- CI matrix excludes the unsupported JDK 17 × Vaadin 25 combination (Vaadin 25
  ships Java 21 bytecode); README documents the per-Vaadin Java requirement
  (Java 17 for Vaadin 24.x, Java 21 for Vaadin 25).
- Compiler release target lowered from Java 21 to **Java 17**, restoring
  compatibility with Vaadin 24 LTS / Spring Boot 3 on Java 17 runtimes.
- CI now tests against JDK 17 and 21 across Vaadin 24.2, 24.6, and 25.1.
- `pom.xml` project URL corrected to this repository; developer URL uses
  `https://`; hardcoded GPG key fingerprint removed from the POM and the
  release workflow.
- Generated SVG normalization cleaned up (no trailing whitespace artifacts;
  guaranteed LF endings and trailing newline).
- Restored `CHANGELOG.md` and `CONTRIBUTING.md`.
- `LucideSvgIcon` validates icon names (`^[a-z0-9-]+$`) and rejects
  invalid input with `IllegalArgumentException`.
- `LucideIconFactory` no longer keeps an unbounded in-memory map of SVG
  content; `getSvgContent()` reads straight from the classpath (the
  `cacheSize()` / `clearCache()` / `evict()` cache methods and their tests
  are gone).
- `LucideIconFactory` documentation reworked: the factory is stateless and
  the SVG content is handled entirely by the browser.

## [1.41.0] - 2026-09-06

### Changed

- Synced icon set with `lucide-static` 1.41.0 (2,066 icons).

## [1.28.0] - 2026-08-02

### Changed

- Synced icon set with `lucide-static` 1.28.0.

## [1.0.0] - Initial release

### Added

- `LucideIcon` enum, `LucideSvgIcon` component, and `LucideIconFactory`
  with bundled Lucide SVG assets.

[1.61.0]: https://github.com/dr0idbot/v-lucide-icons/compare/v1.41.0...v1.61.0
[1.41.0]: https://github.com/dr0idbot/v-lucide-icons/compare/v1.28.0...v1.41.0
[1.28.0]: https://github.com/dr0idbot/v-lucide-icons/releases/tag/v1.28.0
[1.0.0]: https://github.com/dr0idbot/v-lucide-icons/releases/tag/v1.0.0
