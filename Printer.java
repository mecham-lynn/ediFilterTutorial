package ediFilterTutorial;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Printer {

	JTextArea form;
	
	public Printer(JTextArea form) {
		this.form = form;
	}
	public void printToForm(ArrayList<String> printData) {
		for (int y = 0; y < printData.size(); y++) {
			form.append("\n" + printData.get(y));
		}
	}
	public void logError(String Error) {
		System.out.println(Error);
	}
	
	public void printMessageToForm(String message) {
		form.append(message);
	}
	
	public void printDataToForm(String[] data, String segmentTerminator) {
		
		for (int i = 0; i < data.length; i++) {
			//prints the data from the segments array and 
			form.append(data[i] + segmentTerminator + "\n");
		}
	}
	public void setForm(JTextArea form) {
		this.form = form;
	}

}
