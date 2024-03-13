package org.restudios.calc.expressions.numbers;

import org.restudios.calc.expressions.NumberExpression;
@SuppressWarnings("unused")
public class IntegerExpression extends NumberExpression {
    public int value;

    public IntegerExpression(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public IntegerExpression setValue(int value) {
        this.value = value;
        return this;
    }

    @Override
    public NumberExpression eval() {
        return this;
    }

    @Override
    public String toString() {
        return STR."\{value}";
    }

    @Override
    public double doubleValue() {
        return value;
    }
}
