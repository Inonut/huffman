package huffman.domain;

public class Node implements Comparable<Node>{
	
	protected int frequency
	
	protected Node() {}

	public int getFrequency() {
		return frequency
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency
	}
	
	public int compareTo(Node o) {
		return frequency-o.getFrequency()
	}
}
