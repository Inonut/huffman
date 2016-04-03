package nayuki.huffmancoding;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public final class AdaptiveHuffmanCompress {
	
	public static void main(String[] args) throws IOException {
		// Show what command line arguments to use

		String path = "F:\\MEGA\\IdeaProjects\\huffman\\src\\main\\resources\\";

		File inputFile = new File(path + "testMusic.mp3");
		File outputFile = new File( path + "testMusic.huff_png");
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
		BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
		try {
			compress(inputStream, out);
		} finally {
			out.close();
			inputStream.close();
		}
	}
	
	
	static void compress(InputStream inputStream, BitOutputStream out) throws IOException {
		int[] initFreqs = new int[257];
		Arrays.fill(initFreqs, 1);
		
		FrequencyTable freqTable = new FrequencyTable(initFreqs);
		HuffmanEncoder enc = new HuffmanEncoder(out);
		enc.codeTree = freqTable.buildCodeTree();  // We don't need to make a canonical code since we don't transmit the code tree
		int count = 0;
		while (true) {
			int b = inputStream.read();
			if (b == -1)
				break;
			enc.write(b);
			
			freqTable.increment(b);
			count++;
			if (count < 262144 && isPowerOf2(count) || count % 262144 == 0)  // Update code tree
				enc.codeTree = freqTable.buildCodeTree();
			if (count % 262144 == 0)  // Reset frequency table
				freqTable = new FrequencyTable(initFreqs);
		}
		enc.write(256);  // EOF
	}
	
	
	private static boolean isPowerOf2(int x) {
		return x > 0 && (x & -x) == x;
	}
	
}
