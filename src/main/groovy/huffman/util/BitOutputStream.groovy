package huffman.util;

import java.io.IOException;
import java.io.OutputStream;


class BitOutputStream extends BufferedOutputStream{
	
	private int currentByte = 0
	private int nbBits = 0

	BitOutputStream(OutputStream out) {
		super(out)
	}

	BitOutputStream(OutputStream out, int size) {
		super(out, size)
	}

	public void writeBit(int b) {
		if(b != 0 && b != 1){
			throw new Exception("b is not 0 or 1")
		}
		currentByte = currentByte << 1 | b
		nbBits++
		if (nbBits == 8) {
			super.write(currentByte)
			nbBits = 0
			currentByte=0
		}
	}

	public void flushBit() {
		if(nbBits>0){
			super.write(currentByte)
			currentByte = 0
			nbBits = 0
		}
	}
	
}
