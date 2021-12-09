import java.util.Arrays;
import java.io.PrintStream;
 
class PointData {
	/*������ ��� ������� ��������������� ��� ����� PointData x ���  y  �� ������ double /*
	 * 
	 */
        public static double[] x;
        public double y;
   /*��� ������������ ��������� �� �������� �������/*
    * 
    */
        public PointData(double[] x, double y) {
            this.x = x.clone();
            this.y = y;
        }
   /*��������� ������� ��� ������� x/*
    * 
    */
        public double dist(double[] x) {
            double res = 0.0;
            for (int i = 0; i < x.length; i++)
                res = res + Math.pow(( this.x[i] - x[i] ), 2);
           
            return Math.sqrt(res);
        }
    }
 /*���������� �� score,��  ptd ��� �� next /*
  * 
  */
    class Node {
        public double score;
        public PointData ptd;
        public Node next;
   /*� ������������� ��� ������ ���������� ����� ������ ��� �������� ������ ��� �� ���������� �� ptd./*
    * 
    */
        public Node(double[] x, double y) {
           
            ptd = new PointData(x, y);
           
        }
    }
 /*��������� ��� ����� PointList /*
  * 
  */
    class PointList {
    	/*���������� ��  Node first ��� �� dim./*
    	 * 
    	 */
        public static Node first;
        private int dim = 0;
       
       
        public PointList(int dim) {
        	/*���������� �� first==  null ��� ������� ��� ������� this.dim=dim. /*
        	 * 
        	 * 
        	 */
            first = null;
            this.dim = dim;
        }
       
       
        public static PointList readData(String filename) {
            PointList list = null;
            java.io.BufferedReader br = null;
           
            try {
                br = new java.io.BufferedReader(new java.io.FileReader(filename));
                String line = br.readLine();
                String[] data = line.split(" ");
                int m = Integer.parseInt(data[0]);
                int n = Integer.parseInt(data[1]);
               
                list = new PointList(n);
                double[] x = new double[n];
                while((line = br.readLine()) != null) {
                    data = line.split(" ");
                    for(int j = 0; j < n; j++)
                        x[j] = Double.parseDouble(data[j]);
                    System.out.println(Arrays.toString(x));
                    list.append(x, Double.parseDouble(data[n]));
                }
            }
            catch (java.io.IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (br != null) br.close();
                }
                catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }
            return list;
        }
       /*���������� �� �������� ��� ������� ��� ������������� ��� �����./*
        * 
        */
       
        public int getDim() {
            if ( !isEmpty() ) {
               
                return dim;
               
            }
            return 0;
        }
       /*���������� �� ������ ��� ������ ��� ������ /*
        * 
        */
       
        public int length() {
            Node current = first;
            int count = 0;
           
            while ( current != null ) {
               
                count++;
                current = current.next;
               
            }
            return count;
        }
        /*���������� first==null �� � ����� ����� ������./*
         * 
         */
       
        public boolean isEmpty() {
           
            return first == null;
           
        }
       /*������� ��������� ��� ������� �������x,y, ������������ �� ����������� �� ������ PointData/*
        * 
        */
        public boolean append(double[] x, double y) {
            Node previous = first;
            Node newNode = new Node(x, y);
           
            if ( isEmpty() ) {
               
                first = newNode;
               
            }
            else {
               
                while ( previous.next != null ) {
                   
                    previous = previous.next;
                   
                }
               
                previous.next = newNode;
               
            }
           
            return getDim() == x.length;
        }
       
       
        public boolean append(PointList list) {
            return false;
        }
       
       /*�������� �� � ����� ����� ������ ���  ��������� ��� ����� ����� ��� ������ /*
        * 
        */
        public PointData rmFirst() {
            if ( isEmpty() ) {
               
                return null;
               
            }
            else {
               
                Node temp = first;
                first = first.next;
                return new PointData(temp.ptd.x, temp.ptd.y);
            }
        }
        /*���������� ������ ��� ���������� �� ����� /*
         * 
         */
        public void shuffle() {
        }
       
       /* �������� �� � ����� ����� ������ ��� ���� ��������� ��� ��������� ��� �� ����� ��� ����� ��� ���������� �� �������� ��� ��������� ������� ��� ������ ������� x./*
        * 
        */
        
        public PointData rmNearest(double[] x) {
            if ( isEmpty() ) {
               
                return null;
               
            }
            else {
               
               
                return null;
               
            }
        }
        /*��������� ��� ����� ���� k ���������� �������� ��� ������� ������� x/*
         * 
         */
        public PointList findKNearest(double[] x, int k) {
            return null;
        }
   /*�������������� �� ������ ������� x,����� ��� ������� ��� ������������� ��� ����� ���� ����� �������� � �������/*
    * 
    */
        public double classify(double[] x) {
            return 0.0;
        }
       /*���������� ���� ������� ��� ���� ���� ��������� ���� �����./*
        * 
        */
        public void toString(PointList list) {
            Node current = new Node(list.first.ptd.x, list.first.ptd.y);
            System.out.println(first.ptd.x.toString());
}
       
    }
    /*������������ ��� ���������� ��� ��� ����� ��� ��������� � ����� ���� �����/*
     * 
     */
public class KNN {
	 public static void main(String[] args) {
         PointList list = PointList.readData("wine.dat");
         System.out.println();
         list.toString(list);
     }
}
