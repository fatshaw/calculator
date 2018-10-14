package com.fatshaw.learning.refactoring.regex;

import java.util.function.Function;

public interface Re {

    boolean match(String s, Function<String, Boolean> matcher);

    default boolean match(String s) {
        return match(s, b -> true);
    }

    static Re literal(String s) {
        return (b, matcher) -> s.equals(b);
    }

    static Re sequence(Re a, Re b) {
        return (s, matcher) -> {
            for (int i = 0; i < s.length() - 1; i++) {
                if (a.match(s.substring(0, i)) && b.match(s.substring(i))) {
                    return true;
                }
            }
            return false;
        };
    }

    static Re either(Re a, Re b) {
        return (s, matcher) -> a.match(s) || b.match(s);
    }

    static Re any() {
        return (s, matcher) -> true;
    }
}
