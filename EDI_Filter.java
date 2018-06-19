package ediFilterTutorial;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.*;
import java.util.ArrayList;
import java.util.Date;

public class EDI_Filter extends EDI implements ActionListener {

	//Creating all of the components of the application
	JFileChooser fileChooser;
	JTextArea formattedEDI;
	JScrollPane jsp;
	JButton fileSelectorButton, clearContents;
	JLabel ediLab, buttonLab;
	JRadioButton dscoRadio, nordRadio, kohlRadio;
	ButtonGroup filterGroup;
	
	Printer printer = new Printer(formattedEDI);
	Dsco dsco = new Dsco(printer);
	Nord nord = new Nord(printer);
	Kohl kohl = new Kohl(printer);
	FileIO fileIO = new FileIO(printer);
	EDI edi = new EDI();

	//This method is one I found that centers the application window to the bounds of your screen
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
// the main start point of the class
	EDI_Filter() {
		//instantiating and creating the JFrame 
		JFrame frm = new JFrame("EDI Filter Tool");
//setting the settings of the frm object
		frm.setBackground(Color.LIGHT_GRAY);
		// this layout is very basic
		frm.setLayout(new FlowLayout());
		frm.setSize(800, 800);
		//calling the center window method
		centreWindow(frm);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ediLab = new JLabel("EDI Filter 1.0");
		buttonLab = new JLabel("Press a button to select a file to filter or clear the text");

		dscoRadio = new JRadioButton("DSCO EDI (846 READY)");
		// dscoRadio.setActionCommand("Dsco Filter");
		nordRadio = new JRadioButton("NORDSTORM EDI(UNDER DEV)");
		// nordRadio.setActionCommand("Nordstrom Filter");
		kohlRadio = new JRadioButton("KOHLS EDI (UNDER DEV)");
		// kohlRadio.setActionCommand("Kohl Filter");

		filterGroup = new ButtonGroup();

		filterGroup.add(dscoRadio);
		filterGroup.add(nordRadio);
		filterGroup.add(kohlRadio);
		
// instantiating the buttons and adding action listeners
		fileSelectorButton = new JButton("Select File");
		fileSelectorButton.setActionCommand("selectFile");
		clearContents = new JButton("Clear Text");
		clearContents.setActionCommand("clearContents");
		clearContents.addActionListener(this);
		fileSelectorButton.addActionListener(this);
		
//isntantiating the text area (i.e the form where the data is going to show)
		formattedEDI = new JTextArea(30, 60);
		formattedEDI.setEditable(false);
		printer.setForm(formattedEDI);
		
//instantiating the scroll panel and adding it to the TextArea
		jsp = new JScrollPane(formattedEDI);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

//Adding all of the components to the JFrame frm
		frm.add(ediLab);
		frm.add(dscoRadio);
		frm.add(nordRadio);
		frm.add(kohlRadio);
		frm.add(jsp);
		frm.add(buttonLab);
		frm.add(fileSelectorButton);
		frm.add(clearContents);

//Making the frame and its components visible
		frm.setVisible(true);

	}

// Action Listeners method. This is dependent on the Action Commands set on the buttons
	public void actionPerformed(ActionEvent ae) {
	
		// start the switch statement using the action command
		switch (ae.getActionCommand()) {
		case "selectFile":
			setDscoRadioStatus(dscoRadio.isSelected());
			setNordRadioStatus(nordRadio.isSelected());
			setKohlRadioStatus(kohlRadio.isSelected());
			
			// If radio button is selected get the file and start the error checker for
			if (getDscoRadioStatus()) {
				//try catch block to catch possible errors finding the files
				try {
					//calling the File Selector Method which returns a File object and storing it in the variable selectedFile
					fileSelector();
					//call the formatter method which takes the data from the file object and formats it as well as sending the data to the error checking method
					formatter(fileReader(getEdiFile()), getEdiFile(), true, false, false);
				} catch (IOException e) {
					printer.printMessageToForm("\n" + e.getMessage());
					e.printStackTrace();
				}
				// If no radio button is selected just run the formatter method without any error checking
			} else if(getNordRadioStatus()) {
				try {
					fileSelector();
					formatter(fileReader(getEdiFile()),getEdiFile(),false, true, false);
				}catch(IOException e) {
					printer.printMessageToForm("\n" + e.getMessage());
					e.printStackTrace();
				}
				
			} else if (getKohlRadioStatus()) {
				try {
					fileSelector();
					formatter(fileReader(getEdiFile()),getEdiFile(),false,false,true);
				}catch(IOException e) {
					printer.printMessageToForm("\n" + e.getMessage());
					e.printStackTrace();
				}
				
			}
			else {
				try {
					//calling the File Selector Method which returns a File object and storing it in the variable selectedFile
					fileSelector();
					//Runs the formatter method without any error checking
					formatter(fileReader(getEdiFile()), getEdiFile(), false, false, false);

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			break;
			// if the Clear Contents button is pushed set the value inside the TextArea to blank.
		case "clearContents":
			formattedEDI.setText("");
			break;
		}
	}

	// Selects a file based on the users input returns the file object
	public  void fileSelector() {
		// stores an int of 1 or 0 when the user selects a file and clicks either Open or Close 
		int result = fileChooser.showOpenDialog(null);
		
		// if result is a 0 then store the file Object in the selectedFile variable and return it.
		if (result == JFileChooser.APPROVE_OPTION) {
			setEdiFile(fileChooser.getSelectedFile());
			
			
			// if a 1 is sent for the result then write the message to the TextArea
		} else if (result == JFileChooser.CANCEL_OPTION) {
			printer.printMessageToForm("The Cancel Option was selcted");
		}
		
	}

	// Reads the contents of the file and stores it in a string;
	public String fileReader(File selectedFile) throws IOException {
		String unFilteredData = "";
		int value = 0;

		// instantiates a new fileReader object called unFiltered and uses the absolute path of the file selected earlier in the fileSelector method
		FileReader unFiltered = new FileReader(selectedFile.getAbsolutePath());
		
		// instantiates a new BufferedReader object using the unFiltered FileReader object
		BufferedReader reader = new BufferedReader(unFiltered);
		
		// while the character isn't blank append the data into the unFilteredData variable and store a new set of text in the line variable
		
		while((value = reader.read()) != -1) {
			
			// assign the int value of the char to value
			char c = (char)value;
			//store the converted value into unFilteredData 
			unFilteredData += String.valueOf(c);
		}
		// close the readers
		reader.close();
		unFiltered.close();
		// return the populated variable 
		return unFilteredData;
	}

	public void formatter(String toFilter, File selectedFile, boolean isDsco, boolean isNordstrom, boolean isKohls) {
		char delimeter = toFilter.charAt(105);
		char separate = toFilter.charAt(103);
		// Gets the user home folder on the computer and assigns that the the string home
		String home = System.getProperty("user.home");

		String segmentTerminator = "";
		String elementSeparator = "";

		segmentTerminator = String.valueOf(delimeter);
// if the value in separate is an '*' character we will need to add some escape characters to the front of it so it will work this is because the regex in the split method uses the * for a different function 
		if (separate == '*') {
			elementSeparator = "\\*";
		} else {
			elementSeparator = String.valueOf(separate);
		}
// Splits the toFilter data and assigns it to the segments String array
		setSegments(toFilter.split(segmentTerminator));

// checks the file-size if the file size is less than 3MB we can print to the form
		if (selectedFile.length() <= 3000000) {
			if (isDsco) {
				// starts the error checking process for Dsco EDI spec (846 basically done, other documents not done)
				dsco.dscoErrorCheck(getSegments(), selectedFile, elementSeparator);
			} else if (isNordstrom) {
				// starts the error checking process for Nordstrom EDI spec (Not Done)
				nordErrorCheck(getSegments(), selectedFile, elementSeparator);
			} else if (isKohls) {
				// starts the error checking process for Dsco EDI spec (Not Done)
				kohlErrorCheck(getSegments(), selectedFile, elementSeparator);
			} else {
				printer.printDataToForm(getSegments(),segmentTerminator);

			}
// if the size is greater than 3MB we can't print to the form so we print to a file
		} else {
			try {
				boolean flag = true;
				
				setFileWriteFlag(flag);
				
				//print message to the TextArea
				printer.printMessageToForm(
						"The file was too big to process quickly. It is being processed in a seperate file in your Downloads Folder");
				
				String fileName = selectedFile.getName() + "_Filtered.txt";
				
				//Creates a new file at the destination
				setFileWriteLocation(home + "/Downloads/" + fileName);
				
				File newFile = new File(getFileWriteLocation());
				
				//checks if the file exists, if it does exist it doesn't write and stops.
				if (newFile.exists()) {
					printer.printMessageToForm("The file: " + fileName + " already exists");
				} else {
					// this is where the error checking part is going to come in for large files
					if (isDsco) {
						dsco.dscoErrorCheck(getSegments(), selectedFile, elementSeparator);
					} else if (isNordstrom) {
						nordErrorCheck(getSegments(), selectedFile, elementSeparator);
					} else if (isKohls) {
						kohlErrorCheck(getSegments(), selectedFile, elementSeparator);
					} else {
						// if there is no error checking involved write the file to the Downloads folder on the machine
						fileIO.writeToFile(getSegments());
					}
				}
			} catch (Exception e) {
				printer.logError("/n There was an error writing the file: " + e.getMessage());
			}
		}
	}

//possible class for each spec type?
	public void nordErrorCheck(String[] fileData, File selectedFile, String elementSeparator) {

	}

	public void kohlErrorCheck(String[] fileData, File selectedFile, String elementSeparator) {

	}

// In-depth Error checking for Dsco 846:
	

	public static boolean dateChecker(String date, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		formatter.setLenient(false);
		// Date dateValue = null;
		try {
			formatter.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EDI_Filter();
			}
		});
		
	}
}
