//Testing code = This code is intended to take .java files and parse them, and generate different test sets using different testing methods.
//Matthew Haskell - Created 10/17/2020


/*To do list:
 * Statement coverage
 * 	- Parsing lines, checking syntax (if(condition){ vs if(condition)\n{
 * Decision Coverage
 * Condition Coverage
 * file output
 * 	- Getting values from GUI determining whether or not the output is all in one file, or in multiple files
 * 	- File formatting for outputs
 * and much, much more!
 */

package testing;
//import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//import java.awt.*;
//import java.awt.event.*;

public class Main{

	
	public static void main(String[] args)
	{
		/*
		 * LinkedList<Integer> k; 
		 * k = new LinkedList<Integer>();
		 * k.insertToEnd(1);
		 * k.insertToEnd(3); 
		 * k.insertToEnd(2); 
		 * System.out.println(k.getSize());
		 * System.out.println(k); 
		 * k.remove(2); 
		 * System.out.println(k);
		 */
		new GUI();
		boolean[] checkboxStates;
		while(GUI.getGenerateState() == false) // while the Generate button has not been pressed
		{
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Sleep function interrupted");
				e.printStackTrace();
				System.exit(-1);
			}
		}
		System.out.println("Code executing!");
		String filepath = GUI.getFilepath();
		checkboxStates = GUI.getCheckboxStates();
		//System.out.println(filepath);
		new fileProcessor(filepath, checkboxStates);
	}
	
}
