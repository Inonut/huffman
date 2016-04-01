package huffman.algorithm.help

import huffman.domain.InternalNode
import huffman.domain.Leaf
import huffman.domain.Node
import huffman.util.BusinessConstant

/**
 * Created by Dragos on 29.03.2016.
 */
class Tree {

    private InternalNode root
    private Leaf[] leafs

    public Tree(InternalNode root) {
        this.root = root

        leafs = new Leaf[BusinessConstant.NR_CHAR]
        makeCodes(this.root, [])
    }


    private void makeCodes(Node node, List<Integer> code) {

        if (node instanceof Leaf) {
            Leaf leaf = node as Leaf

            leaf.code = code.clone() as List
            leafs[leaf.symbol] = leaf
        } else if (node instanceof InternalNode) {
            InternalNode internalNode = node as InternalNode

            internalNode.children.eachWithIndex{ Node entry, int i ->
                code += i
                makeCodes(entry, code)
                code.remove(code.size()-1)
            }
        }
    }

    public List<Integer> getCode(int symbol) {
        return leafs[symbol].code
    }

    InternalNode getRoot() {
        return root
    }
}
