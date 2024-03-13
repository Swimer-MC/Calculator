package org.restudios.calc.parser;

import org.restudios.calc.expressions.NumberExpression;

public interface Evaluable {
    NumberExpression eval();
}
