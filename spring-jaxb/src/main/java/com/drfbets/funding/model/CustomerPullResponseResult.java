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
@XmlType(name = "", propOrder = {   "field" })
public class CustomerPullResponseResult {

		protected List<CustomerPullResponseResult.Field> field;

        
        public CustomerPullResponseResult(){}
        
        
        public CustomerPullResponseResult(List<CustomerPullResponseResult.Field> field) {
			super();
			this.field = field;
		}

		public List<CustomerPullResponseResult.Field> getField() {
            if (field == null) {
            	field = new ArrayList<CustomerPullResponseResult.Field>();
            }
            return this.field;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "name",   "value"   })
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

		@Override
		public String toString() {
			return "Result [field=" + field + "]";
		}

}
			