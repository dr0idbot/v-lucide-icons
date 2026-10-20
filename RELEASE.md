# Releasing v-lucide-icons

**Version scheme:** the add-on version always mirrors the upstream
`lucide-static` version from which its icon set was generated
(e.g. add-on `1.60.0` ships Lucide `1.60.0` assets).

## When a new Lucide version is released

### 1. Regenerate the icon set

```bash
python3 scripts/generate_icons.py --version <lucide-static-version>
# or omit --version to use the latest stable release
```

- Requires Python 3.10+ (standard library only). Not installed? Nothing else
  is needed — no Node, npm, or Maven involvement.
- The script downloads the exact upstream tarball, rewrites
  `src/main/resources/META-INF/resources/lucide/*.svg` (adding/removing icons
  as upstream changed them), regenerates `LucideIcon.java`, and rewrites
  `NOTICE` with the upstream license reproduced verbatim.
- The generator is a maintainer-only tool. It never runs during Maven
  builds; generated files are always committed.
- Running it twice with the same version is safe (idempotent, no diff).

### 2. Verify consistency

```bash
SVG_COUNT=$(find src/main/resources/META-INF/resources/lucide/ -name '*.svg' | wc -l)
ENUM_COUNT=$(grep -cE '^    [A-Z0-9_]+\("[a-z0-9-]+"\)' src/main/java/io/github/dr0idbot/vlucide/LucideIcon.java)
echo "SVG files: $SVG_COUNT | enum constants: $ENUM_COUNT"
[ "$SVG_COUNT" -eq "$ENUM_COUNT" ] || { echo "MISMATCH - abort"; exit 1; }
./mvnw clean verify
```

### 3. Update the POM version

Set `pom.xml` `<version>` to the exact `lucide-static` version you generated
from. Check it is not already published on Maven Central first — releases
are immutable:

```bash
curl -s -o /dev/null -w "%{http_code}" https://repo1.maven.org/maven2/io/github/dr0idbot/v-lucide-icons/<version>/
# 404 is good; 200 means pick another approach
```

### 4. Update documentation

- `README.md`: icon count, the `lucide-static <version>` mention, and the
  Maven dependency snippet.
- `CHANGELOG.md`: turn `[Unreleased]` into `[<version>] - <YYYY-MM-DD>`,
  summarize icon additions/removals, and fix the bottom compare links.
- `CONTRIBUTING.md`: no changes needed (generator is version-agnostic).

### 5. Commit, tag, push

```bash
git commit -am "Sync icons with lucide-static <version>"
git tag -v<version> -m "Release v<version> (lucide-static <version> sync)"
# GPG-signed variant if the key passphrase is available locally:
#   git tag -sv<version> -m "..."
git push origin master
git push origin v<version>
```

Do not mix the icon sync with unrelated changes; the release commit should
contain only generated files + version + docs.

### 6. Publication

Pushing the `v*` tag triggers `.github/workflows/release.yml`, which builds,
tests, signs with the stored GPG key, and publishes to Maven Central
(`central-publishing-maven-plugin`, autoPublish).

- Monitor the workflow run in GitHub Actions.
- Verify at <https://repo1.maven.org/maven2/io/github/dr0idbot/v-lucide-icons/<version>/>
  (propagation can take from minutes to a couple of hours).
- The [Vaadin Directory](https://vaadin.com/directory) picks up new Central
  versions automatically (~30 minutes after publication).

## Notes

- Upstream Lucide releases frequently (roughly every 1–3 days). There is no
  obligation to mirror every upstream release — sync on a steady cadence
  (e.g. monthly) and take the latest upstream version at that point, skipping
  intermediate version numbers.
- `NOTICE`/`LICENSE` must remain packaged in the JAR `META-INF/`. This is
  wired via `<build><resources>` in `pom.xml`; verify with:

  ```bash
  jar tf target/v-lucide-icons-<version>.jar | grep 'META-INF/\(LICENSE\|NOTICE\)$'
  ```
