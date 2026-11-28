# Vaadin Lucide Icons

[![Maven Central](https://img.shields.io/maven-central/v/io.github.dr0idbot/v-lucide-icons)](https://central.sonatype.com/artifact/io.github.dr0idbot/v-lucide-icons)
[![CI](https://github.com/dr0idbot/v-lucide-icons/actions/workflows/ci.yml/badge.svg)](https://github.com/dr0idbot/v-lucide-icons/actions/workflows/ci.yml)

Server-side [Lucide](https://lucide.dev) icon integration for **Vaadin 24.2+**, **Java 17+**.

Renders icons through the native `<vaadin-icon>` web component via Vaadin's `SvgIcon` class. SVGs are served as static classpath resources — no inline SVG injection.

> **Disclaimer:** This is an independent community add-on. It is not affiliated with, endorsed by, or sponsored by the Lucide project or Vaadin Ltd.

## Add the Dependency

```xml
<dependency>
    <groupId>io.github.dr0idbot</groupId>
    <artifactId>v-lucide-icons</artifactId>
    <version>1.83.0</version>
</dependency>
```

## Usage

```java
// Basic
LucideIcon.SAVE.create();

// Customize
var icon = LucideIcon.STAR.create();
icon.setColor("#ff6b00");           // stroke color via CSS color
icon.setSize("48px");               // width & height
icon.setStrokeWidth(1.5);           // SVG stroke width
icon.setFill("currentColor");       // fill paint (default: none)
icon.setStrokeLinecap(LucideLineCap.SQUARE);    // BUTT | ROUND | SQUARE
icon.setStrokeLinejoin(LucideLineJoin.MITER);   // BEVEL | MITER | ROUND

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
| `setFill(String)` / `getFill()` | `LucideSvgIcon` | Fill paint, default `"none"` |
| `setStrokeLinecap(LucideLineCap)` / `getStrokeLinecap()` | `LucideSvgIcon` | Stroke end cap: `BUTT` \| `ROUND` \| `SQUARE` |
| `setStrokeLinejoin(LucideLineJoin)` / `getStrokeLinejoin()` | `LucideSvgIcon` | Vertex join: `BEVEL` \| `MITER` \| `ROUND` |
| `setDecorative(boolean)` | `LucideSvgIcon` | Mark as presentational |
| `setAriaLabel(String)` / `getAriaLabel()` | `LucideSvgIcon` | Accessible name |

Customization is implemented via CSS custom properties
(`--vaadin-icon-stroke-width`, `--lucide-fill`, `--lucide-stroke-linecap`,
`--lucide-stroke-linejoin`) that override the var()-parameterized paint
attributes baked into the generated SVG files; the property values inherit
into the `<vaadin-icon>` shadow DOM.

### Public Types

- **`LucideIcon`** — enum of all 2,453 Lucide icons (lucide-static **1.83.0**)
- **`LucideSvgIcon`** — extends Vaadin's `SvgIcon`
- **`LucideIconFactory`** — creates and validates icon instances
- **`LucideLineCap`** / **`LucideLineJoin`** — stroke cap and join shapes

## Contributing

Contributions are welcome — see [CONTRIBUTING.md](CONTRIBUTING.md) for the
local build and the icon regeneration workflow, and [RELEASE.md](RELEASE.md)
for the maintainer release procedure.

The add-on version always mirrors the upstream `lucide-static` version from
which its icons are sourced (e.g. add-on `1.83.0` = Lucide `1.83.0` assets).

## License

- This project's code is licensed under the **Apache License, Version 2.0** — see [`LICENSE`](LICENSE).
- Bundled Lucide icons are licensed under the **ISC License**; icons derived
  from Feather are additionally subject to Cole Bemis' **MIT License**. Full
  attributions are in [`NOTICE`](NOTICE).
