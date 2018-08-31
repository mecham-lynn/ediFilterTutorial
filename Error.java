package ediFilterTutorial;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Error {

	public String getErrorMessage(String transactionType, String errorParams) {
		switch (transactionType) {
		case "846":
			return InvErrors(errorParams);
		case "856":
			return ShipErrors(errorParams);
		case "810":
			return InvoiceErrors(errorParams);
		case "870":
			return CancelErrors(errorParams);
		case "General":
			return GenErrors(errorParams);
		default:
			return "";
		}
	}

	private String InvoiceErrors(String errorParams) {
		switch (errorParams) {
		// Messages for BIG
		case "BIG Size":
			return "-- The length of this segment is incorrect it should be 4 elements long--";
		case "BIG Req Empty":
			return "-- One or more of the following elements are empty: BIG01, BIG02, BIG04 --";
		case "BIG01 Format":
			return "-- The date format for BIG01 is incorrect it must be in the CCYYMMDD format --";
		case "BIG03 Format":
			return "-- The date format for BIG03 is incorrect it must be in the CCYYMMDD format --";

		// Messages for CUR
		case "CUR Size":
			return "-- The length of this segment is incorrect it should be 2 elements long --";
		case "CUR01 Value":
			return "-- The value of the CUR01 segment is incorrect it should be 'BY' --";
		case "CUR02 Vlaue":
			return "-- The value of hte CUR02 segment is incorrect it should be 'USD' --";

		// Messages for REF
		case "REF Size":
			return "-- The length of this segment is incorrect it should be 2 or 3 elements long --";
		case "REF01 Value":
			return "-- The value in the REFO1 element is incorrect "
					+ "it should be one of the following: ZZ, IA, IV, CO, CN --";
		case "REF ZZTransportation Code":
			return "-- The value in the REF02 is incorrect. When the REF03 is 'ship_transportation_method_code' the REF02 should "
					+ "be one of the following: A, J, R, or S --";
		case "REF03 ZZValues":
			return "-- The value in the REF03 is incorrect. When the REF01 is ZZ the REF03 should be one of the following: "
					+ "ship_carrier, ship_method, shipping_service_level_code, ship_transportation_method_code, ship_reference_number_equals,"
					+ "invoice_subtotal_excluding_line_items, invoice_line_items_subtotal --";
		case "REF Ship_reference Value":
			return "-- The value in the REF02 is incorrect it must be either 'BM' or 'CN' when the REF03 is ship_reference_number_qualifier --";
		case "REF Decimal":
			return "-- The value in the REF02 is incorrect it should be a decimal number --";

		// Messages for N1
		case "N101 Value":
			return " -- The value of the N101 element is incorrect, it should be 'ST' or 'SF' --";
		case "N1 Size":
			return "-- The length of the N1 segment is incorrect it hsould be 2 elements long --";

		// Messages for N3
		case "N3 Size":
			return "-- The length of the N3 segment is incorrect it should be 2 elements long --";

		// Messages for N4
		case "N4 Size":
			return "-- The length of the N4 segment is incorrect it should be 4 elements long --";

		// Messages for ITD
		case "ITD Size":
			return "-- The length of the ITD segment is incorrect it should be 13 elements long --";
		case "ITD01 Value":
			return "-- The value in the ITD01 element is incorrect it should be one of the following: 01, 02, 05, 08, or 12 --";
		case "ITD02 Value":
			return "-- The value in the ITD02 element is incorrect it should be '3' --";
		case "ITD Req Empty":
			return "-- One of the following element are empty: ITD04, ITD05, ITD06, ITD07, or ITD13 --";
		case "ITD08 Value":
			return "-- The value in the ITD08 is incorrect it must be a decimal number --";
		case "ITD09-12 Populated":
			return "-- One of the elements in the range ITD09-12 is populated when they are supposed to be empty --";

		// Messages for DTM
		case "DTM Size":
			return "-- The length of the DTM segment is incorrect it should be 3 elements long --";
		case "DTM02 Format":
			return "-- The format of the data in the DTM02 element is incorrect it should match CCYYMMDD --";
		case "DTM03 Format":
			return "-- The format of the data in the DTM03 element is incorrect it should match HHMM --";

		// Messages for IT1
		case "IT1 Size":
			return "-- The length of the IT1 segment is incorrect it needs to be between 7 and 19 elements long --";
		case "IT1 Req Empty":
			return "-- One or both of the following fields are empty and cannot be: IT102 and IT107 --";
		case "IT103 Value":
			return "-- The value in the IT103 element is incorrect, it should be 'EA' --";
		case "IT104 Value":
			return "-- The value in the IT104 element is incorrect, it should be a decimal number --";
		case "IT105 Value":
			return "-- The value in the IT105 element is incorrect, it should be either 'QT' or 'LE' --";
		case "IT106 Value":
			return "-- The value in the IT106 element is incorrect, it should be 'SK' --";
		case "IT107 SKU Length":
			return "-- The value in the IT106 element is too long, it needs to be under 70 characters --";
		case "IT108 Value":
			return "-- The value in the IT108 element is incorrect it should be 'UP' --";
		case "IT109 UPC Length":
			return "-- The UPC value in the IT109 has the incorrect length, UPC's need to be 6 or 12 characters --";
		case "IT109 Empty":
			return "-- When the IT108 is provided the IT109 cannot be empty --";
		case "IT110 Value":
			return "-- The value in the IT110 element is incorrect it should be 'EN' --";
		case "IT111 EAN Length":
			return "-- The EAN value in the IT111 element has the incorrect length, EAN's are 13 characters --";
		case "IT111 Empty":
			return " -- When the IT110 is provided the IT111 cannot be empty --";
		case "IT112 Value":
			return "-- The value in the IT112 element is incorrect it should be 'MG' --";
		case "IT113 Empty":
			return "-- When the IT112 is provided the IT113 cannot be empty";
		case "IT114 Value":
			return "-- The value in the IT114 element is incorrect it should be 'ZZ' --";
		case "IT115 Empty":
			return "-- When the IT114 is provided the IT115 cannot be empty";
		case "IT116 Value":
			return "-- The value in the IT116 element is incorrect it should be 'ZZ' --";
		case "IT117 Empty":
			return "-- When the IT116 is provided the IT117 cannot be empty";
		case "IT118 Value":
			return "-- The value in the IT118 element is incorrect it should be 'ZZ' --";
		case "IT119 Empty":
			return "-- When the IT116 is provided the IT117 cannot be empty";

		// Messages for TDS
		case "TDS01 Empty":
			return "-- TDS01 is a required field and cannot be empty --";
		case "TDS Size":
			return "-- The length of the TDS segment is incorrect, it must be 2 elements long --";
		case "TDS01 Value":
			return "-- The TDS01 value is incorrect it must be a decimal value --";
		case "TDS02 Value":
			return "-- The TDS02 value is incorrect it must be a decimal value --";

		// Messages for AMT
		case "AMT01 Value":
			return "-- The AMT01 value is incorrect it should be either 'OH' or 'F7' --";
		case "AMT02 Value":
			return "-- The AMT02 Value is incorrect it should be a decimal value --";

		// Messages for SAC
		case "SAC Size":
			return "-- The length of the SAC segment is incorrect it should be 5 elements long --";
		case "SAC01 Value":
			return "-- The value of the SAC01 element is incorrect it should be 'C' --";
		case "SAC02 Value":
			return "-- The value of hte SAC02 element is incorrect it should be 'D240' --";
		case "SAC Req Populated":
			return "-- Elements SAC03 and SAC04 are required to be empty --";
		case "SAC05 Value":
			return "-- The value of SAC05 is incorrect it should be a decimal character --";

		// Messages for ISS
		case "ISS Size":
			return "-- The length of the ISS segment is incorrect it should be 4 elements long --";
		case "ISS01 Empty":
			return "-- The ISS01 is empty, when the ISS segment is included the ISS01 is needed --";
		case "ISS02 Value":
			return "-- The value of hte ISS02 is incorrect it needs to be one of the following: 'CA', 'BX', 'PK' --";
		case "ISS03 Empty":
			return "-- The ISS03 is empty, when the ISS segment is included the ISS03 is needed --";
		case "ISS04 Value":
			return "-- The ISS04 value is incorrect it should be one of the following: 'LB', 'OZ', '50' --";

		// Messages for CTT
		case "CTT Size":
			return "-- The length of the CTT segment is incorrect it needs to be 1 element long --";
		case "CTT01 Value":
			return " -- The value of the CTT01 segment is incorrect it should be equal to the number of lin-items in the transaction --";

		default:
			return "";

		}
	}

	public String evaluateReqFields(HashMap<String, Boolean> reqFields) {
		String Message = "";
		Iterator<Map.Entry<String, Boolean>> entries = reqFields.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Boolean> entry = entries.next();
			if (entry.getValue() == false) {
				Message += "Required Segment " + entry.getKey() + " was not provided.";
			}
		}
		return Message;

	}

	private String ShipErrors(String errorParams) {
		switch (errorParams) {
		case "BSN01 Value":
			return " --BSN01 must be equal to '00' --";
		case "BSN02":
			return "--BSN02 cannot be empty --";
		case "BSN03 Format":
			return "-- the date format for BSN03 must be equal to CCYYMMDD --";
		case "BSN04 Format":
			return "-- The time format for BSN04 must match HHMM --";
		case "BSN05 Value":
			return "-- BSN05 must equal '004' --";
		case "BSN Size":
			return "-- The BSN segment length is incorrect it must be 5 elments long --";
		case "BSN Segments Empty":
			return "-- One or more elements are empty. All elements in the BSN are required and cannot be empty--";
		case "CUR Size":
			return "-- The CUR segment length is incorrect it must be 3 elements long --";
		case "CUR01 Value":
			return "-- The CUR01 segment must have 'BY' as its value --";
		case "CUR02 Value":
			return "-- The CUR02 segment must have 'USD' as its value --";
		case "HL Size":
			return "-- The HL segment length is incorrect it must be 3 elements long --";
		case "HL Empty":
			return "-- One or more elements are empty. All elements in the HL are required and cannot be empty --";
		case "HL03 Value":
			return "-- The HL03 is incorrect it must be one of the following: 'O', 'I', or 'S' --";
		case "TD5 Size":
			return "-- The TD5 segment length is incorrect it must be 8 elements long -- ";
		case "TDF Segments Empty":
			return "-- One or more elements are empty. All elements except TD506 in the TD5 are required and cannot be empty --";
		case "TD501 Value":
			return "-- The value in the TD501 element is incorrect, it should be 'Z' --";
		case "TD502 Value":
			return "-- The value in the TD502 element is incorrect, it should be 'ZZ' --";
		case "TD504 Value":
			return "-- The value in the TD504 element is incorrect, it should be 'ZZ' --";
		case "TD507 Value":
			return "-- The value in the TD507 element is incorrect, it should be 'ZZ' --";
		case "REF Size":
			return "--The REF segment length is incorrect it must be 2 or 3 elements long -- ";
		case "REF01 Value":
			return "-- The REF01 is incorrect it mus equal one of the following values: 'ZZ', 'IA', 'CO', 'VN' or 'CN' --";
		case "REF01 Empty":
			return "-- The REF01 cannot be empty --";
		case "REF02 Empty":
			return "-- The REF02 cannot be empty --";
		case "LIN02 Empty":
			return "-- The LIN02 and LIN03 elements are required and cannot be empty --";
		case "SN101 Value":
			return "-- The SN101 can only be empty --";
		case "SN1 Empty":
			return "-- The SN102 or SN103 elements can't be empty --";
		case "SN103 Value":
			return "-- The SN103 element value is incorrect it must equal 'EA' --";
		case "DTM Size":
			return "-- The DTM segment length is incorrect it must be 3 elements long --";
		case "DTM Empty":
			return "-- The DTM segment is required and none of the elements can be empty --";
		case "DTM01 Value":
			return "-- The DTM01 value is incorrect it must equal '011' --";
		case "DTM02 Format":
			return "-- The DTM02 value doesn't match teh Date format of CCYYMMDD (YYYYMMDD) --";
		case "DTM03 Format":
			return "-- The DTOM03 Value doesn't match the Time format of HHMM --";
		case "SAC Size":
			return "-- The SAC segment length is incorrect it must be 5 elements long --";
		case "SAC Empty":
			return "-- The elements SAC01, SAC02, SAC05 cannot be empty when the SAC segment is provided --";
		case "SAC01 Value":
			return "-- The SAC01 value is incorrect it must be 'C' --";
		case "SAC02 Value":
			return "-- The SAC02 Value is incorrect it must be 'G821' --";
		case "SAC05 Decimal":
			return "-- The SAC05 Value has a decimal missing, the decimal is required --";
		case "SAC04 Value":
			return "-- The SAC03 and SAC04 values must be empty and cannot be populated --";
		case "PRF Size":
			return "-- The segment length of the PRF value is incorrect it should be 1 element long --";
		case "PRF01 Empty":
			return "-- The PRF01 segment is empty, the PRF01 segment is a required segment and cannot be missing --";

		default:
			return "";
		}
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
		case "ST Size":
			return " --ST Segment size is incorrect it should be 2--";
		case "ST02 Size":
			return " --ST02 segment is greater than the maximum allowed size (9)--";
		case "ArrayBoundsError":
			return "";
		default:
			return "";
		}
	}

	private String InvErrors(String errorParams) {
		switch (errorParams) {

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
		case "LIN02 Req":
			return " --LIN02 is required and cannot be empty--- ";
		case "LIN03 Req":
			return " --LIN03 is required and cannot be empty--- ";
		case "LIN04 Value":
			return " -- LIN04 segment has to be 'UP--- ";
		case "LIN05 Size":
			return " --The UPC needs to be 6 or 12 characters long--- ";
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
			return " --- The QTY segment size is incorrect it must be 3 elements long --- ";
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

	private String CancelErrors(String errorParams) {
		switch (errorParams) {
		
		//Params for BSR segment Errors
		case "BSR Size":
			return "-- The length of the BSR segment is incorrect it should be 4 elements long --";
		case "BSR01 Value":
			return "-- The value in the BSR01 element is incorrect it should be '2' --";
		case "BSR02 Value":
			return "-- The value in the BSR02 element is incorrect is should be 'PP' --";
		case "BSR03 Empty":
			return "-- The BSR03 Value is a required field and cannot be empty --";
		case "BSR04 Format":
			return "-- The BSR04 Value is in the incorrect format it needs to be in the CCYYMMDD format --";
			
		case "HL Size":
			return "-- The length of the HL segment is incorrect it needs to be 3 elements long --";
		case "HL01 Empty":
			return "-- The HL01 segment is empty--";
		case "HL02 Empty":
			return "-- The HL02 segment is empty--";
		case "HL03 Value":
			return "-- The value in the HL03 element is incorrect it should be: 'I' or 'O' --";
		
		
		//Params for PRF segment Errors
		case "PRF Size":
			return "-- The length of the PRF segment is incorrect it shoudl be 1 element long--";
		case "PRF01 Empty":
			return "-- The PRF01 element is Empty --";
			
		//Params for REF segment Errors
		case "REF Size":
			return "-- The length of the REF segment is incorrect it should be either 2 or 3 elements long--";
		case "REF01 Value":
			return "-- The value in the REF01 is incorrect it needs to be one of the following: 'VN', 'CO', 'IA'--";
		case "REF02 Empty":
			return "-- The REF02 segment is empty --";
			
			//Params for PO1 segment
		case "PO1 Req Empty":
			return "-- One of the following fields are empty and cannot be: PO101, PO102, PO103, PO104, PO106, PO107 --";
		case "PO101 Value":
			return "-- The value in the PO101 is incorrect it needs to be greater than 0 --";
		case "PO102 Value":
			return "-- The value in the PO102 is incorrect it needs to be greater than 0 --";
		case "PO103 Value":
			return "-- The value in the PO103 is incorrect it needs to be 'EN' --";
		case "PO104 Value":
			return "-- The value in the PO104 didn't have a decimal, a decimal place has to be included in the PO104 --";
		case "PO105 Not Empty":
			return "-- The PO105 is not empty. The PO105 has to be empty --";
		case "PO106 Value":
			return "-- The PO106 is incorrect, it needs to be 'SK' --";
		case "PO107 Length":
			return "-- The PO107 value is too long, it needs to be under 70 characters long --";
		case "PO108 Value":
			return "-- The value in the PO108 is incorrect it needs to be 'UP' --";
		case "PO109 Length":
			return "-- The length of the UPC in the PO109 is incorrect it needs to be 6 or 12 characters long --";
		case "PO109 Number":
			return "-- The value in the PO109 is incorrect it cannot contain any letters --";
		case "PO109 Empty":
			return "-- The PO109 element is empty. The PO109 element cannot be empty when the PO108 element is provided --";
		case "PO110 Value":
			return "-- The value in the PO110 is incorrect it needs to be 'EN' --";
		case "PO111 Empty":
			return "-- The PO111 element is empty. The PO111 element cannot be empty when the PO110 element is provided --";
		case "PO111 Length":
			return "-- the length of the EAN in the PO111 element is incorrect it needs to be 13 characeters long --";
		case "PO111 Number":
			return "-- The value in the PO111 is incorrect it cannot contain any letters --";
		case "PO112 Value":
			return "-- The value in the PO112 element is incorrect it needs to be 'MG' --";
		case "PO113 Nubmer":
			return "-- The value in the PO113 element is incorrect it needs to be 'ZZ' --";
			
		default:
			return "";
			
			//
		}
	}
}