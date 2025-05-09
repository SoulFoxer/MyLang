package de.soulfoxer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lexer {

    private int currentPosition = 0;
    private String input;
    private int lineNumber = 1;
    private int columnNumber = 0;

    private final Map<String, TokenType> keywords = Map.of(
            "if", TokenType.IF,
            "else", TokenType.ELSE,
            "while", TokenType.WHILE,
            "for", TokenType.FOR,
            "function", TokenType.FUNCTION,
            "return", TokenType.RETURN,
            "var", TokenType.VAR,
            "print", TokenType.PRINT
    );


    public List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        this.input = input;

        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);


            if (Character.isWhitespace(currentChar)) {
                advance();
                continue;
            }

            if (currentChar == '/') {
                if (peek() == '/') {
                    while (currentPosition < input.length() && input.charAt(currentPosition) != '\n') {
                        advance();
                    }
                    continue;
                } else if (peek() == '*') {
                    StringBuilder comment = new StringBuilder();
                    advanceAndIncreaseColumn();
                    advanceAndIncreaseColumn();
                    while (currentPosition < input.length() && !(input.charAt(currentPosition) == '*' && peek() == '/')) {

                        comment.append(input.charAt(currentPosition));
                        advance();
                    }
                    advanceAndIncreaseColumn();
                    tokens.add(new Token(TokenType.MULTI_LINE_COMMENT, comment.toString(), lineNumber, columnNumber));
                    continue;
                }
                tokens.add(new Token(TokenType.DIVIDE, "/", lineNumber, columnNumber));
                advanceAndIncreaseColumn();
            }

            if (Character.isDigit(currentChar)) {
                tokens.add(parseNumber());
            } else if (Character.isLetter(currentChar)) {
                tokens.add(parseIdentifier());
                continue;
            }


            switch (currentChar) {
                case '\n':
                    advance();
                    increaseLineNumber();
                    resetColumNumber();
                    break;
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+", lineNumber, columnNumber));
                    advanceAndIncreaseColumn();
                    break;
                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-", lineNumber, columnNumber));
                    advanceAndIncreaseColumn();
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLY, "*", lineNumber, columnNumber));
                    advanceAndIncreaseColumn();
                    break;
                case '(':
                    tokens.add(new Token(TokenType.OPEN_PAREN, "(", lineNumber, columnNumber));
                    advanceAndIncreaseColumn();
                    break;
                case ')':
                    tokens.add(new Token(TokenType.CLOSE_PAREN, ")", lineNumber, columnNumber));
                    advanceAndIncreaseColumn();
                    break;
                case '{':
                    tokens.add(new Token(TokenType.OPEN_BRACE, "{", lineNumber, columnNumber));
                    advanceAndIncreaseColumn();
                    break;
                case '}':
                    tokens.add(new Token(TokenType.CLOSE_BRACE, "}", lineNumber, columnNumber));
                    advanceAndIncreaseColumn();
                    break;
                case '<':
                    if (peek() == '=') {
                        tokens.add(new Token(TokenType.LESS_EQUAL, "<=", lineNumber, columnNumber));
                        advanceAndIncreaseColumn();
                        advanceAndIncreaseColumn();
                    } else {
                        tokens.add(new Token(TokenType.LESS, "<", lineNumber, columnNumber));
                        advanceAndIncreaseColumn();
                    }
                    break;
                    case '=':
                    if (peek() == '=') {
                        tokens.add(new Token(TokenType.EQUAL, "==", lineNumber, columnNumber));
                        advanceAndIncreaseColumn();
                        advanceAndIncreaseColumn();
                    } else {
                        tokens.add(new Token(TokenType.ASSIGN, "=", lineNumber, columnNumber));
                        advanceAndIncreaseColumn();
                    }
            }
        }
        return tokens;
    }

    private Token parseIdentifier() {
        StringBuilder identifier = new StringBuilder();

        if (Character.isDigit(input.charAt(currentPosition))) {
            throw new IllegalStateException("Identifiers cannot start with a digit");
        }

        while (currentPosition < input.length() && Character.isLetterOrDigit(input.charAt(currentPosition)) && startsWithLetter(input.charAt(currentPosition))) {
            identifier.append(input.charAt(currentPosition));
            advanceAndIncreaseColumn();
        }
        final String identifierString = identifier.toString();
        if (keywords.containsKey(identifierString)) {
            return createKeywordToken(identifierString);
        }


        return new Token(TokenType.IDENTIFIER, identifierString, lineNumber, columnNumber);
    }

    private boolean startsWithLetter(char c) {
        return Character.isLetter(c) || c == '_';
    }


    private void increaseLineNumber() {
        lineNumber++;
    }

    private void advanceColumnNumber() {
        columnNumber++;
    }

    private void resetColumNumber() {
        columnNumber = 0;
    }

    private void advance() {
        currentPosition++;
    }

    private char peek() {
        if (currentPosition + 1 >= input.length()) return '\0';
        return input.charAt(currentPosition + 1);
    }

    private Token createKeywordToken(String keyword) {
        TokenType type = keywords.get(keyword);
        if (type != null) {
            return createToken(type, keyword);
        }
        throw new IllegalStateException("Keyword " + keyword + " not found");
    }

    private Token createToken(TokenType type, String value) {
        return new Token(type, value, lineNumber, columnNumber);
    }
    private Token parseNumber() {
        StringBuilder builder = new StringBuilder();

        while (currentPosition < input.length() && Character.isDigit(input.charAt(currentPosition))) {
            builder.append(input.charAt(currentPosition));
            advanceAndIncreaseColumn();
        }
        return new Token(TokenType.INTEGER_LITERAL, builder.toString(), lineNumber, columnNumber);
    }

    private void advanceAndIncreaseColumn() {
        advance();
        advanceColumnNumber();
    }
}
