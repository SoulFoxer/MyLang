package de.soulfoxer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private int currentPosition = 0;
    private String input;
    private int lineNumber = 1;
    private int columnNumber = 0;


    public List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        this.input = input;

        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);

            switch (currentChar) {
                case ' ':
                case '\t':
                case '\r':
                    currentPosition++;
                    columnNumber++;
                    break;
                case '\n':
                    currentPosition++;
                    lineNumber++;
                    columnNumber = 0;
                    break;
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+", lineNumber, columnNumber));
                    currentPosition++;
                    columnNumber++;
                    break;
                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-", lineNumber, columnNumber));
                    currentPosition++;
                    columnNumber++;
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLY, "*", lineNumber, columnNumber));
                    currentPosition++;
                    columnNumber++;
                    break;
                case '/':

                default:
                    Token token = readIdentifier();
                    tokens.add(token);
                    break;
            }
        }
        return tokens;
    }

    private Token readIdentifier() {
        StringBuilder identifier = new StringBuilder();

        while (currentPosition < input.length() && Character.isLetterOrDigit(input.charAt(currentPosition))) {
            identifier.append(input.charAt(currentPosition));
            currentPosition++;
            columnNumber++;
        }

        String tokenValue = identifier.toString();
        return new Token(TokenType.IDENTIFIER, tokenValue, lineNumber, columnNumber);
    }
}
