package huffman.huffman;

import java.io.File;
import java.io.IOException;

import huffman.algotithm.HuffmanDecoder;
import huffman.algotithm.HuffmanEncoder;
import huffman.algotithm.HuffmanVisitorImpl;
import huffman.util.Util;

public class Main {

	public static void main(String[] args) throws IOException {
		/*long t = System.currentTimeMillis();
		new HuffmanEncoder(new File("testCompress.txt"), new File("test.huff")).compress();
		new HuffmanDecoder(new File("test.huff"), new File("testDecompress.txt")).decompress();
		System.out.println(System.currentTimeMillis()-t);*/
		
		Util.gererateFile(new File("testCompress.txt"),50000000);
		//Util.gererateFile(new File("testCompress.txt"),2);
		long t = System.currentTimeMillis();
		HuffmanEncoder encoder = new HuffmanEncoder(new File("testCompress.txt"), new File("test.huff"));
		HuffmanDecoder decoder = new HuffmanDecoder(new File("test.huff"), new File("testDecompress.txt"));
		
		HuffmanVisitor visitor = new HuffmanVisitorImpl();
		visitor.visit(encoder);
		visitor.visit(decoder);
		System.out.println(System.currentTimeMillis()-t);	
	}
}
