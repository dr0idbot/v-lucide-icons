package io.github.dr0idbot.vlucide;

import java.util.Optional;

/**
 * Vertex join shape for Lucide icon strokes, applied via the
 * {@code --lucide-stroke-linejoin} CSS custom property (which the generated
 * SVG resources consume on every shape element as
 * {@code stroke-linejoin="var(--lucide-stroke-linejoin, round)"}).
 * <p>
 * Joins are only visible at sharp bends between segments; bends drawn as
 * arcs hide joins entirely.
 */
public enum LucideLineJoin {

	BEVEL("bevel"),
	MITER("miter"),
	ROUND("round");

	private final String value;

	LucideLineJoin(String value) {
		this.value = value;
	}

	/**
	 * Returns the value used for the CSS custom property.
	 *
	 * @return the SVG {@code stroke-linejoin} value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Looks up the constant for the given value.
	 *
	 * @param value the SVG {@code stroke-linejoin} value
	 * @return the matching constant, or an empty {@link Optional} if none
	 *         matches
	 */
	public static Optional<LucideLineJoin> from(String value) {
		for (LucideLineJoin join : values()) {
			if (join.value.equals(value)) {
				return Optional.of(join);
			}
		}
		return Optional.empty();
	}

	@Override
	public String toString() {
		return value;
	}
}
