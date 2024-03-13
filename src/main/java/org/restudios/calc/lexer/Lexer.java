package org.restudios.calc.lexer;

import java.util.ArrayList;

public class Lexer {
    String text;
    int position;

    public Lexer(String text) {
        this.text = text;
    }
    public ArrayList<Token> lex(){
        ArrayList<Token> tokens = new ArrayList<>();

        while (text.length() > position){
            String s = text.substring(position);
            Token expression = parseExpression(s);
            position += expression.content.length();
            if(expression.type != TokenType.WHITESPACE) tokens.add(expression);
        }

        return tokens;
    }

    private Token parseExpression(String s) {
        if(s.charAt(0) == ' ' || s.charAt(0) == '\t' || s.charAt(0) == '\r'){
            return new Token(TokenType.WHITESPACE, " ", position);
        }
        if(s.charAt(0) == '(') return new Token(TokenType.O_BRACKET, "(", position);
        if(s.charAt(0) == ')') return new Token(TokenType.C_BRACKET, ")", position);
        Token pi = parsePi(s);
        if(pi != null) return pi;
        String operators = "+-/*^%";
        if(Character.isDigit(s.charAt(0))){
            return parseNumber(s);
        }else if(operators.contains(STR."\{s.charAt(0)}")){
            return parseOperator(s);
        }else{
            return parseIdentifier(s);
        }
    }

    private Token parsePi(String s) {
        char next = s.length() > 2 ? s.toCharArray()[2] : '\0';
        if(s.toLowerCase().startsWith("pi") && !Character.isAlphabetic(next)){
            return new Token(TokenType.PI, "pi", position);
        }else if(s.startsWith(TokenType.PI.text)){
            return new Token(TokenType.PI, TokenType.PI.text, position);
        }
        return null;
    }

    private Token parseIdentifier(String s) {
        StringBuilder name = new StringBuilder();
        for (char c : s.toCharArray()) {
            if(Character.isAlphabetic(c)){
                name.append(c);
                continue;
            }
            if(Character.isDigit(c) && (!name.isEmpty())){
                name.append(c);
                continue;
            }
            if(c == '(' || c == ' ') break;
        }
        return new Token(TokenType.IDENTIFIER, name.toString(), position);
    }

    private Token parseOperator(String s) {
        String e = STR."\{s.toCharArray()[0]}";
        TokenType type = null;
        if(e.equals("+"))type = TokenType.PLUS;
        if(e.equals("-"))type = TokenType.MINUS;
        if(e.equals("/"))type = TokenType.DIV;
        if(e.equals("*"))type = TokenType.MUL;
        if(e.equals("^"))type = TokenType.POW;
        if(e.equals("%"))type = TokenType.MOD;
        if(type == null){
            throw new RuntimeException(STR."Illegal operator: \{e}");
        }
        return new Token(type, e, position);
    }

    private Token parseNumber(String s) {
        StringBuilder exp = new StringBuilder();
        boolean hasDecimal = false;
        boolean hasExponent = false;
        boolean isHex = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String hexCharacters = "0123456789abcdef";
            if(isHex && hexCharacters.contains(STR."\{Character.toLowerCase(c)}")){
                exp.append(c);
                continue;
            }
            if (Character.isDigit(c) || (i == 1 && c == 'x')) {
                exp.append(c);
                if (i == 1 && c == 'x') { // Check if it's a hex number
                    isHex = true;
                }
            } else if (!isHex && ((c == '.' && !hasDecimal) || (Character.toLowerCase(c) == 'e' && !hasExponent))) {
                exp.append(c);
                if (c == '.') {
                    hasDecimal = true;
                } else {
                    hasExponent = true;
                }
            } else {
                break;
            }
        }

        return new Token(TokenType.NUMBER, exp.toString(), position);
    }

}
