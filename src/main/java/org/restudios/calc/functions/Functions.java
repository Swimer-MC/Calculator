package org.restudios.calc.functions;

import org.restudios.calc.expressions.numbers.FloatExpression;

import java.util.HashMap;

public class Functions {
    public static HashMap<String, Function> functions;
    static {
        functions = new HashMap<>();

        functions.put("sqrt", input -> new FloatExpression( Math.sqrt( input.doubleValue() ) ) );
        functions.put("sin", input -> new FloatExpression( Math.sin( input.doubleValue() ) ) );
        functions.put("cos", input -> new FloatExpression( Math.cos( input.doubleValue() ) ) );
        functions.put("tg", input -> new FloatExpression( Math.tan( input.doubleValue() ) ) );
        functions.put("ctg", input -> new FloatExpression( Math.toRadians(Math.cos(input.doubleValue()))/Math.toRadians(Math.sin(input.doubleValue())) ) );
        functions.put("rad", input -> new FloatExpression( Math.toRadians(input.doubleValue() ) ) );
    }
}
