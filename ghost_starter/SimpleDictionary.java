package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        String getanyword =null;
        HashMap<String,Integer> getAnyWordMax = new HashMap<>();
        if(prefix != "" ) {
            for (String s : words) {
                if (s.startsWith(prefix)) {
                    getAnyWordMax.put(s, s.length());
                    int maxlength = 0;

                    if (s.length() > maxlength) {
                        maxlength = s.length();
                        getanyword = s;
                    }
                }
            }

        }
        else{
            getanyword = "t";
        }
        return getanyword;
      //  return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }
}
