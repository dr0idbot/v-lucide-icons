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
 * Supports customization of color, size, and stroke width.
 */
public class LucideSvgIcon extends SvgIcon {

	private static final long serialVersionUID = 1L;

	private static final String RESOURCE_PATH = "lucide/";
	private static final double DEFAULT_STROKE_WIDTH = 2.0;
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
