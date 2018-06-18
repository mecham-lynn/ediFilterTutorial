package ediFilterTutorial;

import java.util.ArrayList;

public class EDI {

	private boolean fileWriteFlag;
	private ArrayList <String> EDI_Data;
	private String fileWriteLocation;
	private String[] segments;
	private String segmentTerminator;
	private String transactionType;
	
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
