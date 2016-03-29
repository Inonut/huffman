package huffman.huffman.algotithm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import huffman.Huffman;
import huffman.HuffmanVisitor;
import huffman.algotithm.help.Tree;
import huffman.algotithm.help.Frequency;
import huffman.util.BitOutputStream;
import huffman.util.Util;
import huffman.util.LambdaExceptionUtil;


public final class HuffmanEncoder implements Huffman{
	
	private InputStream input;
	private BitOutputStream output;
	
	private Tree tree;
	
	private File fin;
	private File fout;
	
	public HuffmanEncoder(File fin, File fout) {
		this.fin = fin;
		this.fout = fout;
	}
	
	public void write(int symbol) throws IOException {
		tree.getCode(symbol).forEach(LambdaExceptionUtil.rethrowConsumer(output::write));
	}
	
	public void compress() throws IOException{
		output = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(fout)));
		input = new BufferedInputStream(new FileInputStream(fin));
		this.compress(fin, fout);
		output.close();
		input.close();
	}
	
	private void compress(File fin, File fout) throws IOException {
		
		Frequency frequency = new Frequency();
		this.tree = frequency.buildTree(); 
		int nbCh = 0;
		while (true) {
			int symbol = input.read();
			if (symbol == -1){
				break;
			}
			this.write(symbol);
			
			frequency.increment(symbol);
			nbCh++;
			if (nbCh % Util.RESET_VALUE == 0){
				this.tree = frequency.buildTree();
				frequency = new Frequency();
				nbCh = 0;
			}
		}
	}

	@Override
	public void accept(HuffmanVisitor huffmanVisitor) {
		huffmanVisitor.visit(this);
	}
	
}
