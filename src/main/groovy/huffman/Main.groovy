package huffman

import groovyx.gpars.group.DefaultPGroup



/**
 * Created by Dragos on 29.03.2016.
 */
class Main {

    def static group = new DefaultPGroup(200)
    def static count = 0

    def static fibAsync(int n){
       // println n
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

        def n = 40

        def t



        t = System.currentTimeMillis()
        println fibAsync(n)
        println "2----- ${System.currentTimeMillis() - t}"

        t = System.currentTimeMillis()
        println fibSync(n)
        println "1----- ${System.currentTimeMillis() - t}"


    }
}
