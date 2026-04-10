package com.seir.algos.strings;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testIsPalindrome() {
        assertTrue(StringUtils.isPalindrome("racecar"));
        assertFalse(StringUtils.isPalindrome("world"));
        assertFalse(StringUtils.isPalindrome(""));
        assertFalse(StringUtils.isPalindrome(null));
    }

    @Test
    public void testFindAcronyms() {
        List<String> acronyms = StringUtils.findAcronyms("ABC", "ABCD");
        assertEquals(1, acronyms.size());
        assertTrue(acronyms.contains("ABC"));

        acronyms = StringUtils.findAcronyms("ABC", "sddCBADas456");
        assertEquals(1, acronyms.size());
        assertTrue(acronyms.contains("CBA"));

        acronyms = StringUtils.findAcronyms("ABC", "  a45tgereresBAC   asdfasdfABCklljkluur");
        assertEquals(2, acronyms.size());
        assertTrue(acronyms.contains("BAC"));
        assertTrue(acronyms.contains("ABC"));

        acronyms = StringUtils.findAcronyms("ABC", "");
        assertTrue(acronyms.isEmpty());
        acronyms = StringUtils.findAcronyms("ABC", null);
        assertTrue(acronyms.isEmpty());
        acronyms = StringUtils.findAcronyms("", "");
        assertTrue(acronyms.isEmpty());
        acronyms = StringUtils.findAcronyms(null, null);
        assertTrue(acronyms.isEmpty());

    }
}
