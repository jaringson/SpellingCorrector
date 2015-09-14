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
		Trie test2 = new Trie();
		test2.add("Jaron");
		test2.add("Dania");
		test2.add("Jaron");
		test2.add("Joseph");
		test2.add("Josh");
		test2.add("Gary");
		test2.add("ZeBRa");
		test2.add("ZeBRa");
		test2.add("Ze");
		test2.add("ZeBRa");
		test2.add("Ze");
		System.out.println(test2.equals(test));
		//test.toString();
		//System.out.println(test.toString());
		//System.out.println("here");
	}
	Node root;
	int nodecount;
	int wordcount;
	{
		root = new Node();
		root.current = root;
		root.current2 = root;
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
		int prime = 31;
		return prime * wordcount + prime ^ nodecount;
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
		if(this.wordcount != obj.wordcount || this.nodecount != obj.nodecount){
			return false;
		}
		return equalsRec(this.root, obj.root);	
	}
}

