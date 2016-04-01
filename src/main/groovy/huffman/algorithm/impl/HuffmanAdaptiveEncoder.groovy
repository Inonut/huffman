package huffman.algorithm.impl

import huffman.algorithm.Huffman
import huffman.algorithm.help.Frequency
import huffman.algorithm.help.Tree
import huffman.util.BitOutputStream
import huffman.util.BusinessConstant
import huffman.util.Util

/**
 * Created by Dragos on 01.04.2016.
 */
class HuffmanAdaptiveEncoder implements Huffman{

    private InputStream input
    private BitOutputStream output

    private Tree tree
    private Frequency frequency

    private File fin
    private File fout
    private int base
    private int nrBitsOfBase
    private int nbCh


    public HuffmanAdaptiveEncoder(File fin, File fout, int base) {
        this.fin = fin
        this.fout = fout
        this.base = base
        this.nrBitsOfBase = Util.toBase2(base-1).size()
        this.nbCh = 0
    }


    @Override
    int read() {
        return input.read()
    }

    @Override
    void write(int symbol) {
        tree.getCode(symbol).forEach {
            List<Integer> bits = Util.toBase2(it)
            while (bits.size() < nrBitsOfBase){
                bits = [0] + bits
            }
            bits.each {output.writeBit(it)}
        }
    }

    @Override
    void onStart() {
        output = new BitOutputStream(new FileOutputStream(fout))
        input = new BufferedInputStream(new FileInputStream(fin))

        nbCh = 0
        frequency = new Frequency()
        Arrays.fill(frequency.frequencies, 1)
        this.tree = frequency.buildTree(base)
    }

    @Override
    void onComplete() {

        write(BusinessConstant.EOF)
        output.flushBit()

        output.close()
        input.close()
    }

    @Override
    void onAfterWrite(int symbol) {
        frequency.increment(symbol)
        if ((++nbCh) % BusinessConstant.RESET_VALUE == 0){
            nbCh = 0
            this.tree = frequency.buildTree(base)
            frequency = new Frequency()
            Arrays.fill(frequency.frequencies, 1)

        }
    }

}
