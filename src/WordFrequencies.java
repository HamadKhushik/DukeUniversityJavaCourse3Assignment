import edu.duke.FileResource;

import java.util.ArrayList;

/**
 * You will write a program to determine the word that occurs the most often in a file.
 * If more than one word occurs as the most often, then return the first such word found.
 * You should make all words lowercase before counting them. Thus, “This” and “this” will
 * both be counted as the lowercase version of “this”. You should not consider punctuation,
 * so “end” and “end,” will be considered different words
 */

public class WordFrequencies {
    // finds out how many times each unique word occurs in a file
    // ignores the punctuation i-e 'the' and 'the.' are classed as two words

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        // finds unique words in a file and adds in myWords,
        // also updates each occurrence in myFreqs at respective index

        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freqs = myFreqs.get(index);
                myFreqs.set(index, freqs + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        System.out.println("unique words \t" + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + '\t' + myFreqs.get(i));
        }
        int max = findIndexOfMax();
        System.out.println("The most common word is " + myWords.get(max) + " which appears " + myFreqs.get(max));
    }

    public int findIndexOfMax() {
        // finds the index of maximum value i-e the index of most occurring word
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (max < myFreqs.get(i)) {
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
