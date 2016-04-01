package huffman.huffman.domain;

import huffman.domain.Node;

import java.util.List;

public class Leaf extends Node {
	
	private int symbol;
	private List<Integer> code;
	
	public Leaf(int symbol){
		this(symbol,1);
	}
	
	public Leaf(int symbol, int frequency) {
		this.symbol = symbol;
		this.frequency = frequency;
	}

	public int getSymbol() {
		return symbol;
	}

	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}

	public List<Integer> getCode() {
		return code;
	}

	public void setCode(List<Integer> code) {
		this.code = code;
	}
}
