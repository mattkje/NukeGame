package no.matkje.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private List<String> words;
    private String dictionary;
    private List<String> usedWords;
    private List<String> prompts;

    public Dictionary(String dictionary) {
        this.dictionary = dictionary;
        this.words = readWordsOfList();
        this.usedWords = new ArrayList<>();
    }

    public List<String> readWordsOfList() {

        try (InputStream inputStream = getClass().getResourceAsStream("/no/matkje/dictionaries/"
                + dictionary + ".txt");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bf = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bf.readLine()) != null && !line.isEmpty()) {
                words.add(line);
            }
            return words;
        } catch (IOException e) {
          throw new RuntimeException(e);
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

    public void createPrompts(int wpp) {
        Random random = new Random();

        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
                for (char c3 = 'a'; c3 <= 'z'; c3++) {

                    char thirdChar = random.nextBoolean() ? '\0' : c3;
                    String prompt = "" + c1 + c2 + thirdChar;
                    prompts.add(prompt);
                }
            }
        }

        for (String prompt : prompts) {
            prompt.contains("hey");
        }
    }


}
