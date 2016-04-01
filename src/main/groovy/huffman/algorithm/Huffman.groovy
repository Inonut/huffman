package huffman.algorithm

import huffman.util.BusinessConstant

/**
 * Created by Dragos on 01.04.2016.
 */
trait Huffman {

    void execute() {

        onStart()
        while (true) {
            onBeforeRead()
            int symbol = read()
            if (symbol == -1 || symbol == BusinessConstant.EOF){
                break
            }
            write(symbol)
            onAfterWrite()
        }
        onComplete()
    }

    void onStart(){}

    void onComplete(){}

    void onBeforeRead(){}

    void onAfterWrite(){}

    abstract int read()

    abstract void write(int symbol)
}