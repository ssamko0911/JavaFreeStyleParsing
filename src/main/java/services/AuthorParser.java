package services;

import entities.Author;
import enums.BookRecordLabel;
import util.AppLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorParser {
    public final static String PATTERN = ",\\s*(?![^()]*\\))";
    private static final AppLogger logger = AppLogger.getInstance();

    public List<Author> parseAuthor(String authorLine){
        List<Author> authors = new ArrayList<>();

        if(authorLine.isEmpty()){
            return authors;
        }

        String[] authorArray = authorLine.split(AuthorParser.PATTERN);

        for(String author : authorArray){
            author = author.trim();
            try {
                if (author.contains("(") && author.contains(")")) {
                    String name = author.substring(0, author.indexOf("(")).trim();
                    String rolesAsString = author.substring(author.indexOf("(") + 1, author.indexOf(")")).trim();
                    List<String> roles = Arrays.stream(rolesAsString.split(","))
                            .map(String::trim)
                            .toList();
                    authors.add(new Author(name, roles));
                } else {
                    authors.add(new Author(author, List.of()));
                }

                AuthorParser.logger.info(String.format(BookRecordLabel.NEW_AUTHOR_LOG.getLabel(), author));
            } catch (StringIndexOutOfBoundsException e) {
                AuthorParser.logger.warn("Failed to parse author: " + author);
                authors.add(new Author(author, List.of()));
            }
        }


        return authors;
    }
}
