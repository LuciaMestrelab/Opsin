
import java.io.*;

public class Test {
    
    public static void main(String[] args){
	try 
        { 
	    Process p = Runtime.getRuntime().exec("obabel -:\"C[C@@H]1CC[C@@H](CC1)C\" -omol --gen2D");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
			p.getInputStream()), 8 * 1024);

		BufferedReader stdError = new BufferedReader(new InputStreamReader(
			p.getErrorStream()));

		// read the output from the command

		StringBuffer result= new StringBuffer();
		String s="";
		while ((s = stdInput.readLine()) != null)
		    result.append(s.replace("[", "").replace("]", "")).append("\r\n");
		System.out.println(result.toString());
        }
        catch(IOException e1) {System.out.println(e1);} 

    }

}
