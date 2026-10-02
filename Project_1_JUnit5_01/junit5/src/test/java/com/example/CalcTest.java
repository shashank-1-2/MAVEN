package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class CalcTest {
    
    private Calc calc;
    
    @BeforeEach
    void setUp() {
        calc = new Calc();
    }
    
    @Test
    @DisplayName("Test division of positive numbers")
    void testDividePositiveNumbers() {
        int result = calc.divide(10, 2);
        assertEquals(5, result, "10 / 2 should equal 5");
    }
    
    @Test
    @DisplayName("Test division by zero throws exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(10, 0);
        }, "Division by zero should throw ArithmeticException");
    }
    
    @Test
    @DisplayName("Test division of negative numbers")
    void testDivideNegativeNumbers() {
        int result = calc.divide(-10, 2);
        assertEquals(-5, result, "-10 / 2 should equal -5");
    }
    
    @Test
    @DisplayName("Test division where result is zero")
    void testDivideResultZero() {
        int result = calc.divide(0, 5);
        assertEquals(0, result, "0 / 5 should equal 0");
    }
}