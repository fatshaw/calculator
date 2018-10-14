package com.fatshaw.learning.refactoring.calculator;

import java.math.BigDecimal;
import java.util.List;

public enum MathOperator {
    PLUS() {
        public BigDecimal operate(List<BigDecimal> args) {
            return args.get(0).add(args.get(1));
        }

        @Override
        public int needParameterNumber() {
            return 2;
        }
    },

    MINUS() {
        public BigDecimal operate(List<BigDecimal> args) {
            return args.get(0).subtract(args.get(1));
        }

        @Override
        public int needParameterNumber() {
            return 2;
        }
    },
    MULTIPLY() {
        public BigDecimal operate(List<BigDecimal> args) {
            return args.get(0).multiply(args.get(1));
        }

        @Override
        public int needParameterNumber() {
            return 2;
        }
    },
    DIVIDE() {
        public BigDecimal operate(List<BigDecimal> args) {
            return args.get(0).divide(args.get(1));
        }

        @Override
        public int needParameterNumber() {
            return 2;
        }
    },
    SQRT() {
        public BigDecimal operate(List<BigDecimal> args) {
            return new BigDecimal(Math.sqrt(args.get(0).doubleValue()));
        }

        @Override
        public int needParameterNumber() {
            return 1;
        }

    };


    public abstract BigDecimal operate(List<BigDecimal> args);

    public abstract int needParameterNumber();

    public static MathOperator create(String name) {
        switch (name) {
            case "+":
                return PLUS;
            case "-":
                return MINUS;
            case "*":
                return MULTIPLY;
            case "/":
                return DIVIDE;
            case "sqrt":
                return SQRT;

        }
        return null;
    }
}
