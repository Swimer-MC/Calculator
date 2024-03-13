package org.restudios.calc.lexer;

public class Token {
    public final TokenType type;
    public final String content;
    public final int position;

    public Token(TokenType type, String content, int position) {
        this.type = type;
        this.content = content;
        this.position = position;
    }

    @Override
    public String toString() {
        return STR."[\{type} \{content}]";
    }
}
