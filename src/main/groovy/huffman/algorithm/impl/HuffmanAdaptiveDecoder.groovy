package huffman.algorithm.impl

import huffman.algorithm.help.Frequency
import huffman.algorithm.help.Tree
import huffman.domain.InternalNode
import huffman.domain.Leaf
import huffman.domain.Node
import huffman.util.BitInputStream
import huffman.util.BusinessConstant
import huffman.util.Util;


public final class HuffmanAdaptiveDecoder {
	
	private BitInputStream input
	private OutputStream output
	
	private Tree tree
	
	private File fin
	private File fout
	private int base
	private int nrBitsOfBase
	
	public HuffmanAdaptiveDecoder(File fin, File fout, int base) {
		this.fin = fin
		this.fout = fout
		this.base = base
		this.nrBitsOfBase = Util.toBase2(base-1).size()
	}
	
	private int read() throws IOException {
		InternalNode currentNode = tree.getRoot();
		while (true) {
			int bit = input.readBit(nrBitsOfBase);

			Node nextNode = null;
			if(bit != -1) {
				nextNode = currentNode.children[bit]
			}else {
				return -1;
			}
			
			if (nextNode instanceof Leaf){
				return (nextNode as Leaf).symbol;
			} else if (nextNode instanceof InternalNode){
				currentNode = nextNode as InternalNode;
			} 
		}
	}
	
	public void decompress() throws IOException {
		output = new BufferedOutputStream(new FileOutputStream(fout));
		input = new BitInputStream(new FileInputStream(fin));
		this.decompressOn(fin, fout);
		output.close();
		input.close();
	}
	
	
	private void decompressOn(File fin, File fout) throws IOException{

		Frequency frequency = new Frequency();
		Arrays.fill(frequency.frequencies, 1)
		this.tree = frequency.buildTree(base);
		int nbCh = 0;
		while (true) {
			int symbol = this.read();
			if (symbol == -1 || symbol == BusinessConstant.EOF) {
				break;
			}
			output.write(symbol);
			
			frequency.increment(symbol);
			nbCh++;
			if (nbCh % BusinessConstant.RESET_VALUE == 0){
				this.tree = frequency.buildTree(base);
				frequency = new Frequency();
				Arrays.fill(frequency.frequencies, 1)
				nbCh = 0;
			}
		}
	}
}
