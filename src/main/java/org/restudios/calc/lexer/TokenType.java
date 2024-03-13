package org.restudios.calc.lexer;

public enum TokenType {
    NUMBER("number"),
    IDENTIFIER,
    PI("π"),
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    POW("^"),
    MOD("%"),
    WHITESPACE(" "),
    EOF("eof"),
    O_BRACKET("("), C_BRACKET(")");
    public final String text;

    TokenType(String text) {
        this.text = text;
    }

    TokenType() {
        this.text = "";
    }
}
