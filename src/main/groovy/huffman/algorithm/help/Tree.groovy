package huffman.algorithm.help

import huffman.domain.InternalNode
import huffman.domain.Leaf

/**
 * Created by Dragos on 29.03.2016.
 */
class Tree {

    private InternalNode root
    private List<Leaf> leafs

    public Tree(InternalNode root) {
        this.root = root

        leafs = []
        makeCodes(root, [])
        leafs.sort{a,b->a.getSymbol()-b.getSymbol()}
    }


    private void makeCodes(Node node, List<Integer> code) {

        if (node instanceof Leaf) {
            Leaf leaf = node as Leaf

            leaf.code = code.clone() as List
            leafs.add(leaf);
        } else if (node instanceof InternalNode) {
            InternalNode internalNode = node as InternalNode

            internalNode.children.eachWithIndex{ Node entry, int i ->
                code += i
                makeCodes(entry, code)
                code -= i
            }
        }
    }

    public List<Integer> getCode(int symbol) {
        return leafs[symbol].getCode();
    }

    public InternalNode getRoot() {
        return root;
    }


    public void setRoot(InternalNode root) {
        this.root = root;
    }
}
