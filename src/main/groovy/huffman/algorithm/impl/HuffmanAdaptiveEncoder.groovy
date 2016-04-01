package huffman.algorithm.impl

import huffman.algorithm.help.Frequency
import huffman.algorithm.help.Tree
import huffman.util.BitOutputStream
import huffman.util.BusinessConstant
import huffman.util.Util

public class HuffmanAdaptiveEncoder {
	
	private InputStream input
	private BitOutputStream output
	
	private Tree tree
	
	private File fin
	private File fout
	private int base
	private int nrBitsOfBase
	
	public HuffmanAdaptiveEncoder(File fin, File fout, int base) {
		this.fin = fin
		this.fout = fout
		this.base = base
		this.nrBitsOfBase = Util.toBase2(base-1).size()
	}
	
	private void write(int symbol) throws IOException {
		tree.getCode(symbol).forEach {
			List<Integer> bits = Util.toBase2(it)
			while (bits.size() < nrBitsOfBase){
				bits = [0] + bits
			}
			bits.each {output.writeBit(it)}
		}
	}
	
	public void compress(){
		output = new BitOutputStream(new FileOutputStream(fout))
		input = new BufferedInputStream(new FileInputStream(fin))
		this.compressOn(fin, fout)
		output.close()
		input.close()
	}
	
	private void compressOn(File fin, File fout) {

		Frequency frequency = new Frequency()
		Arrays.fill(frequency.frequencies, 1)
		this.tree = frequency.buildTree(base)
		int nbCh = 0
		while (true) {
			int symbol = input.read()
			if (symbol == -1){
				break
			}
			this.write(symbol)
			
			frequency.increment(symbol)
			nbCh++
			if (nbCh % BusinessConstant.RESET_VALUE == 0){
				this.tree = frequency.buildTree(base)
				frequency = new Frequency()
				Arrays.fill(frequency.frequencies, 1)
				nbCh = 0
			}
		}

		this.write(BusinessConstant.EOF)
		this.output.flushBit()
	}

}
