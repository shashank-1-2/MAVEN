package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class stringReverseTest {
    
    private stringReverse reverser;
    
    @BeforeEach
    void setUp() {
        reverser = new stringReverse();
    }
    
    // ========== BASIC TESTS ==========
    
    @Test
    @DisplayName("Reverse a simple word")
    void testReverseSimpleWord() {
        String result = reverser.reverseString("hello");
        assertEquals("olleh", result);
    }
    
    @Test
    @DisplayName("Reverse a word with capital letters")
    void testReverseWithCapitals() {
        String result = reverser.reverseString("Java");
        assertEquals("avaJ", result);
    }
    
    @Test
    @DisplayName("Reverse a sentence")
    void testReverseSentence() {
        String result = reverser.reverseString("Hello World");
        assertEquals("dlroW olleH", result);
    }
    
    // ========== EDGE CASES ==========
    
    @Test
    @DisplayName("Reverse a single character")
    void testReverseSingleCharacter() {
        String result = reverser.reverseString("a");
        assertEquals("a", result);
    }
    
    @Test
    @DisplayName("Reverse a palindrome (should be same)")
    void testReversePalindrome() {
        String result = reverser.reverseString("racecar");
        assertEquals("racecar", result);
    }
    
    @Test
    @DisplayName("Reverse string with numbers")
    void testReverseWithNumbers() {
        String result = reverser.reverseString("12345");
        assertEquals("54321", result);
    }
    
    @Test
    @DisplayName("Reverse string with special characters")
    void testReverseWithSpecialChars() {
        String result = reverser.reverseString("!@#$%");
        assertEquals("%$#@!", result);
    }
    
    // ========== NULL AND EMPTY TESTS ==========
    
    @Test
    @DisplayName("Reverse null string should return null")
    void testReverseNull() {
        String result = reverser.reverseString(null);
        assertNull(result);
    }
    
    @Test
    @DisplayName("Reverse empty string should return empty string")
    void testReverseEmptyString() {
        String result = reverser.reverseString("");
        assertEquals("", result);
    }
    
    // ========== PARAMETERIZED TESTS ==========
    
    @ParameterizedTest
    @DisplayName("Reverse multiple strings")
    @CsvSource({
        "apple, elppa",
        "code, edoc",
        "Maven, nevaM",
        "123abc, cba321",
        "Hello World, dlroW olleH"
    })
    void testReverseMultipleStrings(String input, String expected) {
        assertEquals(expected, reverser.reverseString(input));
    }
    
    @ParameterizedTest
    @DisplayName("Reversing twice should return original")
    @ValueSource(strings = {"hello", "world", "Java", "testing", "abc123"})
    void testReverseTwice(String input) {
        String reversed = reverser.reverseString(input);
        String doubleReversed = reverser.reverseString(reversed);
        assertEquals(input, doubleReversed);
    }
    
    @ParameterizedTest
    @DisplayName("Null and empty values")
    @NullAndEmptySource
    void testReverseNullOrEmpty(String input) {
        assertEquals(input, reverser.reverseString(input));
    }
}