package lesson_8_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author László Hágó
 * @version 1.0
 * @since 2016-06-21
 */
public class Synonymer {
    public HashMap<String, String> thesaurusEN;

    public Synonymer () throws IOException {
        thesaurusEN = new HashMap<>();
        for (String line : Files.readAllLines(Paths.get("thesaurus-sv_utf8.txt")))
            thesaurusEN.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1));
    }

    public String getSynonymer(String key) {
        return thesaurusEN.get(key);
    }
}