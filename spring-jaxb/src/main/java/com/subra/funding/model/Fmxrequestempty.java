
package com.subra.funding.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {    "general",    "request"})
@XmlRootElement(name = "fmxrequest")
public class Fmxrequestempty<T> {
	
	

    @XmlElement(required = true)
    protected Fmxrequestempty.General general;
    @XmlElement(required = true)
    protected List<RequestEmpty<T>> request;
    
    public Fmxrequestempty() {}
    
    
	public Fmxrequestempty(General general, List<RequestEmpty<T>> request) {
		super();
		this.general = general;
		this.request = request;
	}


	public Fmxrequestempty.General getGeneral() {
        return general;
    }

    public void setGeneral(Fmxrequestempty.General value) {
        this.general = value;
    }

    public List<RequestEmpty<T>> getRequest() {
        if (request == null) {
            request = new ArrayList<RequestEmpty<T>>();
        }
        return this.request;
    }
    
    public void setRequest(List<RequestEmpty<T>> request) {
		this.request = request;
	}


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {        "timestamp",        "auth"    })
    public static class General {

        @XmlElement(required = true)
        protected String timestamp;
        @XmlElement(required = true)
        protected Fmxrequestempty.General.Auth auth;
        
        
        public General(){   }
        
        public General(String timestamp, Auth auth) {
			super();
			this.timestamp = timestamp;
			this.auth = auth;
		}

		public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String value) {
            this.timestamp = value;
        }

        public Fmxrequestempty.General.Auth getAuth() {
            return auth;
        }

        public void setAuth(Fmxrequestempty.General.Auth value) {
            this.auth = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {            "username",            "password"        })
        public static class Auth {

        	
            @XmlElement(required = true)
            protected String username;
            @XmlElement(required = true)
            protected String password;

            public Auth(){}
            
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
		return "Fmxrequestempty [general=" + general + ", request=" + request
				+ "]";
	}

}//outermost class
