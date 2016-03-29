package huffman

import huffman.util.BitInputStream

/**
 * Created by Dragos on 29.03.2016.
 */
class Main {

    public static void main(String ...args){
        BitInputStream bitInputStream = new BitInputStream(new FileInputStream(new File("F:\\MEGA\\Proiecte\\Huffman3\\src\\huffman\\algotithm\\help\\Tree.java")))

        (1..100).each {
            println bitInputStream.readBit(8)
        }

    }
}
