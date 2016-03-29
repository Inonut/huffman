package huffman.util

class BitInputStream extends BufferedInputStream{

	private int nextByte = -1
	private int nbBits = 0

	BitInputStream(InputStream inputStream) {
		super(inputStream)
	}

	BitInputStream(InputStream inputStream, int size) {
		super(inputStream, size)
	}


	public synchronized int readBit() {

		if (nbBits == 0) {
			nextByte = super.read();
			if (nextByte == -1) {
				return -1;
			}
			nbBits = 8;
		}
		nbBits--;

		return (nextByte >> nbBits) & 1;
	}

	public synchronized int readBit(int nrBits) {

		int result = 0

		(1..nrBits)
				.collect {readBit()}
				.findAll {it != -1}
				.with {
					if(it == null || it.isEmpty()){
						result = -1
					}
					return it
				}
				.each {
					result = (result << 1) | it
				}

		return result
	}

}
