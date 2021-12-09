import java.util.Arrays;
import java.io.PrintStream;
 
class PointData {
	/*Ξεκινω την εργασια κατασκευαζοντας την κλαση PointData x και  y  σε τυπους double /*
	 * 
	 */
        public static double[] x;
        public double y;
   /*στο κατασκευαστη αντιγραφω τα στοιχεια εισοδου/*
    * 
    */
        public PointData(double[] x, double y) {
            this.x = x.clone();
            this.y = y;
        }
   /*ευκλιδεια μεθοδος του σημειου x/*
    * 
    */
        public double dist(double[] x) {
            double res = 0.0;
            for (int i = 0; i < x.length; i++)
                res = res + Math.pow(( this.x[i] - x[i] ), 2);
           
            return Math.sqrt(res);
        }
    }
 /*αρχικοποιω το score,το  ptd και το next /*
  * 
  */
    class Node {
        public double score;
        public PointData ptd;
        public Node next;
   /*Ο κατασκευαστης της κλασης δημιουργει κομβο λιστας από δεδομενο σημειο και το αποθηκευει ως ptd./*
    * 
    */
        public Node(double[] x, double y) {
           
            ptd = new PointData(x, y);
           
        }
    }
 /*δημιουργω την κλαση PointList /*
  * 
  */
    class PointList {
    	/*Αρχικοποιω το  Node first και το dim./*
    	 * 
    	 */
        public static Node first;
        private int dim = 0;
       
       
        public PointList(int dim) {
        	/*αρχικοποιω το first==  null και αναθετω την αναφορα this.dim=dim. /*
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
       /*επιστρέφει τη διάσταση των σημείων που αποθηκεύονται στη λίστα./*
        * 
        */
       
        public int getDim() {
            if ( !isEmpty() ) {
               
                return dim;
               
            }
            return 0;
        }
       /*επιστρέφει το πλήθος των κόμβων της λίστας /*
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
        /*επιστρεφει first==null αν η λιστα είναι γεματη./*
         * 
         */
       
        public boolean isEmpty() {
           
            return first == null;
           
        }
       /*εισάγει αντίγραφο του ζεύγους εισόδουx,y, ενθυλακωμένο σε αντικείμενο τη κλάσης PointData/*
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
       
       /*κοιταζει αν η λιστα είναι γεματη και  διαγράφει τον πρώτο κόμβο της λίστας /*
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
        /*ανακατεύει τυχαία και ομοιόμορφα τη σειρά /*
         * 
         */
        public void shuffle() {
        }
       
       /* κοιταζει αν η λιστα είναι γεματη και μετα εντοπίζει και διαγράφει από τη λίστα τον κόμβο που αποθηκεύει τα δεδομένα του εγγύτερου σημείου στο σημείο εισόδου x./*
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
        /*εντοπίζει στη λίστα τους k εγγύτερους γείτονες του σημείου εισόδου x/*
         * 
         */
        public PointList findKNearest(double[] x, int k) {
            return null;
        }
   /*κατηγοριοποιεί το σημείο εισόδου x,βάσει των σημείων που αποθηκεύονται στη λίστα στην οποία καλείται η μέθοδος/*
    * 
    */
        public double classify(double[] x) {
            return 0.0;
        }
       /*αποθηκευει τους κομβους και μετα τους εμφανιζει στην οθονη./*
        * 
        */
        public void toString(PointList list) {
            Node current = new Node(list.first.ptd.x, list.first.ptd.y);
            System.out.println(first.ptd.x.toString());
}
       
    }
    /*κατασκευάζει και επιστρέφει μία νέα λίστα και εμφανιζει η λιστα στην οθονη/*
     * 
     */
public class KNN {
	 public static void main(String[] args) {
         PointList list = PointList.readData("wine.dat");
         System.out.println();
         list.toString(list);
     }
}
