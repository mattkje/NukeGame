package no.matkje.tools;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Dictionary {
    private List<String> words;
    private String dictionary;

    public Dictionary(String dictionary){
        this.dictionary = dictionary;
        this.words = new ArrayList<>();
    }

    public List<String> readWordsOfList() throws IOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/no/matkje/dictionaries/" + dictionary + ".txt");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bf = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bf.readLine()) != null && !line.isEmpty()) {
                words.add(line);
            }
            return words;
        }
    }

    public List<String> getWords(){
        return words;
    }

    public boolean isInDictionary(String word) {
        return words.contains(word);
    }
}
