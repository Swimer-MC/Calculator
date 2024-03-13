package org.restudios.calc.expressions;

import org.restudios.calc.parser.Evaluable;
import org.restudios.calc.functions.Function;
import org.restudios.calc.functions.Functions;

import java.util.Map;

public class FunctionExpression implements Evaluable {
    String name;
    Evaluable arg;

    public FunctionExpression(String name, Evaluable arg) {
        this.name = name;
        this.arg = arg;
    }

    @Override
    public NumberExpression eval() {
        NumberExpression argument = arg.eval();
        for (Map.Entry<String, Function> fun : Functions.functions.entrySet()) {
            if(fun.getKey().equalsIgnoreCase(name)){
                return fun.getValue().evaluate(argument);
            }
        }
        throw new RuntimeException(STR."Function \{name} not found");
    }

    @Override
    public String toString() {
        String a = arg.toString();
        if(a.startsWith("(") && a.endsWith(")")){
            return STR."\{name}\{a}";
        }

        return STR."\{name}(\{arg})";
    }
}
