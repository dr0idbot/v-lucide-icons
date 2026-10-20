package io.github.dr0idbot.vlucide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LucideSvgIconTest {

	@Test
	@DisplayName("default stroke width is 2.0")
	void defaultStrokeWidth() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		assertEquals(2.0, icon.getStrokeWidth(), 0.001);
	}

	@Test
	@DisplayName("setSize sets width and height")
	void setSize() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setSize("32px");
		assertEquals("32px", icon.getStyle().get("width"));
		assertEquals("32px", icon.getStyle().get("height"));
	}

	@Test
	@DisplayName("setColor sets CSS color for stroke-based icons")
	void setColor() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setColor("red");
		assertEquals("red", icon.getColor());
	}

	@Test
	@DisplayName("getColor returns null when not set")
	void defaultColor() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		assertNull(icon.getColor());
	}

	@Test
	@DisplayName("setStrokeWidth updates the --vaadin-icon-stroke-width variable")
	void setStrokeWidth() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setStrokeWidth(3.0);
		assertEquals(3.0, icon.getStrokeWidth(), 0.001);
	}

	@Test
	@DisplayName("setStrokeWidth with decimal value")
	void setStrokeWidthDecimal() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setStrokeWidth(1.5);
		assertEquals("1.5", icon.getStyle().get("--vaadin-icon-stroke-width"));
	}

	@Test
	@DisplayName("multiple customizations can be applied")
	void multipleCustomizations() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setSize("48px");
		icon.setColor("#ff0000");
		icon.setStrokeWidth(1.5);
		assertEquals("48px", icon.getStyle().get("width"));
		assertEquals("48px", icon.getStyle().get("height"));
		assertEquals("#ff0000", icon.getColor());
		assertEquals("1.5", icon.getStyle().get("--vaadin-icon-stroke-width"));
	}

	@Test
	@DisplayName("constructor rejects null icon name")
	void constructorRejectsNull() {
		assertThrows(IllegalArgumentException.class, () -> new LucideSvgIcon(null));
	}

	@Test
	@DisplayName("constructor rejects blank icon name")
	void constructorRejectsBlank() {
		assertThrows(IllegalArgumentException.class, () -> new LucideSvgIcon("   "));
	}

	@Test
	@DisplayName("constructor rejects uppercase icon name")
	void constructorRejectsUppercase() {
		assertThrows(IllegalArgumentException.class, () -> new LucideSvgIcon("SAVE"));
	}

	@Test
	@DisplayName("constructor rejects icon name with spaces or path separators")
	void constructorRejectsSpacesAndSeparators() {
		assertThrows(IllegalArgumentException.class, () -> new LucideSvgIcon("foo bar"));
		assertThrows(IllegalArgumentException.class, () -> new LucideSvgIcon("../evil"));
		assertThrows(IllegalArgumentException.class, () -> new LucideSvgIcon("$.png"));
	}

	@Test
	@DisplayName("constructor accepts valid names with digits and dashes")
	void constructorAcceptsValidNames() {
		LucideSvgIcon icon = new LucideSvgIcon("arrow-big-up");
		assertEquals("lucide/arrow-big-up.svg", icon.getSrc());
	}

	@Test
	@DisplayName("setAriaLabel sets the aria-label attribute")
	void setAriaLabel() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setAriaLabel("Save icon");
		assertEquals("Save icon", icon.getAriaLabel());
		assertEquals("Save icon", icon.getElement().getAttribute("aria-label"));
	}

	@Test
	@DisplayName("setAriaLabel(null) removes the aria-label attribute")
	void ariaLabelRemove() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setAriaLabel("Save icon");
		icon.setAriaLabel(null);
		assertNull(icon.getAriaLabel());
		assertNull(icon.getElement().getAttribute("aria-label"));
	}

	@Test
	@DisplayName("setDecorative hides from screen readers")
	void setDecorative() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setDecorative(true);
		assertEquals("true", icon.getElement().getAttribute("aria-hidden"));
		assertEquals("presentation", icon.getElement().getAttribute("role"));
	}

	@Test
	@DisplayName("setDecorative(false) removes accessibility attributes")
	void setDecorativeFalse() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		icon.setDecorative(true);
		assertNotNull(icon.getElement().getAttribute("aria-hidden"));
		icon.setDecorative(false);
		assertNull(icon.getElement().getAttribute("aria-hidden"));
		assertNull(icon.getElement().getAttribute("role"));
	}

	@Test
	@DisplayName("src attribute is set to lucide path")
	void srcAttribute() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		assertEquals("lucide/save.svg", icon.getSrc());
	}

	@Test
	@DisplayName("icon has vaadin-icon tag")
	void tagName() {
		LucideSvgIcon icon = LucideIconFactory.create("save");
		assertEquals("vaadin-icon", icon.getElement().getTag());
	}
}
