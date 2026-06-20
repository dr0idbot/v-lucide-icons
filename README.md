# Vaadin Lucide Icons

[![Maven Central](https://img.shields.io/maven-central/v/io.github.dr0idbot/v-lucide-icons)](https://central.sonatype.com/artifact/io.github.dr0idbot/v-lucide-icons)

Server-side [Lucide](https://lucide.dev) icon integration for Vaadin 24+ (Java 21+).

Renders icons through the native `<vaadin-icon>` web component via Vaadin's `SvgIcon` class. SVGs are served as static classpath resources — no inline SVG injection.

## Add the Dependency

```xml
<dependency>
    <groupId>io.github.dr0idbot</groupId>
    <artifactId>v-lucide-icons</artifactId>
    <version>1.0.0</version>
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
icon.setDecorative(true);                           // hide from screen readers
icon.getElement().setAttribute("aria-label", "Star"); // labelled
```

## API

| Method | Source | Description |
|--------|--------|-------------|
| `setColor(String)` | `SvgIcon` | Icon color via CSS `color` |
| `setSize(String)` | `AbstractIcon` | Width and height (e.g. `"24px"`, `"2em"`) |
| `setStrokeWidth(double)` | `LucideSvgIcon` | SVG stroke width |
| `setDecorative(boolean)` | `LucideSvgIcon` | Mark as presentational |

### Public Types

- **`LucideIcon`** — enum of all 1986 Lucide icons
- **`LucideSvgIcon`** — extends Vaadin's `SvgIcon`
- **`LucideIconFactory`** — creates and validates icon instances

## Updating Icons

The icon enum and SVGs are regenerated from the [lucide-static](https://www.npmjs.com/package/lucide-static) npm package. The generator lives in the [tools repository](https://github.com/dr0idbot/vaadin-lucide-icons).

1. Clone the tools repo and bump `lucide-static` in `v-lucide-icons-generator/package.json`
2. Run the generator:
   ```
   mvn -pl v-lucide-icons-generator compile exec:java
   ```
3. Copy the output to this project:
   ```bash
   cp -r ../vaadin-lucide-icons/generated/resources/* src/main/resources/
   cp -r ../vaadin-lucide-icons/generated/java/* src/main/java/
   ```
4. Verify counts and commit

## License

This project is licensed under the Apache 2.0 License.
Lucide icons are licensed under the ISC License.