package huffman.huffman.util;

import java.io.IOException;
import java.io.OutputStream;


public class BitOutputStream {
	
	private OutputStream output;
	
	private int currentByte;
	
	private int nbBits;
	
	public BitOutputStream(OutputStream output) {
		this.output = output;
		this.currentByte = 0;
		this.nbBits = 0;
	}
	
	public void write(int b) throws IOException {
		currentByte = currentByte << 1 | b;
		nbBits++;
		if (nbBits == 8) {
			output.write(currentByte);
			nbBits = 0;
			currentByte=0;
		}
	}

	public void flush() throws IOException{
		if(currentByte>0){
			output.write(currentByte);
			currentByte=0;
		}
	}
	
	public void close() throws IOException {
		this.flush();
		output.close();
	}
	
}
