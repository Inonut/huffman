package huffman.util

/**
 * Created by Dragos on 4/1/2016.
 */
class Util {

    def static toBase2(int val){
        if(val == 0){
            return [0]
        } else {
            if(val/2 as int != 0){
                return toBase2(val/2 as int) + [val%2]
            } else {
                return [val%2]
            }
        }
    }
}
