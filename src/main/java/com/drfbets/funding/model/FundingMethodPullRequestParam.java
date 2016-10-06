package com.drfbets.funding.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="param")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {  "account",     "type",      "fundingmethodid",   "field"      })
//in Funding Method delete Request fundingmehodid comes before account. Need to test.
public class FundingMethodPullRequestParam {

    @XmlElement(required = true)
    protected BigInteger account;
    protected String type;
    protected BigInteger fundingmethodid;
    protected List<FundingMethodPullRequestParam.Field> field; 

    public FundingMethodPullRequestParam(){}
    
    public FundingMethodPullRequestParam(BigInteger account, String type,
			BigInteger fundingmethodid, List<Field> field) {
		super();
		this.account = account;
		this.type = type;
		this.fundingmethodid = fundingmethodid;
		this.field = field;
	}

	@Override
	public String toString() {
		return "FundingMethodPullRequestParam [account=" + account + ", type="
				+ type + ", fundingmethodid=" + fundingmethodid + ", field="
				+ field + "]";
	}

	public BigInteger getAccount() {
        return account;
    }

    public void setAccount(BigInteger value) {
        this.account = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public BigInteger getFundingmethodid() {
        return fundingmethodid;
    }

    public void setFundingmethodid(BigInteger value) {
        this.fundingmethodid = value;
    }

    public List<FundingMethodPullRequestParam.Field> getField() {
        if (field == null) {
            field = new ArrayList<FundingMethodPullRequestParam.Field>();
        }
        return this.field;
    }

    public void setField(List<FundingMethodPullRequestParam.Field> field) {
		this.field = field;
	}



	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {        "name",  "value"     })
    public static class Field {

		@XmlElement(required = true)
        protected String name;
		
        @XmlElement(required = true)
        protected String value;

    	public Field(){}
    	
        public Field(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }
        
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Field [name=" + name + ", value=" + value + "]";
		}
        
        

    }

}

	