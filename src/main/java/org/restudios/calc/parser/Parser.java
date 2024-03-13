package org.restudios.calc.parser;

import org.restudios.calc.expressions.BracketExpression;
import org.restudios.calc.expressions.FunctionExpression;
import org.restudios.calc.expressions.PiExpression;
import org.restudios.calc.expressions.VariableExpression;
import org.restudios.calc.expressions.numbers.FloatExpression;
import org.restudios.calc.expressions.numbers.IntegerExpression;
import org.restudios.calc.lexer.Token;
import org.restudios.calc.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> code;
    private int pos = 0;
    private final VariableContext variableContext;
    private static final Token EOF = new Token(TokenType.EOF, "\r", Integer.MAX_VALUE);

    public Parser(ArrayList<Token> code) {
        this.code = code;
        variableContext = new VariableContext();
    }

    public VariableContext getVariableContext() {
        return variableContext;
    }

    public Evaluable parse(){
        return parsePlusMinus();
    }

    private Evaluable parsePlusMinus(){
        Evaluable left = parseMultiplyDivide();
        Token curr = curr();
        while (curr.type == TokenType.PLUS || curr.type == TokenType.MINUS) {
            consume();
            Evaluable right = parseMultiplyDivide();
            left = new BinaryOperation(left, curr.type, right);
            curr = curr();
        }
        return left;
    }

    private Evaluable parseMultiplyDivide(){
        Evaluable left = parsePower();
        Token curr = curr();
        while (curr.type == TokenType.MUL || curr.type == TokenType.DIV) {
            consume();
            Evaluable right = parsePower();
            left = new BinaryOperation(left, curr.type, right);
            curr = curr();
        }
        return left;
    }

    private Evaluable parsePower(){
        Evaluable left = parseModule();
        Token curr = curr();
        if(curr.type == TokenType.POW){
            consume();
            Evaluable right = parsePower();
            return new BinaryOperation(left, curr.type, right);
        }
        return left;
    }

    private Evaluable parseModule(){
        Evaluable left = parseBrackets();
        Token curr = curr();
        if(curr.type == TokenType.MOD){
            consume();
            Evaluable right = parseModule();
            return new BinaryOperation(left, curr.type, right);
        }
        return left;
    }
    private Evaluable parseBrackets(){
        if(curr().type == TokenType.O_BRACKET){
            consume();
            Evaluable evaluable = parse();
            if(curr().type != TokenType.C_BRACKET) {
                throw new RuntimeException(STR."Excepted ), got \{curr().content}");
            }
            consume();
            return new BracketExpression(evaluable);
        }
        return parseFunction();
    }

    private Evaluable parseFunction(){
        if(curr().type == TokenType.IDENTIFIER && next().type == TokenType.O_BRACKET){
            String name = pick().content;
            consume(TokenType.O_BRACKET);
            Evaluable content = parse();
            consume(TokenType.C_BRACKET);
            return new FunctionExpression(name, content);
        }
        return parseVariable();
    }

    private Evaluable parseVariable(){
        if(curr().type == TokenType.IDENTIFIER){
            String name = pick().content;
            return new VariableExpression(name, variableContext);
        }
        return parsePi();
    }

    private Evaluable parsePi(){
        if(curr().type == TokenType.PI){
            consume();
            return new PiExpression();
        }
        return parseNumber();
    }
    private Evaluable parseNumber(){
        consume(TokenType.NUMBER);
        Token p = getN(-1);
        if(p.content.startsWith("0x")){
            String r16 = p.content.substring(2);
            return new IntegerExpression(Integer.parseInt(r16, 16));
        }else if (p.content.contains(".")){
            return new FloatExpression(Float.parseFloat(p.content));
        }else{
            return new IntegerExpression(Integer.parseInt(p.content));
        }
    }
    private void consume(TokenType excepted){
        Token pick = pick();
        if(pick.type == excepted) return;
        throw new RuntimeException(STR."[\{pos}] Excepted \{excepted.text}, got \{pick.content}");
    }
    private void consume(){
        pos++;
    }
    private Token pick(){
        return code.get(pos++);
    }
    private Token getN(int p){
        if(code.size() <= pos+p) return EOF;
        return code.get(pos+p);
    }
    private Token next(){
        return getN(1);
    }
    private Token curr(){
        return getN(0);
    }
}
