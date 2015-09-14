package spell;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {
	private Trie dictionary = new Trie();
	private ArrayList<String> guesses = new ArrayList<String>(100); 
	@Override
	public void useDictionary(String dictionaryFileName) throws IOException {
		Scanner reader = new Scanner(new BufferedInputStream(new FileInputStream(dictionaryFileName)));
		
		String word = new String();
		while(reader.hasNext()){
			word = reader.next();
			dictionary.add(word);
		}
		//System.out.println(dictionary.toString());
		//System.out.println(word);
		//System.out.println("here");
		
	}

	
	
	@Override
	public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
		if(dictionary.find(inputWord) != null){
			return inputWord;
		}
		
		deletion(inputWord);
		transposition(inputWord);
		alteration(inputWord);
		insertion(inputWord);
		int winner = 0;
		int max = 0;
		for(int i = 0; i < guesses.size();i++){
			System.out.println("here1");
			Node temp = (Node)dictionary.find(guesses.get(i));
			if(temp != null){
				if(temp.getValue() > max){
					max = temp.getValue();
					winner = i;
				}
				System.out.println(temp.getValue()+" "+guesses.get(i));
			}
		}
		if(max == 0){
			System.out.println("here2");
			for(int i = 0;i < guesses.size();i++){
				deletion(guesses.get(i));
				transposition(guesses.get(i));
				alteration(guesses.get(i));
				insertion(guesses.get(i));
			}
			for(int i = 0; i < guesses.size();i++){
				
				Node temp = (Node)dictionary.find(guesses.get(i));
				if(temp != null){
					if(temp.getValue() > max){
						max = temp.getValue();
						winner = i;
					}
					System.out.println(temp.getValue()+" "+guesses.get(i));
				}
			}
		}
		if(max == 0){
			return null;
		}
		return guesses.get(winner);
	}
	private void insertion(String inputWord) {
		ArrayList<String> container = new ArrayList<String>();
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < inputWord.length()+1;i++){
			for(int j = 0; j < 26;j++){
				StringBuilder temp = new StringBuilder();
				if(i==0){
					temp = temp.append(alpha.charAt(j)).append(inputWord);
				}
				else if(i == inputWord.length()+1){
					temp = temp.append(inputWord).append(alpha.charAt(j));
				}
				else{
					temp = temp.append(inputWord.substring(0, i)).append(alpha.charAt(j)).append(inputWord.substring(i, inputWord.length()));
				}
				container.add(temp.toString());
				System.out.println(temp);
			}
		}
		for(int i = 0; i< container.size();i++){
			guesses.add(container.get(i));
		}
	}
	private void alteration(String inputWord) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		ArrayList<String> container = new ArrayList<String>();
		for(int i = 0; i < inputWord.length();i++){
			for(int j = 0; j < 26;j++){
				StringBuilder temp = new StringBuilder();
				if(i==0){
					temp = temp.append(alpha.charAt(j)).append(inputWord.substring(i+1, inputWord.length()));
				}
				else{
					temp = temp.append(inputWord.substring(0, i)).append(alpha.charAt(j)).append(inputWord.substring(i+1, inputWord.length()));
				}
				container.add(temp.toString());
				//System.out.println(temp);
			}
		}
		for(int i = 0; i< container.size();i++){
			guesses.add(container.get(i));
		}
	}
	private void transposition(String inputWord) {
		ArrayList<String> container = new ArrayList<String>();
		for(int i = 0; i < inputWord.length()-1;i++){
			int j = i +1;
			char front = inputWord.charAt(j);
			char back = inputWord.charAt(i);
			StringBuilder temp = new StringBuilder();
			if (i == 0){
				temp = temp.append(front).append(back).append(inputWord.substring(2, inputWord.length()));
			}
			else if(i == inputWord.length()-2){
				temp = temp.append(inputWord.substring(0, inputWord.length()-2)).append(front).append(back);
			}
			else{
				temp = temp.append(inputWord.substring(0, i)).append(front).append(back).append(inputWord.substring(i+2, inputWord.length()));
			}
			container.add(temp.toString());
			//System.out.println(temp.toString());
		}
		for(int i = 0; i< container.size();i++){
			guesses.add(container.get(i));
		}
		
	}
	private void deletion(String inputWord){
		ArrayList<String> container = new ArrayList<String>();
		for(int i = 0; i < inputWord.length();i++){
			String temp;
			if(i == 0 ){
				temp = inputWord.substring(1, inputWord.length());
			}
			else if(i == inputWord.length() -1){
				temp = inputWord.substring(0, inputWord.length()-1);
			}
			else{
				temp = (inputWord.substring(0, i) + inputWord.substring(i+1, inputWord.length()));
			}
			container.add(temp);
			//System.out.println(temp);
		}
		for(int i = 0; i< container.size();i++){
			guesses.add(container.get(i));
		}
	}

}
