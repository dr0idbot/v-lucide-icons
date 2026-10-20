# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Apache 2.0 `LICENSE` and third-party `NOTICE` (Lucide ISC, Feather MIT/Cole Bemis),
  both packaged into the JAR `META-INF/`.
- Standalone Python icon generator (`scripts/generate_icons.py`) replacing the
  external Node.js/npm-based generator toolchain; regenerates all SVGs, the
  `LucideIcon` enum, and `NOTICE` deterministically from an exact
  `lucide-static` version.
- `setAriaLabel(String)` / `getAriaLabel()` convenience methods on
  `LucideSvgIcon`.
- Maven Wrapper (`mvnw`) for reproducible builds.

### Changed

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

[Unreleased]: https://github.com/dr0idbot/v-lucide-icons/compare/v1.41.0...HEAD
[1.41.0]: https://github.com/dr0idbot/v-lucide-icons/compare/v1.28.0...v1.41.0
[1.28.0]: https://github.com/dr0idbot/v-lucide-icons/releases/tag/v1.28.0
[1.0.0]: https://github.com/dr0idbot/v-lucide-icons/releases/tag/v1.0.0
