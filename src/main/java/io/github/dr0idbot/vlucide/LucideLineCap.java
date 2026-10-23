package io.github.dr0idbot.vlucide;

import java.util.Optional;

/**
 * Stroke end cap shape for Lucide icons, applied via the
 * {@code --lucide-stroke-linecap} CSS custom property (which the generated
 * SVG resources consume on every shape element as
 * {@code stroke-linecap="var(--lucide-stroke-linecap, round)"}).
 * <p>
 * Caps are only visible on open stroke ends; a closed path (denoted by
 * {@code z} in path data) has no caps.
 */
public enum LucideLineCap {

	BUTT("butt"),
	ROUND("round"),
	SQUARE("square");

	private final String value;

	LucideLineCap(String value) {
		this.value = value;
	}

	/**
	 * Returns the value used for the CSS custom property.
	 *
	 * @return the SVG {@code stroke-linecap} value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Looks up the constant for the given value.
	 *
	 * @param value the SVG {@code stroke-linecap} value
	 * @return the matching constant, or an empty {@link Optional} if none
	 *         matches
	 */
	public static Optional<LucideLineCap> from(String value) {
		for (LucideLineCap cap : values()) {
			if (cap.value.equals(value)) {
				return Optional.of(cap);
			}
		}
		return Optional.empty();
	}

	@Override
	public String toString() {
		return value;
	}
}
