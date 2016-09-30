package com.subra.funding;


import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamResult;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;


import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.drfbets.funding.model.AddressPullRequestParam;
import com.drfbets.funding.model.AddressPullResponseResult;
import com.drfbets.funding.model.CustomerPullResponseResult;
import com.drfbets.funding.model.FundingMethodPullRequestParam;
import com.subra.funding.model.CustomerPullRequestModel;
import com.subra.funding.model.Fmxrequestempty;
import com.subra.funding.model.Fmxrequestempty.General;
import com.subra.funding.model.Fmxresponse;
import com.subra.funding.model.ParamCustomerPullRequest;
import com.subra.funding.model.RequestEmpty;
import com.subra.funding.model.ParamCustomerPullRequest.Field;
import com.subra.funding.model.Response;

public class ResponseRequestMainDriver {

	public static void main(String [] args) throws XmlMappingException, IOException{
		
		//preapareAddressPullString();
		//prepareCustomerPullResponseString();
		//prepareCustomerPullRequestString();
		//prepareAddressPullResponseString();
		prepareFundingMethodPullRequestString();
		  System.out.println("XML Created Sucessfully");		
		
	}
	
	public static void prepareFundingMethodPullRequestString() throws XmlMappingException, IOException{
		//prepare request[]
		//1. field
		List<FundingMethodPullRequestParam.Field> field = new  ArrayList<FundingMethodPullRequestParam.Field>();
		
		FundingMethodPullRequestParam.Field field1 = new FundingMethodPullRequestParam.Field("field1", "value1");
		FundingMethodPullRequestParam.Field field2 = new FundingMethodPullRequestParam.Field("field2", "value2");
		field.add(field1); field.add(field2);
		//field = null;
		//account 2nd
		BigInteger bigint = BigInteger.valueOf(7523); 
		BigInteger fundingmethodid = BigInteger.valueOf(77);
		//fundingmethodid = null;
		//param 3rd
		//FundingMethodPullRequestParam fundingMethodPullRequestParam = new FundingMethodPullRequestParam(bigint, "type_ach", fundingmethodid, field);
		FundingMethodPullRequestParam fundingMethodPullRequestParam = new FundingMethodPullRequestParam(bigint, null, fundingmethodid, field);
		
		//request 4th
		RequestEmpty<FundingMethodPullRequestParam> requestFundingMethodpullRequestParam = 
				new RequestEmpty<FundingMethodPullRequestParam>("categoryName", "functionName", fundingMethodPullRequestParam );
		
		ArrayList<RequestEmpty<FundingMethodPullRequestParam>> requests = new ArrayList<RequestEmpty<FundingMethodPullRequestParam>>(); //1 only
		requests.add(requestFundingMethodpullRequestParam);

		//5th and final fill frmrequest
		Fmxrequestempty<FundingMethodPullRequestParam> objFundingMethodPullRequestModel = new Fmxrequestempty<FundingMethodPullRequestParam>();


		//set gneral
		String timestamp = new Timestamp(new Date().getTime()).toString();	
		objFundingMethodPullRequestModel.setGeneral(new General("09-30-2016 :07:10:47", new General.Auth("sdass", "qqq123")));	
		objFundingMethodPullRequestModel.setRequest(requests);

		
		System.out.println("whatObj=" + objFundingMethodPullRequestModel.toString());
		//System.exit(1);
		// execute for jaxb done
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxrequestempty.class, RequestEmpty.class, FundingMethodPullRequestParam.class);		
		marshaller.marshal(objFundingMethodPullRequestModel, new StreamResult(new FileWriter("fundingMethodPullrequest.xml")));

		
	}
	
	public static void prepareCustomerPullRequestString() throws XmlMappingException, IOException {
		//prepare request[]
		//field 1st
		ArrayList<ParamCustomerPullRequest.Field> fieldList = new ArrayList<ParamCustomerPullRequest.Field>(1); //sending only one
		fieldList.add(new ParamCustomerPullRequest.Field("value1, value2, value3"));
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

		
	}

	public static void prepareAddressPullResponseString() throws XmlMappingException, IOException{
	
		// 1. fillup field
		AddressPullResponseResult.Address.Field field1 = new AddressPullResponseResult.Address.Field("AddrName1", "AddrValue1");
		//2. make address
		AddressPullResponseResult.Address address1 = new AddressPullResponseResult.Address("typeResidence", field1);
		
		//3. fillout result [addresses list 1st]
		List<AddressPullResponseResult.Address> addresses =  new ArrayList<AddressPullResponseResult.Address>();
		addresses.add(address1);
		AddressPullResponseResult addressPullResponseResult = new AddressPullResponseResult(addresses);
		//3. prepare response [1st populate error]
		//Response.Error error = new Response.Error(BigInteger.valueOf(3), "errorMsg");		
		Response.Error error = new Response.Error(BigInteger.valueOf(0), "");
		
		Response<AddressPullResponseResult> response = new  Response<AddressPullResponseResult>(error, "categoryName", "functionName", addressPullResponseResult);
		
		//4. fillout Fmxresponse [1st prepare list of Response]
		List<Response<AddressPullResponseResult>> responseList = new ArrayList<Response<AddressPullResponseResult>>();
		responseList.add(response);
		Fmxresponse<AddressPullResponseResult> objfmxresponse =  new Fmxresponse<AddressPullResponseResult>(responseList);
		System.out.println("whatObj=" + objfmxresponse.toString());
		//System.exit(1);
		// execute for jaxb 
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxresponse.class, Response.class, 
				AddressPullResponseResult.class);		
		marshaller.marshal(objfmxresponse, new StreamResult(new FileWriter("addressPullresponseResult.xml")));
		
	}
	
	
	public static void prepareCustomerPullResponseString() throws XmlMappingException, IOException {

		//1. fill out field list
		ArrayList<CustomerPullResponseResult.Field> fieldList = new ArrayList<CustomerPullResponseResult.Field>();
		CustomerPullResponseResult.Field field1 = new CustomerPullResponseResult.Field("name1", "value1");
		CustomerPullResponseResult.Field field2 = new CustomerPullResponseResult.Field("name2", "value2");
		CustomerPullResponseResult.Field field3 = new CustomerPullResponseResult.Field("name3", "value3");
		fieldList.add(field1); fieldList.add(field2); fieldList.add(field3);
		//2. fill out result
		CustomerPullResponseResult customerPullResponseResult = new CustomerPullResponseResult(fieldList);
		//3. prepare response [1st populate error]
		//Response.Error error = new Response.Error(BigInteger.valueOf(3), "errorMsg");		
		Response.Error error = new Response.Error(BigInteger.valueOf(0), "");
		
		Response<CustomerPullResponseResult> response = new  Response<CustomerPullResponseResult>(error, "categoryName", "functionName", customerPullResponseResult);
		
		//4. fillout Fmxresponse [1st prepare list of Response]
		List<Response<CustomerPullResponseResult>> responseList = new ArrayList<Response<CustomerPullResponseResult>>();
		responseList.add(response);
		Fmxresponse<CustomerPullResponseResult> objfmxresponse =  new Fmxresponse<CustomerPullResponseResult>(responseList);
		System.out.println("whatObj=" + objfmxresponse.toString());
		//System.exit(1);
		
		// execute for jaxb 
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxresponse.class, Response.class, 
				CustomerPullResponseResult.class);		
		marshaller.marshal(objfmxresponse, new StreamResult(new FileWriter("customerPullresponseResult.xml")));
				
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
		// execute for jaxb 
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxrequestempty.class, RequestEmpty.class, 
				ParamCustomerPullRequest.class, AddressPullRequestParam.class);		
		marshaller.marshal(objAddressPullRequestModel, new StreamResult(new FileWriter("addressPullrequest.xml")));


		
	}
}
