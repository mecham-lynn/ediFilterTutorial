package ediFilterTutorial;

import java.io.File;
import java.util.ArrayList;

public class Dsco {
	Error error = new Error();
	Printer printer;
	
	public Dsco(Printer printer) {
		this.printer = printer;
	}


	public void dscoErrorCheck(String[] fileData, File selectedFile, String elementSeparator) {
		// temporary holder for each line of the document
				String holder = "";
				String[] element;

				boolean go = true;
				int stop = 0;

				// key for the document type will be 846, 855, 810, 870 or 856
				String transactionType = "";

				// List containing the segment data from ST to SE, which is where the transactions specific data is
				ArrayList<String> transactionData = new ArrayList<String>();

				// List containing the segments ISA, GS, GE, and IEA. (not sure if I need this)
				ArrayList<String> documentHeaderData = new ArrayList<String>();
				
				// while the variable is true continue the loop
				while (go == true) {
					for (int i = 0; i < fileData.length; i++) {
						if (stop == 1) {
							stop = 0;
							break;
						} else {
							// sets the contents of the array into a String where it can be split later
							holder = fileData[i];
							
							// split the holder string into elements using the elementSeparator variable
							element = holder.split(elementSeparator);
							
							//filter through the element looking at position 0
							for (int x = 0; x < element.length; x++) {
								if (stop == 1) {
									break;
								} else {
									//
									if (element[0].equals("ST")) {
										transactionData.add(fileData[i]);
										if (element[1].isEmpty()) {
											
											// call printer class here
											printer.logError(" " + error.getErrorMessage("General", "ST01 Empty"));
											stop = 1;
											// stop filter because we don't know what the document is going to be doing.
											go = false;
											break;
										}
										// if the ST01 isn't empty store it in the variable this is what we are going to Switch on later
										transactionType = element[1];
										break;
										// if any of the 0 position elements are these values add them, but don't filter them
									} else if (element[0].equals("ISA") || element[0].equals("GS") || element[0].equals("GE")
											|| element[0].equals("IEA")) {
										documentHeaderData.add(fileData[i]);
										break;
										// if the 0 element is "SE" that is the end of the transaction and the data in the transactionData array list is what we are going to do the bulk of the error checking
									} else if (element[0].equals("SE")) {
										transactionData.add(fileData[i]);
										
										// take the variable in the transactionType and do a switch based on that value to error check the correct document
										switch (transactionType) {
										case "846":
											//start the error checking process for the Dsco 846 passing the Array List of the data and the elementSeparator variable
											dsco846(transactionData, elementSeparator);
											break;
										case "856":
											System.out.println(transactionData);
											//formattedEDI.append("\n You got to the 856 Switch statement");
											// dsco856(transactionData);
											break;
										case "870":
											System.out.println(transactionData);
											//formattedEDI.append("\n You got to the 870 Switch statement");
											// dsco870(transactionData);
											break;
										case "810":
											System.out.println(transactionData);
											//formattedEDI.append("\n You got to the 810 Switch statement");
											// dsco810(transactionData);
											break;
										}
										go = false;
										stop = 1;
										break;
									} else {
										// formattedEDI.setText(element[0]);
										transactionData.add(fileData[i]);
										break;
									}
								}
							}
						}
					}
				}
			}

	
	private void dsco846(ArrayList<String> transactionData, String elementSeparator) {
		
		String holder = "";
		String[] element;
		String message = "";
		String dateFormat = "yyyyMMdd";
		String timeFormat = "HHmmss";


		boolean stop = false;
		String transactionSetControlHeader = "";
		
		// this will be the array that contains what we are going to print to the field--- had to do it this way because the error messages weren't getting added to the original ArrayList
		ArrayList<String> errorInformation = new ArrayList<String>();

		// Start the loop through the passed transaction data
		for (int i = 0; i < transactionData.size(); i++) {
			if (stop != false) {
				stop = false;
			}
			// assign the array element to holder 
			holder = transactionData.get(i);
			//split holder into the element array. So we can evaluate each segment of the EDI
			element = holder.split(elementSeparator);
			for (int x = 0; x < element.length; x++) {
				if (stop != false) {
					break;
				}
				switch (element[0]) {
				
				// Error Checking for the ST segment
				case "ST":
					//checking the length of the array
					if (element.length != 3) {
						message += error.getErrorMessage("General", "ST Size");
						break;
					}
					//if the ST02 element isn't empty check the length of the data in that element.
					if (!element[2].isEmpty()) {
						if (element[2].length() > 9) {
							message += error.getErrorMessage("846", "ST02 Size");
						}
					}
					//check to see if ST02 is empty if it is pull an error
					if (element[2].isEmpty()) {
						message += error.getErrorMessage("General", "ST02 empty");
					}
					// if it isn't empty store the value in the ST02 in the transactionSetControlHeader variable for later comparisson
					else {
						transactionSetControlHeader += element[2];
					}
					// add the data with any errors to the errorInformation ArrayList
					errorInformation.add(holder + message);
					//set message to blank to be ready for the next set of errors
					message = "";
					// get out of the loop so we can move on to the next segement in the EDI
					stop = true;
					break;
					
					
					
				// Error Checking of the BIA segment
				case "BIA":
					//Try catch to look out for segments that are out of bounds. Most segments in the EDI are optional and don't get checked for formatting errors. So sometimes we run into a segment that isn't as long as it should be
					//which means that the elements array is shorter than expected.
					try {
						// most of the logic for the rest of the method is specific to the 846 spec.
						if (!element[1].equals("00")) {
							message += error.getErrorMessage("846", "BIA01 Value");
						}if (!element[2].equals("MM")) {
							message += error.getErrorMessage("846", "BIA02 Value");
						}if (element[3].isEmpty()) {
							message += error.getErrorMessage("846", "BIA03 Empty");
						}if (EDI_Filter.dateChecker(element[4], dateFormat) == false) {
							message += error.getErrorMessage("846", "BIA04 Date");
						}if (EDI_Filter.dateChecker(element[5], timeFormat) == false) {
							message += error.getErrorMessage("846", "BIA05 Time");
						}if (element.length < 6 || element.length > 6) {
							message += error.getErrorMessage("846", "BIA Size");
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;

				// Error Checking of the CUR segment
				case "CUR":
					try {
						if (element.length > 3 || element.length < 3) {
							message += error.getErrorMessage("846", "CUR Size");
							break;
						} else if (!element[1].equals("SE")) {
							message += error.getErrorMessage("846", "CUR01 Value");
						} else if (!element[2].equals("USD")) {
							message += error.getErrorMessage("846", "CUR02 Value");
						}

					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;
				
					
				// Error Checking of the REF segment
				case "REF":
					try {
						// REF segment can be either 2 or 3 segments long.
						if (element.length > 4 || element.length < 3) {
							message += error.getErrorMessage("846", "REF Size");
							break;
						}
						//check value of the REF01
						if (element[1].equals("IA")) {
							if (element[2].isEmpty()) {
								message += error.getErrorMessage("846", "REF02 Empty");
							}
						}
						//check value of the REF01
						if (element[1].equals("ZZ")) {
							if (element[3].equals("status")) {
								//checks for all of the possible values that the REF02 can be
								if (!element[2].equals("in-stock") && !element[2].equals("out-of-stock")
										&& !element[2].equals("discontinued") && !element[2].equals("hidden")
										&& !element[2].equals("pending")) {
									message += error.getErrorMessage("846", "REF02ZZ Status Value");
								}
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;
				// Error checking of the LIN segment

					// This is the big segment where all the item information is populated and updated.
				case "LIN":
					try {
						// Check the length for the max and min values
						if (element.length > 32 || element.length < 4) {
							message += error.getErrorMessage("846", "LIN Size");
						}
						//if the LIN02 segment is empty throw error since that field is required
						if (element[2].isEmpty()) {
							message += error.getErrorMessage("846", "LIN02 Req");
						}
						//Check the value of the LIN02, which has to be equal to 'SK'
						if (!element[2].equals("SK")) {
							message += error.getErrorMessage("846", "LIN02 Value");
						}// LIN03 can't be empty
						if (element[3].isEmpty()) {
							message += error.getErrorMessage("846", "LIN03 Req");
						}
						// Check the length of the element because suppliers can send less than 5 segments
						if (element.length >= 6) {
							if (!element[4].isEmpty()) {
								//LIN04 has to be "UP"
								if (!element[4].equals("UP")) {
									message += error.getErrorMessage("846", "LIN04 Value");
								}
								// UPCs have to be either 8 or 12 digits long. They cannot be anything else (most common error I see with EDI)
								if (element[5].length() != 12 && element[5].length() != 8) {
									message += error.getErrorMessage("846", "LIN05 Size");
								}//LIN05 can't be empty when LIN04 has data
								if (element[5].isEmpty()) {
									message = message + error.getErrorMessage("846", "LIN05 Empty");
								}
							}
						}
						if (element.length >= 7) {
							if (!element[6].equals("EN")) {
								message += error.getErrorMessage("846", "LIN06 Value");
							}
							if (!element[6].isEmpty()) {
								if (element[7].isEmpty()) {
									message += error.getErrorMessage("846", "LIN07 Empty");
								} else {
									//EAN's can only have 13 digits. No more no less.
									if (element[7].length() != 13) {
										message += error.getErrorMessage("846", "LIN07 Size");
									}
								}
							}
						}
						if (element.length >= 9) {
							if (!element[8].isEmpty()) {
								if (!element[8].equals("MG")) {
									message += error.getErrorMessage("846", "LIN08 Value");
								}
								if (element[9].isEmpty()) {
									message += error.getErrorMessage("846", "LIN09 Empty");
								}

							}
						}
						if (element.length >= 11) {
							if (!element[10].isEmpty()) {
								if (!element[10].equals("IB")) {
									message += error.getErrorMessage("846", "LIN10 Value");
								} else if (element[11].isEmpty()) {
									message += error.getErrorMessage("846", "LIN11 Empty");
								}
							}
						}
						if (element.length >= 13) {
							if (!element[12].isEmpty()) {
								if (!element[12].equals("UK")) {
									message += error.getErrorMessage("846", "LIN12 Value");
								}
								if (element[13].isEmpty()) {
									message += error.getErrorMessage("846", "LIN13 Empty");
								}
							}
						}
						if (element.length >= 15) {
							if (!element[14].isEmpty()) {
								if (!element[14].equals("BL")) {
									message += error.getErrorMessage("846", "LIN14 Value");
								}
								if (element[15].isEmpty()) {
									message += error.getErrorMessage("846", "LIN15 Empty");
								}
							}
						}
						if (element.length >= 17) {
							if (!element[16].isEmpty()) {
							}
							if (!element[16].equals("SK")) {
								message += error.getErrorMessage("846", "LIN16 Value");
							}
							if (element[17].isEmpty()) {
								message += error.getErrorMessage("846", "LIN17 Empty");
							}
						}
						if (element.length >= 19) {
							if (!element[18].isEmpty()) {
							}
							if (!element[18].equals("SK")) {
								message += error.getErrorMessage("846", "LIN18 Value");
							}
							if (element[19].isEmpty()) {
								message += error.getErrorMessage("846", "LIN19 Empty");
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;
				// Error checking for the PID segment

				case "PID":
					try {
						//size if this segment must be 5. 
						if (element.length != 6) {
							message += error.getErrorMessage("846", "PID Size");
							break;
						}
						if (!element[1].equals("F")) {
							message += error.getErrorMessage("846", "PID01 Value");
						}
						if (!element[2].equals("08")) {
							message += error.getErrorMessage("846", "PID02 Value");
						}
						if (!element[1].isEmpty() && !element[2].isEmpty() && element[5].isEmpty()) {
							message += error.getErrorMessage("846", "PID05 Empty");
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;

				// Error Checking for the CTP Segment
				case "CTP":
					try {
						if (element.length != 4) {
							message += error.getErrorMessage("846", "CTP Size");
						}
						if (!element[1].equals("AS")) {
							message += error.getErrorMessage("846", "CTP01 Value");
						}
						if (!element[2].equals("WHL")) {
							message += error.getErrorMessage("846", "CTP02 Value");
						}
						if (!element[3].matches("\\d*\\.?\\d+")) {
							message += error.getErrorMessage("846", "CTP03 Value");
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;

				// Error check for the QTY segment
				case "QTY":
					try {
						if (element.length != 4) {
							message += error.getErrorMessage("846", "QTY Size");
						}
						if (element[1].isEmpty() || element[2].isEmpty() || element[3].isEmpty()) {
							message += error.getErrorMessage("846", "QTY Empty");
							break;
						}
						if (!element[1].isEmpty()) {
							if (!element[1].equals("33")) {
								message += error.getErrorMessage("846", "QTY01 Value");
							}
							if (!element[3].equals("EA")) {
								message += error.getErrorMessage("846", "QTY03 Vlaue");
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;

				// Error checking for the SCH01 segment
				case "SCH":
					try {
						if (element.length != 7) {
							message += error.getErrorMessage("846", "SCH Size");
							break;
						} else if (!element[1].isEmpty()) {
							if (!element[2].equals("EA")) {
								message += error.getErrorMessage("846", "SCH02 Value");
							}
							if (!element[5].equals("018")) {
								message += error.getErrorMessage("846", "SCHO5 Value");
							}
							if (!element[5].isEmpty()) {
								if (element[6].isEmpty()) {
									message += error.getErrorMessage("846", "SCH06 Empty");
								}
								if (!element[6].equals("20391231")) {
									if (!EDI_Filter.dateChecker(element[6], dateFormat)) {
										message += error.getErrorMessage("846", "SCH06 Value");
									}
								}

							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;

				// Error Checking for the N1 segment
				case "N1":
					try {
						if (element.length != 5) {
							message += error.getErrorMessage("846", "N1 Size");
						} else if (!element[1].isEmpty()) {
							if (!element[1].equals("SE")) {
								message += error.getErrorMessage("846", "N101 Value");
							}
							if (element[2].isEmpty() || element[3].isEmpty() || element[4].isEmpty()) {
								message += error.getErrorMessage("846", "N1 Empty");
							}
							if (!element[3].equals("ZZ")) {
								message += error.getErrorMessage("846", "N103 Value");
							}
							if (element[4].isEmpty()) {
								message += error.getErrorMessage("846", "N104 Empty");
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;

				// Error Checking for the SE segment

				case "SE":
					try {
						int result;
						String count = "";
						count += element[1];
						result = Integer.parseInt(count);
						if (element.length != 3) {
							message += error.getErrorMessage("General", "SE Size");
						}
						if (element[1].isEmpty()) {
							message += error.getErrorMessage("General", "SE01 Empty");
							break;
						}
						if (result != transactionData.size()) {
							message += error.getErrorMessage("General", "SE01 Value");
						}
						if (!element[2].equals(transactionSetControlHeader)) {
							message += error.getErrorMessage("General", "SE02 Value");
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						message += error.getErrorMessage("General", "ArrayBoundsError");
					}
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;
				default:
					message = "Check this segment: " + element[0]
							+ " there may be whitespace that the filter isn't catching.";
					errorInformation.add(holder + message);
					message = "";
					stop = true;
					break;
				}
			}

		}
		printer.printToForm(errorInformation);
	}
	}