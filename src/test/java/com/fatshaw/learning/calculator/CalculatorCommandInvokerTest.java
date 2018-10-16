package com.fatshaw.learning.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorCommandInvokerTest {

    @Test
    public void input_1_1_1_should_return_stack_1_1_1() {
        CalculatorCommandInvoker calculatorCommandParser = new CalculatorCommandInvoker();
        assertEquals("stack: 1 1 1", calculatorCommandParser.parse("1 1 1"));
    }

    @Test
    public void input_1_1_plus_should_return_stack_2() {
        CalculatorCommandInvoker calculatorCommandParser = new CalculatorCommandInvoker();
        assertEquals("stack: 2", calculatorCommandParser.parse("1 1 +"));
    }

    @Test
    public void input_1_1_1_plus_plus_should_return_stack_3() {
        CalculatorCommandInvoker calculatorCommandParser = new CalculatorCommandInvoker();
        assertEquals("stack: 3", calculatorCommandParser.parse("1 1 1 + +"));
    }

    @Test
    public void input_1_1_1_plus_plus_undo_should_return_stack_1_2() {
        CalculatorCommandInvoker calculatorCommandParser = new CalculatorCommandInvoker();
        assertEquals("stack: 1 2", calculatorCommandParser.parse("1 1 1 + + undo"));
    }

    @Test
    public void input_2_sqrt_should_return_stack_1_4142135623() {
        CalculatorCommandInvoker calculatorCommandParser = new CalculatorCommandInvoker();
        assertEquals("stack: 1.4142135623", calculatorCommandParser.parse("2 sqrt"));
    }

    @Test()
    public void input_1_2_3_multiply_5_plus_multiply_multiply_6_5_should_return_operator_multiply_position_15__insucient_parameters() {
        CalculatorCommandInvoker calculatorCommandParser = new CalculatorCommandInvoker();
        assertEquals("operator * (position: 15): insufficient parameters\nstack: 11",
            calculatorCommandParser.parse("1 2 3 * 5 + * * 6 5"));
    }

    @Test
    public void input_2_3_5_multiply_and_undo_should_return_2_3_5() {
        CalculatorCommandInvoker calculatorCommandParser = new CalculatorCommandInvoker();
        assertEquals("stack: 2 15",
            calculatorCommandParser.parse("2 3 5 *"));
        assertEquals("stack: 2 3 5",
            calculatorCommandParser.parse("undo"));
    }
}
