package com.subra.funding;

import java.util.regex.*;

import org.springframework.aop.framework.autoproxy.target.QuickTargetSourceCreator;

public class DBLoggingUtil {
		
	
	public static String quickTestPasswordmask(String compactStr){

		String compactXmlStr = compactStr.replaceFirst("<password>\\w*</password>","<password>ppppp</password>");
		System.out.println("newMaskedpasswordStr=" + compactXmlStr);
		return compactXmlStr;		
		
	}

	public static String quickTestccNumberdmask(String compactStr){

		String compactXmlStr = compactStr.replaceFirst("<field><name>cardnumber</name><value>\\w*</value></field>","<field><name>cardnumber</name><value>nnnnnnnnnnn</value></field>");
		System.out.println("quickTestccNumberdmask=" + compactXmlStr);
		return compactXmlStr;		
		
	}
	
	
	public static String requestMasking(String requestStr, Category reqCategory) {
		
		String maskedreq = "";
		System.out.println("requestStr=" + requestStr);
		String compactStr = removeSpaceBetweenNodes(requestStr);
		compactStr = quickTestPasswordmask(compactStr);
		quickTestccNumberdmask(compactStr);
		
		//Pattern password = Pattern.compile("(<password>\\w)(\\w*)(\\w</password>)"); //1st & last digit printed
		Pattern password = Pattern.compile("(<password>\\w)(\\w*)(</password>)");
		//Pattern password = Pattern.compile("(<password>)(\\w*)(</password>)");
		//works with $1 Pattern password = Pattern.compile("(<password>\\w)(\\w*)");
		
		
		Matcher matchPassowrd = password.matcher(requestStr);
		
		if(matchPassowrd.find()){
			//maskedreq = matchPassowrd.replaceFirst("$1xxxxx");
			maskedreq = matchPassowrd.replaceFirst("$1xxxxx$3");
		}

		
		switch (reqCategory) {

		case AddressPull:
			System.out.println("addresspull:" + requestStr + " ordinal"
					+ reqCategory.ordinal() + " " + reqCategory.name()
					+ "code=" + reqCategory.getCode());

			break;

		case Transaction:
			System.out.println("transacation:" + " code="
					+ reqCategory.getCode() + " " + reqCategory.name());
			// Pattern creditcard =
			// Pattern.compile("(<name>cardnumber</name><value>\\w)(\\w*)(\\w</value>)");
			Pattern creditcard = Pattern
					.compile("(<name>cardnumber</name><value>\\w)(\\w*)");
			Matcher matchCreditCard = creditcard.matcher(maskedreq);
			if (matchCreditCard.find()) {
				System.out.println("creditcard match found");
				maskedreq = matchCreditCard.replaceFirst("$1xxxxxxxxxxx");
			}

			// Pattern cvccode =
			// Pattern.compile("(<name>cvccode</name><value>\\w)(\\w*)(\\w</value></field>)");
			Pattern cvccode = Pattern
					.compile("(<name>cvccode</name><value>\\w)(\\w*)");
			Matcher matchcvcCode = cvccode.matcher(maskedreq);
			if (matchcvcCode.find()) {
				System.out.println("cvccode match found");
				maskedreq = matchcvcCode.replaceFirst("$1xxx");

			}

			/*
			 * works //pattern year Pattern yearExpiration =
			 * Pattern.compile("(<name>expirationyear</name><value>\\w)(\\w*)");
			 * Matcher matchExpYear = yearExpiration.matcher(maskedreq);
			 * if(matchExpYear.find()){
			 * System.out.println("expiration year match found"); maskedreq =
			 * matchExpYear.replaceFirst("$1xxxx"); } works
			 */

			/*
			 * works better than above pattern year //pattern year Pattern
			 * yearExpiration = Pattern.compile(
			 * "(<name>expirationyear</name><value>\\w)(\\w*)(</value>)");
			 * Matcher matchExpYear = yearExpiration.matcher(maskedreq);
			 * if(matchExpYear.find()){
			 * System.out.println("expiration year match found"); maskedreq =
			 * matchExpYear.replaceFirst("$1xxxx$3"); }
			 */

			// works well Pattern monthYearExpiration =
			// Pattern.compile("(<name>expirationmonth</name><value>\\w)(\\w*)(</value></field><field><name>expirationyear</name><value>\\w)(\\w*)(</value>)");
			// stop printing 1st digit of moth
			Pattern monthYearExpiration = Pattern
					.compile("(<name>expirationmonth</name><value>)(\\w*)(</value></field><field><name>expirationyear</name><value>\\w)(\\w*)(</value>)");
			Matcher matchExpMonthYear = monthYearExpiration.matcher(maskedreq);
			if (matchExpMonthYear.find()) {
				System.out.println("month year Expiration match found");
				maskedreq = matchExpMonthYear.replaceFirst("$1xx$3xxx$5");
			}

			/*
			 * //Account Credit Request Pattern accountCreditNote =
			 * Pattern.compile(regex);
			 * 
			 * //GreenDot Moneypak number Pattern moneyPakNumber =
			 * Pattern.compile
			 */
			break;

		case CustomerPull:

			break;

		default:

		}

		return maskedreq;
	}

	static String removeSpaceBetweenNodes(String xmlStr){
		System.out.println("a=" + xmlStr);
		System.out.println(" Inside removeSpaceBetweenNodes . . .");
		
		String compactXmlStr = xmlStr.replaceAll(">\\s+<","><");
		System.out.println("compactXmlStr=" + compactXmlStr);
		return compactXmlStr;
	}
	
	static String responseMasking(String strXml, Category responseCategory){
		//responseCategory.
		
		String mastkeString = "";
		switch(responseCategory){
		
		case AddressPull: 
			System.out.println("addresspull:" + strXml + " ordinal" + responseCategory.ordinal() + " " + responseCategory.name() + "code=" + responseCategory.getCode());
			break;
		
		case Transaction:
			System.out.println("transacation:" + strXml + " ordinal" + responseCategory.ordinal() + " " + responseCategory.name() + "code=" + responseCategory.getCode());
			break;
		
		case CustomerPull:
			
			break;
		

		default:
			
		
		}
		
		return "";
		
	}
}
