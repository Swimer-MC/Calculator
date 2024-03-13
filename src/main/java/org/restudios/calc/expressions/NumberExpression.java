package org.restudios.calc.expressions;

import org.restudios.calc.parser.Evaluable;

public abstract class NumberExpression implements Evaluable {

    public abstract double doubleValue();
}
