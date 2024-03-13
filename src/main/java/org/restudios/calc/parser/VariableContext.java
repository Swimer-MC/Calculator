package org.restudios.calc.parser;

import org.restudios.calc.expressions.NumberExpression;
import org.restudios.calc.expressions.numbers.FloatExpression;
import org.restudios.calc.expressions.numbers.IntegerExpression;

import java.util.HashMap;

@SuppressWarnings("unused")
public class VariableContext {
    private final HashMap<String, NumberExpression> variables;

    public VariableContext(HashMap<String, NumberExpression> variables) {
        this.variables = variables;
    }

    public VariableContext() {
        variables = new HashMap<>();
    }

    public NumberExpression get(String key) {
        return variables.get(key);
    }

    public void declare(String key, NumberExpression value) {
        variables.put(key, value);
    }
    public void declare(String key, float value) {
        variables.put(key, new FloatExpression(value));
    }
    public void declare(String key, double value) {
        variables.put(key, new FloatExpression(value));
    }
    public void declare(String key, int value) {
        variables.put(key, new IntegerExpression(value));
    }

    public boolean contains(String name) {
        return variables.containsKey(name);
    }
}
