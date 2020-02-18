import edu.duke.FileResource;

import java.util.ArrayList;

/**
 * Write a program to print out the main characters in one of Shakespeare’s plays,
 * those with the most speaking parts. You should identify a speaking part by reading
 * the file line-by-line and finding the location of the first period on the line.
 * Then you will assume that everything up to the first period is the name of a character
 * and count how many times that occurs in the file. You will only print those characters
 * that appear more often than others. Notice our method is somewhat error prone.
 * For example, a period is also used to indicate the end of a sentence. By printing out
 * only those characters that appear a lot, we will get rid of most of the errors.
 * Periods that indicate the end of a sentence will likely be a unique phrase so you won’t
 * print that as it would just occur once or maybe twice.
 */

public class CharactersInPlay {

    private ArrayList<String> characters;
    private ArrayList<Integer> characterCount;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        characterCount = new ArrayList<Integer>();
    }

    public void update(String person) {
        //update the two ArrayLists, adding the character’s name if it is not already there,
        // and counting this line as one speaking part for this person

        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            characterCount.add(1);
        } else {
            int cCount = characterCount.get(index);
            characterCount.set(index, cCount + 1);
        }
    }

    public void findAllCharacters() {
        //opens a file, and reads the file line-by-line. For each line, if there is a period on the line,
        // extract the possible name of the speaking part, and call 'update' to count it as an occurrence for
        // this person

        characters.clear();
        characterCount.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            line = line.trim();
            int index = line.indexOf('.');
            if (index != -1) {
                String name = line.substring(0, index);
                update(name);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        //print out the names of all those characters that have exactly number speaking parts,
        // where number is greater than or equal to num1 and less than or equal to num2

        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (characterCount.get(i) <= num2 && characterCount.get(i) >= num1) {
                System.out.println(characters.get(i) + '\t' + characterCount.get(i));
            }
        }
    }

    public int findIndexOfMax() {
        // finds the index of maximum value i-e the index of most speaking character

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < characterCount.size(); i++) {
            if (max < characterCount.get(i)) {
                max = characterCount.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void tester() {
        /*findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(characters.get(i) + '\t' + characterCount.get(i));
        }*/
        charactersWithNumParts(10, 15);
        //int max = findIndexOfMax();
        //System.out.println("Character " + characters.get(max) + " has most speaking parts i-e " +
        //        characterCount.get(max));
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}
