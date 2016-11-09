package com.google.engedu.anagrams;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> listLengthMatch = new ArrayList<String>();
    ArrayList<String> listAnagram = new ArrayList<String>();
    ArrayList<String> listLengthMatchSort = new ArrayList<String>();
    HashMap<Integer,ArrayList<String>> hm = new HashMap<Integer,ArrayList<String>>();
    HashSet<String> hs = new HashSet<String>();
    HashMap<String,Integer> inputhm = new HashMap<>();
    HashMap<String,Integer> currenthm = new HashMap<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            list.add(word);
            hs.add(word);
            if(hm.containsKey(word.length())){
                hm.get(word.length()).add(word);

            }
            else{
                hm.put(word.length(),new ArrayList<String>());
                hm.get(word.length()).add(word);
            }
            System.out.print(word);
            Log.d("word",word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        return true;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        char[] charTargetWord = targetWord.toCharArray();
        String sortedTargetWord = sortLetters(targetWord);
        int sortedTargetWordlength = sortedTargetWord.length();
        for(int i =0;i <targetWord.length();i++){
            if(inputhm.get(targetWord.charAt(i))  > 0 ){
           /*     int count =inputhm.get(targetWord.charAt(i)).intValue();
                count+=1;
                inputhm.put(targetWord.substring(i,i+1),count);
           */
          inputhm.put(targetWord.substring(i,i+1) ,inputhm.get(targetWord.charAt(i))+1 );
            }
            else{
                inputhm.put(targetWord.substring(i,i+1),1);
            }
       // inputhm.put(s,count);
        }

        for(int i =0 ; i < list.size();i++){
            if(list.get(i).length() ==sortedTargetWordlength){
                if(sortLetters(list.get(i)).equals(sortedTargetWord) ){
                    result.add(list.get(i));
                }


            }
        }


        return result;


    }

    public String sortLetters(String testString){
        int len = testString.length();
        char[] charinput = testString.toCharArray();
        Arrays.sort(charinput);
     //   String sortedcharinput = charinput.toString();
       String sortedcharinput = new String(charinput);
        return  sortedcharinput;
        }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        int wordlen = word.length()+1;
        for(String currentWord : hm.get(wordlen ) ){
            for(int i =0; i < currentWord.length();i++){
                if(currenthm.containsKey(currentWord.charAt(i))){
                    int count =currenthm.get(currentWord.charAt(i)).intValue();
                    count+=1;
                    currenthm.put(currentWord.substring(i,i+1),count);
                }
                else{
                    currenthm.put(currentWord.substring(i,i+1),1);
                }
            }
            boolean flag = true;
            for(int i=0; i<word.length();i++) {
                if ( inputhm.get(word.charAt(i)) <= currenthm.get(word.charAt(i)) & !currentWord.contains(word) & flag ){
                    result.add(currentWord);
                }
                else{
                     flag = false ;
                }
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
        return "sstsosp";
    }
}
