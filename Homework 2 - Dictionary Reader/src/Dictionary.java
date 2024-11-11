import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//Marc Sulsenti
//I pledge my honor that I have abided by the Stevens Honor System
public class Dictionary {
    //Variables
    private ArrayList <DictionaryItem> dictArrayList;
    private ArrayList<String> wordList;


    //Constructors
    public Dictionary() {
    try {
        /*Attempt to construct a new dictionary object, with two arraylists, both with
        * an initial capacity of 1300. We are assuming the text file that will be used is "ionDictionary.txt"
        * which contains less than 1300 words. */
        wordList = new ArrayList<String>(1300);
        dictArrayList = new ArrayList<DictionaryItem>(1300);
        readFile("ionDictionary.txt");
    }
    //File not found error
    catch(FileNotFoundException e){
        System.out.println("File not found");
    }

    }

    //Methods
    public Dictionary(String filename) {
        /*Attempt to construct a new dictionary object. Allows input of any txt file. Object  with two arraylists, both with
         * an initial capacity of 1300. We are assuming the text file that will be used is "ionDictionary.txt"
         * which contains less than 1300 words. */
        try {
            wordList = new ArrayList<String>(1300);
            dictArrayList = new ArrayList<DictionaryItem>(1300);
            readFile("ionDictionary.txt");
        }
        //File not found error catch
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public  void printMenu(){
        /* printMenu Creates the menu that the user can interact with to use any of the three options.
        * Only values that can be entered are integers 1,2,3. Any other number makes the method ask again.
        * Any non integer input catches a NumberFormatException*/
        System.out.println("Please choose one of the following menu items indicated with 1-3");
        System.out.println("1: To print all the words in the dictionary, choose 1");
        System.out.println("2: To search a word in the dictionary, choose 2");
        System.out.println("3: To quit the program, choose 3");
        Scanner scan = new Scanner(System.in);
        try {
            int choice = Integer.parseInt(scan.nextLine());
            if( choice == 1 || choice == 2 || choice == 3){
                processMenuItem(choice,scan);
            }
            else{
                System.out.println("Wrong number entered. 1, 2 or 3 must be entered. Try again");
                printMenu();
            }
        }
        catch(NumberFormatException e){
            throw new NumberFormatException("Did not enter an integer!");

        }
    }

    private boolean processMenuItem(int menuItem, Scanner scan){
        //1 Prints the every word within the dictionary
        //2 asks for the user for a word, then the program outputs how many times that word appears in the book
        //3 quits the program
        /* ProcessMenuItem takes in the user integer input from printMenu and processes it, with the options listed above. */
        if(menuItem == 1){
            /* Choice one prints out every word in the text file. Utilizes the printDictionary method
            to acomplish this  */
           printDictionary();
           printMenu();
        }
            if(menuItem == 2){
                /* Option two asks the user to input a word, they would like the count for.
                Calls the searchDictionary method to acomplish this.
                 */

                System.out.println("Please enter the word you would like to search for.");
                String word = scan.nextLine();
                if(wordList.contains(word)) {
                    System.out.println("The word  '" + word +"' occurred " + searchDictionary(word)+ " time(s) in the book!");
                    printMenu();
                }
                else {
                    System.out.println("The word '" + word + "' does not exist in the Ion Dictionary!.");
                    printMenu();
                }
            }
            if(menuItem == 3){
                //Quits the program
                System.out.println("Thank you for using Ion Dictionary! See you later!");
                return false;
            }
            //If anything else (shouldn't ever reach this) than quit.
            else{
                return true;
            }
    }

    public void readFile(String filename) throws FileNotFoundException {
        /*
        readFile takes an input file, from the one of the constructor methods, and reads that file.
        Assuming the file is ionDictionary.txt, we continiously  read each line of the file through this method,
        but utilize the splitStoreLine method to extract the two points of data we want from the text file. The word
        and the count of the word.
         */
        try {
            Scanner scan = new Scanner(new File(filename));
            // the first 4 lines of the file are useless.
            //assuming the file is "ionDictionary.txt"
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();

            while (scan.hasNextLine()) {
                //While there is a next line, split the next line into the two components of info needed
                splitStoreLine(scan);
            }
        } catch (FileNotFoundException e) {
            //File not found
            System.out.println("File not found: " + filename);
        }

    }

    private void splitStoreLine(Scanner scan){
        /* splitStoreLine method splits the lines in "ionDictionary.txt" into the two components of data
        * that is needed. The word and its count. All the words in the dictionary are put into word list.
        * While at the same time, another list dictArrayList holds objects containing every word and their count */
        String line = scan.nextLine();
        //converting the data into a list "parts" where parts[0] is the word and parts[1] is the count.
        String[] parts = line.split("\\|"); //splits the string into an array at the "|" character
        parts[0]=parts[0].trim(); //.trm() removes any trailing or leading white spaces
        parts[1]=parts[1].trim();
        String word = parts[0];
        int count = Integer.parseInt(parts[1]);
        wordList.add(word);
        dictArrayList.add(new DictionaryItem(word, count));
    }

    public void printDictionary(){
        //Prints out all the words in the dictionary. No counts
        System.out.println("All the words in the Ion book");
        System.out.println("Words");
        System.out.println("------");
        for (String word : wordList) {
            System.out.println(word);
        }

    }

    public int searchDictionary(String word){
        //searchDictionary is given a input word from the user. Uses binarySearch to find the index of this word
        // than uses the index to get the count of this specific word.
            return dictArrayList.get(binarySearch(this.wordList, word, 0, wordList.size())).getCount();
            //what if the word is not in the wordList? combat this
    }

    public static int binarySearch(ArrayList<String> wordList_search, String word, int low, int high) {
        //Binary search algorithim that returns the index at which the target "word" is at.
        //Two halves of the list are taken, look for where the word is in either half. Keep the
        // half the word is in, discard the other half. Recursively repeat this until we can return the index
        // in wordList_search of the word we are looking for.
        if (high < low) {
            return -1; // invalid input
        }
        int mid = (low + high) / 2;

        if (wordList_search.get(mid).compareTo(word) > 0) {
            return binarySearch(wordList_search, word, low, mid - 1);

        } else if (wordList_search.get(mid).compareTo(word) < 0) {

            return binarySearch(wordList_search, word, mid + 1, high);
        } else {
            return mid;
        }
    }

}
