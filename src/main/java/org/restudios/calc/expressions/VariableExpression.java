package org.restudios.calc.expressions;

import org.restudios.calc.parser.VariableContext;

public class VariableExpression extends NumberExpression {
    public String name;
    VariableContext context;

    public VariableExpression(String name, VariableContext context) {
        this.name = name;
        this.context = context;
    }

    @Override
    public NumberExpression eval() {
        if(!context.contains(name)){
            throw new RuntimeException(STR."Variable \{name} not found");
        }
        return context.get(name).eval();
    }

    @Override
    public double doubleValue() {
        return context.get(name).eval().doubleValue();
    }

    @Override
    public String toString() {
        return name;
    }
}
