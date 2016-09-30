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
@XmlType(name = "", propOrder = {     "address" })
public class AddressPullResponseResult {

	protected List<AddressPullResponseResult.Address> address;

	public AddressPullResponseResult(){}
	
    public AddressPullResponseResult(List<Address> address) {
		super();
		this.address = address;
	}

	public List<AddressPullResponseResult.Address> getAddress() {
        if (address == null) {
            address = new ArrayList<AddressPullResponseResult.Address>();
        }
        return this.address;
    }

    public void setAddress(List<AddressPullResponseResult.Address> address) {
		this.address = address;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {     "type",        "field"     })
    public static class Address {

        @XmlElement(required = true)
        protected String type;
        @XmlElement(required = true)
        protected AddressPullResponseResult.Address.Field field;
        
        public Address(){}
        
        public Address(String type, Field field) {
			super();
			this.type = type;
			this.field = field;
		}

		@Override
		public String toString() {
			return "Address [type=" + type + ", field=" + field + "]";
		}

		public String getType() {
            return type;
        }

        public void setType(String value) {
            this.type = value;
        }

        public AddressPullResponseResult.Address.Field getField() {
            return field;
        }

        public void setField(AddressPullResponseResult.Address.Field value) {
            this.field = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {         "name",          "value"     })
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

			@Override
			public String toString() {
				return "Field [name=" + name + ", value=" + value + "]";
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

        }

    }

	@Override
	public String toString() {
		return "AddressPullResponseResult [address=" + address + "]";
	}

}
