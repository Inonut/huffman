package huffman.huffman;

import huffman.algotithm.HuffmanDecoder;
import huffman.algotithm.HuffmanEncoder;

public interface HuffmanVisitor {

	void visit(HuffmanDecoder huffmanDecoder);
	void visit(HuffmanEncoder huffmanEncoder);
}
