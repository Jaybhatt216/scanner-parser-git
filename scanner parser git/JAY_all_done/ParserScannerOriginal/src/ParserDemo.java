public class ParserDemo {
	private static String file2 = "C:\\Users\\jangerhard\\Desktop\\tests\\prog2.jay";
	private static String file3 = "C:\\Users\\jangerhard\\Desktop\\tests\\prog3.jay";
	private static String file4 = "C:\\Users\\jangerhard\\Desktop\\tests\\prog4.jay";
	private static String file5 = "C:\\Users\\jangerhard\\Desktop\\tests\\prog5.jay";
	private static String file6 = "C:\\Users\\jangerhard\\Desktop\\tests\\prog6.jay";
	private static String file7 = "C:\\Users\\jangerhard\\Desktop\\tests\\prog7.jay";

	public static void main(String[] args) { 
		
		int fileNum = 2; // Edit this number to test different files
		
		String fileStart = "C:\\Users\\jangerhard\\Desktop\\tests\\prog"; // Edit filepath here to match where you put your test-files
		String fileEnd = ".jay";
		String file = fileStart+fileNum+fileEnd;
		
		TokenStream tStream = new TokenStream(file);
		System.out.println("Testing test-file number: " + fileNum);
		ConcreteSyntax cSyntax = new ConcreteSyntax(tStream);
		Program p = cSyntax.program();
		System.out.println(p.display());
		System.out.println("Done");
	}

}
