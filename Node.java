package spell;

import java.util.ArrayList;
import java.util.Collections;

public class Node implements ITrie.INode {
	
	public Node[] children;
	public int frequency;
	public static Node current = new Node();
	public static Node current2 = new Node();
	public static int nodecount;
	public static ArrayList<String> words = new ArrayList<String>();
	
	public Node(){
		//System.out.println("constructor");
		nodecount++;
		children = new Node[26];
	}
	
	public void setChild(int loc){
		children = new Node[26];
		Node temp = children[loc];
	}
	
	
	Boolean addRec(String word){
		if(word.length() != 0){
			//System.out.println("here");
			char c = word.charAt(0);
			//System.out.println(c);
			int index = c - 'a';
			//System.out.println(index);
			if(current.children[index] == null){
				//System.out.println("here2");
				current.children[index] = new Node();
				current = current.children[index];
			}
			else{
				//System.out.println("here3");
				current = current.children[index];
			}
			word = word.substring(1, word.length());
			addRec(word);
			return true;
		}
		else{
			return true;
		}
	}
	public void add(String word){
		word = word.toLowerCase();
		if(!words.contains(word)){
			words.add(word);
		}
		addRec(word);
		current.addFrequncy();
	}
	
	Node findRec(String word){
		if(word.length() != 0){
			//System.out.println("here");
			char c = word.charAt(0);
			//System.out.println(c);
			int index = c - 'a';
			//System.out.println(index);
			if(current.children[index] == null ){
				return null;
			}
			else{

				//System.out.println("here3");
				current = current.children[index];
			}
			word = word.substring(1, word.length());
			return findRec(word);
		}
		else if (current.frequency == 0){
			return null;
		}
		else{
			return current;
		}
	}
	public Node find(String word){
		word = word.toLowerCase();
		return findRec(word);
	}
	
	public void addFrequncy(){
		frequency++;
	}
	
	
	
	public String toString(){
		Collections.sort(words);
		StringBuilder output = new StringBuilder();
		for(int i = 0; i <words.size();i++){
			output.append(words.get(i));
			output.append("\n");
		}
		return output.toString();
	}
	
	@Override
	public int getValue() {
		return frequency;
	}

}
