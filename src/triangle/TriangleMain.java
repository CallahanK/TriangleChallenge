package triangle;

import java.io.LineNumberReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TriangleMain {

	private static final String FILE_LOCATION  =  "src\\inputFiles\\triangle.txt";
	
	
	public static void main(String[] args) throws IOException {
		 
		try {
			//Reads in the designated file
			LineNumberReader in = new LineNumberReader(new FileReader(FILE_LOCATION));
			String line = null;
			
			//Reads in the first line and creates the first node as the root
			//and sets its value to the first integer, adds that node to an array containing the 
			//nodes of the previous line
			TriangleGraphNode root = new TriangleGraphNode(Integer.parseInt(in.readLine().trim()));
			ArrayList<TriangleGraphNode> prevRow = new ArrayList<TriangleGraphNode>();
			prevRow.add(root);

			System.out.println("Root Value: " + root.getValue());
			
			//Reads in values from src
			//Builds triangle graph
		    while ((line = in.readLine()) != null) {
		    	
		    	String[] tmpString = line.split("\\s");
		    	
		    	//Stores the size of the previous row
		    	int tmpSize = prevRow.size();
		    	
		    	//Creates a new node for the first integer in the current row
		    	TriangleGraphNode prevSibling = new TriangleGraphNode(Integer.parseInt(tmpString[0]));
		    	
		    	//For the nodes in the previous row creates and adds new nodes as children 
		    	//from the integers on the current line
		    	for(int i = 0; i<tmpSize; i++){
		    		TriangleGraphNode currentSibling = new TriangleGraphNode(Integer.parseInt(tmpString[i+1]));
		    		
		    		prevRow.get(i).addChild(prevSibling);
		    		prevRow.get(i).addChild(currentSibling);
		    		
		    		//Overwrites the parent node in the previous row with
		    		//its child once both children have been added to it
		    		prevRow.set(i, prevSibling);
		    		
		    		prevSibling = currentSibling;
		    	}
		    	prevRow.add(prevSibling);
		    }
		    
		    System.out.println("Total Rows: " + in.getLineNumber());
		    in.close();
		    
		    System.out.println("First Leaf: " + root.getFirstLeaf().getValue());
		    
		    int maximumTotal = collapseGraph(root);
		    System.out.println("Output: " + maximumTotal);
			
		} catch (FileNotFoundException e) {
			System.out.println("I/O Error");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Recursively finds the maximum total from top to bottom of the triangle graph
	 * 
	 * @param node
	 * @return
	 */
	public static int collapseGraph(TriangleGraphNode node){
		if (node==null){
			return 0;
		} 
		if (node.isVisited()){
			return (int) node.getValue();
		} else {
			//System.out.println(node.getValue());
			int leftValue = collapseGraph(node.getChildL());
			int rightValue = collapseGraph(node.getChildR());
			
			int returnVal = (int) node.getValue();
			node.setVisited(true);
			returnVal = returnVal + Math.max(leftValue, rightValue);
			node.setValue(returnVal);
			return returnVal;
		}
	}
	
	


}
