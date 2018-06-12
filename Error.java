package ediFilterTutorial;

public class Error {

	public String getErrorMessage(String transactionType, String errorParams) {
		if (transactionType.equals("846")) {
			return InvErrors(errorParams);
		} else if (transactionType.equals("General")) {
			return GenErrors(errorParams);
		}
		return "";
	}

	private String GenErrors(String errorParams) {
		switch (errorParams) {

		// General Errors
		case "ST01 Empty":
			return " --ST01 Segment cannot be empty--";
		case "ST02 Empty":
			return "--ST02 Segment cannot be empty--";
		case "SE Size":
			return "-- the SE segment size is incorrect the SE segment must be 2 segments long -- ";
		case "SE02 Empty":
			return " -- The SE01 segment cannot be empty -- ";
		case "SE01 Value":
			return " -- The SE01 must be the same size as the number of lines in the transaction set --";
		case "SE02 Value":
			return " -- This value must be the same as the value in ST02 -- ";
		case "ArrayBoundsError":
			return "";
		default:
			return "";
		}
	}

	private String InvErrors(String errorParams) {
		switch (errorParams) {

		// errors for ST segment
		case "ST Size":
			return " --ST Segment size is incorrect it should be 2--";
		case "ST02 Size":
			return " --ST02 segment is greater than the maximum allowed size (9)--";

		// errors for BIA segment
		case "BIA01 Value":
			return " --The BIA01 value must equal '00'--";
		case "BIA02 Value":
			return " --The BIA02 value must equal 'MM'--";
		case "BIA03 Empty":
			return " --The BIA03 segment cannot be empty--";
		case "BIA04 Date":
			return " --The value in BIA04 doesn't match the format 'yyyy/MM/dd'--";
		case "BIA05 Time":
			return " --The value in BIA05 doesn't match the format 'HHMMSS'--";
		case "BIA Size":
			return " --The BIA Segment size is incorrect it should be 5--";

		// Errors for CUR segment
		case "CUR Size":
			return " --The CUR segment size is incorrect it should be 2 --- ";
		case "CUR01 Value":
			return " -- The CUR01 segment needs to equal 'SE'--- ";
		case "CUR02 Value":
			return " -- THE CUR02 segment needs to equal 'USD'--- ";

		// Errors for REF segment

		case "REF Size":
			return " -- The REF segment size is incorrect it should be 2--- ";
		case "REF02 Empty":
			return " -- The REF02 segment must be included when REF01 is 'IA' --- ";
		case "REF02ZZ Status Empty":
			return " -- The REF02 Value must be populated when the REF03 is set to 'status' --";
		case "REF02ZZ Status Value":
			return " -- The REF02 Value must be: 'in-stock', 'out-of-stock', 'discontinued', 'hidden', or 'pending' --";

		// Errors for LIN segment

		case "LIN Size":
			return " -- The LIN segment size is incorrect it should be max of 31 or a minimum of 3 segments long --- ";
		case "LIN02 Value":
			return " -- The LIN02 segment has to be 'SK'--- ";
		case "LIN03 Size":
			return " -- The LIN03 segment cannot be longer than 70 characters--- ";
		case "LIN04 Value":
			return " -- LIN04 segment has to be 'UP--- ";
		case "LIN05 Size":
			return " --The UPC needs to be 6 or 12 characters long--- ";
		case "LIN02 Req":
			return " --LIN02 is required and cannot be empty--- ";
		case "LIN03 Req":
			return " --LIN03 is required and cannot be empty--- ";
		case "LIN06 Value":
			return " --LIN06 must be 'EN'--- ";
		case "LIN07 Size":
			return " --EAN numbers can only be 13 characters long--- ";
		case "LIN07 Empty":
			return " -- LIN07 cannot be empty when LIN 06 is included --";
		case "LIN08 Value":
			return " --LIN08 has to be 'MG'--- ";
		case "LIN09 Empty":
			return " --LIN09 Cannot be empty when LIN08 is provided--- ";
		case "LIN10 Value":
			return " -- LIN10 must be 'IB' --";
		case "LIN11 Empty":
			return " --LIN11 Cannot be empty when LIN10 is provided--- ";
		case "LIN12 Value":
			return " -- LIN12 must be 'UK' --";
		case "LIN13 Empty":
			return " -- LIN13 cannot be empty when LIN12 is provided--- ";
		case "LIN14 Value":
			return " -- LIN14 must be 'BL' --";
		case "LIN15 Empty":
			return " -- LIN15 cannot be empty when LIN14 is provided--- ";
		case "LIN16 Value":
			return " -- LIN16 must be 'SK' and this value is required for K suppliers --- ";
		case "LIN17 Empty":
			return " -- LIN17 must be included when LIN16 is provided --- ";
		case "LIN18 Value":
			return " -- LIN18 must be 'SK' --- ";
		case "LIN19 Empty":
			return " -- LIN19 must be included when LIN18 is provided --- ";

		// Params for PID error messages
		case "PID Size":
			return " -- The PID segment size is incorrect it should be 5 segments long --- ";
		case "PID01 Value":
			return " -- The PID01 segment must be 'F' --- ";
		case "PID02 Value":
			return " -- The PID02 segment must be '08' --- ";
		case "PID05 Empty":
			return "-- The PID05 must be included when PID01 and PID02 are provided --- ";

		// Params for CTP error messages
		case "CTP Size":
			return "-- The CTP segment size is incorrect it must be 4 segments long --- ";
		case "CTP01 Value":
			return " -- The CTP01 segment must be 'AS' --- ";
		case "CTP02 Value":
			return " -- The CTP02 segment must be 'WHL' --- ";
		case "CTP03 Value":
			return " --- The CTP03 value must contain a decimal place --- ";

		// Params for QTY error messages
		case "QTY Size":
			return " ---The QTY segment size is incorrect it must be 3 segments long --- ";
		case "QTY Empty":
			return "-- The QTY segment is required. None of the segments can be blank --- ";
		case "QTY01 Value":
			return "-- The QTY01 segment must be '33' --- ";
		case "QTY03 Value":
			return "-- The QTY03 segment must be 'EA' --- ";

		case "SCH Size":
			return " -- The SCH segment size is incorrect it must be 6 segments long -- ";
		case "SCH01 Value":
			return " -- The SCH01 must be either '1' or '0' --";
		case "SCH02 Value":
			return "-- The SCH02 Value must be 'EA' --";
		case "SCH05 Value":
			return "-- The SCH05 Value must be '018' --";
		case "SCH06 Empty":
			return "-- The SCH06 segment cannot be empty when SCH05 is provided -- ";
		case "SCH06 Value":
			return "-- The SCH06 segment must be a valid date format of 'yyyyMMdd' or equal to '20391231' -- ";

		// params for N1 segment errors
		case "N1 Size":
			return "-- The N1 segment size is incorrect it must be 4 segments long -- ";
		case "N101 Value":
			return " -- The N101 segment must be 'SE' -- ";
		case "N1 Empty":
			return " -- The N1 segments must be provided when N101 is provided --";
		case "N103 Value":
			return " -- The N103 segment must be 'ZZ' --";
		case "N104 Empty":
			return " -- The N104 segment cannot be empty --";

		default:
			return "";

		}
	}
}