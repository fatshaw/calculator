package com.fatshaw.learning.refactoring.calculator;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface CalculatorConsumer<T, U> extends BiConsumer<T, U> {

}
