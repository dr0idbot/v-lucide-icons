package io.github.dr0idbot.vlucide;

import java.util.regex.Pattern;

import com.vaadin.flow.component.icon.SvgIcon;

/**
 * A Vaadin component that renders a Lucide SVG icon using the native
 * {@code <vaadin-icon>} web component.
 * <p>
 * Extends {@link SvgIcon} to inherit Vaadin's icon infrastructure: theming,
 * sizing, click handling, and tooltip support. SVGs are loaded via the
 * {@code src} attribute and rendered inside the web component's shadow DOM.
 * <p>
 * Supports customization of color, size, stroke width, fill, and the
 * stroke-cap and stroke-join shapes. Fill, stroke width, line cap, and
 * line join are driven by CSS custom properties that override the
 * var()-parameterized paint attributes baked into the generated SVG
 * resources (see {@code scripts/generate_icons.py}).
 */
public class LucideSvgIcon extends SvgIcon {

	private static final long serialVersionUID = 1L;

	private static final String RESOURCE_PATH = "lucide/";
	private static final double DEFAULT_STROKE_WIDTH = 2.0;
	private static final String DEFAULT_FILL = "none";
	private static final Pattern ICON_NAME_PATTERN = Pattern.compile("^[a-z0-9-]+$");

	/**
	 * Creates a new LucideSvgIcon for the given icon name.
	 *
	 * @param iconName the Lucide icon file name (without .svg extension);
	 *                 lowercase letters, digits, and dashes only
	 * @throws IllegalArgumentException if the icon name is null, blank, or
	 *                                  contains characters other than
	 *                                  lowercase letters, digits, and dashes
	 */
	public LucideSvgIcon(String iconName) {
		validateIconName(iconName);
		setSrc(RESOURCE_PATH + iconName + ".svg");
	}

	private static void validateIconName(String iconName) {
		if (iconName == null || iconName.isBlank() || !ICON_NAME_PATTERN.matcher(iconName).matches()) {
			throw new IllegalArgumentException(
					"Invalid Lucide icon name: " + iconName
					+ " (expected lowercase letters, digits, or dashes, e.g. \"arrow-big-up\")");
		}
	}

	/**
	 * Sets the stroke width of the icon using Vaadin's native
	 * {@code --vaadin-icon-stroke-width} CSS custom property, which is applied by
	 * the {@code <vaadin-icon>} shadow DOM.
	 *
	 * @param strokeWidth the stroke width in pixels
	 */
	public void setStrokeWidth(double strokeWidth) {
		String value = strokeWidth == (int) strokeWidth ? String.valueOf((int) strokeWidth)
				: String.valueOf(strokeWidth);
		getStyle().set("--vaadin-icon-stroke-width", value);
	}

	/**
	 * Returns the current stroke width.
	 *
	 * @return the stroke width value, or 2.0 if not set
	 */
	public double getStrokeWidth() {
		String sw = getStyle().get("--vaadin-icon-stroke-width");
		if (sw == null || sw.isEmpty()) {
			return DEFAULT_STROKE_WIDTH;
		}
		return Double.parseDouble(sw);
	}

	/**
	 * Sets the fill paint of the icon shapes via the {@code --lucide-fill}
	 * CSS custom property, which the generated SVG resource consumes on
	 * every shape element as {@code fill="var(--lucide-fill, none)"}.
	 * <p>
	 * The Lucide canon is {@code none} (outline style); passing a color
	 * solidly fills closed path areas. Because the property is a CSS custom
	 * property, it inherits into the {@code <vaadin-icon>} shadow DOM on
	 * Vaadin 24.x and 25.x alike; static usage of the SVG files without any
	 * of the variables set (e.g. {@code <img>} links) keeps the baked-in
	 * fallback instead.
	 *
	 * @param fill the CSS value to use for {@code fill}, or {@code null} to
	 *             restore the default ({@code none})
	 */
	public void setFill(String fill) {
		if (fill == null) {
			getStyle().remove("--lucide-fill");
		} else {
			getStyle().set("--lucide-fill", fill);
		}
	}

	/**
	 * Returns the current fill paint.
	 *
	 * @return the CSS value set via {@link #setFill(String)}, or
	 *         {@code "none"} if not set
	 */
	public String getFill() {
		String fill = getStyle().get("--lucide-fill");
		if (fill == null || fill.isEmpty()) {
			return DEFAULT_FILL;
		}
		return fill;
	}

	/**
	 * Sets the stroke end cap shape via the {@code --lucide-stroke-linecap}
	 * CSS custom property, consumed by the generated SVG on every shape
	 * element as {@code stroke-linecap="var(--lucide-stroke-linecap, round)"}.
	 * <p>
	 * Note that caps are only visible on open stroke ends; a closed path has
	 * no caps at all.
	 *
	 * @param linecap the cap shape, or {@code null} to restore the Lucide
	 *                default ({@link LucideLineCap#ROUND})
	 */
	public void setStrokeLinecap(LucideLineCap linecap) {
		if (linecap == null) {
			getStyle().remove("--lucide-stroke-linecap");
			return;
		}
		getStyle().set("--lucide-stroke-linecap", linecap.getValue());
	}

	/**
	 * Returns the current stroke end cap shape.
	 *
	 * @return the value set via {@link #setStrokeLinecap(LucideLineCap)}, or
	 *         {@link LucideLineCap#ROUND} if not set
	 */
	public LucideLineCap getStrokeLinecap() {
		String value = getStyle().get("--lucide-stroke-linecap");
		if (value == null || value.isEmpty()) {
			return LucideLineCap.ROUND;
		}
		return LucideLineCap.from(value).orElse(LucideLineCap.ROUND);
	}

	/**
	 * Sets the stroke vertex join shape via the
	 * {@code --lucide-stroke-linejoin} CSS custom property, consumed by the
	 * generated SVG on every shape element as
	 * {@code stroke-linejoin="var(--lucide-stroke-linejoin, round)"}.
	 * <p>
	 * Note that joins are only visible at sharp bends between segments;
	 * bends drawn as arcs hide joins entirely.
	 *
	 * @param linejoin the join shape, or {@code null} to restore the Lucide
	 *                 default ({@link LucideLineJoin#ROUND})
	 */
	public void setStrokeLinejoin(LucideLineJoin linejoin) {
		if (linejoin == null) {
			getStyle().remove("--lucide-stroke-linejoin");
			return;
		}
		getStyle().set("--lucide-stroke-linejoin", linejoin.getValue());
	}

	/**
	 * Returns the current stroke vertex join shape.
	 *
	 * @return the value set via {@link #setStrokeLinejoin(LucideLineJoin)}, or
	 *         {@link LucideLineJoin#ROUND} if not set
	 */
	public LucideLineJoin getStrokeLinejoin() {
		String value = getStyle().get("--lucide-stroke-linejoin");
		if (value == null || value.isEmpty()) {
			return LucideLineJoin.ROUND;
		}
		return LucideLineJoin.from(value).orElse(LucideLineJoin.ROUND);
	}

	/**
	 * Sets the icon color via the CSS {@code color} property.
	 * <p>
	 * Lucide icons are stroke-based ({@code stroke="currentColor"}), so
	 * {@code color} is used instead of {@code fill} to control the stroke color.
	 *
	 * @param color the CSS color value (e.g. {@code "#ef4444"}, {@code "red"})
	 */
	@Override
	public void setColor(String color) {
		getStyle().set("color", color);
	}

	/**
	 * Returns the current icon color.
	 *
	 * @return the CSS color value, or {@code null} if not set
	 */
	@Override
	public String getColor() {
		return getStyle().get("color");
	}

	/**
	 * Marks this icon as decorative (presentational only).
	 * <p>
	 * Decorative icons are hidden from screen readers and assistive technology. Use
	 * this for purely visual icons that don't convey meaningful information.
	 *
	 * @param decorative true to mark as decorative, false to remove
	 */
	public void setDecorative(boolean decorative) {
		if (decorative) {
			getElement().setAttribute("aria-hidden", "true");
			getElement().setAttribute("role", "presentation");
		} else {
			getElement().removeAttribute("aria-hidden");
			getElement().removeAttribute("role");
		}
	}

	/**
	 * Sets the accessible name of this icon via the {@code aria-label}
	 * attribute.
	 * <p>
	 * Screen readers announce the label instead of the icon resource path.
	 * Use this when the icon conveys information on its own; for purely
	 * decorative icons, prefer {@link #setDecorative(boolean)} instead.
	 *
	 * @param label the accessible name, or {@code null} to remove the
	 *              attribute
	 */
	public void setAriaLabel(String label) {
		if (label == null) {
			getElement().removeAttribute("aria-label");
		} else {
			getElement().setAttribute("aria-label", label);
		}
	}

	/**
	 * Returns the accessible name of this icon.
	 *
	 * @return the {@code aria-label} attribute value, or {@code null} if not
	 *         set
	 */
	public String getAriaLabel() {
		return getElement().getAttribute("aria-label");
	}

}
