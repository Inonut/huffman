package huffman.algorithm.impl

import huffman.algorithm.Huffman
import huffman.algorithm.help.Frequency
import huffman.algorithm.help.Tree
import huffman.domain.InternalNode
import huffman.domain.Leaf
import huffman.domain.Node
import huffman.util.BitInputStream
import huffman.util.BusinessConstant
import huffman.util.Util

/**
 * Created by Dragos on 01.04.2016.
 */
class HuffmanAdaptiveDecoder implements Huffman{

    private BitInputStream input
    private OutputStream output

    private Tree tree
    private Frequency frequency

    private File fin
    private File fout
    private int base
    private int nrBitsOfBase
    private int nbCh

    public HuffmanAdaptiveDecoder(File fin, File fout, int base) {
        this.fin = fin
        this.fout = fout
        this.base = base
        this.nrBitsOfBase = Util.toBase2(base-1).size()
    }


    @Override
    int read() {
        InternalNode currentNode = tree.getRoot();
        while (true) {
            int bit = input.readBit(nrBitsOfBase);

            Node nextNode = null;
            if(bit != -1) {
                nextNode = currentNode.children[bit]
            }else {
                return -1;
            }

            if (nextNode instanceof Leaf){
                return (nextNode as Leaf).symbol;
            } else if (nextNode instanceof InternalNode){
                currentNode = nextNode as InternalNode;
            }
        }
    }

    @Override
    void write(int symbol) {
        output.write(symbol)
    }

    @Override
    void onStart() {
        output = new BufferedOutputStream(new FileOutputStream(fout));
        input = new BitInputStream(new FileInputStream(fin));

        nbCh = 0
        frequency = new Frequency()
        Arrays.fill(frequency.frequencies, 1)
        this.tree = frequency.buildTree(base)
    }

    @Override
    void onComplete() {
        output.close()
        input.close()
    }

    @Override
    void onAfterWrite(int symbol) {
        frequency.increment(symbol)
        if (Util.isPowerOf2(++nbCh)){
            this.tree = frequency.buildTree(base)
            frequency = new Frequency()
            Arrays.fill(frequency.frequencies, 1)
        }
    }


}
