package com.drfbets.funding.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {  "fundingmethod" })
public class FundingMethodPullResponseResult {

        protected List<FundingMethodPullResponseResult.Fundingmethod> fundingmethod;
 
        public FundingMethodPullResponseResult() {	}
        
		public FundingMethodPullResponseResult(List<Fundingmethod> fundingmethod) {
			super();
			this.fundingmethod = fundingmethod;
		}


		public List<FundingMethodPullResponseResult.Fundingmethod> getFundingmethod() {
            if (fundingmethod == null) {
                fundingmethod = new ArrayList<FundingMethodPullResponseResult.Fundingmethod>();
            }
            return this.fundingmethod;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {      "id",       "field"      })
        public static class Fundingmethod {

            @XmlElement(required = true)
            protected BigInteger id;
            protected List<FundingMethodPullResponseResult.Fundingmethod.Field> field;

            public Fundingmethod() {}

			public Fundingmethod(BigInteger id, List<Field> field) {
				super();
				this.id = id;
				this.field = field;
			}

			
			public BigInteger getId() {
                return id;
            }

            public void setId(BigInteger value) {
                this.id = value;
            }

            public List<FundingMethodPullResponseResult.Fundingmethod.Field> getField() {
                if (field == null) {
                    field = new ArrayList<FundingMethodPullResponseResult.Fundingmethod.Field>();
                }
                return this.field;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {      "name",      "value"    })
            public static class Field {

                @XmlElement(required = true)
                protected String name;
                @XmlElement(required = true)
                protected String value;
                
                public Field() { }

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
				return "Fundingmethod [id=" + id + ", field=" + field + "]";
			}

        }

		@Override
		public String toString() {
			return "FundingMethodPullResponseResult [fundingmethod="
					+ fundingmethod + "]";
		}

    }