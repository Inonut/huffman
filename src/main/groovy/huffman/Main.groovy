package huffman

import huffman.algorithm.impl.HuffmanAdaptiveDecoder
import huffman.algorithm.impl.HuffmanAdaptiveEncoder


/**
 * Created by Dragos on 29.03.2016.
 */
class Main {


    public static void main(String ...argss){

        String path = "F:\\MEGA\\IdeaProjects\\huffman\\src\\main\\resources\\"

        File fin = new File("${path}log1.txt")
        File fout = new File("${path}log1.huff_png")
        File fout2 = new File("${path}log12.txt")

        HuffmanAdaptiveEncoder encoder = new HuffmanAdaptiveEncoder(fin, fout, 4)
        encoder.execute()

        println "------------"

        HuffmanAdaptiveDecoder decoder = new HuffmanAdaptiveDecoder(fout, fout2, 4)
        decoder.execute()

    }
}
