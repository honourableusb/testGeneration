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
	
	fileProcessor(String filepath)
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

}