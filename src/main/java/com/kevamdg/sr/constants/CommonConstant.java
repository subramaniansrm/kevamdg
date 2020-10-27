package com.kevamdg.sr.constants;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class CommonConstant {
	
	@Value("#{'${staticScreenIds}'.split(',')}") 
	public static List<Integer> staticScreenIds;

	public static final Integer CONSTANT_ZERO = 0;
	public static final Integer CONSTANT_ONE = 1;
	public static final Integer CONSTANT_TWO = 2;
	public static final Integer CONSTANT_THREE = 3;
	public static final Integer CONSTANT_FOUR = 4;
	public static final Integer CONSTANT_FIVE = 5;
	public static final Integer CONSTANT_SIX = 6;
	public static final Integer CONSTANT_SEVEN = 7;
	public static final Integer CONSTANT_EIGHT = 8;
	public static final Integer CONSTANT_NINE = 9;
	public static final Integer CONSTANT_TEN = 10;
	public static final Integer CONSTANT_ELEVEN = 11;
	public static final Integer CONSTANT_THIRTEEN = 13;
	public static final Integer CONSTANT_FOURTEEN = 14;

	public static final Integer CONSTANT_FIFTEEN = 15;

	public static final Integer CONSTANT_TWELVE = 12;
	public static final Integer NEW_PASSWORD_LENGTH = 5;

	public static final Integer NOT_WORKING = 9;

	public static final String REQUEST_ESCALATED = "Request Escalated ";

	public static final String SUCCESS_CODE = "200";
	public static final String ERROR_CODE = "500";
	public static final Integer RESULT_FAULURE = 303;
	public static final Integer RESULT_VALIDATION = 302;
	public static final String FAILURE_CODE = "400";
	public static final String NULL = null;

	public static final Character FLAG_ZERO = '0';
	public static final Character FLAG_ONE = '1';

	public static final String STRING_Y = "Y";
	public static final String STRING_N = "N";
	public static final String STRING_A = "A";
	
	public static final Integer DISTRIBUTION_CHANNEL = 1;
	public static final Integer MATERIAL_TYPE = 2;
	public static final Integer NEW_EXTENDS = 5;
	public static final Integer MATERIAL_PURPOSE = 4;
	public static final Integer UNIT = 3;

	public static final Integer OTHER = 6;
	public static final Integer PACK_TYPE = 7;
	public static final Integer PACK_SIZE = 8;
	public static final Integer CATEGORY_MAP = 9;
	public static final Integer MATERIAL_GROUP = 10;
	public static final Integer FLAVOUR_SOLUBILITY = 11;
	public static final Integer LEGAL_STATUS = 12;
	public static final Integer LEGAL_DECLARATION = 13;
	/*public static final String DISTRIBUTION_CHANNEL = "DISTRIBUTION CHANNEL";
	public static final String SALES_ORG = "SALES ORG";
	public static final String MATERIAL_TYPE = "Material Type";
	public static final String NEW_EXTENDS = "NEW EXTENDS";
	public static final String MATERIAL_PURPOSE = "Material Purpose";
	public static final String PLANT = "Plant";
	public static final String UNIT = "Unit";*/
	public static final String STRING_ZERO = "0";
	public static final String STRING_ONE = "1";
	public static final String STRING_TWO = "2";
	public static final String STRING_THREE = "3";

	public static final String FORMAT_DD_MM_YYYY_HYPHEN = "dd-MM-yyyy";
	public static final String FORMAT_DD_MM_YYYY_HH_MM_SS_HYPHEN = "dd-MM-yyyy h:mm:ss";
	public static final String DATE_YYYY_MM_DD_HH_MI_SS = "yyyy/MM/dd HH:mm:ss";

	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String ZERO = "0";
	public static final Integer TEN = 10;

	public static final Boolean BOOLEAN_TRUE = true;
	public static final Boolean BOOLEAN_FALSE = false;
	
	
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";

	public static final String CAPITAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String SMALL_CHARS = "abcdefghijklmnopqrstuvwxyz";
	public static final String NUMBERS = "0123456789";
	public static final String SYMBOLS = "!@#$%^&*_=+-/.?<>)";
	public static final String HYPHEN = "-";
	public static final String SLASH = "/";
	public static final String EMPTY = " ";
	public static final String BLANK = "";

	public static final String MAIL_TO = "aruljothia@srmtech.com";
	public static final String MAIL_FROM = "noreply@srmtech.com";
	public static final String MAIL_HOST = "mail.srmtech.com";

	public static final String MAIL_HOST_USER = "chn.srmtech.com";

	public static final Integer TRANSACTION_LOG_TIMELIMIT = 30;
	
	public static String getCurrentDateTimeAsString() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(DATE_YYYY_MM_DD_HH_MI_SS);
		return df.format(calendar.getTime());
	}
	
	public static String getCurrentDateTimeAsStringHyphen() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_DD_MM_YYYY_HH_MM_SS_HYPHEN);
		return df.format(calendar.getTime());
	}

	public static Date getCalenderDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String formatDatetoString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateString = sdf.format(date);
		return dateString;
	}

	public static Date formatStringDatetoDate(final String dateValue, String pattern) {
		Date date = null;
		try {
			if (null != dateValue) {
				DateFormat formatter = new SimpleDateFormat(pattern);
				date = (Date) formatter.parse(dateValue);
			}
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getNextDateAsDate(int noOfDays) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, +noOfDays);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		return cal.getTime();
	}

	public static Date formatIsoStringtoDate(final String dateValue, String pattern) {

		Date date = null;
		try {
			if (null != dateValue) {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				SimpleDateFormat outputFormat = new SimpleDateFormat(pattern);
				date = inputFormat.parse(dateValue);
				String formattedDate = outputFormat.format(date);
				date = (Date) outputFormat.parse(formattedDate);
				date = addDays(date, 1);
			}
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Date addDays(Date date, int j) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, j);

		return cal.getTime();
	}

	// DELETE_VALIDATION
	public static final String PROJECT = "Project";
	public static final String POTENTIAL_MARGIN = "Potential Margin";
	public static final String USER = "User";
	public static final String APPLICATION_MASTER = "Application";
	public static final String BASE = "Base";
	public static final String CATEGORY = "Category";
	public static final String DIVISION = "Division";
	public static final int ACTIVE = 1;

	public static final String NOT_DELETED = "false";

	public static final String FILE_NAME_FORMAT_DATE = "yyyyMMddHHmmss";

	public static final Integer REOPEN = 7;

	public static final String VALIDATION_SUCCESS = "SUCCESS";

	
	public static final String Request = "Request";
	
	

	public static final String RequestType = "RequestType";

	public static final String RequestSubType = "RequestSubType";

	public static final String FlashNews = "FlashNews";
	public static final String SubLocation = "SubLocation";
	public static final String SalesOrg = "SalesOrg";

	public static final String Active = "Active";

	public static final String InActive = "InActive";
	
	
	
	//Room Booking Mail

	public static final String RBR = "ROOMBOOK_REQUEST";
	
	public static final String RBU = "ROOMBOOK_UPDATE";
	
	public static final String RBD = "ROOMBOOK_DELETE";


	public static final String format(String message, Object... arg) {

		return MessageFormat.format(message, arg);

	}
	public static  List<Integer> getStaticScreenIds() {
		return staticScreenIds;
	}
	public static final String png = "png";
	public static final String jpg = "jpg";
	public static final String jpeg = "jpeg";



}