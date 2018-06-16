package ediFilterTutorial;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO extends EDI{
	Printer printer;
	public FileIO(Printer printer) {
		this.printer = printer;
	}
	
	public void writeToFile(String[] data) {
		try (FileWriter fileWrite = new FileWriter(getFileWriteLocation())){
		
		for (int i = 0; i < data.length; i++) {
			if (data[i] != "" && data != null) {
				fileWrite.write(data + getSegmentTerminator() + "\n");
			}
		}
		//inform the user that the document was created
		printer.printMessageToForm("/n The Document" + getFileWriteLocation() + " has been created.");
		
		}catch(IOException io) {
			io.printStackTrace();
		}
	}
}
