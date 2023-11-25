package no.matkje.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<String> words;
    private String dictionary;
    private List<String> usedWords;

    public Dictionary(String dictionary) throws IOException {
        this.dictionary = dictionary;
        this.words = readWordsOfList();
        this.usedWords = new ArrayList<>();
    }

    public List<String> readWordsOfList() throws IOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/no/matkje/dictionaries/"
                + dictionary + ".txt");
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

    private boolean isInDictionary(String word) {
        if(usedWords.contains(word)) return false;
        else return words.contains(word);
    }

    public void usedWords(String word){
        if(isInDictionary(word)) usedWords.add(word);
    }
}
