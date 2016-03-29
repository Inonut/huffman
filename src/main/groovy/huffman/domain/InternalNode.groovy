package huffman.domain;

public class InternalNode extends Node {
	
	private List<Node> children

	InternalNode(List<Node> children) {
		this(children, children*.frequency.sum() as int)
	}

	InternalNode(List<Node> children, int frequency) {
		this.children = children
		this.frequency = frequency
	}

	List<Node> getChildren() {
		return children
	}

	void setChildren(List<Node> children) {
		this.children = children
	}
}
