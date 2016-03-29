package huffman.huffman.util;

import java.io.IOException;
import java.io.InputStream;


public class BitInputStream {
	
	private InputStream input;
	
	private int nextByte;
	
	private int nbBits;
	
	public BitInputStream(InputStream input) {
		this.input = input;
		this.nbBits = 0;
		this.nextByte = 0;
	}
	
	public int read() throws IOException {
		if (nbBits == 0) {
			nextByte = input.read();
			if (nextByte == -1) {
				return -1;
			}
			nbBits = 8;
		}
		nbBits--;
		return (nextByte >> nbBits) & 1;
	}
	
	
	public void close() throws IOException {
		input.close();
	}
}
