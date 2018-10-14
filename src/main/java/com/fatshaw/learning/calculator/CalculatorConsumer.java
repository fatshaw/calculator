package com.fatshaw.learning.calculator;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface CalculatorConsumer<T, U> extends BiConsumer<T, U> {

}
