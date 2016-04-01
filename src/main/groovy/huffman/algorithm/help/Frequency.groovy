package huffman.algorithm.help

import huffman.domain.InternalNode
import huffman.domain.Leaf
import huffman.domain.Node
import huffman.util.BusinessConstant

public class Frequency {
	
	private int[] frequencies
	
	public Frequency() {
		frequencies = new int[BusinessConstant.NR_CHAR]
	}
	
	public void increment(int symbol) {
		frequencies[symbol]++
	}
	
	public Tree buildTree(int base) {
		Queue<Node> pqueue = new PriorityQueue<Node>()

		frequencies.eachWithIndex{ int entry, int i -> entry > 0 ? pqueue.add(new Leaf(i, entry)) : null}

		while (pqueue.size() > 1) {
			List<Node> nodes = []
			(1..base).each {
				if(pqueue.size() > 0){
					nodes += pqueue.remove()
				} else {
					nodes += new Leaf(-1,0)
				}
			}
			pqueue.add(new InternalNode(nodes, nodes.sum {it.frequency} as int))
		}
		
		return new Tree(pqueue.remove() as InternalNode)
	}

	int[] getFrequencies() {
		return frequencies
	}

	void setFrequencies(int[] frequencies) {
		this.frequencies = frequencies
	}
}
