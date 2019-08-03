/**
 * @author Jan
 * 
 */
public class ScannerDemo {

	private static String file1 = "C:\\Users\\jangerhard\\Desktop\\prog2.jay";
	private static String file2 = "C:\\Users\\jangerhard\\Desktop\\prog1.jay";
	private static int counter = 1;

	public static void main(String args[]) {

		TokenStream ts = new TokenStream(file1);

		System.out.println(file1);
		
		Token t;

		while (!ts.isEndofFile()) {
			// TO BE COMPLETEDs
			
			t = ts.nextToken();
			
			System.out.println("Token " + counter + " - Type: " + t.getType() + " - Value: " + t.getValue());
			counter++;
		}
		
		System.out.println("\n****************************************\n");
		
		ts = new TokenStream(file2);
		
		counter = 1;

		System.out.println(file2);

		while (!ts.isEndofFile()) {
			// TO BE COMPLETEDs
			
			t = ts.nextToken();
			
			System.out.println("Token " + counter + " - Type: " + t.getType() + " - Value: " + t.getValue());
			counter++;
		}
	}
}
