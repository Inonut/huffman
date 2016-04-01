package huffman.huffman.domain;

public class InternalNode extends huffman.domain.Node {
	
	private huffman.domain.Node leftChild;
	
	private huffman.domain.Node rightChild;
	
	public InternalNode(huffman.domain.Node leftChild, huffman.domain.Node rightChild) {
		this(leftChild , rightChild, leftChild.frequency+rightChild.frequency);
	}
	
	public InternalNode(huffman.domain.Node leftChild, huffman.domain.Node rightChild, int frequency) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.frequency = frequency;
	}

	public huffman.domain.Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(huffman.domain.Node leftChild) {
		this.leftChild = leftChild;
	}

	public huffman.domain.Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(huffman.domain.Node rightChild) {
		this.rightChild = rightChild;
	}
}
