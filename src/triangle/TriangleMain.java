package triangle;

import java.io.LineNumberReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class TriangleMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		try {
			LineNumberReader in = new LineNumberReader(new FileReader("src\\inputFiles\\tmp.txt"));
			String line = null;
			
			
			TriangleGraphNode root = new TriangleGraphNode(Integer.parseInt(in.readLine().trim()));
			ArrayList<TriangleGraphNode> prevRow = new ArrayList<TriangleGraphNode>();
			prevRow.add(root);

			System.out.println(in.getLineNumber() + "   " + root.getValue());
			
			//Reads in values from src
			//Builds triangle graph
		    while ((line = in.readLine()) != null) {
		    	
		    	String[] tmpString = line.split("\\s");
		    	System.out.print(in.getLineNumber() + "  [");
		    	
		    	int tmpSize = prevRow.size();
		    	
		    	TriangleGraphNode prevSibling = new TriangleGraphNode(Integer.parseInt(tmpString[0]));
		    	
		    	for(int i = 0; i<tmpSize; i++){
		    		TriangleGraphNode currentSibling = new TriangleGraphNode(Integer.parseInt(tmpString[i+1]));
		    		
		    		prevRow.get(i).addChild(prevSibling);
		    		prevRow.get(i).addChild(currentSibling);
		    		
		    		prevRow.set(i, prevSibling);
		    		
		    		prevSibling = currentSibling;
		    		
		    		System.out.print( tmpString[i] + " " );
		    	}
		    	System.out.print( tmpString[tmpSize] + " " );
		    	prevRow.add(prevSibling);
		    	System.out.println("]");
		    }
		    in.close();
		    
		   
		    
		    System.out.println("Output: " + collapseGraph(root));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I/O Error");
			e.printStackTrace();
		}
		
		
	
		
		
		
	}
	
	
	public static int collapseGraph(TriangleGraphNode node){
		if (node==null){
			return 0;
		} 
		
		if (node.isVisited()){
			return (int) node.getValue();
		} else {
			System.out.println(node.getValue());
			int leftValue = collapseGraph(node.getChildL());
			int rightValue = collapseGraph(node.getChildR());
			
			int returnVal = (int) node.getValue();
			node.setVisited(true);
			
			return returnVal + Math.max(leftValue, rightValue);
		}
	}
	
	


}
