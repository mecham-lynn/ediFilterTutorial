package ediFilterTutorial;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class EDI {

	private boolean fileWriteFlag;
	private ArrayList <String> EDI_Data;
	private String fileWriteLocation;
	private String[] segments;
	private String segmentTerminator;
	private String transactionType;
	private File ediFile;
	private String unfilteredEdi;
	
	private boolean dscoRadioStatus;
	private boolean nordRadioStatus;
	private boolean kohlRadioStatus;
	
//	private JTextArea form;
//	
//	public void setForm(JTextArea form) {
//		this.form = form;
//	}
//	public JTextArea getForm() {
//		return form;
//	}
	
	public String getUnfilteredEDI() {
		return this.unfilteredEdi;
	}
	
	
	public void setUnfilteredEDI(String data) {
		this.unfilteredEdi = data;
	}
	
	
	public boolean getNordRadioStatus() {
		return this.nordRadioStatus;
	}

	public void setNordRadioStatus(boolean nordRadioStatus) {
		this.nordRadioStatus = nordRadioStatus;
	}

	public boolean getKohlRadioStatus() {
		return this.kohlRadioStatus;
	}

	public void setKohlRadioStatus(boolean kohlRadioStatus) {
		this.kohlRadioStatus = kohlRadioStatus;
	}

	public boolean getDscoRadioStatus() {
		return this.dscoRadioStatus;
	}

	public void setDscoRadioStatus(boolean dscoRadioStatus) {
		this.dscoRadioStatus = dscoRadioStatus;
	}
	
	public File getEdiFile() {
		return ediFile;
	}
	
	public void setEdiFile(File data) {
		this.ediFile = data;
	}
	
	
	public String getTransactionType() {
		return this.transactionType;
	}
	
	public void setTransactionType(String type) {
		this.transactionType = type;
	}
	
	public String getSegmentTerminator() {
		return this.segmentTerminator;
	}
	
	public void setSegmentTerminator(String terminator) {
		this.segmentTerminator = terminator;
	}
	
	public void setSegments(String[] segments) {
		this.segments = segments;
	}
	
	public String[] getSegments() {
		return this.segments;
	}
	
	public void setEDIData(ArrayList <String> Data) {
		this.EDI_Data = Data;
	}
	
	public ArrayList <String> getEDIData() {
		return this.EDI_Data;
	}
	
	
	public void setFileWriteLocation(String location) {
		this.fileWriteLocation = location;
	}
	
	public String getFileWriteLocation() {
		return this.fileWriteLocation;
	}
	
	
	
	public void setFileWriteFlag(boolean flag) {
		this.fileWriteFlag = flag;
	}
	public boolean getFileWriteFlag() {
		
		return this.fileWriteFlag;
	}
}
