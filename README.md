## Calculator

#### Example:
```java
public class Test {
    public static void main(String[] args) {

        // with custom variables
        {
            String script = "sin( sqrt( 5 ^ 5 / 5 * 5 - 5 + 5 % 5 ) ) * x";
            Lexer lexer = new Lexer(script);
            Parser parser = new Parser(lexer.lex());

            // declare variable
            parser.getVariableContext().declare("x", 255);

            double result = parser.parse().eval().doubleValue();
        }
        // without custom variables
        {
            double result = Calculator.calculate("sin( sqrt( 5 ^ 5 / 5 * 5 - 5 + 5 % 5 ) )");
        }
    }
}
```
