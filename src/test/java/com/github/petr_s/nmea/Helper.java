package com.github.petr_s.nmea;

import org.mockito.ArgumentMatcher;

import java.util.List;
import java.util.Set;

public class Helper {
    public static ArgumentMatcher<Double> roughlyEq(final double expected) {
        return roughlyEq(expected, 0.0001);
    }

    public static ArgumentMatcher<Double> roughlyEq(final double expected, final double delta) {
        return new ArgumentMatcher<Double>() {
            @Override
            public boolean matches(Double argument) {
                return argument != null && Math.abs(expected - argument) <= delta;
            }
        };
    }

    public static ArgumentMatcher<Float> roughlyEq(final float expected) {
        return roughlyEq(expected, 0.0001f);
    }

    public static ArgumentMatcher<Float> roughlyEq(final float expected, final float delta) {
        return new ArgumentMatcher<Float>() {
            @Override
            public boolean matches(Float argument) {
                return argument != null && Math.abs(expected - argument) <= delta;
            }
        };
    }

    public static <T> ArgumentMatcher<Set<T>> eq(final Set<T> expected) {
        return new ArgumentMatcher<Set<T>>() {
            @Override
            public boolean matches(Set<T> argument) {
                return expected.equals(argument);
            }
        };
    }

    public static <T> ArgumentMatcher<List<T>> eq(final List<T> expected) {
        return new ArgumentMatcher<List<T>>() {
            @Override
            public boolean matches(List<T> argument) {
                return expected.equals(argument);
            }
        };
    }
}
