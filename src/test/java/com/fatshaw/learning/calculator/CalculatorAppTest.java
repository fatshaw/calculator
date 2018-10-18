package com.fatshaw.learning.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorAppTest {

    @Test
    public void input_1_1_1_should_return_stack_1_1_1() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("stack: 1 1 1", calculatorApp.processLine("1 1 1"));
    }

    @Test
    public void input_1_k_should_return_error() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("wrong input token:k at position:3", calculatorApp.processLine("1 k"));
    }

    @Test
    public void input_0_1_divide_should_return_stack_0_1() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("stack: 0 1", calculatorApp.processLine("0 1 /"));
    }

    @Test
    public void input_1_1_plus_should_return_stack_2() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("stack: 2", calculatorApp.processLine("1 1 +"));
    }

    @Test
    public void input_1_1_1_plus_plus_should_return_stack_3() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("stack: 3", calculatorApp.processLine("1 1 1 + +"));
    }

    @Test
    public void input_1_1_1_plus_plus_undo_should_return_stack_1_2() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("stack: 1 2", calculatorApp.processLine("1 1 1 + + undo"));
    }

    @Test
    public void input_2_sqrt_should_return_stack_1_4142135623() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("stack: 1.4142135623", calculatorApp.processLine("2 sqrt"));
    }

    @Test()
    public void input_1_2_3_multiply_5_plus_multiply_multiply_6_5_should_return_operator_multiply_position_15__insucient_parameters() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("operator * (position: 15): insufficient parameters\nstack: 11",
            calculatorApp.processLine("1 2 3 * 5 + * * 6 5"));
    }

    @Test
    public void input_2_3_5_multiply_and_undo_should_return_2_3_5() {
        CalculatorApp calculatorApp = new CalculatorApp();
        assertEquals("stack: 2 15",
            calculatorApp.processLine("2 3 5 *"));
        assertEquals("stack: 2 3 5",
            calculatorApp.processLine("undo"));
    }
}
