package org.restudios.calc.functions;

import org.restudios.calc.expressions.NumberExpression;

public interface Function {
    NumberExpression evaluate(NumberExpression input);
}
