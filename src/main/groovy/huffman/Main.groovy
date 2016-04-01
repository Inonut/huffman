package huffman

import groovyx.gpars.GParsPool

import groovyx.gpars.group.DefaultPGroup
import huffman.algorithm.HuffmanDecoder
import huffman.algorithm.HuffmanEncoder
import huffman.util.Util


/**
 * Created by Dragos on 29.03.2016.
 */
class Main {

    def static group = new DefaultPGroup(200)
    def static count = 0

    def static fibAsync(int n){
        //println group.threadPool.poolSize
        if(n <=2 ){
            return 1
        } else {
            if(count < 100){
                count+=2
                def a = group.task{ fibAsync(n-1) }
                def b = group.task{ fibAsync(n-2) }
                def r = a.get() + b.get()
                count--
                return r
            } else {
                return fibSync(n)
            }


        }
    }

    def static fibSync(int n){
        //println n
        if(n <=2 ){
            return 1
        } else {
            return fibSync(n-2) + fibSync(n-1)
        }
    }


    public static void main(String ...argss){

        File fin = new File("D:\\jetBrains\\huffman\\src\\main\\resources\\testCompress.txt")
        File fout = new File("D:\\jetBrains\\huffman\\src\\main\\resources\\testCompress.huff_txt")
        File fout2 = new File("D:\\jetBrains\\huffman\\src\\main\\resources\\testDecompress.txt")

        HuffmanEncoder encoder = new HuffmanEncoder(fin, fout, 3)
        encoder.compress()

        println "------------"

        HuffmanDecoder decoder = new HuffmanDecoder(fout, fout2, 3)
        decoder.decompress()


      /*  def n = 38

        def t

        t = System.currentTimeMillis()
        Closure fib = {number ->
            if (number <= 2) {
                return 1
            }
            forkOffChild(number - 1)
            forkOffChild(number - 2)
            return getChildrenResults().sum() as int
        }

        GParsPool.withPool(200) {
            println GParsPool.runForkJoin(n, fib)
        }
        println "3----- ${System.currentTimeMillis() - t}"

        t = System.currentTimeMillis()
        println fibAsync(n)
        println "2----- ${System.currentTimeMillis() - t}"

        t = System.currentTimeMillis()
        println fibSync(n)
        println "1----- ${System.currentTimeMillis() - t}"
*/

       /* t = System.currentTimeMillis()
        GParsPool.withPool(200) {
            println ((1..100).collectParallel { Thread.sleep(10); return it;}.sumParallel())
        }
        println "0----- ${System.currentTimeMillis() - t}"

*/

    }
}
