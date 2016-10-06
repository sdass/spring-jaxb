package com.subra.funding;


import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamResult;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;


import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.drfbets.funding.model.AddressPullRequestParam;
import com.subra.funding.model.CustomerPullRequestModel;
import com.subra.funding.model.Fmxrequestempty;
import com.subra.funding.model.Fmxrequestempty.General;
import com.subra.funding.model.ParamCustomerPullRequest;
import com.subra.funding.model.RequestEmpty;
import com.subra.funding.model.ParamCustomerPullRequest.Field;

public class MainDriver {

	public static void main(String [] args) throws XmlMappingException, IOException{
		/*
		//prepare request[]
		//field 1st
		ArrayList<ParamCustomerPullRequest.Field> fieldList = new ArrayList<ParamCustomerPullRequest.Field>(1); //sending only one
		fieldList.add(new ParamCustomerPullRequest.Field("value1, value2"));
		//account 2nd
		BigInteger bigint = BigInteger.valueOf(1523); 
		//param 3rd
		ParamCustomerPullRequest customerPullParam = new ParamCustomerPullRequest(bigint, fieldList); //works
		//customerPullParam = null; //testing when param is not there
		
		//request 4th
		RequestEmpty<ParamCustomerPullRequest> requestCustomerPullwithParam = 
				new RequestEmpty<ParamCustomerPullRequest>("categoryName", "functionName", customerPullParam );
		
		ArrayList<RequestEmpty<ParamCustomerPullRequest>> requests = new ArrayList<RequestEmpty<ParamCustomerPullRequest>>(); //1 only
		requests.add(requestCustomerPullwithParam);

		
		//5th and final fill frmrequest
		//CustomerPullRequestModel objCustomerPullRequestModel = new CustomerPullRequestModel();
		Fmxrequestempty<ParamCustomerPullRequest> objCustomerPullRequestModel = new Fmxrequestempty<ParamCustomerPullRequest>();
		

		//set gneral
		String timestamp = new Timestamp(new Date().getTime()).toString();	
		objCustomerPullRequestModel.setGeneral(new General("09-24-2016 :09:30:45", new General.Auth("sdass", "qqq123")));	
		objCustomerPullRequestModel.setRequest(requests);
		
		System.out.println("whatObj=" + objCustomerPullRequestModel.toString());
		//System.exit(1);
		// execute for jaxb done
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxrequestempty.class, RequestEmpty.class, ParamCustomerPullRequest.class);		
		marshaller.marshal(objCustomerPullRequestModel, new StreamResult(new FileWriter("customerPullrequest.xml")));

		  */
		
		preapareAddressPullString();
		  
		  System.out.println("XML Created Sucessfully");		
		
	}
	
	public static void preapareAddressPullString() throws XmlMappingException, IOException {
		
		//prepare request[]
		//field 1st
		ArrayList<AddressPullRequestParam.Field> fieldList = new ArrayList<AddressPullRequestParam.Field>(1); //sending only one
		//fieldList.add(new ParamCustomerPullRequest.Field("value3, value4"));
		fieldList = null;
		//account 2nd
		BigInteger bigint = BigInteger.valueOf(71523); 
		//param 3rd
		String addressType = "residence"; addressType = null;
		AddressPullRequestParam addressPullRequestParam = new AddressPullRequestParam(bigint, addressType, fieldList) ; 
		//customerPullParam = null; //testing when param is not there
		
		//request 4th
		RequestEmpty<AddressPullRequestParam> requestAddressPullwithParam = 
				new RequestEmpty<AddressPullRequestParam>("categoryName", "functionName", addressPullRequestParam );
		
		ArrayList<RequestEmpty<AddressPullRequestParam>> requests = new ArrayList<RequestEmpty<AddressPullRequestParam>>(); //1 only
		requests.add(requestAddressPullwithParam);
		
		//5th and final fill frmrequest
		//CustomerPullRequestModel objCustomerPullRequestModel = new CustomerPullRequestModel();
		Fmxrequestempty<AddressPullRequestParam> objAddressPullRequestModel = new Fmxrequestempty<AddressPullRequestParam>();

		//set gneral
		String timestamp = new Timestamp(new Date().getTime()).toString();	
		objAddressPullRequestModel.setGeneral(new General("09-27-2016 :19:35:45", new General.Auth("sdass", "qqq123")));	
		objAddressPullRequestModel.setRequest(requests);
		
		System.out.println("whatObj=" + objAddressPullRequestModel.toString());
		//System.exit(1);
		// execute for jaxb done
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxrequestempty.class, RequestEmpty.class, 
				ParamCustomerPullRequest.class, AddressPullRequestParam.class);		
		marshaller.marshal(objAddressPullRequestModel, new StreamResult(new FileWriter("addressPullrequest.xml")));


		
	}
}
