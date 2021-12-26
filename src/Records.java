import java.util.ArrayList;


public class Records {
	public long nComp;
	private double[][] data;

	
	private double euclidean(double[] p1, double[] p2) {
		if (p1.length != p2.length) 
			return -1; 
		double sum = 0;
		for (int i=0 ; i < p1.length ; i++) {
			sum += Math.pow(p1[i] - p2[i], 2);
		}
		return Math.sqrt(sum);
	}

	public Records(int m, int n) {
		data = new double[m][n]; 
	}

	
	public double[] getRecord(int i) {
		if (i > -1 && i < data.length)
			return data[i];
		else 
			return null;
	}

	
	public void setRecord(int i, double[] rec) {
		for (int j=0 ; j < rec.length ; j++) {
			data[i][j] = rec[j];
		}
	}

	
	public Integer[] containsScan(double[] pmin, double[] pmax) {	
  
    ArrayList<Integer> indices = new ArrayList<Integer>();  
    
    for (int i = 0; i<data.length; i++) {
      
      
      boolean found = true;
      int j = 0;
      
      while (found && j< data[i].length) {
          
        if (data[i][j] < pmin[j] || data[i][j]>pmax[j]) {
          found = false;
        }
        j++;
      }
      
      
      if (found) {
        indices.add(i);
      }
      
      
    }
    
    if (indices.size()>0) {
      return indices.toArray(new Integer[indices.size()]);
    }
    else {
      return null;
    }
    
    
		
	}

	
	public Integer[] rangeScan(double[] p, double r) {	
		ArrayList<Integer> indices = new ArrayList<Integer>();  
    
    for (int i = 0; i<data.length; i++) {
      
      
      if (this.euclidean(p,data[i]) <r) {
        indices.add(i);
      }
      

    }
    
    if (indices.size()>0) {
      return indices.toArray(new Integer[indices.size()]);
    }
    else {
      return null;
    }
	}
  
}
