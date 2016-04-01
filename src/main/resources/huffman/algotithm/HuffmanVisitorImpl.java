package huffman.huffman.algotithm;

import huffman.HuffmanVisitor;
import huffman.util.LambdaExceptionUtil;

import java.util.Optional;

public class HuffmanVisitorImpl implements HuffmanVisitor{

	@Override
	public void visit(HuffmanDecoder huffmanDecoder) {
		Optional.ofNullable(huffmanDecoder).ifPresent(decoder->LambdaExceptionUtil.uncheck(decoder::decompress));
	}

	@Override
	public void visit(HuffmanEncoder huffmanEncoder) {
		Optional.ofNullable(huffmanEncoder).ifPresent(encoder->LambdaExceptionUtil.uncheck(encoder::compress));
	}

}
