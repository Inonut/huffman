package huffman.domain;

import java.util.List;

import huffman.util.Util;

public class Leaf extends Node {
	
	private int symbol
	private List<Integer> code
	
	public Leaf(int symbol){
		this(symbol,1)
	}
	
	public Leaf(int symbol, int frequency) {
		this.symbol = symbol
		this.frequency = frequency
	}

	int getSymbol() {
		return symbol
	}

	void setSymbol(int symbol) {
		this.symbol = symbol
	}

	List<Integer> getCode() {
		return code
	}

	void setCode(List<Integer> code) {
		this.code = code
	}
}
