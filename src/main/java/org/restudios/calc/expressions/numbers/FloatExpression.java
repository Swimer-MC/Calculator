package org.restudios.calc.expressions.numbers;

import org.restudios.calc.expressions.NumberExpression;

@SuppressWarnings("unused")
public class FloatExpression extends NumberExpression {
    public float value;

    public FloatExpression(float value) {
        this.value = value;
    }
    public FloatExpression(double value) {
        this.value = (float) value;
    }


    public float getValue() {
        return value;
    }

    public FloatExpression setValue(float value) {
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
