
package com.subra.funding.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "account",
    "field"
})
@XmlRootElement(name = "param")
public class ParamCustomerPullRequest {

    @XmlElement(required = true)
    protected BigInteger account;
    protected List<ParamCustomerPullRequest.Field> field;

    public ParamCustomerPullRequest(){}

    public ParamCustomerPullRequest(BigInteger account, List<Field> field) {
		super();
		this.account = account;
		this.field = field;
	}

	public BigInteger getAccount() {
        return account;
    }


    public void setAccount(BigInteger value) {
        this.account = value;
    }


    public List<ParamCustomerPullRequest.Field> getField() {
        if (field == null) {
            field = new ArrayList<ParamCustomerPullRequest.Field>();
        }
        return this.field;
    }

    public void setField(List<ParamCustomerPullRequest.Field> field) {
		this.field = field;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name"
    })
    public static class Field {

		public Field(){}

        public Field(String name) {
			super();
			this.name = name;
		}


		@XmlElement(required = true)
        protected String name;


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
		return "ParamCustomerPullRequest [account=" + account + ", field="
				+ field + "]";
	}

}
