import java.io.*;
import java.util.*;

public class p{
	
	public static void main(String arg[]) throws Exception{
		FileWriter f1 = new FileWriter("p.txt");
		BufferedWriter b1 = new BufferedWriter(f1);
		
		b1.write("L1"+ "--" +"3");
		b1.write("L2"+ "--" +"6");
		b1.newLine();
		
		b1.close();
	}
}