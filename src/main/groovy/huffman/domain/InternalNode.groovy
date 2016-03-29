package huffman.domain;

public class InternalNode extends Node {
	
	private Node leftChild
	
	private Node rightChild
	
	public InternalNode(Node leftChild, Node rightChild) {
		this(leftChild , rightChild, leftChild.frequency+rightChild.frequency);
	}
	
	public InternalNode(Node leftChild, Node rightChild, int frequency) {
		this.leftChild = leftChild
		this.rightChild = rightChild
		this.frequency = frequency
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
}
