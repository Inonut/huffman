package huffman.huffman.algotithm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import huffman.Huffman;
import huffman.HuffmanVisitor;
import huffman.algotithm.help.Tree;
import huffman.algotithm.help.Frequency;
import huffman.domain.InternalNode;
import huffman.domain.Leaf;
import huffman.domain.Node;
import huffman.util.BitInputStream;
import huffman.util.Util;


public final class HuffmanDecoder implements Huffman{
	
	private BitInputStream input;
	private OutputStream output;
	
	private Tree tree;
	
	private File fin;
	private File fout;
	
	public HuffmanDecoder(File fin, File fout) {
		this.fin = fin;
		this.fout = fout;
	}
	
	public int read() throws IOException {
		InternalNode currentNode = tree.getRoot();
		while (true) {
			int bit = input.read();
			Node nextNode = null;
			if(bit == 0) {
				nextNode = currentNode.getLeftChild();
			} else if (bit == 1){
				nextNode = currentNode.getRightChild();
			} else {
				return -1;
			}
			
			if (nextNode instanceof Leaf){
				return ((Leaf)nextNode).getSymbol();
			} else if (nextNode instanceof InternalNode){
				currentNode = (InternalNode)nextNode;
			} 
		}
	}
	
	public void decompress() throws IOException {
		output = new BufferedOutputStream(new FileOutputStream(fout));
		input = new BitInputStream(new BufferedInputStream(new FileInputStream(fin)));
		this.decompress(fin, fout);
		output.close();
		input.close();
	}
	
	
	private void decompress(File fin, File fout) throws IOException{
		
		Frequency frequency = new Frequency();
		this.tree = frequency.buildTree();
		int nbCh = 0;
		while (true) {
			int symbol = this.read();
			if (symbol == -1) {
				break;
			}
			output.write(symbol);
			
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
