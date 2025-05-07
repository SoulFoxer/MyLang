package de.soulfoxer;

public enum TokenType {
    // Keywords
    IF("IF"),
    ELSE("ELSE"),
    WHILE("WHILE"),
    FOR("FOR"),
    FUNCTION("FUNCTION"),
    RETURN("RETURN"),

    VAR("VAR"),

    // Logical operators
    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    // Comparison operators
    EQUAL("EQUAL"),
    NOT_EQUAL("NOT_EQUAL"),

    GREATER("GREATER"),
    LESS("LESS"),
    GREATER_EQUAL("GREATER_EQUAL"),
    LESS_EQUAL("LESS_EQUAL"),
    // Assignment
    ASSIGN("ASSIGN"),
    // Punctuation
    OPEN_PAREN("("),
    CLOSE_PAREN(")"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    OPEN_BRACKET("["),
    CLOSE_BRACKET("]"),
    // Comments
    SINGLE_LINE_COMMENT("//"),

    MULTI_LINE_COMMENT("/*"),
    MULTI_LINE_COMMENT_END("*/"),
    // String operators
    CONCATENATE("CONCATENATE"),

    INCREMENT("INCREMENT"),
    DECREMENT("DECREMENT"),
    // Ternary operator
    TERNARY("TERNARY"),
    // Type casting



    // Operators
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),

    // Delimiters
    SEMICOLON(";"),
    COMMA(","),
    DOT("."),
    COLON(":"),

    // Identifiers
    IDENTIFIER("IDENTIFIER"),

    // Literals
    INTEGER_LITERAL("INTEGER_LITERAL"),
    FLOAT_LITERAL("FLOAT_LITERAL"),
    STRING_LITERAL("STRING_LITERAL"),
    BOOLEAN_LITERAL("BOOLEAN_LITERAL"),

    ERROR("ERROR"),

    // Miscellaneous
    EOF("EOF");

    private final String type;

    TokenType(String type) {
        this.type = type;
    }
}
