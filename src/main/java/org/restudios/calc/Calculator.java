package org.restudios.calc;

import org.restudios.calc.lexer.Lexer;
import org.restudios.calc.parser.Parser;
@SuppressWarnings("unused")
public class Calculator {
    public static Parser parse(String code){
        return new Parser(new Lexer(code).lex());
    }
    public static double calculate(String code){
        return parse(code).parse().eval().doubleValue();
    }
}
