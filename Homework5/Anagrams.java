import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Marc Sulsenti
//I pledge my honor that I have abided by the Stevens Honor System
public class Anagrams {
	final Integer[] primes =
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};

	 final char[] letters = {'a', 'b', 'c','d','e','f','g','h','i','j','k','l','m','n',
	'o','p','q','r','s','t','u','v','w','x','y','z'};



	Map<Character,Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	public void buildLetterTable() {
		//This method builds the Letter to Integer hashmap table
		//Increment through both lists at the same time since equivalent indexes correspond to the right number-integer pairs
		letterTable = new HashMap<Character,Integer>();
		for (int i = 0; i < 26; i++) {
			//incrementing through both lists and putting them into the hashmap
			letterTable.put(letters[i], primes[i]);
		}
	}

		Anagrams(){
		/*
		Constructor method for Anagrams that creates the LetterTable hashmap and creates a new anagramTable object.
		 */
			buildLetterTable();
			anagramTable = new HashMap<Long, ArrayList<String>>();
		}

		public void addWord (String s){
		/*
		This method adds the given word from the input file to the anagramTable hashmap
		 */
			long key = myHashCode(s);

			//If the key exists (anagram case)
			if(anagramTable.containsKey(key)){
				//Add the anagram (word) to the key's list value
				anagramTable.get(key).add(s);

			}
			//If the hash key does not exist in the map (new word case)
			else{

				//Create a new anagramList, add the word to it and put it in the map.
				ArrayList<String> newWordList = new ArrayList<>();
				newWordList.add(s);
				anagramTable.put(key, newWordList);
			}
		}

		public long myHashCode (String s){
		/*
		This method creates the hash code of the inputted string
		 */
			long key = 1;
			if (s == null || s.equals("")) {
				throw new IllegalArgumentException("String can not be empty");
			}
			for (char c : s.toCharArray()) {
				key *= letterTable.get(c);
			}
			return key;
		}

		public void processFile (String s) throws IOException {
			FileInputStream fstream = new FileInputStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				this.addWord(strLine);
			}
			br.close();
		}

	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		/*
		This method  returns the entires within the anagramTable hashmap with the largest number of anagrams
		 */
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int maxCount = 0;

		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			//This forloop itterates through anagram table, finding the largest number of anagrams
			ArrayList<String> anagrams = entry.getValue();
			int count = anagrams.size();
			if (count > maxCount) {
				maxEntries.clear();
				maxEntries.add(entry);
				maxCount = count;
			} else if (count == maxCount) {
				maxEntries.add(entry);
			}
		}
		return maxEntries;
	}





	public static void main (String[]args){
		//MAIN
			Anagrams a = new Anagrams();

			final long startTime = System.nanoTime();
			try {
				a.processFile("words_alpha.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
			final long estimatedTime = System.nanoTime() - startTime;
			final double seconds = ((double) estimatedTime / 1000000000);
			System.out.println("Time: " + seconds);
			System.out.println("List of max anagrams: " + maxEntries);
		}
	}
