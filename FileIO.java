package ediFilterTutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO extends EDI{
	Printer printer;
	
	
	public FileIO(Printer printer) {
		this.printer = printer;
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
			setUnfilteredEDI(unFilteredData);
			return getUnfilteredEDI();
		}
	
	public void writeToFile(ArrayList<String> arrayList) {
		try (FileWriter fileWrite = new FileWriter(getFileWriteLocation())){
		
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i) != "" && arrayList != null) {
				fileWrite.write(arrayList + getSegmentTerminator() + "\n");
			}
		}
		//inform the user that the document was created
		printer.printMessageToForm("/n The Document" + getFileWriteLocation() + " has been created.");
		
		}catch(IOException io) {
			io.printStackTrace();
		}
	}
	public void writeToFile(String[] arrayList) {
		try (FileWriter fileWrite = new FileWriter(getFileWriteLocation())){
		
		for (int i = 0; i < arrayList.length; i++) {
			if (arrayList[i] != "" && arrayList != null) {
				fileWrite.write(arrayList + getSegmentTerminator() + "\n");
			}
		}
		//inform the user that the document was created
		printer.printMessageToForm("/n The Document" + getFileWriteLocation() + " has been created.");
		
		}catch(IOException io) {
			io.printStackTrace();
		}
	}
}
