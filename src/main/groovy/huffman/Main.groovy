package huffman

import huffman.algorithm.Huffman
import huffman.algorithm.impl.HuffmanAdaptiveDecoder
import huffman.algorithm.impl.HuffmanAdaptiveEncoder


/**
 * Created by Dragos on 29.03.2016.
 */
class Main {


    public static void main(String ...argss){

        String path = "F:\\MEGA\\IdeaProjects\\huffman\\src\\main\\resources\\"

        File fin = new File("${path}testMusic.mp3")
        File fout = new File("${path}testMusic.huff_png")
        File fout2 = new File("${path}testMusic2.mp3")

        Huffman encoder = new HuffmanAdaptiveEncoder(fin, fout, 2)
        encoder.execute()

        println "------------"

        Huffman decoder = new HuffmanAdaptiveDecoder(fout, fout2, 2)
        decoder.execute()

        //println Integer.toString(2, 2).split("").collect()

    }
}
