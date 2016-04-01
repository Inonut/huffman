package huffman.huffman.algotithm.help;

import huffman.domain.InternalNode;
import huffman.domain.Leaf;
import huffman.domain.Node;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	private InternalNode root;  
	private List<Leaf> leafs;
	
	public Tree(InternalNode root) {
		this.root = root;
		
		leafs = new ArrayList<Leaf>(); 
		makeCodes(root, new ArrayList<Integer>());
		leafs.sort((a,b)->a.getSymbol()-b.getSymbol());
	}
	
	
	private void makeCodes(Node node, List<Integer> code) {
		
		if (node instanceof Leaf) {
			Leaf leaf = (Leaf)node;
			leaf.setCode(new ArrayList<Integer>(code));
			leafs.add(leaf);
		} else if (node instanceof InternalNode) {
			InternalNode internalNode = (InternalNode) node;
			
			code.add(0);
			makeCodes(internalNode.getLeftChild() , code);
			code.remove(code.size() - 1);
			
			code.add(1);
			makeCodes(internalNode.getRightChild(), code);
			code.remove(code.size() - 1);
		}
	}
	
	public List<Integer> getCode(int symbol) {
		return leafs.get(symbol).getCode();
	}

	public InternalNode getRoot() {
		return root;
	}


	public void setRoot(InternalNode root) {
		this.root = root;
	}
}
