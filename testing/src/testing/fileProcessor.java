//fileProcessor.java
//This program will do most of the parsing of the file given, specifically obtaining the values for lines, statements, and decisions.

package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileProcessor {
	
	File inputFile;
	String filepath;
	private fileInformation info;
	String[] lines;
	
	fileProcessor(String filepath, boolean[] tests)
	{
		int lines;
		info = new fileInformation();
		this.filepath = filepath;
		System.out.println("Filepath entered is: " + filepath);
		inputFile = new File(filepath);
		System.out.println("Before countLines");
		lines = countLines();
		//System.out.println("Right before nullpointer - lines = " + lines);
		info.setTotalLines(lines);
		//System.out.println(info.getTotalLines());
		//System.out.println(filepath + " lines: " + lines);
		//Test code - Print the entire file
		/*String placeholder;
		while (input.hasNextLine())
		{
			placeholder = input.nextLine();
			System.out.println(placeholder);
		}*/
		statementList();
	}
	
	private int countLines()
	{
		int lineCounter = 0;
		Scanner input = null;
		try {
			input = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found :" + inputFile.getName());
			e.printStackTrace();
		}
		while(input.hasNext())
		{
			
			lineCounter++;
			input.nextLine();		
		}
		//System.out.println(lineCounter);
		return lineCounter;
	}
	
	/* statementList = This method is the main workhorse of the fileProcessor class. It parses the file given into parts of executable code
	 * 
	 */
	private void statementList()
	{
		Scanner line = null;
		try {
			line = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + inputFile.getName());
			e.printStackTrace();
		}
		
		String input = "";
		//this loop executes through the initial chunk of code before the first methods of a class, including class variables.
		while(line.hasNextLine())
		{
			input = line.nextLine(); 
			input = input.replaceAll("\t", "");
			// System.out.println(input);
			if(input.contains("(") && input.contains(")")) //If the line has a set of parentheses
				if(!(input.contains("new") || input.contains("="))) 	
				{
					System.out.println("Beginning of executable code (rough pass)\n" + input);
					break;
				}
				
				
				
		}
		
	}
	
	private class fileInformation {
		private int totalLines;
		LinkedList<Pair<Integer,String>> statements;
		int[] decisions;
		int[] conditions;
		
		fileInformation()
		{
		totalLines = -1;
		statements = new LinkedList<Pair<Integer,String>>();
		}
		
		public void setTotalLines(int val)
		{
			this.totalLines = val;
		}
		
		public int getTotalLines()
		{
			return totalLines;
		}
}

}