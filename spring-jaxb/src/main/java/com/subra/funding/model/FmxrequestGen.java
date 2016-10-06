
package com.subra.funding.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {    "general",    "request"})
@XmlRootElement(name = "fmxrequest")
public class FmxrequestGen<T> {

    @XmlElement(required = true)
    protected FmxrequestGen.General general;
    @XmlElement(required = true)
    protected List<RequestGen<T>> request;
    
    public FmxrequestGen() {} //needed for jaxb
    
/*	
   public FmxrequestGen(General general, List<RequestGen<T>> request) {
		super();
		this.general = general;
		this.request = request;
	}
	*/

	public FmxrequestGen(List<RequestGen<T>> request) {
		super();
		this.general = new General();
		this.request = request;
	}	
	
	public FmxrequestGen.General getGeneral() {
        return general;
    }

    public void setGeneral(FmxrequestGen.General value) {
        this.general = value;
    }

    public List<RequestGen<T>> getRequest() {
        if (request == null) {
            request = new ArrayList<RequestGen<T>>();
        }
        return this.request;
    }
    
    public void setRequest(List<RequestGen<T>> request) {
		this.request = request;
	}

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {    "timestamp",   "auth"    })
    public static class General {

        @XmlElement(required = true)
        protected String timestamp;
        @XmlElement(required = true)
        protected static FmxrequestGen.General.Auth auth;
        
        static {
        	auth = new FmxrequestGen.General.Auth();
        }
        
        public General(){ 
        	super();
        	//provide all defaults
        	this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        }

        /*        
        public General(String timestamp, Auth auth) {
			super();
			this.timestamp = timestamp;
			this.auth = auth;
		}

        */
        
		public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String value) {
            this.timestamp = value;
        }

        public FmxrequestGen.General.Auth getAuth() {
            return auth;
        }

        public void setAuth(FmxrequestGen.General.Auth value) {
            this.auth = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {            "username",            "password"        })
        public static class Auth {
     	
            @XmlElement(required = true)
           // protected String username;
            protected static String username;
            @XmlElement(required = true)
            //protected String password;
            protected static String password;

            static { //encrypt by jasypt and read externally etc here
            	username = "sdass";
            	password = "qqq123";
            }
            
            public Auth(){ //give all default
            	// this(username, password); // this line not needed
            }
            
            public Auth(String username, String password) {
				super();
				this.username = username;
				this.password = password;
			}

			public String getUsername() {
                return username;
            }

            public void setUsername(String value) {
                this.username = value;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String value) {
                this.password = value;
            }

			@Override
			public String toString() {
				return "Auth [username=" + username + ", password=" + password
						+ "]";
			}

        }

		@Override
		public String toString() {
			return "General [timestamp=" + timestamp + ", auth=" + auth + "]";
		}

    }

	@Override
	public String toString() {
		return "FmxrequestGen [general=" + general + ", request=" + request
				+ "]";
	}

	
}//outermost class
