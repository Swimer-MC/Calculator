package org.restudios.calc.expressions;

import org.restudios.calc.expressions.numbers.FloatExpression;
import org.restudios.calc.lexer.TokenType;

public class PiExpression extends NumberExpression {
    @Override
    public NumberExpression eval() {
        return new FloatExpression(Math.PI);
    }

    @Override
    public double doubleValue() {
        return Math.PI;
    }

    @Override
    public String toString() {
        return TokenType.PI.text;
    }
}
