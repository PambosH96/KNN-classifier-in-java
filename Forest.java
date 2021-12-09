import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Node {
	public double dData;           // data item (key)
	public int id;           	   // data item
	public Node leftChild;         // this node's left child
	public Node rightChild;        // this node's right child
}

public class Forest {
	public long nComp;
	private Node[] roots;
	private Records records;
	
  
	public Forest(int m, int n) {
    records = new Records(m,n);
    roots = new Node[n];
    
	}

	public void load(String filename, int lines, int subDim) 
	{
		BufferedReader br = null;
		String sLine = "";
		int nCntLines = 0;
		double dd = 0;
		try {
			br = new BufferedReader(new FileReader(filename));
			br.readLine(); // Skip first line of file (header line)
			while ((sLine=br.readLine()) != null && nCntLines < lines) {	
				sLine = sLine.trim();				
				String[] arLine = sLine.split("\t");
				int id = nCntLines; 
				double[] rec = new double[roots.length];
				for (int i=0 ; i < arLine.length ; i++) {					
					if ((i > 4) && (i<4+subDim+1)) {
						String s = arLine[i];
						if (s.trim().isEmpty()) 
							dd = 0;
						else
							dd = Double.parseDouble(s);					
						insert(i-5, id, dd);
						rec[i-5] = dd;
					}
				}
				records.setRecord(id, rec);				
				nCntLines++;
			}
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
		finally {
			try {
				if (br != null)
					br.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	private double euclidean(double[] p1, double[] p2) {
		if (p1.length != p2.length)
			return -1; 
		double sum = 0;
		for (int i=0 ; i < p1.length ; i++) {
			sum += Math.pow(p1[i] - p2[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public void insert(int j, int id, double dd)
	{
		Node newNode = new Node();    
		newNode.id = id;          
		newNode.dData = dd;
		if(roots[j]==null)
			roots[j] = newNode;
		else {
			Node current = roots[j];       
			Node parent;
			while(true) {
				parent = current;
				if(dd < current.dData)  {
					current = current.leftChild;
					if(current == null) {             
						parent.leftChild = newNode;
						return;
					}
				}  
				else  {
					current = current.rightChild;
					if(current == null)  {
						parent.rightChild = newNode;
						return;
					}
				}  
			}  
		}  
	} 
  private ArrayList<Integer> intervalIndices;
  

  
	
	public Integer[] findInterval(int j, double k1, double k2) { 
    
    
		intervalIndices = new ArrayList<Integer>();  
    
    addIndex(roots[j],k1,k2);
    
    
    if (intervalIndices.size()>0) {
      return intervalIndices.toArray(new Integer[intervalIndices.size()]);
    }
    else {
      return null;
    }
    
	}
  
  private void addIndex(Node node, double k1, double k2) {
    if (node==null) { return; }
    
    
    if (node.dData>k1) {
      addIndex(node.leftChild,k1,k2);
    }
    
    if (node.dData>k1 && node.dData<k2) {
      intervalIndices.add(node.id);
    }
    
    if (node.dData<k2) {
      addIndex(node.rightChild,k1,k2);
    }
  }
  
  private Integer[] findRangeInterval(int j, double p, double r) { 
    
    
		intervalIndices = new ArrayList<Integer>();  
    addIndex(roots[j],p,r);
    
    
    if (intervalIndices.size()>0) {
      return intervalIndices.toArray(new Integer[intervalIndices.size()]);
    }
    else {
      return null;
    }
    
	}
  
  private void addRangeIndex(Node node, double p, double r) {
    if (node==null) { return; }
    
    double[] p1 = {p};
    double[] p2 = {node.dData};
    if (this.euclidean(p1,p2)<r) {
      intervalIndices.add(node.id);
    }
    
    addIndex(node.leftChild,p,r);
    addIndex(node.rightChild,p,r);
    
  }
  
  
  
	
	
	public Integer[] containsIndex(double[] pmin, double[] pmax) {	
		ArrayList<Integer[]> resultList = new ArrayList<Integer[]>();
    for (int i=0; i<pmin.length; i++){
      resultList.add(findInterval(i,pmin[i],pmax[i]));
    }
    
    
		if (resultList.size()>0) {
      return intersect(resultList.toArray(new Integer[resultList.size()][]));
    }
    else {
      return null;
    }
	}

	
	private Integer[] intersect(Integer[][] results) {
    Set<Integer> intersection = new HashSet<Integer>(Arrays.asList(results[0]));
    Set<Integer> otherSet;
    
    
    for (int i=1; i<results.length;i++) {
      if (results[i]==null) { return null; }
      
      otherSet = new HashSet<Integer>(Arrays.asList(results[i]));
      intersection.retainAll(otherSet);
    }

    
    if (intersection.size()>0) {
      return intersection.toArray(new Integer[intersection.size()]);
    }
    else {
      return null;
    }
	}

	
	public Integer[] rangeIndex(double[] p, double r) {	
		ArrayList<Integer[]> resultList = new ArrayList<Integer[]>();
    for (int i=0; i<p.length; i++){
      resultList.add(findRangeInterval(i,p[i],r));
    }
    
    
		if (resultList.size()>0) {
      return intersect(resultList.toArray(new Integer[resultList.size()][]));
    }
    else {
      return null;
    }
	}

	public static void main(String[] args) {
    Forest forest = new Forest(1000, 4); 
    forest.load("NBA-5d-17265n.txt", 1000, 4);
    
    
    
    Integer[] ids  = forest.findInterval(0,100,200);
    for (Integer i: ids) {
      System.out.println(i);
    }
    
    
	}  
}
