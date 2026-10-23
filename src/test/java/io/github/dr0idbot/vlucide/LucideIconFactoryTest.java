package io.github.dr0idbot.vlucide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LucideIconFactoryTest {

    @Test
    @DisplayName("create returns a non-null LucideSvgIcon")
    void create() {
        LucideSvgIcon icon = LucideIconFactory.create("save");
        assertNotNull(icon);
    }

    @Test
    @DisplayName("create throws for unknown icon")
    void createUnknown() {
        assertThrows(IllegalArgumentException.class,
                () -> LucideIconFactory.create("nonexistent-icon-xyz"));
    }

    @Test
    @DisplayName("getSvgContent returns non-empty SVG markup directly from the classpath")
    void getSvgContent() {
        String content = LucideIconFactory.getSvgContent("save");
        assertNotNull(content);
        assertFalse(content.isEmpty());
        assertTrue(content.contains("<svg"));
        assertTrue(content.contains("</svg>"));
        String again = LucideIconFactory.getSvgContent("save");
        assertEquals(content, again);
    }

    @Test
    @DisplayName("getSvgContent throws for unknown icon")
    void getSvgContentUnknown() {
        assertThrows(IllegalArgumentException.class,
                () -> LucideIconFactory.getSvgContent("nonexistent-icon-xyz"));
    }

    @Test
    @DisplayName("exists returns true for known icons")
    void exists() {
        assertTrue(LucideIconFactory.exists("save"));
        assertTrue(LucideIconFactory.exists("arrow-left"));
        assertTrue(LucideIconFactory.exists("user-round"));
    }

    @Test
    @DisplayName("exists returns false for unknown icons")
    void existsUnknown() {
        assertFalse(LucideIconFactory.exists("nonexistent-icon-xyz"));
    }
}
