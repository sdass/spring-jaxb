//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 


package com.drfbets.funding.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

        @XmlRootElement(name = "result")
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {           "balance"    })
        public class GeneralOperationResponseResult {

            @XmlElement(required = true)
            protected List<Balance> balance;
            

            public GeneralOperationResponseResult(List<Balance> balance) {
				super();
				this.balance = balance;
			}


			public GeneralOperationResponseResult() {
				super();
			}
            

			public List<Balance> getBalance() {
                if (balance == null) {
                    balance = new ArrayList<Balance>();
                }
                return this.balance;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {   "type",    "amount"    })
            public static class Balance {

                @XmlElement(required = true)
                protected String type;
                @XmlElement(required = true)
                protected Double amount;

                
                public Balance() {
					super();
				}
                
				public Balance(String type, Double amount) {
					super();
					this.type = type;
					this.amount = amount;
				}


				public String getType() {
                    return type;
                }
         
                public void setType(String value) {
                    this.type = value;
                }
            
                public Double getAmount() {
                    return amount;
                }

                public void setAmount(Double value) {
                    this.amount = value;
                }

				@Override
				public String toString() {
					return "Balance [type=" + type + ", amount=" + amount + "]";
				}

            }

			@Override
			public String toString() {
				return "GeneralOperationResponseResult [balance=" + balance
						+ "]";
			}

        }