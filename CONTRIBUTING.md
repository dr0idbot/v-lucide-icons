# Contributing to v-lucide-icons

Thank you for considering a contribution! This is an independent community
add-on and is not affiliated with, endorsed by, or sponsored by the Lucide
project or Vaadin Ltd.

## Building

The project uses the Maven Wrapper with a pinned Maven 3.9.x distribution:

```bash
./mvnw clean test      # run tests
./mvnw clean package   # build the JAR
./mvnw clean verify    # package + integration-phase checks
```

- Java 17 or newer is required to build; the artifact targets Java 17 and
  runs on 17 and 21+.
- The library is compiled against the Vaadin version in `pom.xml`
  (`-Dvaadin.version=<version>` overrides it for testing).

## Pull Requests

1. Fork the repository and create a feature branch from `master`.
2. Keep changes focused; one feature or fix per PR.
3. Add or update unit tests for any behavior change
   (`src/test/java/io/github/dr0idbot/vlucide/`).
4. Run `./mvnw clean verify` locally and make sure it passes.
5. Follow the existing code style (tabs in this codebase, concise Javadoc
   for public API).
6. Sign off your intention via a normal PR description; note that all
   contributions are made under the Apache License, Version 2.0.

## Regenerating the Icon Set

Icon updates from upstream Lucide **must not** be edited by hand. Use the
maintainer generator:

```bash
python3 scripts/generate_icons.py --version <lucide-static-version>
```

- Requires Python 3.10+ (standard library only; no Node/npm).
- Rewrites the SVG assets, `LucideIcon.java`, and `NOTICE` deterministically.
- Commit the regenerated files together with a version bump, following the
  release process in [RELEASE.md](RELEASE.md).

## Reporting Issues

Please include:

- Vaadin and Java versions used.
- A minimal code snippet reproducing the issue.
- Whether the issue is visual (icon rendering) or API-related.

## Licensing

By contributing, you agree that your contributions will be licensed under
the Apache License, Version 2.0.
