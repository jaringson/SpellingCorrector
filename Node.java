package spell;

import java.util.ArrayList;
import java.util.Collections;

public class Node implements ITrie.INode {
	
	public Node[] children;
	public int frequency = 0;
	
	public static Node current2 = new Node();
	
	public Node(){
		//System.out.println("constructor");
		//nodecount = nodecount + 1;
		//nodecount = 1;
		//words = new ArrayList<String>();
		children = new Node[26];
	}
	
	/*public void setChild(int loc){
		children = new Node[26];
		Node temp = children[loc];
	}*/
	
	
	public void addFrequncy(){
		frequency++;
	}
	
	@Override
	public int getValue() {
		return frequency;
	}

}
