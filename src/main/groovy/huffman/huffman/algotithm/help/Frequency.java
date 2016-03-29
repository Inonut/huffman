package huffman.huffman.algotithm.help;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import huffman.domain.InternalNode;
import huffman.domain.Leaf;
import huffman.domain.Node;
import huffman.util.Util;

public class Frequency {
	
	private int[] frequencies;
	
	public Frequency() {
		frequencies = new int[257];
		Arrays.fill(frequencies, 1);
	}
	
	public void increment(int symbol) {
		frequencies[symbol]++;
	}
	
	public Tree buildTree() {
		Queue<Node> pqueue = new PriorityQueue<Node>();
		
		for (int i = 0; i < frequencies.length; i++) {
			pqueue.add(new Leaf(i, frequencies[i]));
		}
		
		while (pqueue.size() > 1) {
			Node n1 = pqueue.remove();
			Node n2 = pqueue.remove();
			pqueue.add(new InternalNode(n1, n2, n1.getFrequency()+n2.getFrequency()));
		}
		
		return new Tree((InternalNode)pqueue.remove());
	}
}
