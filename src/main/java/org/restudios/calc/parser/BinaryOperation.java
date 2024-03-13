package org.restudios.calc.parser;

import org.restudios.calc.expressions.NumberExpression;
import org.restudios.calc.expressions.numbers.FloatExpression;
import org.restudios.calc.expressions.numbers.IntegerExpression;
import org.restudios.calc.lexer.TokenType;

public class BinaryOperation implements Evaluable {

    public Evaluable left;
    public TokenType operator;
    public Evaluable right;

    public BinaryOperation(Evaluable left, TokenType operator, Evaluable right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public NumberExpression eval() {
        left = left.eval();
        right = right.eval();

        if (left instanceof IntegerExpression leftI) {
            if (right instanceof IntegerExpression rightI) {
                int result = evalOperation(leftI.value, rightI.value);
                return new IntegerExpression(result);
            } else if (right instanceof FloatExpression rightF) {
                float result = evalOperation(leftI.value, rightF.value);
                return new FloatExpression(result);
            }
        } else if (left instanceof FloatExpression leftF) {
            if (right instanceof IntegerExpression rightI) {
                float result = evalOperation(leftF.value, rightI.value);
                return new FloatExpression(result);
            } else if (right instanceof FloatExpression rightF) {
                float result = evalOperation(leftF.value, rightF.value);
                return new FloatExpression(result);
            }
        }
        throw new UnsupportedOperationException("Unsupported types or operation");
    }

    private int evalOperation(int left, int right) {
        return switch (operator) {
            case PLUS -> left + right;
            case MINUS -> left - right;
            case MUL -> left * right;
            case DIV -> left / right;
            case POW -> (int) Math.pow(left, right);
            case MOD -> left % right;
            default -> throw new UnsupportedOperationException("Unsupported operation");
        };
    }

    private float evalOperation(float left, float right) {
        return switch (operator) {
            case PLUS -> left + right;
            case MINUS -> left - right;
            case MUL -> left * right;
            case DIV -> left / right;
            case POW -> (float) Math.pow(left, right);
            default -> throw new UnsupportedOperationException("Unsupported operation");
        };
    }

    @Override
    public String toString() {
        return STR."(\{left} \{operator.text} \{right})";
    }
}
