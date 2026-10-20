# Vaadin Lucide Icons

[![Maven Central](https://img.shields.io/maven-central/v/io.github.dr0idbot/v-lucide-icons)](https://central.sonatype.com/artifact/io.github.dr0idbot/v-lucide-icons)
[![CI](https://github.com/dr0idbot/v-lucide-icons/actions/workflows/ci.yml/badge.svg)](https://github.com/dr0idbot/v-lucide-icons/actions/workflows/ci.yml)

Server-side [Lucide](https://lucide.dev) icon integration for **Vaadin 24.2+ and Vaadin 25+**, **Java 17+** (Java 21 is required when using Vaadin 25, matching the Vaadin platform's own requirement).

Renders icons through the native `<vaadin-icon>` web component via Vaadin's `SvgIcon` class. SVGs are served as static classpath resources — no inline SVG injection.

> **Disclaimer:** This is an independent community add-on. It is not affiliated with, endorsed by, or sponsored by the Lucide project or Vaadin Ltd.

## Add the Dependency

```xml
<dependency>
    <groupId>io.github.dr0idbot</groupId>
    <artifactId>v-lucide-icons</artifactId>
    <version>1.60.0</version>
</dependency>
```

## Usage

```java
// Basic
LucideIcon.SAVE.create();

// Customize
var icon = LucideIcon.STAR.create();
icon.setColor("#ff6b00");           // via CSS color
icon.setSize("48px");               // width & height
icon.setStrokeWidth(1.5);           // SVG stroke width

// Accessibility
icon.setDecorative(true);           // hide from screen readers
icon.setAriaLabel("Star rating");   // labelled
```

## API

| Method | Source | Description |
|--------|--------|-------------|
| `setColor(String)` | `SvgIcon` | Icon color via CSS `color` |
| `setSize(String)` | `AbstractIcon` | Width and height (e.g. `"24px"`, `"2em"`) |
| `setStrokeWidth(double)` | `LucideSvgIcon` | SVG stroke width |
| `setDecorative(boolean)` | `LucideSvgIcon` | Mark as presentational |
| `setAriaLabel(String)` / `getAriaLabel()` | `LucideSvgIcon` | Accessible name |

### Public Types

- **`LucideIcon`** — enum of all 2,167 Lucide icons (lucide-static **1.60.0**)
- **`LucideSvgIcon`** — extends Vaadin's `SvgIcon`
- **`LucideIconFactory`** — creates and validates icon instances

## Updating Icons

The icon set, `LucideIcon` enum, and `NOTICE` are regenerated with a single
script. No Node.js, npm, Maven, or external tool repositories are required —
only Python 3.10+ (standard library only):

```bash
python3 scripts/generate_icons.py --version <lucide-static-version>
```

- Downloads the exact versioned `lucide-static` tarball from the npm registry.
- Rewrites `src/main/resources/META-INF/resources/lucide/*.svg`,
  `src/main/java/io/github/dr0idbot/vlucide/LucideIcon.java`, and `NOTICE`
  deterministically; stale icons are removed automatically.
- Run it, commit the regenerated files, and release as noted below.

A release's version always mirrors the upstream `lucide-static` version from
which its icons are sourced (e.g. add-on `1.60.0` = Lucide `1.60.0` assets).
See [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

- This project's code is licensed under the **Apache License, Version 2.0** — see [`LICENSE`](LICENSE).
- Bundled Lucide icons are licensed under the **ISC License**; icons derived
  from Feather are additionally subject to Cole Bemis' **MIT License**. Full
  attributions are in [`NOTICE`](NOTICE).
