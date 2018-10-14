package com.fatshaw.learning.calculator;

import java.util.function.BiConsumer;

/**
 * calculator biconsume
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface CalculatorConsumer<T, U> extends BiConsumer<T, U> {

}
