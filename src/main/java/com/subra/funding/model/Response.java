package com.subra.funding.model;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {    "error",    "category",    "function",    "result" })
public class Response<T> {
	
        @XmlElement(required = true)
        protected Response.Error error;
        @XmlElement(required = true)
        protected String category;
        @XmlElement(required = true)
        protected String function;
        @XmlAnyElement(lax=true) 
        protected T result;

        public Response() {
			super();
		}

		public Response(Error error, String category, String function, T result) {
			super();
			this.error = error;
			this.category = category;
			this.function = function;
			this.result = result;
		}
		

		public Response.Error getError() {
            return error;
        }

        public void setError(Response.Error value) {
            this.error = value;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String value) {
            this.category = value;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String value) {
            this.function = value;
        }

        public T getResult() {
            return result;
        }

        public void setResult(T value) {
            this.result = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {  "code",  "mesg"     })
        public static class Error {

            @XmlElement(required = true)
            protected BigInteger code;
            @XmlElement(required = true)
            protected String mesg;

            public Error(){}
            
            public Error(BigInteger code, String mesg) {
				super();
				this.code = code;
				this.mesg = mesg;
			}

			public BigInteger getCode() {
                return code;
            }

            public void setCode(BigInteger value) {
                this.code = value;
            }

            public String getMesg() {
                return mesg;
            }

            public void setMesg(String value) {
                this.mesg = value;
            }

			@Override
			public String toString() {
				return "Error [code=" + code + ", mesg=" + mesg + "]";
			}

        }

		@Override
		public String toString() {
			return "Response [error=" + error + ", category=" + category
					+ ", function=" + function + ", result=" + result + "]";
		}

}
