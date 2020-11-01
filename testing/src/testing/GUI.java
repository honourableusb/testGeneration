package testing;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI extends Frame implements ActionListener, WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RIGHT = 0;
	private static final int CENTER = 0;
	private static boolean generateState = false; //the Generate button has NOT been pressed yet
	private final static int CHECKBOX_COUNT =  3;
	private static boolean[] checkBoxStates = new boolean[CHECKBOX_COUNT];
	private Button chooseFile = new Button ("Select file...");
	private Button Generate;
	private Checkbox StatementTest = new Checkbox("Statement Coverage");
	private Checkbox ConditionTest = new Checkbox("Condition Coverage");
	private Checkbox DecisionTest = new Checkbox("Decision Coverage");
	private CheckboxGroup outputFormat = new CheckboxGroup();
	private Dialog fileDialog = new Dialog(this, "Enter Filepath");
	private FileDialog fileStuff = new FileDialog(fileDialog);
	private Label filepathLBL;
	private Label outputLBL;
	private Label TestLBL = new Label("Generate Test sets for:" , CENTER);;
	private Panel inputPanel = new Panel();
	private Panel testPanel = new Panel();
	private Panel outputPanel = new Panel();
	private Panel generatePanel = new Panel();
	private String filepath;
	private static TextField filepathTXT = new TextField("", 30);
	
	GUI()
	{
		setLayout(new FlowLayout());
		setTitle("Test Generator");
		setSize(700,425);
		addWindowListener(this);
		
		fileStuff.setMode(FileDialog.LOAD);
		fileStuff.setMultipleMode(false);
		
		
		filepathLBL = new Label("Enter filepath for .java file here ->" , RIGHT);
		inputPanel.add(filepathLBL);
		inputPanel.add(filepathTXT);
		chooseFile.addActionListener(this);
		chooseFile.setActionCommand("fileInput");
		inputPanel.add(chooseFile);
		//This next thing does not work
		filepathTXT.setText(filepath);
		
		testPanel.add(TestLBL);
		testPanel.add(StatementTest);
		testPanel.add(DecisionTest);
		testPanel.add(ConditionTest);		

		outputLBL = new Label("Select an output format:");
		outputPanel.add(outputLBL);
		outputPanel.add(new Checkbox("All test sets in one file" , outputFormat,true));
		outputPanel.add(new Checkbox("Individual files for each set", outputFormat, false));
		
		Generate = new Button("Generate Tests");
		Generate.addActionListener(this);
		Generate.setActionCommand("Generate");
		generatePanel.add(Generate);
		
		add(inputPanel);
		add(testPanel);
		add(outputPanel);
		add(generatePanel);
		setVisible(true);
	}
	
	public static boolean[] getCheckboxStates()
	{
		return checkBoxStates;
	}
	
	public int getCheckboxCount()
	{
		return GUI.CHECKBOX_COUNT;
	}
	
	public void setGenerateState(boolean state)
	{
		generateState = state;
	}
	
	public static boolean getGenerateState()
	{
		return generateState;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("A");
		if(e.getActionCommand().equals("fileInput")) //If an action has been received from the "Select file..." Button, open a file dialog
			//for users to pick their file from
		{
			//System.out.println("a");
			fileStuff.setVisible(true);
			filepath = fileStuff.getDirectory() + fileStuff.getFile();
			if(!filepath.equals("nullnull")) // if a file was selected in the fileDialog
				filepathTXT.setText(filepath);
		}
		if(e.getActionCommand().equals("Generate")) //If an action has been received from the Generate button
		{
			setGenerateState(true); // the generate button has been pressed, if the inputs are valid at the end of the method
			// the main computing components of the program will begin execution
			
			//Gets the filepath that is in the textfield
			String filepath = filepathTXT.getText();
			
			if(filepath.equals("")) //if the text field is not populated, throw an error message
			{
				new ErrorPopup("You need to have a file!");
				setGenerateState(false); // do not continue with the driver function
				return;
			}
			
			//Creating a file to check if the file exists
			File inputFile = new File(filepath);
			//If the file doesn't exist, throw an error and stop proceeding.
			if(!inputFile.exists())
			{
				new ErrorPopup("File not found. Please enter the full path of the file, or try a different file.");
				setGenerateState(false);
				return;
			}
			
			//If the file exists, proceed on to the next condition, checking the checkBoxes for which test sets to generate
			checkBoxStates[0] = StatementTest.getState();
			checkBoxStates[1] = DecisionTest.getState();
			checkBoxStates[2] = ConditionTest.getState();
			int stateSum = 0;
			for(boolean status : checkBoxStates)
			{
				if(status == true)
					stateSum++;
			}
			//If no test sets are to be generated, throw an error. There's nothing for the code to do in that case!
			if (stateSum == 0)
			{
				new ErrorPopup("Please select at least one test set to generate.");
				setGenerateState(false); //try again!
				return;
			}
			
			//Now we need to check which test sets are desired, and modify selections accordingly.
			//Note: both condition and decision coverage will be using information from statement coverage, specifically the conditional
			//statements that are found and interpreted there.
		}
	}

	public static String getFilepath() { return filepathTXT.getText(); }
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class ErrorPopup extends Frame implements ActionListener, WindowListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		Panel errorPanel = new Panel();
		Button exitButton = new Button("Ok");
		Label ErrorMSG;
		
		@SuppressWarnings("unused")
		//In case I'm stupid, I have an error to tell me!
		ErrorPopup()
		{
			//code this fully 
			setAlwaysOnTop(true);
			setLayout(new FlowLayout());
			setTitle("Test Generator - Error");
			this.ErrorMSG = new Label("Error: The programmer forgot to specify what error occured!");
			add(ErrorMSG);
			setSize(500,75);
			add(exitButton);
			exitButton.addActionListener(this);
			addWindowListener(this);
			setAlwaysOnTop(true);
			addWindowListener(this);
			setVisible(true);
		}
		
		ErrorPopup(String ErrorMSG)
		{
			setAlwaysOnTop(true);
			setLayout(new FlowLayout());
			setTitle("Test Generator - Error");
			this.ErrorMSG = new Label("Error: " + ErrorMSG);
			errorPanel.add(this.ErrorMSG);
			add(errorPanel);
			add(exitButton);
			exitButton.addActionListener(this);
			exitButton.setActionCommand("Ok");
			addWindowListener(this);
			setSize(600,100);
			setAlwaysOnTop(true);
			setVisible(true);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			//debugging code to ensure a proper action is being received
			//System.out.println("WindowClosing activated within " + this.toString());
			setVisible(false);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("Ok"))
			{
				setVisible(false);
				return;
			}
		}
	}
}
