package de.soulfoxer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        try {
            String contentLang = Files.readString(Path.of(Main.class.getResource("source.mylang").toURI()));
            System.out.println("Gelesener Inhalt: " + contentLang);

            Lexer lexer = new Lexer();
            List<Token> tokenList =  lexer.lex(contentLang);
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}