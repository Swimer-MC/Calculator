package org.restudios.calc.expressions;

import org.restudios.calc.parser.Evaluable;

public class BracketExpression implements Evaluable {
    Evaluable evaluable;

    public BracketExpression(Evaluable evaluable) {
        this.evaluable = evaluable;
    }

    @Override
    public NumberExpression eval() {
        return evaluable.eval();
    }

    @Override
    public String toString() {
        return STR."(\{evaluable})";
    }
}
