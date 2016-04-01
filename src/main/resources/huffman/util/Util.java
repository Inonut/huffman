package huffman.huffman.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
	
	public static final int BYTE = 256;
	public static final long RESET_VALUE = 262144;
	
	public static void gererateFile(File file, long n) throws IOException{
		BufferedOutputStream pw = new BufferedOutputStream(new FileOutputStream(file));
		for(int i=0;i<n;i++){
			pw.write((int)(Math.random()*100));
		}
		pw.close();
	}
	
	
}
