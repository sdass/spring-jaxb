package com.subra.funding;


import java.io.FileWriter;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;


import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import com.drfbets.funding.model.AddressPullRequestParam;
import com.drfbets.funding.model.AddressPullResponseResult;
import com.drfbets.funding.model.CustomerPullResponseResult;
import com.drfbets.funding.model.FundingMethodPullRequestParam;
import com.drfbets.funding.model.FundingMethodPullResponseResult;
import com.drfbets.funding.model.GeneralOperationRequestParam;
import com.drfbets.funding.model.GeneralOperationResponseResult;
import com.drfbets.funding.model.GeneralOperationResponseResult.Balance;
import com.drfbets.funding.model.LimitAvailableRequestParam;
import com.drfbets.funding.model.LimitAvailableResponseResult;
import com.drfbets.funding.model.LimitAvailableResponseResult.Limit;
import com.drfbets.funding.model.LimitAvailableResponseResult.Limit.Next;
import com.drfbets.funding.model.LimitAvailableResponseResult.Limit.Remaining;
import com.drfbets.funding.model.TransactionSendRequestParam;
import com.drfbets.funding.model.TransactionSendRequestParam.Amount;
import com.drfbets.funding.model.TransactionSendRequestParam.Amount.Fee;
import com.drfbets.funding.model.TransactionSendRequestParam.Details;
import com.subra.funding.model.CustomerPullRequestModel;
import com.subra.funding.model.FmxrequestGen;
import com.subra.funding.model.Fmxrequestempty;
import com.subra.funding.model.Fmxrequestempty.General;
import com.subra.funding.model.Fmxresponse;
import com.subra.funding.model.ParamCustomerPullRequest;
import com.subra.funding.model.RequestEmpty;
import com.subra.funding.model.ParamCustomerPullRequest.Field;
import com.subra.funding.model.RequestGen;
import com.subra.funding.model.Response;
import com.subra.funding.model.Response.Error;

public class ResponseRequestMainDriver {

	static Logger log = LoggerFactory.getLogger(ResponseRequestMainDriver.class);
	public static void main(String [] args) throws XmlMappingException, IOException{
		
		//preapareAddressPullString();
		//prepareAddressPullResponseString();
		
		//prepareCustomerPullResponseString();
		//prepareCustomerPullRequestString();

		//prepareFundingMethodPullRequestString();
		//prepareFundingMethodPullResponseString();
		
		//prepareLimitRequestParamString(); // a good one
		//prepareLimitResponseString();
		
		//prepGeneralOperationRequestParamString(); //must use this for compact design
		//prepareGeneralOperationResponseString();
		prepareTransactionRequestParamString();
	//	checkRestcall();
		
		  System.out.println("XML Created Sucessfully");		
		
	}
	
	@SuppressWarnings("deprecation")
	public static void checkRestcall() {
		RestTemplate restTemplate = new RestTemplate();
		//1591999
		String responseType;
		String url = "https://qa.xpressbetonline.com/fmxapi/fmx_api.aspx";
		//String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fmxrequest><general><timestamp>2016-10-06 04:48:14</timestamp> <auth><username>xxxxxx</username><password>xxxxxx</password></auth></general><request><category>customer</category><function>pull</function><param> <account>1591999</account><field><name>account,firstname</name></field></param></request></fmxrequest>";
			
		/* works-->	*/ String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fmxrequest><general><timestamp>2016-10-06 04:48:14</timestamp> <auth><username>xxxxxx</username><password>xxxxxx</password></auth></general><request><category>customer</category><function>pull</function><param> <account>1486588</account><field><name>account,firstname</name></field></param></request></fmxrequest>";
		
		/* This code perfectly works as String 
		 
		ResponseEntity<String> ret = null;
	
		ret = restTemplate.postForEntity(url, data, String.class); //(url, responseType);
		System.out.println("ret=" + ret);
		
		HttpHeaders  h = ret.getHeaders();
		HttpStatus code = ret.getStatusCode();
		String body = ret.getBody(); 
		System.out.println("------------------------------\nheader=" + h );
		System.out.println("statusCode=" + code);
		System.out.println( "body=" + body);
	   */
		
	/*response back ret=<200 OK, <?xml version="1.0" encoding="UTF-8"?> <fmxresponse><response><error><code>0</code><mesg></mesg></error><category>customer</category><function>pull</function><result><field><name>fedexcharge</name><value>17.00</value></field><field><name>vscharge</name><value>5.00</value></field><field><name>mccharge</name><value>5.00</value></field><field><name>account</name><value>1486588</value></field><field><name>firstname</name><value>fifthTester</value></field></result></response></fmxresponse>,{Date=[Fri, 14 Oct 2016 16:44:52 GMT], Server=[Apache], Vary=[Accept-Encoding], Content-Length=[491], Keep-Alive=[timeout=15], Connection=[Keep-Alive], Content-Type=[text/html; charset=ISO-8859-1], Set-Cookie=[NSC_WTr_ycp.rb.T=ffffffff0906003b45525d5f4f58455e445a4a423660;Version=1;path=/;secure;httponly]}> */
		
		ResponseEntity<Fmxresponse<CustomerPullResponseResult>> ret = null;
		
		Fmxresponse<CustomerPullResponseResult> arb = new Fmxresponse<CustomerPullResponseResult>();
		Class myclass = arb.getClass();
		
		
		//exception	ret = restTemplate.postForEntity(url, data, myclass); //(url, responseType);
		// errorr ret = restTemplate.postForEntity(url, data, new Fmxresponse<CustomerPullResponseResult>);  // new Fmxresponse<CustomerPullResponseResult>()
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(data);
		
		//ret = restTemplate.exchange(url, HttpMethod.POST, 
		
	// exception 	ret = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference <Fmxresponse<CustomerPullResponseResult>>() {});
		ResponseEntity<String> ret2 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

		
		//System.out.println("2. parameterized ret=" + ret2);
		
		
		if(ret2.getStatusCode() == HttpStatus.OK){
			String body = ret2.getBody();
			body = body.trim(); //is needed to work
			System.out.println("3. body=" + body);
			StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(body);
			StreamSource streamSource = new StreamSource(stringBufferInputStream);
			// problem with static jaxb Fmxresponse<CustomerPullResponseResult> objCustomerPull = (Fmxresponse<CustomerPullResponseResult> )	marshaller.unmarshal(streamSource);
			//individualized jaxb below

			Jaxb2Marshaller marshaller2 = new Jaxb2Marshaller();
			marshaller2.setClassesToBeBound( Fmxresponse.class, Response.class, CustomerPullResponseResult.class);
			Fmxresponse<CustomerPullResponseResult> objCustomerPull = (Fmxresponse<CustomerPullResponseResult> )	marshaller2.unmarshal(streamSource);
			log.info("objCustomerPull=" + objCustomerPull);
			/* mapped nicely as below

3. body=<?xml version="1.0" encoding="UTF-8"?>
<fmxresponse><response><error><code>0</code><mesg></mesg></error>
	<category>customer</category><function>pull</function>

<result><field><name>fedexcharge</name><value>17.00</value></field>
	<field><name>vscharge</name><value>5.00</value></field><field><name>mccharge</name><value>5.00</value></field>
<field><name>account</name><value>1486588</value></field><field><name>firstname</name><value>fifthTester</value></field></result></response></fmxresponse>


INFO  ResponseRequestMainDriver - 
objCustomerPull=Fmxresponse [response=[Response [error=Error [code=0, mesg=], 
	category=customer, function=pull, 

result=Result [field=[Field [name=fedexcharge, value=17.00], 
	Field [name=vscharge, value=5.00], Field [name=mccharge, value=5.00], 
	Field [name=account, value=23232], Field [name=firstname, value=dfdsf]]]]]]
XML Created Sucessfully
			 
			 */
		}else {
			
			System.out.println("Error occurs. . .");
		}
		
		//ret = restTemplate.postForEntity(url, data, new Fmxresponse<CustomerPullResponseResult>().class); //(url, responseType);
		//ret = restTemplate.postForEntity(url, data, arb.getClass()); //(url, responseType);
		
	}
	
	public static void prepareTransactionRequestParamString() throws IOException{
		BigInteger account = new BigInteger("56855"); 
		String type = "type_withdrawl"; //"type_deposit"; 
		String id= "id_ach";
		id = "credit"; //"id_cc";
		String subid= null; //"subid_none";
		subid ="general"; //3rd test case
		Integer fundingmethodid = null; //6545; // can be missing
		//prepare Details
		List<TransactionSendRequestParam.Details.Field> fields = new ArrayList<TransactionSendRequestParam.Details.Field>();
		TransactionSendRequestParam.Details.Field field1 = new TransactionSendRequestParam.Details.Field("name_abanumber", "1129000");
		TransactionSendRequestParam.Details.Field field2 = new TransactionSendRequestParam.Details.Field("name_accountnumber", "6789000");
		TransactionSendRequestParam.Details.Field field3 = new TransactionSendRequestParam.Details.Field("name_checknumber", "1234");
		TransactionSendRequestParam.Details.Field field4 = new TransactionSendRequestParam.Details.Field("name_cardType", "visa");
		TransactionSendRequestParam.Details.Field field5 = new TransactionSendRequestParam.Details.Field("note", "Test note");
		//comment out for 3rd test case fields.add(field1); fields.add(field2);  fields.add(field3); fields.add(field4);
		fields.add(field5);
		Details details = new Details(fields);
		
		//prepare Amount
		List<Fee> fees = new ArrayList<Fee>();
		Fee fee1 = new Fee("type_feedex", 5.25);
		Fee fee2 = new Fee("type_feedex", 0.0);
		//fees.add(fee1);
		fees.add(fee2);
		 //fees = null; //1st,  test case //comment out for 2nd 3rd, 7th and 8th test case
		Amount amount = new Amount(10.00, fees);
		//now create param
		TransactionSendRequestParam transactionSendRequestParam = new TransactionSendRequestParam(account, type, id, subid, fundingmethodid, details, amount);
		RequestGen<TransactionSendRequestParam> request = new RequestGen<TransactionSendRequestParam>("my_category", "my_function", transactionSendRequestParam);
		List<RequestGen<TransactionSendRequestParam>> requests = new ArrayList<RequestGen<TransactionSendRequestParam>>();
		requests.add(request);
		FmxrequestGen<TransactionSendRequestParam> objTransactionSendRequest = new FmxrequestGen<TransactionSendRequestParam>(requests);
		System.out.println("whatobj=" + objTransactionSendRequest);
		
		
		
		//more flexible
		StringWriter strWriterForXml = new StringWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		strWriterForXml.append("\n"); //for debugging only
		StreamResult xmlStringWriter = new StreamResult(strWriterForXml);
		marshaller.marshal(objTransactionSendRequest, xmlStringWriter); //statically created
		String transactionRequestStrBuffer = xmlStringWriter.getWriter().toString();
		log.info("limitAvaliableStrBuffer=" + transactionRequestStrBuffer);
		FileWriter xmlFileWriter = new FileWriter("limitAvailableResponsestring.xml");
		xmlFileWriter.write(transactionRequestStrBuffer); xmlFileWriter.flush();
		 
		
		
	}
	
	
	public static void prepareLimitRequestParamString() throws XmlMappingException, IOException{
		//use this of jaxb for flexible manipulation
		
		//prepare param to make request
		BigInteger bigint = BigInteger.valueOf(9523); 
		//LimitAvailableRequestParam limitAvailableRequestParam = new LimitAvailableRequestParam(bigint , "type_ach");
		LimitAvailableRequestParam limitAvailableRequestParam = new LimitAvailableRequestParam(bigint , null);
		//make request
		RequestEmpty<LimitAvailableRequestParam> request = new RequestEmpty<LimitAvailableRequestParam>("categoryName", "function_name", limitAvailableRequestParam);
		//make fmxrequest
		List<RequestEmpty<LimitAvailableRequestParam>> requests = new ArrayList<RequestEmpty<LimitAvailableRequestParam>>();
		requests.add(request);
		Fmxrequestempty<LimitAvailableRequestParam> objLimitAvalableRequest = new Fmxrequestempty<LimitAvailableRequestParam>();
		objLimitAvalableRequest.setRequest(requests);
		//set gneral
		//String timestamp = new Timestamp(new Date().getTime()).toString();	
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());	

		objLimitAvalableRequest.setGeneral(new General(timestamp, new General.Auth("sdass", "qqq123")));
		System.out.println("whatObj= " + objLimitAvalableRequest.toString());

		// execute for jaxb done
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		prop.put(javax.xml.bind.Marshaller.JAXB_FRAGMENT, true); //for removing 1st line declaration
 		// both or none -- does not work -- prop.put("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");  //now add customized header
 		 
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxrequestempty.class, RequestEmpty.class, LimitAvailableRequestParam.class);		
		// works marshaller.marshal(objLimitAvalableRequest, new StreamResult(new FileWriter("limitAvailable.xml")));
		//more flexible below
		//now add the declaration
		 StringWriter strWriterForXml = new StringWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		 strWriterForXml.append("\n"); //for debugging only
		//StreamResult xmlStringWriter = new StreamResult(new StringWriter()); 
		 StreamResult xmlStringWriter = new StreamResult(strWriterForXml);
		
		marshaller.marshal(objLimitAvalableRequest, xmlStringWriter); 
		String limitAvailableBuffer = xmlStringWriter.getWriter().toString();
		System.out.println(limitAvailableBuffer);
		FileWriter xmlFileWriter = new FileWriter("limitAvailable.xml"); xmlFileWriter.write(limitAvailableBuffer); xmlFileWriter.flush(); 
		xmlFileWriter.close();
	}

	public static Jaxb2Marshaller marshaller;
	static {
		// execute for jaxb done
		marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		prop.put(javax.xml.bind.Marshaller.JAXB_FRAGMENT, true); //for removing 1st line declaration
 		// both or none -- does not work -- prop.put("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");  //now add customized header
		marshaller.setMarshallerProperties(prop);
		//bind all classes
		marshaller.setClassesToBeBound(FmxrequestGen.class, RequestGen.class, Fmxresponse.class, Response.class, 
				GeneralOperationRequestParam.class, 
				LimitAvailableRequestParam.class,
				AddressPullRequestParam.class,
				FundingMethodPullRequestParam.class,
				GeneralOperationRequestParam.class,
				LimitAvailableRequestParam.class,
				//CustomerPullRequestModel.class,
				ParamCustomerPullRequest.class,
				AddressPullResponseResult.class,
				CustomerPullResponseResult.class,
				FundingMethodPullResponseResult.class,
				LimitAvailableResponseResult.class,
				GeneralOperationResponseResult.class,
				TransactionSendRequestParam.class
				
				);

	}
	
	public static void prepGeneralOperationRequestParamString() throws IOException {
		
	GeneralOperationRequestParam generalOperationReqParam =	new GeneralOperationRequestParam(new BigInteger("32561"), "type_current");
	RequestGen<GeneralOperationRequestParam> request = new RequestGen<GeneralOperationRequestParam>("category_name", "function_name", generalOperationReqParam);
	List<RequestGen<GeneralOperationRequestParam>> requests = 	new ArrayList<RequestGen<GeneralOperationRequestParam>>();
	requests.add(request);
	FmxrequestGen<GeneralOperationRequestParam> objGeneralOperationReqString = new FmxrequestGen<GeneralOperationRequestParam>(requests);
	System.out.println("whatObj= " + objGeneralOperationReqString.toString());

	// marshaller.setClassesToBeBound(FmxrequestGen.class, RequestGen.class, GeneralOperationRequestParam.class);

	//more flexible below, now add the declaration
	 StringWriter strWriterForXml = new StringWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
	 strWriterForXml.append("\n"); //for debugging only
	 StreamResult xmlStringWriter = new StreamResult(strWriterForXml);
	
	marshaller.marshal(objGeneralOperationReqString, xmlStringWriter); 
	String generaloperationStrBuffer = xmlStringWriter.getWriter().toString();
	System.out.println(generaloperationStrBuffer);
	FileWriter xmlFileWriter = new FileWriter("generalOperationReq.xml"); xmlFileWriter.write(generaloperationStrBuffer); xmlFileWriter.flush(); 
	xmlFileWriter.close();

	
	}

	public static void prepareFundingMethodPullRequestString() throws XmlMappingException, IOException{
		//prepare request[]
		//1. field
		List<FundingMethodPullRequestParam.Field> field = new  ArrayList<FundingMethodPullRequestParam.Field>();
		
		FundingMethodPullRequestParam.Field field1 = new FundingMethodPullRequestParam.Field("field1", "value1");
		FundingMethodPullRequestParam.Field field2 = new FundingMethodPullRequestParam.Field("field2", "value2");
		field.add(field1); field.add(field2);
		field = null;
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
		//String timestamp = new Timestamp(new Date().getTime()).toString();	
		String timestamp = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
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
		AddressPullResponseResult.Address.Field field1a = new AddressPullResponseResult.Address.Field("AddrName11", "AddrValue11");
		AddressPullResponseResult.Address.Field field1b = new AddressPullResponseResult.Address.Field("AddrName22", "AddrValue22");
		//make fieldList
		List<AddressPullResponseResult.Address.Field> addrList1 = new ArrayList<AddressPullResponseResult.Address.Field>();
		addrList1.add(field1); addrList1.add(field1a); addrList1.add(field1b);
		//2. make address 1 of 2
		AddressPullResponseResult.Address address1 = new AddressPullResponseResult.Address("type_residence", addrList1);

		List<AddressPullResponseResult.Address.Field> addrList2 = new ArrayList<AddressPullResponseResult.Address.Field>();
		//2b. make address 2 of 2
		AddressPullResponseResult.Address.Field field2 = new AddressPullResponseResult.Address.Field("AddrName2", "AddrValue2");
		AddressPullResponseResult.Address.Field field2b = new AddressPullResponseResult.Address.Field("AddrName2bb", "AddrValue2bb");
		addrList2.add(field2); addrList2.add(field2b);
		AddressPullResponseResult.Address address2 = new AddressPullResponseResult.Address("type_mailing", addrList2);
		
		//3. fillout result [addresses list 1st]
		List<AddressPullResponseResult.Address> addresses =  new ArrayList<AddressPullResponseResult.Address>();
		addresses.add(address1); 
		addresses.add(address2);
		AddressPullResponseResult addressPullResponseResult = new AddressPullResponseResult(addresses);
		//3. prepare response [1st populate error]
		//Response.Error error = new Response.Error(BigInteger.valueOf(3), "errorMsg");		
		Response.Error error = new Response.Error(0, "");
		
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
	
	public static void prepareFundingMethodPullResponseString() throws XmlMappingException, IOException{
		//1. fill out field list
		FundingMethodPullResponseResult.Fundingmethod.Field field1 = new FundingMethodPullResponseResult.Fundingmethod.Field("field1", "value1");
		FundingMethodPullResponseResult.Fundingmethod.Field field2 = new FundingMethodPullResponseResult.Fundingmethod.Field("field2", "value2");
		List<FundingMethodPullResponseResult.Fundingmethod.Field> field = new ArrayList<FundingMethodPullResponseResult.Fundingmethod.Field>();
		field.add(field1); field.add(field2);
		//2. create fundingmethod object
		BigInteger bigint = BigInteger.valueOf(200);
		FundingMethodPullResponseResult.Fundingmethod fundingMethod1 = new FundingMethodPullResponseResult.Fundingmethod(bigint, field);
		FundingMethodPullResponseResult.Fundingmethod fundingMethod2 = new FundingMethodPullResponseResult.Fundingmethod(new BigInteger("9923"), field);
		
		List<FundingMethodPullResponseResult.Fundingmethod> fundingMehtodList = new ArrayList<FundingMethodPullResponseResult.Fundingmethod>();
		fundingMehtodList.add(fundingMethod1);
		fundingMehtodList.add(fundingMethod2);
		//fundingMehtodList = null; //funding method edit simulate string
		//3. create result object
		FundingMethodPullResponseResult result = new FundingMethodPullResponseResult(fundingMehtodList);
		//4. prepare response
		//Response.Error error = new Response.Error(BigInteger.valueOf(0), "");
		Response.Error error = new Response.Error(5, "Invalid");
		Response<FundingMethodPullResponseResult> response1 = new  Response<FundingMethodPullResponseResult>(error, "myCategory", "Myfunction", result);
		//5. last step: prepae formresponse
		List<Response<FundingMethodPullResponseResult>> responseList = new ArrayList<Response<FundingMethodPullResponseResult>>();
		responseList.add(response1);
		Fmxresponse<FundingMethodPullResponseResult> objfmxresponse = new Fmxresponse<FundingMethodPullResponseResult>(responseList); 
		System.out.println("whatObj=" + objfmxresponse.toString());
		//System.exit(1);
		// execute for jaxb 
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxresponse.class, Response.class, 
				FundingMethodPullResponseResult.class);		
		marshaller.marshal(objfmxresponse, new StreamResult(new FileWriter("fundingMethodPullResponstring.xml")));
		
	}
	
	public static void prepareGeneralOperationResponseString() throws IOException{
	 //prepre result
	 Balance balance1 = new Balance("current", 1314.20);
	 Balance balance2 = new Balance("availble", 900.00);
	 List<Balance> balanceList = new ArrayList<Balance>();
	 balanceList.add(balance1);
	 balanceList.add(balance2); //commented out for test case1.
	 GeneralOperationResponseResult generalOperationResponseResult = new GeneralOperationResponseResult(balanceList);
	//prepare error, response and Fmxresponse obj
	 Response.Error error = new Response.Error(0,"");
	 Response<GeneralOperationResponseResult> response1 = new Response<GeneralOperationResponseResult>(error, "my_category", "my_function", generalOperationResponseResult);
	 List<Response<GeneralOperationResponseResult>> responseList = new ArrayList<Response<GeneralOperationResponseResult>>();
	 responseList.add(response1);
	 Fmxresponse<GeneralOperationResponseResult> objfmxresponse = new Fmxresponse<GeneralOperationResponseResult>(responseList);
	 System.out.println("whatObj=" + objfmxresponse.toString());
	 
	StringWriter strWriterForXml = new StringWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
	strWriterForXml.append("\n"); //for debugging only
	StreamResult xmlStringWriter = new StreamResult(strWriterForXml);
	marshaller.marshal(objfmxresponse, xmlStringWriter); //statically created
	String generalOperationStrBuffer = xmlStringWriter.getWriter().toString();
	log.info("generalOperationStrBuffer=" + generalOperationStrBuffer);
	FileWriter xmlFileWriter = new FileWriter("generalOperationResponsestring.xml");
	xmlFileWriter.write(generalOperationStrBuffer); xmlFileWriter.flush();

	 
	}
	
	public static void prepareLimitResponseString() throws XmlMappingException, IOException{
		//prepare two limits
		//limit1
		Remaining remaining1 = new Remaining(456.78, 345);
		String date = "10-12-2016 :09:35:40";
		Next next1 = new Next(date,99.56);
		String timeframe1 = "daily"; int fundingmethodid1 = 3456; String type1 = "ach"; short authorized1 = 1;
		Limit limit1 = new Limit(fundingmethodid1, timeframe1, type1, authorized1, remaining1, next1);
		//limit2
		String timeframe2 = "none"; int fundingmethodid2 = 33337; String type2 = "cc"; short authorized2 = 0;
		Remaining remaining2 = new Remaining(777.78, 177);
		Next next2 =null;
		Limit limit2 = new Limit(fundingmethodid2, timeframe2, type2, authorized2, remaining2, next2);
		List<Limit> limitList = new ArrayList<Limit>();
		limitList.add(limit1); limitList.add(limit2);
		//prepare error, response and Fmxresponse obj
		LimitAvailableResponseResult limitAvailableResponseResult = new LimitAvailableResponseResult(limitList);
		Response.Error error = new Response.Error(0, "");
		Response<LimitAvailableResponseResult> response1 = new Response<LimitAvailableResponseResult>(error, "my_category","my_function", limitAvailableResponseResult);
		List<Response<LimitAvailableResponseResult>> responseList = new ArrayList<Response<LimitAvailableResponseResult>>();
		responseList.add(response1);
		Fmxresponse<LimitAvailableResponseResult> objfmxresponse = new Fmxresponse<LimitAvailableResponseResult>(responseList);
		System.out.println("whatObj=" + objfmxresponse.toString());
		
		/*// execute for jaxb 
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true); //for debugging
		marshaller.setMarshallerProperties(prop);
		marshaller.setClassesToBeBound(Fmxresponse.class, Response.class, 
				LimitAvailableResponseResult.class);		
		marshaller.marshal(objfmxresponse, new StreamResult(new FileWriter("limitAvailableResponsestring.xml")));
		*/
		
		//more flexible
		StringWriter strWriterForXml = new StringWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		strWriterForXml.append("\n"); //for debugging only
		StreamResult xmlStringWriter = new StreamResult(strWriterForXml);
		marshaller.marshal(objfmxresponse, xmlStringWriter); //statically created
		String limitAvaliableStrBuffer = xmlStringWriter.getWriter().toString();
		log.info("limitAvaliableStrBuffer=" + limitAvaliableStrBuffer);
		FileWriter xmlFileWriter = new FileWriter("limitAvailableResponsestring.xml");
		xmlFileWriter.write(limitAvaliableStrBuffer); xmlFileWriter.flush();
		 
		
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
		Response.Error error = new Response.Error(0, "");
		
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
