package ediFilterTutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;

public class Reader {
	// Reads the contents of the file and stores it in a string;
	public String fileReader(File selectedFile) throws IOException {
		String unFilteredData = "";
//		String line = "";
		int value = 0;

		FileReader unFiltered = new FileReader(selectedFile.getAbsolutePath());
		
		// instantiates a new BufferedReader object using the unFiltered FileReader object
		BufferedReader reader = new BufferedReader(unFiltered);

		// stores each line in the variable line
		//line = reader.readLine();
		
		// while line isn't null append the data into the unFilteredData variable and store a new set of text in the line variable
		
		while((value = reader.read()) != -1) {
			
			char c = (char)value;
			unFilteredData += String.valueOf(c);
		}
		
		
//		while (line != null) {
//			unFilteredData += line;
//			line = reader.readLine();
//		}
		// close the readers
		reader.close();
		unFiltered.close();
		// return the populated variable 
		return unFilteredData;
	}

	public void formatter(String toFilter, File selectedFile, boolean isDsco, boolean isNordstrom, boolean isKohls, JTextArea formattedEdi) {
		char delimeter = toFilter.charAt(105);
		char separate = toFilter.charAt(103);
		// Gets the user home folder on the computer and assigns that the the string home
		String home = System.getProperty("user.home");

		String segmentTerminator = "";
		String elementSeparator = "";

		segmentTerminator = String.valueOf(delimeter);
// if the value in separate is an '*' character we will need to add some escampe characters to the front of it so it will work this is because the regex in the split method uses the * for a different function 
		if (separate == '*') {
			elementSeparator = "\\*";
		} else {
			elementSeparator = String.valueOf(separate);
		}
// Splits the toFilter data and assigns it to the segments String array
		String[] segments = toFilter.split(segmentTerminator);

// checks the file-size if the file size is less than 3MB we can print to the form
		if (selectedFile.length() <= 3000000) {
			if (isDsco) {
				// starts the error checking process for Dsco EDI spec (846 basically done, other documents not done)
				dscoErrorCheck(segments, selectedFile, elementSeparator);
			} else if (isNordstrom) {
				// starts the error checking process for Nordstrom EDI spec (Not Done)
				nordErrorCheck(segments, selectedFile, elementSeparator);
			} else if (isKohls) {
				// starts the error checking process for Dsco EDI spec (Not Done)
				kohlErrorCheck(segments, selectedFile, elementSeparator);
			} else {
				for (int i = 0; i < segments.length; i++) {
					//prints the data from the segments array and 
					formattedEDI.append(segments[i] + segmentTerminator + "\n");
				}

			}
// if the size is greater than 3MB we can't print to the form so we print to a file
		} else {
			try {
				//print message to the TextArea
				formattedEDI.setText(
						"The file was too big to process quickly. It is being processed in a seperate file in your Downloads Folder");
				String fileName = selectedFile.getName() + "_Filtered.txt";
				
				//Creates a new file at the destination
				File newFile = new File(home + "/Downloads/" + fileName);
				
				//checks if the file exists, if it does exist it doesn't write and stops.
				if (newFile.exists()) {
					formattedEDI.append("The file: " + fileName + " already exists");
				} else {
					// this is where the error checking part is going to come in for large files
					if (isDsco) {
						dscoErrorCheck(segments, selectedFile, elementSeparator);
					} else if (isNordstrom) {
						nordErrorCheck(segments, selectedFile, elementSeparator);
					} else if (isKohls) {
						kohlErrorCheck(segments, selectedFile, elementSeparator);
					} else {
						// if there is no error checking involved write the file to the Downloads folder on the machine
						FileWriter fileWrite = new FileWriter(home + "/Downloads/" + fileName);
						
						for (int i = 0; i < segments.length; i++) {
							if (segments[i] != "" && segments[i] != null) {
								fileWrite.write(segments[i] + segmentTerminator + "\n");
							}
						}
						//close the writer
						fileWrite.close();
						//inform the user that the document was created
						formattedEDI.append("/n The Document" + fileName + " has been created.");
					}
				}
			} catch (IOException e) {
				formattedEDI.append("/n There was an error writing the file: " + e.getMessage());
			}
		}
	}
}
