package triangle;

import java.util.*;

public class TriangleGraphNode {
	
	private List<TriangleGraphNode> children = new ArrayList<TriangleGraphNode>(2);
	private List<TriangleGraphNode> parents = new ArrayList<TriangleGraphNode>(2);
	private Object value = 0;
	private Boolean visited = false;
	
	/**Constructs a triangle graph node that has no
	*parents and no children
	*/
	public TriangleGraphNode(){
		this(null);
	}
	
	/**Constructs a triangle graph node that has no
	*  parents and no children, and initializes it 
	*  with the specified user object
	*/
	public TriangleGraphNode(Object value){
		this.setValue(value);
	}
	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the visited
	 */
	public Boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

	/**
	 * Adds a new node to this node's parents array
	 * Should only be used by insert
	 * 
	 * @param newParent the node to be added to the parents array
	 */
	public void addParent(TriangleGraphNode newParent){
		parents.add(newParent);
	}
	/**
	 * Adds a newChild to this node's child array
	 * and makes this node a parent of newChild
	 * 
	 * @param newChild the new child node to be added to this node's children array
	 */
	public void addChild(TriangleGraphNode newChild){
		newChild.addParent(this);
        if (getChildren() == null) {
            setChildren(new ArrayList<TriangleGraphNode>(2));
        }
        getChildren().add(newChild);
	}
	
	/**
	 * @return the children
	 */
	public List<TriangleGraphNode> getChildren() {
		return children;
	}

		
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TriangleGraphNode> children) {
		this.children = children;
	}

	/**
	 * Returns the left child if it exists
	 * @return
	 */
	public TriangleGraphNode getChildL(){
		if (!hasChildren()) {
            return null;
        } else {
            return this.children.get(0);
        }
	}	
	/**
	 * Returns the right child if it exists
	 * @return
	 */
	public TriangleGraphNode getChildR(){
		if (!hasChildren()) {
            return null;
        } else {
            return this.children.get(1);
        }
	}
	
	/**
	 * Returns true if the node has children
	 * @return
	 */
	public Boolean hasChildren(){
		return !(getChildCount()==0);
	}
	
	/**
	 * Returns the number of children a node has
	 * @return
	 */
	public int getChildCount() {
        if (getChildren() == null) {
            return 0;
        } else {
            return getChildren().size();
        }
	}
	
	/**
	 * Returns true if the node is a leaf
	 * @return
	 */
	public boolean isLeaf(){
		return (getChildCount() == 0);
	}
	
	/**Finds and returns the first leaf 
	 * that is a descendant of this node 
	 * -- either this node or its first 
	 * child's first leaf. 
	 * Returns this node if it is a leaf.
	 * 
	 */
	public TriangleGraphNode getFirstLeaf(){
		TriangleGraphNode node = this;
		while(!node.isLeaf()){
			node = (TriangleGraphNode)node.getFirstChild();
		}
		
		return node;
	}
	
	/**
	 * Returns the child node at a particular index
	 * @param index The desired index to get the child node from
	 * @return
	 */
	public TriangleGraphNode getChildAt(int index) {
	        if (getChildren() == null) {
	            throw new ArrayIndexOutOfBoundsException("node has no children");
	        }
	        return (TriangleGraphNode)getChildren().get(index);
	}
	
	/**
	 * Returns a node's first child from its child array
	 * @return
	 */
	public TriangleGraphNode getFirstChild() {
	        if (getChildCount() == 0) {
	            throw new NoSuchElementException("node has no children");
	        }
	        return getChildAt(0);
	}
	
	/**
	 * Returns a node's last child from its child array
	 * @return
	 */
	public TriangleGraphNode getLastChild() {
	        if (getChildCount() == 0) {
	            throw new NoSuchElementException("node has no children");
	        }
	        return getChildAt(getChildCount()-1);
	}
	
	
	
	
	
	
	
	
	
	
}
