package spell;

import java.util.Map;
import java.util.HashMap;

public class Trie implements ITrie {
	
	
	public static void main(String[] args) {
		Trie test = new Trie();
		test.add("Jaron");
		test.add("Dania");
		test.add("Jaron");
		test.add("Joseph");
		test.add("Josh");
		test.add("Gary");
		test.add("ZeBRa");
		test.add("ZeBRa");
		test.add("Ze");
		test.add("ZeBRa");
		test.add("Ze");
		
		System.out.println(test.toString());
		//System.out.println("here");
	}
	Node root;
	int nodecount;
	int wordcount;
	{
		root = new Node();
		root.current = root;
	}

	@Override
	public void add(String word) {
		wordcount++;
		root.add(word);
		root.current = root;
	}

	@Override
	public INode find(String word) {
		root.current = root;
		return root.find(word);
	}

	@Override
	public int getWordCount() {
		return wordcount;
	}

	@Override
	public int getNodeCount() {
		return root.nodecount;
	}
	
	@Override
	public String toString(){
		return root.toString();
	}
	
	@Override
	public int hashCode(){
		return 0;
	}
	
	@Override
	public boolean equals(Object o){
		return false;
	}

}

