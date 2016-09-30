package com.drfbets.funding.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "param")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "account",
    "type",
    "field"
})
public class AddressPullRequestParam {

    @XmlElement(required = true)
    protected BigInteger account;
    protected String type;
    protected List<AddressPullRequestParam.Field> field;
    
    public AddressPullRequestParam(){}
    
    public AddressPullRequestParam(BigInteger account, String type, List<Field> field) {
		super();
		this.account = account;
		this.type = type;
		this.field = field;
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

    public List<AddressPullRequestParam.Field> getField() {
        if (field == null) {
            field = new ArrayList<AddressPullRequestParam.Field>();
        }
        return this.field;
    }

    public void setField(List<AddressPullRequestParam.Field> field) {
		this.field = field;
	}



	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name"
    })
    public static class Field {

        @XmlElement(required = true)
        protected String name;
        
        public Field(){}
        public Field(String name) {
        	super();
        	this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }
        
		@Override
		public String toString() {
			return "Field [name=" + name + "]";
		}

        
    }


	@Override
	public String toString() {
		return "AddressPullRequestParam [account=" + account + ", type=" + type
				+ ", field=" + field + "]";
	}
	

}
