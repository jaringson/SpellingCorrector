package spell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Trie implements ITrie {
	
	
	public static void main(String[] args) {
		Trie test = new Trie();
		test.add("cares");
		test.add("cares");
		/*test.add("caress");
		test.add("caress");*/
		/*test.add("Joseph");
		test.add("Josh");
		test.add("Gary");
		test.add("ZeBRa");
		test.add("ZeBRa");
		test.add("Ze");
		test.add("ZeBRa");
		test.add("Ze");*/
		Trie test2 = new Trie();
		test2.add("caress");
		test2.add("caress");
		/*test2.add("Dania");
		test2.add("Jaron");
		test2.add("Joseph");
		test2.add("Josh");
		test2.add("Gary");
		test2.add("ZeBRa");
		test2.add("ZeBRa");
		test2.add("Ze");
		test2.add("ZeBRa");
		test2.add("Ze");*/
		//System.out.println(test2.equals(test));
		//test.toString();
		//System.out.println(test.root.nodecount);
		System.out.println(test.getNodeCount());
		System.out.println(test2.getNodeCount());
		//System.out.println(test.getWordCount() + "Here1");
		//System.out.println(test2.getWordCount()+ "Here2");
		System.out.println(test.find("cares"));
		//System.out.println("here");
	}
	Node root;
	public static Node current = new Node();
	public ArrayList<String> words = new ArrayList<String>();
	int nodecount = 1;
	
	{
		root = new Node();
		current = root;
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
				nodecount++;
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
			//System.out.println("here4");
			current.addFrequncy();
			return true;
		}
	}
	
	@Override
	public void add(String word){
		//System.out.println(word);
		current = root;
		word = word.toLowerCase();
		if(!words.contains(word)){
			words.add(word);
		}
		addRec(word);
		//current.addFrequncy();
		//current.frequency++;
	}

	
	Node findRec(String word){
		if(word.length() != 0){
			//System.out.println("here");
			char c = word.charAt(0);
			System.out.println(c);
			int index = c - 'a';
			//System.out.println(index);
			if(current.children[index] == null ){
				return null;
			}
			else{

				System.out.println("here3");
				current = current.children[index];
			}
			word = word.substring(1, word.length());
			return findRec(word);
		}
		else if (current.getValue() == 0){
			System.out.println("here2");
			return null;
		}
		else{
			return current;
		}
	}
	@Override
	public INode find(String word) {
		word = word.toLowerCase();
		current = root;
		return findRec(word);
	}

	@Override
	public int getWordCount() {
		return words.size();
	}

	@Override
	public int getNodeCount() {
		System.out.println(nodecount);
		return nodecount;
	}
	
	@Override
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
	public int hashCode(){
		int prime = 31;
		return prime * /*root.words.size()*/ + prime ^ nodecount;
	}
	
	private boolean equalsRec(Node root1, Node root2) {
		
		if(root1.frequency != root2.frequency){
			//System.out.println("here");
			return false;
		}
		
		for(int i = 0; i < 26;i++){
			
			if(!((root1.children[i] == null) == (root1.children[i] == null))){
				return false;
			}
			else if (root1.children[i] != null && root2.children[i] != null){
				Boolean test = equalsRec(root1.children[i], root2.children[i]);
				if(test == false){
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(this == o){
			return true;
		}
		if(this.getClass() != o.getClass()){
			return false;
		}
		Trie obj = (Trie)o;
		if(this.getWordCount() != obj.getWordCount() || this.getNodeCount() != obj.getNodeCount()){
			return false;
		}
		return equalsRec(this.root, obj.root);	
	}
}

