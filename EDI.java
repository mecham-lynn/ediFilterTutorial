package ediFilterTutorial;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private boolean dsco3RadioStatus;
	
//	private JTextArea form;
//	
//	public void setForm(JTextArea form) {
//		this.form = form;
//	}
//	public JTextArea getForm() {
//		return form;
//	}
	
	private HashMap<String, Boolean> requiredFields = new HashMap<String, Boolean>();
	
	
	private HashMap<String, Boolean> Dsco856 = new HashMap<String, Boolean>();
	private HashMap<String, Boolean> Dsco846 = new HashMap<String, Boolean>();
	private HashMap<String, Boolean> Dsco810 = new HashMap<String, Boolean>();
	private HashMap<String, Boolean> Dsco870 = new HashMap<String, Boolean>();
	
//	public void clearEDIData() {
//		EDI_Data.clear();
//		ediFile = null;
//	}
	
	public void createFieldsDsco870() {
		Dsco870.put("ST", false);
		Dsco870.put("BSR", false);
		Dsco870.put("HL", false);
		Dsco870.put("PRF", false);
		Dsco870.put("PO1", false);
		Dsco870.put("ISR", false);
		Dsco870.put("SE", false);
		
		setRequiredFields(Dsco870);
	}
	
	
	public void createFieldsDsco810() {
		Dsco810.put("ST", false);
		Dsco810.put("BIG", false);
		Dsco810.put("ITD", false);
		Dsco810.put("IT1", false);
		Dsco810.put("TDS", false);
		Dsco810.put("CTT", false);
		Dsco810.put("SE", false);
		
		setRequiredFields(Dsco810);
	}
	
	public void createFieldsDsco856() {
		Dsco856.put("ST", false);
		Dsco856.put("BSN", false);
		Dsco856.put("HL", false);
		Dsco856.put("TD5", false);
		Dsco856.put("REF", false);
		Dsco856.put("DTM", false);
		Dsco856.put("LIN", false);
		Dsco856.put("SN1", false);
		Dsco856.put("PRF", false);
		Dsco856.put("SE", false);
		
		setRequiredFields(Dsco856);
	}
	
	public void createFieldsDsco846() {
		
		Dsco846.put("ST", false);
		Dsco846.put("BIA", false);
		Dsco846.put("LIN", false);
		Dsco846.put("QTY", false);
		Dsco846.put("SE", false);
		
		setRequiredFields(Dsco846);
	}
	
	public void setRequiredFields(HashMap<String, Boolean> data) {
		this.requiredFields = data;
	}
	
	public HashMap<String, Boolean> getRequiredFields(){
		return this.requiredFields;
	}
	
	public String getUnfilteredEDI() {
		return this.unfilteredEdi;
	}
	
	
	public void setUnfilteredEDI(String data) {
		this.unfilteredEdi = data;
	}
	
	public boolean getDsco3RadioStatus() {
		return this.dsco3RadioStatus;
	}
	
	
	public void setDsco3RadioStatus(boolean status) {
		this.dsco3RadioStatus = status;
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
