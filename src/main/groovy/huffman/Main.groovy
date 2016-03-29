package huffman

import huffman.util.BitInputStream

/**
 * Created by Dragos on 29.03.2016.
 */
class Main {

    public static void main(String ...args){

        def list = [[value: 1],[value: 1],[value: 1],[value: 1],[value: 1],[value: 1]]

        println list*.value.sum()
    }
}
